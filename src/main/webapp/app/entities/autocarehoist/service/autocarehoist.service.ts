import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAutocarehoist, NewAutocarehoist } from '../autocarehoist.model';

export type PartialUpdateAutocarehoist = Partial<IAutocarehoist> & Pick<IAutocarehoist, 'id'>;

type RestOf<T extends IAutocarehoist | NewAutocarehoist> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestAutocarehoist = RestOf<IAutocarehoist>;

export type NewRestAutocarehoist = RestOf<NewAutocarehoist>;

export type PartialUpdateRestAutocarehoist = RestOf<PartialUpdateAutocarehoist>;

export type EntityResponseType = HttpResponse<IAutocarehoist>;
export type EntityArrayResponseType = HttpResponse<IAutocarehoist[]>;

@Injectable({ providedIn: 'root' })
export class AutocarehoistService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/autocarehoists');

  create(autocarehoist: NewAutocarehoist): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocarehoist);
    return this.http
      .post<RestAutocarehoist>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(autocarehoist: IAutocarehoist): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocarehoist);
    return this.http
      .put<RestAutocarehoist>(`${this.resourceUrl}/${this.getAutocarehoistIdentifier(autocarehoist)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(autocarehoist: PartialUpdateAutocarehoist): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocarehoist);
    return this.http
      .patch<RestAutocarehoist>(`${this.resourceUrl}/${this.getAutocarehoistIdentifier(autocarehoist)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestAutocarehoist>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAutocarehoist[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAutocarehoistIdentifier(autocarehoist: Pick<IAutocarehoist, 'id'>): number {
    return autocarehoist.id;
  }

  compareAutocarehoist(o1: Pick<IAutocarehoist, 'id'> | null, o2: Pick<IAutocarehoist, 'id'> | null): boolean {
    return o1 && o2 ? this.getAutocarehoistIdentifier(o1) === this.getAutocarehoistIdentifier(o2) : o1 === o2;
  }

  addAutocarehoistToCollectionIfMissing<Type extends Pick<IAutocarehoist, 'id'>>(
    autocarehoistCollection: Type[],
    ...autocarehoistsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const autocarehoists: Type[] = autocarehoistsToCheck.filter(isPresent);
    if (autocarehoists.length > 0) {
      const autocarehoistCollectionIdentifiers = autocarehoistCollection.map(autocarehoistItem =>
        this.getAutocarehoistIdentifier(autocarehoistItem),
      );
      const autocarehoistsToAdd = autocarehoists.filter(autocarehoistItem => {
        const autocarehoistIdentifier = this.getAutocarehoistIdentifier(autocarehoistItem);
        if (autocarehoistCollectionIdentifiers.includes(autocarehoistIdentifier)) {
          return false;
        }
        autocarehoistCollectionIdentifiers.push(autocarehoistIdentifier);
        return true;
      });
      return [...autocarehoistsToAdd, ...autocarehoistCollection];
    }
    return autocarehoistCollection;
  }

  protected convertDateFromClient<T extends IAutocarehoist | NewAutocarehoist | PartialUpdateAutocarehoist>(autocarehoist: T): RestOf<T> {
    return {
      ...autocarehoist,
      lmd: autocarehoist.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restAutocarehoist: RestAutocarehoist): IAutocarehoist {
    return {
      ...restAutocarehoist,
      lmd: restAutocarehoist.lmd ? dayjs(restAutocarehoist.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAutocarehoist>): HttpResponse<IAutocarehoist> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAutocarehoist[]>): HttpResponse<IAutocarehoist[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
