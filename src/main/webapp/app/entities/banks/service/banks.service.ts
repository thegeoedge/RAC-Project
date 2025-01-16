import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBanks, NewBanks } from '../banks.model';

export type PartialUpdateBanks = Partial<IBanks> & Pick<IBanks, 'id'>;

type RestOf<T extends IBanks | NewBanks> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestBanks = RestOf<IBanks>;

export type NewRestBanks = RestOf<NewBanks>;

export type PartialUpdateRestBanks = RestOf<PartialUpdateBanks>;

export type EntityResponseType = HttpResponse<IBanks>;
export type EntityArrayResponseType = HttpResponse<IBanks[]>;

@Injectable({ providedIn: 'root' })
export class BanksService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/banks');

  create(banks: NewBanks): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(banks);
    return this.http.post<RestBanks>(this.resourceUrl, copy, { observe: 'response' }).pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(banks: IBanks): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(banks);
    return this.http
      .put<RestBanks>(`${this.resourceUrl}/${this.getBanksIdentifier(banks)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(banks: PartialUpdateBanks): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(banks);
    return this.http
      .patch<RestBanks>(`${this.resourceUrl}/${this.getBanksIdentifier(banks)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestBanks>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestBanks[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getBanksIdentifier(banks: Pick<IBanks, 'id'>): number {
    return banks.id;
  }

  compareBanks(o1: Pick<IBanks, 'id'> | null, o2: Pick<IBanks, 'id'> | null): boolean {
    return o1 && o2 ? this.getBanksIdentifier(o1) === this.getBanksIdentifier(o2) : o1 === o2;
  }

  addBanksToCollectionIfMissing<Type extends Pick<IBanks, 'id'>>(
    banksCollection: Type[],
    ...banksToCheck: (Type | null | undefined)[]
  ): Type[] {
    const banks: Type[] = banksToCheck.filter(isPresent);
    if (banks.length > 0) {
      const banksCollectionIdentifiers = banksCollection.map(banksItem => this.getBanksIdentifier(banksItem));
      const banksToAdd = banks.filter(banksItem => {
        const banksIdentifier = this.getBanksIdentifier(banksItem);
        if (banksCollectionIdentifiers.includes(banksIdentifier)) {
          return false;
        }
        banksCollectionIdentifiers.push(banksIdentifier);
        return true;
      });
      return [...banksToAdd, ...banksCollection];
    }
    return banksCollection;
  }

  protected convertDateFromClient<T extends IBanks | NewBanks | PartialUpdateBanks>(banks: T): RestOf<T> {
    return {
      ...banks,
      lmd: banks.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restBanks: RestBanks): IBanks {
    return {
      ...restBanks,
      lmd: restBanks.lmd ? dayjs(restBanks.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestBanks>): HttpResponse<IBanks> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestBanks[]>): HttpResponse<IBanks[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
