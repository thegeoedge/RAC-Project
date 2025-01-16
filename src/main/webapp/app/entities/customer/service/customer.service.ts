import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICustomer, NewCustomer } from '../customer.model';

export type PartialUpdateCustomer = Partial<ICustomer> & Pick<ICustomer, 'id'>;

type RestOf<T extends ICustomer | NewCustomer> = Omit<
  T,
  'nicissueddate' | 'dateofbirth' | 'marrieddate' | 'businessregdate' | 'registrationdate' | 'lmd'
> & {
  nicissueddate?: string | null;
  dateofbirth?: string | null;
  marrieddate?: string | null;
  businessregdate?: string | null;
  registrationdate?: string | null;
  lmd?: string | null;
};

export type RestCustomer = RestOf<ICustomer>;

export type NewRestCustomer = RestOf<NewCustomer>;

export type PartialUpdateRestCustomer = RestOf<PartialUpdateCustomer>;

export type EntityResponseType = HttpResponse<ICustomer>;
export type EntityArrayResponseType = HttpResponse<ICustomer[]>;

@Injectable({ providedIn: 'root' })
export class CustomerService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/customers');

  create(customer: NewCustomer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(customer);
    return this.http
      .post<RestCustomer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(customer: ICustomer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(customer);
    return this.http
      .put<RestCustomer>(`${this.resourceUrl}/${this.getCustomerIdentifier(customer)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(customer: PartialUpdateCustomer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(customer);
    return this.http
      .patch<RestCustomer>(`${this.resourceUrl}/${this.getCustomerIdentifier(customer)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestCustomer>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestCustomer[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCustomerIdentifier(customer: Pick<ICustomer, 'id'>): number {
    return customer.id;
  }

  compareCustomer(o1: Pick<ICustomer, 'id'> | null, o2: Pick<ICustomer, 'id'> | null): boolean {
    return o1 && o2 ? this.getCustomerIdentifier(o1) === this.getCustomerIdentifier(o2) : o1 === o2;
  }

  addCustomerToCollectionIfMissing<Type extends Pick<ICustomer, 'id'>>(
    customerCollection: Type[],
    ...customersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const customers: Type[] = customersToCheck.filter(isPresent);
    if (customers.length > 0) {
      const customerCollectionIdentifiers = customerCollection.map(customerItem => this.getCustomerIdentifier(customerItem));
      const customersToAdd = customers.filter(customerItem => {
        const customerIdentifier = this.getCustomerIdentifier(customerItem);
        if (customerCollectionIdentifiers.includes(customerIdentifier)) {
          return false;
        }
        customerCollectionIdentifiers.push(customerIdentifier);
        return true;
      });
      return [...customersToAdd, ...customerCollection];
    }
    return customerCollection;
  }

  protected convertDateFromClient<T extends ICustomer | NewCustomer | PartialUpdateCustomer>(customer: T): RestOf<T> {
    return {
      ...customer,
      nicissueddate: customer.nicissueddate?.format(DATE_FORMAT) ?? null,
      dateofbirth: customer.dateofbirth?.format(DATE_FORMAT) ?? null,
      marrieddate: customer.marrieddate?.format(DATE_FORMAT) ?? null,
      businessregdate: customer.businessregdate?.format(DATE_FORMAT) ?? null,
      registrationdate: customer.registrationdate?.toJSON() ?? null,
      lmd: customer.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restCustomer: RestCustomer): ICustomer {
    return {
      ...restCustomer,
      nicissueddate: restCustomer.nicissueddate ? dayjs(restCustomer.nicissueddate) : undefined,
      dateofbirth: restCustomer.dateofbirth ? dayjs(restCustomer.dateofbirth) : undefined,
      marrieddate: restCustomer.marrieddate ? dayjs(restCustomer.marrieddate) : undefined,
      businessregdate: restCustomer.businessregdate ? dayjs(restCustomer.businessregdate) : undefined,
      registrationdate: restCustomer.registrationdate ? dayjs(restCustomer.registrationdate) : undefined,
      lmd: restCustomer.lmd ? dayjs(restCustomer.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestCustomer>): HttpResponse<ICustomer> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestCustomer[]>): HttpResponse<ICustomer[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
