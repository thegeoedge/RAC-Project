import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITaxes, NewTaxes } from '../taxes.model';

export type PartialUpdateTaxes = Partial<ITaxes> & Pick<ITaxes, 'id'>;

type RestOf<T extends ITaxes | NewTaxes> = Omit<T, 'effectivefrom' | 'effectiveto' | 'lmd'> & {
  effectivefrom?: string | null;
  effectiveto?: string | null;
  lmd?: string | null;
};

export type RestTaxes = RestOf<ITaxes>;

export type NewRestTaxes = RestOf<NewTaxes>;

export type PartialUpdateRestTaxes = RestOf<PartialUpdateTaxes>;

export type EntityResponseType = HttpResponse<ITaxes>;
export type EntityArrayResponseType = HttpResponse<ITaxes[]>;

@Injectable({ providedIn: 'root' })
export class TaxesService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/taxes');

  create(taxes: NewTaxes): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(taxes);
    return this.http.post<RestTaxes>(this.resourceUrl, copy, { observe: 'response' }).pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(taxes: ITaxes): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(taxes);
    return this.http
      .put<RestTaxes>(`${this.resourceUrl}/${this.getTaxesIdentifier(taxes)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(taxes: PartialUpdateTaxes): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(taxes);
    return this.http
      .patch<RestTaxes>(`${this.resourceUrl}/${this.getTaxesIdentifier(taxes)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestTaxes>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestTaxes[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getTaxesIdentifier(taxes: Pick<ITaxes, 'id'>): number {
    return taxes.id;
  }

  compareTaxes(o1: Pick<ITaxes, 'id'> | null, o2: Pick<ITaxes, 'id'> | null): boolean {
    return o1 && o2 ? this.getTaxesIdentifier(o1) === this.getTaxesIdentifier(o2) : o1 === o2;
  }

  addTaxesToCollectionIfMissing<Type extends Pick<ITaxes, 'id'>>(
    taxesCollection: Type[],
    ...taxesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const taxes: Type[] = taxesToCheck.filter(isPresent);
    if (taxes.length > 0) {
      const taxesCollectionIdentifiers = taxesCollection.map(taxesItem => this.getTaxesIdentifier(taxesItem));
      const taxesToAdd = taxes.filter(taxesItem => {
        const taxesIdentifier = this.getTaxesIdentifier(taxesItem);
        if (taxesCollectionIdentifiers.includes(taxesIdentifier)) {
          return false;
        }
        taxesCollectionIdentifiers.push(taxesIdentifier);
        return true;
      });
      return [...taxesToAdd, ...taxesCollection];
    }
    return taxesCollection;
  }

  protected convertDateFromClient<T extends ITaxes | NewTaxes | PartialUpdateTaxes>(taxes: T): RestOf<T> {
    return {
      ...taxes,
      effectivefrom: taxes.effectivefrom?.format(DATE_FORMAT) ?? null,
      effectiveto: taxes.effectiveto?.format(DATE_FORMAT) ?? null,
      lmd: taxes.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restTaxes: RestTaxes): ITaxes {
    return {
      ...restTaxes,
      effectivefrom: restTaxes.effectivefrom ? dayjs(restTaxes.effectivefrom) : undefined,
      effectiveto: restTaxes.effectiveto ? dayjs(restTaxes.effectiveto) : undefined,
      lmd: restTaxes.lmd ? dayjs(restTaxes.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestTaxes>): HttpResponse<ITaxes> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestTaxes[]>): HttpResponse<ITaxes[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
