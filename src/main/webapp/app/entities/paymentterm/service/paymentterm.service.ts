import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IPaymentterm, NewPaymentterm } from '../paymentterm.model';

export type PartialUpdatePaymentterm = Partial<IPaymentterm> & Pick<IPaymentterm, 'id'>;

export type EntityResponseType = HttpResponse<IPaymentterm>;
export type EntityArrayResponseType = HttpResponse<IPaymentterm[]>;

@Injectable({ providedIn: 'root' })
export class PaymenttermService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/paymentterms');

  create(paymentterm: NewPaymentterm): Observable<EntityResponseType> {
    return this.http.post<IPaymentterm>(this.resourceUrl, paymentterm, { observe: 'response' });
  }

  update(paymentterm: IPaymentterm): Observable<EntityResponseType> {
    return this.http.put<IPaymentterm>(`${this.resourceUrl}/${this.getPaymenttermIdentifier(paymentterm)}`, paymentterm, {
      observe: 'response',
    });
  }

  partialUpdate(paymentterm: PartialUpdatePaymentterm): Observable<EntityResponseType> {
    return this.http.patch<IPaymentterm>(`${this.resourceUrl}/${this.getPaymenttermIdentifier(paymentterm)}`, paymentterm, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPaymentterm>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPaymentterm[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getPaymenttermIdentifier(paymentterm: Pick<IPaymentterm, 'id'>): number {
    return paymentterm.id;
  }

  comparePaymentterm(o1: Pick<IPaymentterm, 'id'> | null, o2: Pick<IPaymentterm, 'id'> | null): boolean {
    return o1 && o2 ? this.getPaymenttermIdentifier(o1) === this.getPaymenttermIdentifier(o2) : o1 === o2;
  }

  addPaymenttermToCollectionIfMissing<Type extends Pick<IPaymentterm, 'id'>>(
    paymenttermCollection: Type[],
    ...paymenttermsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const paymentterms: Type[] = paymenttermsToCheck.filter(isPresent);
    if (paymentterms.length > 0) {
      const paymenttermCollectionIdentifiers = paymenttermCollection.map(paymenttermItem => this.getPaymenttermIdentifier(paymenttermItem));
      const paymenttermsToAdd = paymentterms.filter(paymenttermItem => {
        const paymenttermIdentifier = this.getPaymenttermIdentifier(paymenttermItem);
        if (paymenttermCollectionIdentifiers.includes(paymenttermIdentifier)) {
          return false;
        }
        paymenttermCollectionIdentifiers.push(paymenttermIdentifier);
        return true;
      });
      return [...paymenttermsToAdd, ...paymenttermCollection];
    }
    return paymenttermCollection;
  }
}
