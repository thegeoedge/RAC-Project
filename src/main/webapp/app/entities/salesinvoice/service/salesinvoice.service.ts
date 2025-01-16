import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISalesinvoice, NewSalesinvoice } from '../salesinvoice.model';

export type PartialUpdateSalesinvoice = Partial<ISalesinvoice> & Pick<ISalesinvoice, 'id'>;

type RestOf<T extends ISalesinvoice | NewSalesinvoice> = Omit<
  T,
  'invoicedate' | 'createddate' | 'delieverydate' | 'lmd' | 'invcanceldate'
> & {
  invoicedate?: string | null;
  createddate?: string | null;
  delieverydate?: string | null;
  lmd?: string | null;
  invcanceldate?: string | null;
};

export type RestSalesinvoice = RestOf<ISalesinvoice>;

export type NewRestSalesinvoice = RestOf<NewSalesinvoice>;

export type PartialUpdateRestSalesinvoice = RestOf<PartialUpdateSalesinvoice>;

export type EntityResponseType = HttpResponse<ISalesinvoice>;
export type EntityArrayResponseType = HttpResponse<ISalesinvoice[]>;

@Injectable({ providedIn: 'root' })
export class SalesinvoiceService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/salesinvoices');

  create(salesinvoice: NewSalesinvoice): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(salesinvoice);
    return this.http
      .post<RestSalesinvoice>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(salesinvoice: ISalesinvoice): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(salesinvoice);
    return this.http
      .put<RestSalesinvoice>(`${this.resourceUrl}/${this.getSalesinvoiceIdentifier(salesinvoice)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(salesinvoice: PartialUpdateSalesinvoice): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(salesinvoice);
    return this.http
      .patch<RestSalesinvoice>(`${this.resourceUrl}/${this.getSalesinvoiceIdentifier(salesinvoice)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestSalesinvoice>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSalesinvoice[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSalesinvoiceIdentifier(salesinvoice: Pick<ISalesinvoice, 'id'>): number {
    return salesinvoice.id;
  }

  compareSalesinvoice(o1: Pick<ISalesinvoice, 'id'> | null, o2: Pick<ISalesinvoice, 'id'> | null): boolean {
    return o1 && o2 ? this.getSalesinvoiceIdentifier(o1) === this.getSalesinvoiceIdentifier(o2) : o1 === o2;
  }

  addSalesinvoiceToCollectionIfMissing<Type extends Pick<ISalesinvoice, 'id'>>(
    salesinvoiceCollection: Type[],
    ...salesinvoicesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const salesinvoices: Type[] = salesinvoicesToCheck.filter(isPresent);
    if (salesinvoices.length > 0) {
      const salesinvoiceCollectionIdentifiers = salesinvoiceCollection.map(salesinvoiceItem =>
        this.getSalesinvoiceIdentifier(salesinvoiceItem),
      );
      const salesinvoicesToAdd = salesinvoices.filter(salesinvoiceItem => {
        const salesinvoiceIdentifier = this.getSalesinvoiceIdentifier(salesinvoiceItem);
        if (salesinvoiceCollectionIdentifiers.includes(salesinvoiceIdentifier)) {
          return false;
        }
        salesinvoiceCollectionIdentifiers.push(salesinvoiceIdentifier);
        return true;
      });
      return [...salesinvoicesToAdd, ...salesinvoiceCollection];
    }
    return salesinvoiceCollection;
  }

  protected convertDateFromClient<T extends ISalesinvoice | NewSalesinvoice | PartialUpdateSalesinvoice>(salesinvoice: T): RestOf<T> {
    return {
      ...salesinvoice,
      invoicedate: salesinvoice.invoicedate?.toJSON() ?? null,
      createddate: salesinvoice.createddate?.toJSON() ?? null,
      delieverydate: salesinvoice.delieverydate?.toJSON() ?? null,
      lmd: salesinvoice.lmd?.toJSON() ?? null,
      invcanceldate: salesinvoice.invcanceldate?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restSalesinvoice: RestSalesinvoice): ISalesinvoice {
    return {
      ...restSalesinvoice,
      invoicedate: restSalesinvoice.invoicedate ? dayjs(restSalesinvoice.invoicedate) : undefined,
      createddate: restSalesinvoice.createddate ? dayjs(restSalesinvoice.createddate) : undefined,
      delieverydate: restSalesinvoice.delieverydate ? dayjs(restSalesinvoice.delieverydate) : undefined,
      lmd: restSalesinvoice.lmd ? dayjs(restSalesinvoice.lmd) : undefined,
      invcanceldate: restSalesinvoice.invcanceldate ? dayjs(restSalesinvoice.invcanceldate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSalesinvoice>): HttpResponse<ISalesinvoice> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSalesinvoice[]>): HttpResponse<ISalesinvoice[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
