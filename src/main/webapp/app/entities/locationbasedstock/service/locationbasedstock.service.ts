import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ILocationbasedstock, NewLocationbasedstock } from '../locationbasedstock.model';

export type PartialUpdateLocationbasedstock = Partial<ILocationbasedstock> & Pick<ILocationbasedstock, 'id'>;

type RestOf<T extends ILocationbasedstock | NewLocationbasedstock> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestLocationbasedstock = RestOf<ILocationbasedstock>;

export type NewRestLocationbasedstock = RestOf<NewLocationbasedstock>;

export type PartialUpdateRestLocationbasedstock = RestOf<PartialUpdateLocationbasedstock>;

export type EntityResponseType = HttpResponse<ILocationbasedstock>;
export type EntityArrayResponseType = HttpResponse<ILocationbasedstock[]>;

@Injectable({ providedIn: 'root' })
export class LocationbasedstockService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/locationbasedstocks');

  create(locationbasedstock: NewLocationbasedstock): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(locationbasedstock);
    return this.http
      .post<RestLocationbasedstock>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(locationbasedstock: ILocationbasedstock): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(locationbasedstock);
    return this.http
      .put<RestLocationbasedstock>(`${this.resourceUrl}/${this.getLocationbasedstockIdentifier(locationbasedstock)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(locationbasedstock: PartialUpdateLocationbasedstock): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(locationbasedstock);
    return this.http
      .patch<RestLocationbasedstock>(`${this.resourceUrl}/${this.getLocationbasedstockIdentifier(locationbasedstock)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestLocationbasedstock>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestLocationbasedstock[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getLocationbasedstockIdentifier(locationbasedstock: Pick<ILocationbasedstock, 'id'>): number {
    return locationbasedstock.id;
  }

  compareLocationbasedstock(o1: Pick<ILocationbasedstock, 'id'> | null, o2: Pick<ILocationbasedstock, 'id'> | null): boolean {
    return o1 && o2 ? this.getLocationbasedstockIdentifier(o1) === this.getLocationbasedstockIdentifier(o2) : o1 === o2;
  }

  addLocationbasedstockToCollectionIfMissing<Type extends Pick<ILocationbasedstock, 'id'>>(
    locationbasedstockCollection: Type[],
    ...locationbasedstocksToCheck: (Type | null | undefined)[]
  ): Type[] {
    const locationbasedstocks: Type[] = locationbasedstocksToCheck.filter(isPresent);
    if (locationbasedstocks.length > 0) {
      const locationbasedstockCollectionIdentifiers = locationbasedstockCollection.map(locationbasedstockItem =>
        this.getLocationbasedstockIdentifier(locationbasedstockItem),
      );
      const locationbasedstocksToAdd = locationbasedstocks.filter(locationbasedstockItem => {
        const locationbasedstockIdentifier = this.getLocationbasedstockIdentifier(locationbasedstockItem);
        if (locationbasedstockCollectionIdentifiers.includes(locationbasedstockIdentifier)) {
          return false;
        }
        locationbasedstockCollectionIdentifiers.push(locationbasedstockIdentifier);
        return true;
      });
      return [...locationbasedstocksToAdd, ...locationbasedstockCollection];
    }
    return locationbasedstockCollection;
  }

  protected convertDateFromClient<T extends ILocationbasedstock | NewLocationbasedstock | PartialUpdateLocationbasedstock>(
    locationbasedstock: T,
  ): RestOf<T> {
    return {
      ...locationbasedstock,
      lmd: locationbasedstock.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restLocationbasedstock: RestLocationbasedstock): ILocationbasedstock {
    return {
      ...restLocationbasedstock,
      lmd: restLocationbasedstock.lmd ? dayjs(restLocationbasedstock.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestLocationbasedstock>): HttpResponse<ILocationbasedstock> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestLocationbasedstock[]>): HttpResponse<ILocationbasedstock[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
