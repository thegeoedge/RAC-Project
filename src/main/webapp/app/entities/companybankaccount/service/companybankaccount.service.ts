import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICompanybankaccount, NewCompanybankaccount } from '../companybankaccount.model';

export type PartialUpdateCompanybankaccount = Partial<ICompanybankaccount> & Pick<ICompanybankaccount, 'id'>;

type RestOf<T extends ICompanybankaccount | NewCompanybankaccount> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestCompanybankaccount = RestOf<ICompanybankaccount>;

export type NewRestCompanybankaccount = RestOf<NewCompanybankaccount>;

export type PartialUpdateRestCompanybankaccount = RestOf<PartialUpdateCompanybankaccount>;

export type EntityResponseType = HttpResponse<ICompanybankaccount>;
export type EntityArrayResponseType = HttpResponse<ICompanybankaccount[]>;

@Injectable({ providedIn: 'root' })
export class CompanybankaccountService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/companybankaccounts');

  create(companybankaccount: NewCompanybankaccount): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(companybankaccount);
    return this.http
      .post<RestCompanybankaccount>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(companybankaccount: ICompanybankaccount): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(companybankaccount);
    return this.http
      .put<RestCompanybankaccount>(`${this.resourceUrl}/${this.getCompanybankaccountIdentifier(companybankaccount)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(companybankaccount: PartialUpdateCompanybankaccount): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(companybankaccount);
    return this.http
      .patch<RestCompanybankaccount>(`${this.resourceUrl}/${this.getCompanybankaccountIdentifier(companybankaccount)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestCompanybankaccount>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestCompanybankaccount[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCompanybankaccountIdentifier(companybankaccount: Pick<ICompanybankaccount, 'id'>): number {
    return companybankaccount.id;
  }

  compareCompanybankaccount(o1: Pick<ICompanybankaccount, 'id'> | null, o2: Pick<ICompanybankaccount, 'id'> | null): boolean {
    return o1 && o2 ? this.getCompanybankaccountIdentifier(o1) === this.getCompanybankaccountIdentifier(o2) : o1 === o2;
  }

  addCompanybankaccountToCollectionIfMissing<Type extends Pick<ICompanybankaccount, 'id'>>(
    companybankaccountCollection: Type[],
    ...companybankaccountsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const companybankaccounts: Type[] = companybankaccountsToCheck.filter(isPresent);
    if (companybankaccounts.length > 0) {
      const companybankaccountCollectionIdentifiers = companybankaccountCollection.map(companybankaccountItem =>
        this.getCompanybankaccountIdentifier(companybankaccountItem),
      );
      const companybankaccountsToAdd = companybankaccounts.filter(companybankaccountItem => {
        const companybankaccountIdentifier = this.getCompanybankaccountIdentifier(companybankaccountItem);
        if (companybankaccountCollectionIdentifiers.includes(companybankaccountIdentifier)) {
          return false;
        }
        companybankaccountCollectionIdentifiers.push(companybankaccountIdentifier);
        return true;
      });
      return [...companybankaccountsToAdd, ...companybankaccountCollection];
    }
    return companybankaccountCollection;
  }

  protected convertDateFromClient<T extends ICompanybankaccount | NewCompanybankaccount | PartialUpdateCompanybankaccount>(
    companybankaccount: T,
  ): RestOf<T> {
    return {
      ...companybankaccount,
      lmd: companybankaccount.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restCompanybankaccount: RestCompanybankaccount): ICompanybankaccount {
    return {
      ...restCompanybankaccount,
      lmd: restCompanybankaccount.lmd ? dayjs(restCompanybankaccount.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestCompanybankaccount>): HttpResponse<ICompanybankaccount> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestCompanybankaccount[]>): HttpResponse<ICompanybankaccount[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
