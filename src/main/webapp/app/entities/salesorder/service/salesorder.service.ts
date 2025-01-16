import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISalesorder, NewSalesorder } from '../salesorder.model';

export type PartialUpdateSalesorder = Partial<ISalesorder> & Pick<ISalesorder, 'id'>;

type RestOf<T extends ISalesorder | NewSalesorder> = Omit<T, 'orderdate' | 'createddate' | 'lmd' | 'advancepaymentreturndate'> & {
  orderdate?: string | null;
  createddate?: string | null;
  lmd?: string | null;
  advancepaymentreturndate?: string | null;
};

export type RestSalesorder = RestOf<ISalesorder>;

export type NewRestSalesorder = RestOf<NewSalesorder>;

export type PartialUpdateRestSalesorder = RestOf<PartialUpdateSalesorder>;

export type EntityResponseType = HttpResponse<ISalesorder>;
export type EntityArrayResponseType = HttpResponse<ISalesorder[]>;

@Injectable({ providedIn: 'root' })
export class SalesorderService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/salesorders');

  create(salesorder: NewSalesorder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(salesorder);
    return this.http
      .post<RestSalesorder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(salesorder: ISalesorder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(salesorder);
    return this.http
      .put<RestSalesorder>(`${this.resourceUrl}/${this.getSalesorderIdentifier(salesorder)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(salesorder: PartialUpdateSalesorder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(salesorder);
    return this.http
      .patch<RestSalesorder>(`${this.resourceUrl}/${this.getSalesorderIdentifier(salesorder)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestSalesorder>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSalesorder[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSalesorderIdentifier(salesorder: Pick<ISalesorder, 'id'>): number {
    return salesorder.id;
  }

  compareSalesorder(o1: Pick<ISalesorder, 'id'> | null, o2: Pick<ISalesorder, 'id'> | null): boolean {
    return o1 && o2 ? this.getSalesorderIdentifier(o1) === this.getSalesorderIdentifier(o2) : o1 === o2;
  }

  addSalesorderToCollectionIfMissing<Type extends Pick<ISalesorder, 'id'>>(
    salesorderCollection: Type[],
    ...salesordersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const salesorders: Type[] = salesordersToCheck.filter(isPresent);
    if (salesorders.length > 0) {
      const salesorderCollectionIdentifiers = salesorderCollection.map(salesorderItem => this.getSalesorderIdentifier(salesorderItem));
      const salesordersToAdd = salesorders.filter(salesorderItem => {
        const salesorderIdentifier = this.getSalesorderIdentifier(salesorderItem);
        if (salesorderCollectionIdentifiers.includes(salesorderIdentifier)) {
          return false;
        }
        salesorderCollectionIdentifiers.push(salesorderIdentifier);
        return true;
      });
      return [...salesordersToAdd, ...salesorderCollection];
    }
    return salesorderCollection;
  }

  protected convertDateFromClient<T extends ISalesorder | NewSalesorder | PartialUpdateSalesorder>(salesorder: T): RestOf<T> {
    return {
      ...salesorder,
      orderdate: salesorder.orderdate?.toJSON() ?? null,
      createddate: salesorder.createddate?.toJSON() ?? null,
      lmd: salesorder.lmd?.toJSON() ?? null,
      advancepaymentreturndate: salesorder.advancepaymentreturndate?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restSalesorder: RestSalesorder): ISalesorder {
    return {
      ...restSalesorder,
      orderdate: restSalesorder.orderdate ? dayjs(restSalesorder.orderdate) : undefined,
      createddate: restSalesorder.createddate ? dayjs(restSalesorder.createddate) : undefined,
      lmd: restSalesorder.lmd ? dayjs(restSalesorder.lmd) : undefined,
      advancepaymentreturndate: restSalesorder.advancepaymentreturndate ? dayjs(restSalesorder.advancepaymentreturndate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSalesorder>): HttpResponse<ISalesorder> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSalesorder[]>): HttpResponse<ISalesorder[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
