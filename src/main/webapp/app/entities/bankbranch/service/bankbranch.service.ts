import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBankbranch, NewBankbranch } from '../bankbranch.model';

export type PartialUpdateBankbranch = Partial<IBankbranch> & Pick<IBankbranch, 'id'>;

export type EntityResponseType = HttpResponse<IBankbranch>;
export type EntityArrayResponseType = HttpResponse<IBankbranch[]>;

@Injectable({ providedIn: 'root' })
export class BankbranchService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/bankbranches');

  create(bankbranch: NewBankbranch): Observable<EntityResponseType> {
    return this.http.post<IBankbranch>(this.resourceUrl, bankbranch, { observe: 'response' });
  }

  update(bankbranch: IBankbranch): Observable<EntityResponseType> {
    return this.http.put<IBankbranch>(`${this.resourceUrl}/${this.getBankbranchIdentifier(bankbranch)}`, bankbranch, {
      observe: 'response',
    });
  }

  partialUpdate(bankbranch: PartialUpdateBankbranch): Observable<EntityResponseType> {
    return this.http.patch<IBankbranch>(`${this.resourceUrl}/${this.getBankbranchIdentifier(bankbranch)}`, bankbranch, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBankbranch>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBankbranch[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getBankbranchIdentifier(bankbranch: Pick<IBankbranch, 'id'>): number {
    return bankbranch.id;
  }

  compareBankbranch(o1: Pick<IBankbranch, 'id'> | null, o2: Pick<IBankbranch, 'id'> | null): boolean {
    return o1 && o2 ? this.getBankbranchIdentifier(o1) === this.getBankbranchIdentifier(o2) : o1 === o2;
  }

  addBankbranchToCollectionIfMissing<Type extends Pick<IBankbranch, 'id'>>(
    bankbranchCollection: Type[],
    ...bankbranchesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const bankbranches: Type[] = bankbranchesToCheck.filter(isPresent);
    if (bankbranches.length > 0) {
      const bankbranchCollectionIdentifiers = bankbranchCollection.map(bankbranchItem => this.getBankbranchIdentifier(bankbranchItem));
      const bankbranchesToAdd = bankbranches.filter(bankbranchItem => {
        const bankbranchIdentifier = this.getBankbranchIdentifier(bankbranchItem);
        if (bankbranchCollectionIdentifiers.includes(bankbranchIdentifier)) {
          return false;
        }
        bankbranchCollectionIdentifiers.push(bankbranchIdentifier);
        return true;
      });
      return [...bankbranchesToAdd, ...bankbranchCollection];
    }
    return bankbranchCollection;
  }
}
