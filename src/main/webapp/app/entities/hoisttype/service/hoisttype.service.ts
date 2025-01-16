import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IHoisttype, NewHoisttype } from '../hoisttype.model';

export type PartialUpdateHoisttype = Partial<IHoisttype> & Pick<IHoisttype, 'id'>;

type RestOf<T extends IHoisttype | NewHoisttype> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestHoisttype = RestOf<IHoisttype>;

export type NewRestHoisttype = RestOf<NewHoisttype>;

export type PartialUpdateRestHoisttype = RestOf<PartialUpdateHoisttype>;

export type EntityResponseType = HttpResponse<IHoisttype>;
export type EntityArrayResponseType = HttpResponse<IHoisttype[]>;

@Injectable({ providedIn: 'root' })
export class HoisttypeService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/hoisttypes');

  create(hoisttype: NewHoisttype): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hoisttype);
    return this.http
      .post<RestHoisttype>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(hoisttype: IHoisttype): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hoisttype);
    return this.http
      .put<RestHoisttype>(`${this.resourceUrl}/${this.getHoisttypeIdentifier(hoisttype)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(hoisttype: PartialUpdateHoisttype): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hoisttype);
    return this.http
      .patch<RestHoisttype>(`${this.resourceUrl}/${this.getHoisttypeIdentifier(hoisttype)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestHoisttype>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestHoisttype[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getHoisttypeIdentifier(hoisttype: Pick<IHoisttype, 'id'>): number {
    return hoisttype.id;
  }

  compareHoisttype(o1: Pick<IHoisttype, 'id'> | null, o2: Pick<IHoisttype, 'id'> | null): boolean {
    return o1 && o2 ? this.getHoisttypeIdentifier(o1) === this.getHoisttypeIdentifier(o2) : o1 === o2;
  }

  addHoisttypeToCollectionIfMissing<Type extends Pick<IHoisttype, 'id'>>(
    hoisttypeCollection: Type[],
    ...hoisttypesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const hoisttypes: Type[] = hoisttypesToCheck.filter(isPresent);
    if (hoisttypes.length > 0) {
      const hoisttypeCollectionIdentifiers = hoisttypeCollection.map(hoisttypeItem => this.getHoisttypeIdentifier(hoisttypeItem));
      const hoisttypesToAdd = hoisttypes.filter(hoisttypeItem => {
        const hoisttypeIdentifier = this.getHoisttypeIdentifier(hoisttypeItem);
        if (hoisttypeCollectionIdentifiers.includes(hoisttypeIdentifier)) {
          return false;
        }
        hoisttypeCollectionIdentifiers.push(hoisttypeIdentifier);
        return true;
      });
      return [...hoisttypesToAdd, ...hoisttypeCollection];
    }
    return hoisttypeCollection;
  }

  protected convertDateFromClient<T extends IHoisttype | NewHoisttype | PartialUpdateHoisttype>(hoisttype: T): RestOf<T> {
    return {
      ...hoisttype,
      lmd: hoisttype.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restHoisttype: RestHoisttype): IHoisttype {
    return {
      ...restHoisttype,
      lmd: restHoisttype.lmd ? dayjs(restHoisttype.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestHoisttype>): HttpResponse<IHoisttype> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestHoisttype[]>): HttpResponse<IHoisttype[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
