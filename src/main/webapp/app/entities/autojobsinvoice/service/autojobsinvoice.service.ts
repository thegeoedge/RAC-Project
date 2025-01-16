import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAutojobsinvoice, NewAutojobsinvoice } from '../autojobsinvoice.model';

export type PartialUpdateAutojobsinvoice = Partial<IAutojobsinvoice> & Pick<IAutojobsinvoice, 'id'>;

type RestOf<T extends IAutojobsinvoice | NewAutojobsinvoice> = Omit<T, 'invoicedate' | 'createddate' | 'delieverydate' | 'lmd'> & {
  invoicedate?: string | null;
  createddate?: string | null;
  delieverydate?: string | null;
  lmd?: string | null;
};

export type RestAutojobsinvoice = RestOf<IAutojobsinvoice>;

export type NewRestAutojobsinvoice = RestOf<NewAutojobsinvoice>;

export type PartialUpdateRestAutojobsinvoice = RestOf<PartialUpdateAutojobsinvoice>;

export type EntityResponseType = HttpResponse<IAutojobsinvoice>;
export type EntityArrayResponseType = HttpResponse<IAutojobsinvoice[]>;

@Injectable({ providedIn: 'root' })
export class AutojobsinvoiceService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/autojobsinvoices');

  create(autojobsinvoice: NewAutojobsinvoice): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autojobsinvoice);
    return this.http
      .post<RestAutojobsinvoice>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(autojobsinvoice: IAutojobsinvoice): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autojobsinvoice);
    return this.http
      .put<RestAutojobsinvoice>(`${this.resourceUrl}/${this.getAutojobsinvoiceIdentifier(autojobsinvoice)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(autojobsinvoice: PartialUpdateAutojobsinvoice): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autojobsinvoice);
    return this.http
      .patch<RestAutojobsinvoice>(`${this.resourceUrl}/${this.getAutojobsinvoiceIdentifier(autojobsinvoice)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestAutojobsinvoice>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAutojobsinvoice[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAutojobsinvoiceIdentifier(autojobsinvoice: Pick<IAutojobsinvoice, 'id'>): number {
    return autojobsinvoice.id;
  }

  compareAutojobsinvoice(o1: Pick<IAutojobsinvoice, 'id'> | null, o2: Pick<IAutojobsinvoice, 'id'> | null): boolean {
    return o1 && o2 ? this.getAutojobsinvoiceIdentifier(o1) === this.getAutojobsinvoiceIdentifier(o2) : o1 === o2;
  }

  addAutojobsinvoiceToCollectionIfMissing<Type extends Pick<IAutojobsinvoice, 'id'>>(
    autojobsinvoiceCollection: Type[],
    ...autojobsinvoicesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const autojobsinvoices: Type[] = autojobsinvoicesToCheck.filter(isPresent);
    if (autojobsinvoices.length > 0) {
      const autojobsinvoiceCollectionIdentifiers = autojobsinvoiceCollection.map(autojobsinvoiceItem =>
        this.getAutojobsinvoiceIdentifier(autojobsinvoiceItem),
      );
      const autojobsinvoicesToAdd = autojobsinvoices.filter(autojobsinvoiceItem => {
        const autojobsinvoiceIdentifier = this.getAutojobsinvoiceIdentifier(autojobsinvoiceItem);
        if (autojobsinvoiceCollectionIdentifiers.includes(autojobsinvoiceIdentifier)) {
          return false;
        }
        autojobsinvoiceCollectionIdentifiers.push(autojobsinvoiceIdentifier);
        return true;
      });
      return [...autojobsinvoicesToAdd, ...autojobsinvoiceCollection];
    }
    return autojobsinvoiceCollection;
  }

  protected convertDateFromClient<T extends IAutojobsinvoice | NewAutojobsinvoice | PartialUpdateAutojobsinvoice>(
    autojobsinvoice: T,
  ): RestOf<T> {
    return {
      ...autojobsinvoice,
      invoicedate: autojobsinvoice.invoicedate?.toJSON() ?? null,
      createddate: autojobsinvoice.createddate?.toJSON() ?? null,
      delieverydate: autojobsinvoice.delieverydate?.toJSON() ?? null,
      lmd: autojobsinvoice.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restAutojobsinvoice: RestAutojobsinvoice): IAutojobsinvoice {
    return {
      ...restAutojobsinvoice,
      invoicedate: restAutojobsinvoice.invoicedate ? dayjs(restAutojobsinvoice.invoicedate) : undefined,
      createddate: restAutojobsinvoice.createddate ? dayjs(restAutojobsinvoice.createddate) : undefined,
      delieverydate: restAutojobsinvoice.delieverydate ? dayjs(restAutojobsinvoice.delieverydate) : undefined,
      lmd: restAutojobsinvoice.lmd ? dayjs(restAutojobsinvoice.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAutojobsinvoice>): HttpResponse<IAutojobsinvoice> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAutojobsinvoice[]>): HttpResponse<IAutojobsinvoice[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
