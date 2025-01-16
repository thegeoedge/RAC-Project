import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAccounts, NewAccounts } from '../accounts.model';

export type PartialUpdateAccounts = Partial<IAccounts> & Pick<IAccounts, 'id'>;

type RestOf<T extends IAccounts | NewAccounts> = Omit<T, 'date' | 'lmd'> & {
  date?: string | null;
  lmd?: string | null;
};

export type RestAccounts = RestOf<IAccounts>;

export type NewRestAccounts = RestOf<NewAccounts>;

export type PartialUpdateRestAccounts = RestOf<PartialUpdateAccounts>;

export type EntityResponseType = HttpResponse<IAccounts>;
export type EntityArrayResponseType = HttpResponse<IAccounts[]>;

@Injectable({ providedIn: 'root' })
export class AccountsService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/accounts');

  create(accounts: NewAccounts): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(accounts);
    return this.http
      .post<RestAccounts>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(accounts: IAccounts): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(accounts);
    return this.http
      .put<RestAccounts>(`${this.resourceUrl}/${this.getAccountsIdentifier(accounts)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(accounts: PartialUpdateAccounts): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(accounts);
    return this.http
      .patch<RestAccounts>(`${this.resourceUrl}/${this.getAccountsIdentifier(accounts)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestAccounts>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAccounts[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAccountsIdentifier(accounts: Pick<IAccounts, 'id'>): number {
    return accounts.id;
  }

  compareAccounts(o1: Pick<IAccounts, 'id'> | null, o2: Pick<IAccounts, 'id'> | null): boolean {
    return o1 && o2 ? this.getAccountsIdentifier(o1) === this.getAccountsIdentifier(o2) : o1 === o2;
  }

  addAccountsToCollectionIfMissing<Type extends Pick<IAccounts, 'id'>>(
    accountsCollection: Type[],
    ...accountsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const accounts: Type[] = accountsToCheck.filter(isPresent);
    if (accounts.length > 0) {
      const accountsCollectionIdentifiers = accountsCollection.map(accountsItem => this.getAccountsIdentifier(accountsItem));
      const accountsToAdd = accounts.filter(accountsItem => {
        const accountsIdentifier = this.getAccountsIdentifier(accountsItem);
        if (accountsCollectionIdentifiers.includes(accountsIdentifier)) {
          return false;
        }
        accountsCollectionIdentifiers.push(accountsIdentifier);
        return true;
      });
      return [...accountsToAdd, ...accountsCollection];
    }
    return accountsCollection;
  }

  protected convertDateFromClient<T extends IAccounts | NewAccounts | PartialUpdateAccounts>(accounts: T): RestOf<T> {
    return {
      ...accounts,
      date: accounts.date?.toJSON() ?? null,
      lmd: accounts.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restAccounts: RestAccounts): IAccounts {
    return {
      ...restAccounts,
      date: restAccounts.date ? dayjs(restAccounts.date) : undefined,
      lmd: restAccounts.lmd ? dayjs(restAccounts.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAccounts>): HttpResponse<IAccounts> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAccounts[]>): HttpResponse<IAccounts[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
