import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IReceipt, NewReceipt } from '../receipt.model';

export type PartialUpdateReceipt = Partial<IReceipt> & Pick<IReceipt, 'id'>;

type RestOf<T extends IReceipt | NewReceipt> = Omit<T, 'receiptdate' | 'lmd' | 'date' | 'checkdate'> & {
  receiptdate?: string | null;
  lmd?: string | null;
  date?: string | null;
  checkdate?: string | null;
};

export type RestReceipt = RestOf<IReceipt>;

export type NewRestReceipt = RestOf<NewReceipt>;

export type PartialUpdateRestReceipt = RestOf<PartialUpdateReceipt>;

export type EntityResponseType = HttpResponse<IReceipt>;
export type EntityArrayResponseType = HttpResponse<IReceipt[]>;

@Injectable({ providedIn: 'root' })
export class ReceiptService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/receipts');

  create(receipt: NewReceipt): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(receipt);
    return this.http
      .post<RestReceipt>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(receipt: IReceipt): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(receipt);
    return this.http
      .put<RestReceipt>(`${this.resourceUrl}/${this.getReceiptIdentifier(receipt)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(receipt: PartialUpdateReceipt): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(receipt);
    return this.http
      .patch<RestReceipt>(`${this.resourceUrl}/${this.getReceiptIdentifier(receipt)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestReceipt>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestReceipt[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getReceiptIdentifier(receipt: Pick<IReceipt, 'id'>): number {
    return receipt.id;
  }

  compareReceipt(o1: Pick<IReceipt, 'id'> | null, o2: Pick<IReceipt, 'id'> | null): boolean {
    return o1 && o2 ? this.getReceiptIdentifier(o1) === this.getReceiptIdentifier(o2) : o1 === o2;
  }

  addReceiptToCollectionIfMissing<Type extends Pick<IReceipt, 'id'>>(
    receiptCollection: Type[],
    ...receiptsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const receipts: Type[] = receiptsToCheck.filter(isPresent);
    if (receipts.length > 0) {
      const receiptCollectionIdentifiers = receiptCollection.map(receiptItem => this.getReceiptIdentifier(receiptItem));
      const receiptsToAdd = receipts.filter(receiptItem => {
        const receiptIdentifier = this.getReceiptIdentifier(receiptItem);
        if (receiptCollectionIdentifiers.includes(receiptIdentifier)) {
          return false;
        }
        receiptCollectionIdentifiers.push(receiptIdentifier);
        return true;
      });
      return [...receiptsToAdd, ...receiptCollection];
    }
    return receiptCollection;
  }

  protected convertDateFromClient<T extends IReceipt | NewReceipt | PartialUpdateReceipt>(receipt: T): RestOf<T> {
    return {
      ...receipt,
      receiptdate: receipt.receiptdate?.toJSON() ?? null,
      lmd: receipt.lmd?.toJSON() ?? null,
      date: receipt.date?.toJSON() ?? null,
      checkdate: receipt.checkdate?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restReceipt: RestReceipt): IReceipt {
    return {
      ...restReceipt,
      receiptdate: restReceipt.receiptdate ? dayjs(restReceipt.receiptdate) : undefined,
      lmd: restReceipt.lmd ? dayjs(restReceipt.lmd) : undefined,
      date: restReceipt.date ? dayjs(restReceipt.date) : undefined,
      checkdate: restReceipt.checkdate ? dayjs(restReceipt.checkdate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestReceipt>): HttpResponse<IReceipt> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestReceipt[]>): HttpResponse<IReceipt[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
