import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAutocarecancelitemopt, NewAutocarecancelitemopt } from '../autocarecancelitemopt.model';

export type PartialUpdateAutocarecancelitemopt = Partial<IAutocarecancelitemopt> & Pick<IAutocarecancelitemopt, 'id'>;

export type EntityResponseType = HttpResponse<IAutocarecancelitemopt>;
export type EntityArrayResponseType = HttpResponse<IAutocarecancelitemopt[]>;

@Injectable({ providedIn: 'root' })
export class AutocarecancelitemoptService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/autocarecancelitemopts');

  create(autocarecancelitemopt: NewAutocarecancelitemopt): Observable<EntityResponseType> {
    return this.http.post<IAutocarecancelitemopt>(this.resourceUrl, autocarecancelitemopt, { observe: 'response' });
  }

  update(autocarecancelitemopt: IAutocarecancelitemopt): Observable<EntityResponseType> {
    return this.http.put<IAutocarecancelitemopt>(
      `${this.resourceUrl}/${this.getAutocarecancelitemoptIdentifier(autocarecancelitemopt)}`,
      autocarecancelitemopt,
      { observe: 'response' },
    );
  }

  partialUpdate(autocarecancelitemopt: PartialUpdateAutocarecancelitemopt): Observable<EntityResponseType> {
    return this.http.patch<IAutocarecancelitemopt>(
      `${this.resourceUrl}/${this.getAutocarecancelitemoptIdentifier(autocarecancelitemopt)}`,
      autocarecancelitemopt,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAutocarecancelitemopt>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAutocarecancelitemopt[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAutocarecancelitemoptIdentifier(autocarecancelitemopt: Pick<IAutocarecancelitemopt, 'id'>): number {
    return autocarecancelitemopt.id;
  }

  compareAutocarecancelitemopt(o1: Pick<IAutocarecancelitemopt, 'id'> | null, o2: Pick<IAutocarecancelitemopt, 'id'> | null): boolean {
    return o1 && o2 ? this.getAutocarecancelitemoptIdentifier(o1) === this.getAutocarecancelitemoptIdentifier(o2) : o1 === o2;
  }

  addAutocarecancelitemoptToCollectionIfMissing<Type extends Pick<IAutocarecancelitemopt, 'id'>>(
    autocarecancelitemoptCollection: Type[],
    ...autocarecancelitemoptsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const autocarecancelitemopts: Type[] = autocarecancelitemoptsToCheck.filter(isPresent);
    if (autocarecancelitemopts.length > 0) {
      const autocarecancelitemoptCollectionIdentifiers = autocarecancelitemoptCollection.map(autocarecancelitemoptItem =>
        this.getAutocarecancelitemoptIdentifier(autocarecancelitemoptItem),
      );
      const autocarecancelitemoptsToAdd = autocarecancelitemopts.filter(autocarecancelitemoptItem => {
        const autocarecancelitemoptIdentifier = this.getAutocarecancelitemoptIdentifier(autocarecancelitemoptItem);
        if (autocarecancelitemoptCollectionIdentifiers.includes(autocarecancelitemoptIdentifier)) {
          return false;
        }
        autocarecancelitemoptCollectionIdentifiers.push(autocarecancelitemoptIdentifier);
        return true;
      });
      return [...autocarecancelitemoptsToAdd, ...autocarecancelitemoptCollection];
    }
    return autocarecancelitemoptCollection;
  }
}
