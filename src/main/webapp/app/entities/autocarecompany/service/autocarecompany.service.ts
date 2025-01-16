import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAutocarecompany, NewAutocarecompany } from '../autocarecompany.model';

export type PartialUpdateAutocarecompany = Partial<IAutocarecompany> & Pick<IAutocarecompany, 'id'>;

type RestOf<T extends IAutocarecompany | NewAutocarecompany> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestAutocarecompany = RestOf<IAutocarecompany>;

export type NewRestAutocarecompany = RestOf<NewAutocarecompany>;

export type PartialUpdateRestAutocarecompany = RestOf<PartialUpdateAutocarecompany>;

export type EntityResponseType = HttpResponse<IAutocarecompany>;
export type EntityArrayResponseType = HttpResponse<IAutocarecompany[]>;

@Injectable({ providedIn: 'root' })
export class AutocarecompanyService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/autocarecompanies');

  create(autocarecompany: NewAutocarecompany): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocarecompany);
    return this.http
      .post<RestAutocarecompany>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(autocarecompany: IAutocarecompany): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocarecompany);
    return this.http
      .put<RestAutocarecompany>(`${this.resourceUrl}/${this.getAutocarecompanyIdentifier(autocarecompany)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(autocarecompany: PartialUpdateAutocarecompany): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocarecompany);
    return this.http
      .patch<RestAutocarecompany>(`${this.resourceUrl}/${this.getAutocarecompanyIdentifier(autocarecompany)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestAutocarecompany>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAutocarecompany[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAutocarecompanyIdentifier(autocarecompany: Pick<IAutocarecompany, 'id'>): number {
    return autocarecompany.id;
  }

  compareAutocarecompany(o1: Pick<IAutocarecompany, 'id'> | null, o2: Pick<IAutocarecompany, 'id'> | null): boolean {
    return o1 && o2 ? this.getAutocarecompanyIdentifier(o1) === this.getAutocarecompanyIdentifier(o2) : o1 === o2;
  }

  addAutocarecompanyToCollectionIfMissing<Type extends Pick<IAutocarecompany, 'id'>>(
    autocarecompanyCollection: Type[],
    ...autocarecompaniesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const autocarecompanies: Type[] = autocarecompaniesToCheck.filter(isPresent);
    if (autocarecompanies.length > 0) {
      const autocarecompanyCollectionIdentifiers = autocarecompanyCollection.map(autocarecompanyItem =>
        this.getAutocarecompanyIdentifier(autocarecompanyItem),
      );
      const autocarecompaniesToAdd = autocarecompanies.filter(autocarecompanyItem => {
        const autocarecompanyIdentifier = this.getAutocarecompanyIdentifier(autocarecompanyItem);
        if (autocarecompanyCollectionIdentifiers.includes(autocarecompanyIdentifier)) {
          return false;
        }
        autocarecompanyCollectionIdentifiers.push(autocarecompanyIdentifier);
        return true;
      });
      return [...autocarecompaniesToAdd, ...autocarecompanyCollection];
    }
    return autocarecompanyCollection;
  }

  protected convertDateFromClient<T extends IAutocarecompany | NewAutocarecompany | PartialUpdateAutocarecompany>(
    autocarecompany: T,
  ): RestOf<T> {
    return {
      ...autocarecompany,
      lmd: autocarecompany.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restAutocarecompany: RestAutocarecompany): IAutocarecompany {
    return {
      ...restAutocarecompany,
      lmd: restAutocarecompany.lmd ? dayjs(restAutocarecompany.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAutocarecompany>): HttpResponse<IAutocarecompany> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAutocarecompany[]>): HttpResponse<IAutocarecompany[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
