import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBillingserviceoption, NewBillingserviceoption } from '../billingserviceoption.model';

export type PartialUpdateBillingserviceoption = Partial<IBillingserviceoption> & Pick<IBillingserviceoption, 'id'>;

type RestOf<T extends IBillingserviceoption | NewBillingserviceoption> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestBillingserviceoption = RestOf<IBillingserviceoption>;

export type NewRestBillingserviceoption = RestOf<NewBillingserviceoption>;

export type PartialUpdateRestBillingserviceoption = RestOf<PartialUpdateBillingserviceoption>;

export type EntityResponseType = HttpResponse<IBillingserviceoption>;
export type EntityArrayResponseType = HttpResponse<IBillingserviceoption[]>;

@Injectable({ providedIn: 'root' })
export class BillingserviceoptionService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/billingserviceoptions');

  create(billingserviceoption: NewBillingserviceoption): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billingserviceoption);
    return this.http
      .post<RestBillingserviceoption>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(billingserviceoption: IBillingserviceoption): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billingserviceoption);
    return this.http
      .put<RestBillingserviceoption>(`${this.resourceUrl}/${this.getBillingserviceoptionIdentifier(billingserviceoption)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(billingserviceoption: PartialUpdateBillingserviceoption): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billingserviceoption);
    return this.http
      .patch<RestBillingserviceoption>(`${this.resourceUrl}/${this.getBillingserviceoptionIdentifier(billingserviceoption)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestBillingserviceoption>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestBillingserviceoption[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getBillingserviceoptionIdentifier(billingserviceoption: Pick<IBillingserviceoption, 'id'>): number {
    return billingserviceoption.id;
  }

  compareBillingserviceoption(o1: Pick<IBillingserviceoption, 'id'> | null, o2: Pick<IBillingserviceoption, 'id'> | null): boolean {
    return o1 && o2 ? this.getBillingserviceoptionIdentifier(o1) === this.getBillingserviceoptionIdentifier(o2) : o1 === o2;
  }

  addBillingserviceoptionToCollectionIfMissing<Type extends Pick<IBillingserviceoption, 'id'>>(
    billingserviceoptionCollection: Type[],
    ...billingserviceoptionsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const billingserviceoptions: Type[] = billingserviceoptionsToCheck.filter(isPresent);
    if (billingserviceoptions.length > 0) {
      const billingserviceoptionCollectionIdentifiers = billingserviceoptionCollection.map(billingserviceoptionItem =>
        this.getBillingserviceoptionIdentifier(billingserviceoptionItem),
      );
      const billingserviceoptionsToAdd = billingserviceoptions.filter(billingserviceoptionItem => {
        const billingserviceoptionIdentifier = this.getBillingserviceoptionIdentifier(billingserviceoptionItem);
        if (billingserviceoptionCollectionIdentifiers.includes(billingserviceoptionIdentifier)) {
          return false;
        }
        billingserviceoptionCollectionIdentifiers.push(billingserviceoptionIdentifier);
        return true;
      });
      return [...billingserviceoptionsToAdd, ...billingserviceoptionCollection];
    }
    return billingserviceoptionCollection;
  }

  protected convertDateFromClient<T extends IBillingserviceoption | NewBillingserviceoption | PartialUpdateBillingserviceoption>(
    billingserviceoption: T,
  ): RestOf<T> {
    return {
      ...billingserviceoption,
      lmd: billingserviceoption.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restBillingserviceoption: RestBillingserviceoption): IBillingserviceoption {
    return {
      ...restBillingserviceoption,
      lmd: restBillingserviceoption.lmd ? dayjs(restBillingserviceoption.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestBillingserviceoption>): HttpResponse<IBillingserviceoption> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestBillingserviceoption[]>): HttpResponse<IBillingserviceoption[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
