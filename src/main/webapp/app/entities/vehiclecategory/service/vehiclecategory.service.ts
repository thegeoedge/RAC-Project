import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IVehiclecategory, NewVehiclecategory } from '../vehiclecategory.model';

export type PartialUpdateVehiclecategory = Partial<IVehiclecategory> & Pick<IVehiclecategory, 'id'>;

type RestOf<T extends IVehiclecategory | NewVehiclecategory> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestVehiclecategory = RestOf<IVehiclecategory>;

export type NewRestVehiclecategory = RestOf<NewVehiclecategory>;

export type PartialUpdateRestVehiclecategory = RestOf<PartialUpdateVehiclecategory>;

export type EntityResponseType = HttpResponse<IVehiclecategory>;
export type EntityArrayResponseType = HttpResponse<IVehiclecategory[]>;

@Injectable({ providedIn: 'root' })
export class VehiclecategoryService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/vehiclecategories');

  create(vehiclecategory: NewVehiclecategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vehiclecategory);
    return this.http
      .post<RestVehiclecategory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(vehiclecategory: IVehiclecategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vehiclecategory);
    return this.http
      .put<RestVehiclecategory>(`${this.resourceUrl}/${this.getVehiclecategoryIdentifier(vehiclecategory)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(vehiclecategory: PartialUpdateVehiclecategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vehiclecategory);
    return this.http
      .patch<RestVehiclecategory>(`${this.resourceUrl}/${this.getVehiclecategoryIdentifier(vehiclecategory)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestVehiclecategory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestVehiclecategory[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getVehiclecategoryIdentifier(vehiclecategory: Pick<IVehiclecategory, 'id'>): number {
    return vehiclecategory.id;
  }

  compareVehiclecategory(o1: Pick<IVehiclecategory, 'id'> | null, o2: Pick<IVehiclecategory, 'id'> | null): boolean {
    return o1 && o2 ? this.getVehiclecategoryIdentifier(o1) === this.getVehiclecategoryIdentifier(o2) : o1 === o2;
  }

  addVehiclecategoryToCollectionIfMissing<Type extends Pick<IVehiclecategory, 'id'>>(
    vehiclecategoryCollection: Type[],
    ...vehiclecategoriesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const vehiclecategories: Type[] = vehiclecategoriesToCheck.filter(isPresent);
    if (vehiclecategories.length > 0) {
      const vehiclecategoryCollectionIdentifiers = vehiclecategoryCollection.map(vehiclecategoryItem =>
        this.getVehiclecategoryIdentifier(vehiclecategoryItem),
      );
      const vehiclecategoriesToAdd = vehiclecategories.filter(vehiclecategoryItem => {
        const vehiclecategoryIdentifier = this.getVehiclecategoryIdentifier(vehiclecategoryItem);
        if (vehiclecategoryCollectionIdentifiers.includes(vehiclecategoryIdentifier)) {
          return false;
        }
        vehiclecategoryCollectionIdentifiers.push(vehiclecategoryIdentifier);
        return true;
      });
      return [...vehiclecategoriesToAdd, ...vehiclecategoryCollection];
    }
    return vehiclecategoryCollection;
  }

  protected convertDateFromClient<T extends IVehiclecategory | NewVehiclecategory | PartialUpdateVehiclecategory>(
    vehiclecategory: T,
  ): RestOf<T> {
    return {
      ...vehiclecategory,
      lmd: vehiclecategory.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restVehiclecategory: RestVehiclecategory): IVehiclecategory {
    return {
      ...restVehiclecategory,
      lmd: restVehiclecategory.lmd ? dayjs(restVehiclecategory.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestVehiclecategory>): HttpResponse<IVehiclecategory> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestVehiclecategory[]>): HttpResponse<IVehiclecategory[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
