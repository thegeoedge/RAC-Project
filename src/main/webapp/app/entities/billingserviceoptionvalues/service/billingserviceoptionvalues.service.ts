import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBillingserviceoptionvalues, NewBillingserviceoptionvalues } from '../billingserviceoptionvalues.model';

export type PartialUpdateBillingserviceoptionvalues = Partial<IBillingserviceoptionvalues> & Pick<IBillingserviceoptionvalues, 'id'>;

type RestOf<T extends IBillingserviceoptionvalues | NewBillingserviceoptionvalues> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestBillingserviceoptionvalues = RestOf<IBillingserviceoptionvalues>;

export type NewRestBillingserviceoptionvalues = RestOf<NewBillingserviceoptionvalues>;

export type PartialUpdateRestBillingserviceoptionvalues = RestOf<PartialUpdateBillingserviceoptionvalues>;

export type EntityResponseType = HttpResponse<IBillingserviceoptionvalues>;
export type EntityArrayResponseType = HttpResponse<IBillingserviceoptionvalues[]>;

@Injectable({ providedIn: 'root' })
export class BillingserviceoptionvaluesService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/billingserviceoptionvalues');

  create(billingserviceoptionvalues: NewBillingserviceoptionvalues): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billingserviceoptionvalues);
    return this.http
      .post<RestBillingserviceoptionvalues>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(billingserviceoptionvalues: IBillingserviceoptionvalues): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billingserviceoptionvalues);
    return this.http
      .put<RestBillingserviceoptionvalues>(
        `${this.resourceUrl}/${this.getBillingserviceoptionvaluesIdentifier(billingserviceoptionvalues)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(billingserviceoptionvalues: PartialUpdateBillingserviceoptionvalues): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billingserviceoptionvalues);
    return this.http
      .patch<RestBillingserviceoptionvalues>(
        `${this.resourceUrl}/${this.getBillingserviceoptionvaluesIdentifier(billingserviceoptionvalues)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestBillingserviceoptionvalues>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestBillingserviceoptionvalues[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getBillingserviceoptionvaluesIdentifier(billingserviceoptionvalues: Pick<IBillingserviceoptionvalues, 'id'>): number {
    return billingserviceoptionvalues.id;
  }

  compareBillingserviceoptionvalues(
    o1: Pick<IBillingserviceoptionvalues, 'id'> | null,
    o2: Pick<IBillingserviceoptionvalues, 'id'> | null,
  ): boolean {
    return o1 && o2 ? this.getBillingserviceoptionvaluesIdentifier(o1) === this.getBillingserviceoptionvaluesIdentifier(o2) : o1 === o2;
  }

  addBillingserviceoptionvaluesToCollectionIfMissing<Type extends Pick<IBillingserviceoptionvalues, 'id'>>(
    billingserviceoptionvaluesCollection: Type[],
    ...billingserviceoptionvaluesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const billingserviceoptionvalues: Type[] = billingserviceoptionvaluesToCheck.filter(isPresent);
    if (billingserviceoptionvalues.length > 0) {
      const billingserviceoptionvaluesCollectionIdentifiers = billingserviceoptionvaluesCollection.map(billingserviceoptionvaluesItem =>
        this.getBillingserviceoptionvaluesIdentifier(billingserviceoptionvaluesItem),
      );
      const billingserviceoptionvaluesToAdd = billingserviceoptionvalues.filter(billingserviceoptionvaluesItem => {
        const billingserviceoptionvaluesIdentifier = this.getBillingserviceoptionvaluesIdentifier(billingserviceoptionvaluesItem);
        if (billingserviceoptionvaluesCollectionIdentifiers.includes(billingserviceoptionvaluesIdentifier)) {
          return false;
        }
        billingserviceoptionvaluesCollectionIdentifiers.push(billingserviceoptionvaluesIdentifier);
        return true;
      });
      return [...billingserviceoptionvaluesToAdd, ...billingserviceoptionvaluesCollection];
    }
    return billingserviceoptionvaluesCollection;
  }

  protected convertDateFromClient<
    T extends IBillingserviceoptionvalues | NewBillingserviceoptionvalues | PartialUpdateBillingserviceoptionvalues,
  >(billingserviceoptionvalues: T): RestOf<T> {
    return {
      ...billingserviceoptionvalues,
      lmd: billingserviceoptionvalues.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restBillingserviceoptionvalues: RestBillingserviceoptionvalues): IBillingserviceoptionvalues {
    return {
      ...restBillingserviceoptionvalues,
      lmd: restBillingserviceoptionvalues.lmd ? dayjs(restBillingserviceoptionvalues.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestBillingserviceoptionvalues>): HttpResponse<IBillingserviceoptionvalues> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestBillingserviceoptionvalues[]>,
  ): HttpResponse<IBillingserviceoptionvalues[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
