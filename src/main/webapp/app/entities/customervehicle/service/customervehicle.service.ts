import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICustomervehicle, NewCustomervehicle } from '../customervehicle.model';

export type PartialUpdateCustomervehicle = Partial<ICustomervehicle> & Pick<ICustomervehicle, 'id'>;

type RestOf<T extends ICustomervehicle | NewCustomervehicle> = Omit<T, 'lastservicedate' | 'nextservicedate' | 'lmd'> & {
  lastservicedate?: string | null;
  nextservicedate?: string | null;
  lmd?: string | null;
};

export type RestCustomervehicle = RestOf<ICustomervehicle>;

export type NewRestCustomervehicle = RestOf<NewCustomervehicle>;

export type PartialUpdateRestCustomervehicle = RestOf<PartialUpdateCustomervehicle>;

export type EntityResponseType = HttpResponse<ICustomervehicle>;
export type EntityArrayResponseType = HttpResponse<ICustomervehicle[]>;

@Injectable({ providedIn: 'root' })
export class CustomervehicleService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/customervehicles');

  create(customervehicle: NewCustomervehicle): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(customervehicle);
    return this.http
      .post<RestCustomervehicle>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(customervehicle: ICustomervehicle): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(customervehicle);
    return this.http
      .put<RestCustomervehicle>(`${this.resourceUrl}/${this.getCustomervehicleIdentifier(customervehicle)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(customervehicle: PartialUpdateCustomervehicle): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(customervehicle);
    return this.http
      .patch<RestCustomervehicle>(`${this.resourceUrl}/${this.getCustomervehicleIdentifier(customervehicle)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestCustomervehicle>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  findByVehicleNumber(vehiclenumber: string): Observable<EntityArrayResponseType> {
    const options = createRequestOption({ 'vehiclenumber.contains': vehiclenumber });
    return this.http
      .get<RestCustomervehicle[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestCustomervehicle[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCustomervehicleIdentifier(customervehicle: Pick<ICustomervehicle, 'id'>): number {
    return customervehicle.id;
  }

  compareCustomervehicle(o1: Pick<ICustomervehicle, 'id'> | null, o2: Pick<ICustomervehicle, 'id'> | null): boolean {
    return o1 && o2 ? this.getCustomervehicleIdentifier(o1) === this.getCustomervehicleIdentifier(o2) : o1 === o2;
  }

  addCustomervehicleToCollectionIfMissing<Type extends Pick<ICustomervehicle, 'id'>>(
    customervehicleCollection: Type[],
    ...customervehiclesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const customervehicles: Type[] = customervehiclesToCheck.filter(isPresent);
    if (customervehicles.length > 0) {
      const customervehicleCollectionIdentifiers = customervehicleCollection.map(customervehicleItem =>
        this.getCustomervehicleIdentifier(customervehicleItem),
      );
      const customervehiclesToAdd = customervehicles.filter(customervehicleItem => {
        const customervehicleIdentifier = this.getCustomervehicleIdentifier(customervehicleItem);
        if (customervehicleCollectionIdentifiers.includes(customervehicleIdentifier)) {
          return false;
        }
        customervehicleCollectionIdentifiers.push(customervehicleIdentifier);
        return true;
      });
      return [...customervehiclesToAdd, ...customervehicleCollection];
    }
    return customervehicleCollection;
  }

  protected convertDateFromClient<T extends ICustomervehicle | NewCustomervehicle | PartialUpdateCustomervehicle>(
    customervehicle: T,
  ): RestOf<T> {
    return {
      ...customervehicle,
      lastservicedate: customervehicle.lastservicedate?.format(DATE_FORMAT) ?? null,
      nextservicedate: customervehicle.nextservicedate?.format(DATE_FORMAT) ?? null,
      lmd: customervehicle.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restCustomervehicle: RestCustomervehicle): ICustomervehicle {
    return {
      ...restCustomervehicle,
      lastservicedate: restCustomervehicle.lastservicedate ? dayjs(restCustomervehicle.lastservicedate) : undefined,
      nextservicedate: restCustomervehicle.nextservicedate ? dayjs(restCustomervehicle.nextservicedate) : undefined,
      lmd: restCustomervehicle.lmd ? dayjs(restCustomervehicle.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestCustomervehicle>): HttpResponse<ICustomervehicle> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestCustomervehicle[]>): HttpResponse<ICustomervehicle[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
