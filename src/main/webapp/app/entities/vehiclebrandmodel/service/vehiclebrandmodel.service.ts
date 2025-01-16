import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IVehiclebrandmodel, NewVehiclebrandmodel } from '../vehiclebrandmodel.model';

export type PartialUpdateVehiclebrandmodel = Partial<IVehiclebrandmodel> & Pick<IVehiclebrandmodel, 'id'>;

type RestOf<T extends IVehiclebrandmodel | NewVehiclebrandmodel> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestVehiclebrandmodel = RestOf<IVehiclebrandmodel>;

export type NewRestVehiclebrandmodel = RestOf<NewVehiclebrandmodel>;

export type PartialUpdateRestVehiclebrandmodel = RestOf<PartialUpdateVehiclebrandmodel>;

export type EntityResponseType = HttpResponse<IVehiclebrandmodel>;
export type EntityArrayResponseType = HttpResponse<IVehiclebrandmodel[]>;

@Injectable({ providedIn: 'root' })
export class VehiclebrandmodelService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/vehiclebrandmodels');

  create(vehiclebrandmodel: NewVehiclebrandmodel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vehiclebrandmodel);
    return this.http
      .post<RestVehiclebrandmodel>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(vehiclebrandmodel: IVehiclebrandmodel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vehiclebrandmodel);
    return this.http
      .put<RestVehiclebrandmodel>(`${this.resourceUrl}/${this.getVehiclebrandmodelIdentifier(vehiclebrandmodel)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(vehiclebrandmodel: PartialUpdateVehiclebrandmodel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vehiclebrandmodel);
    return this.http
      .patch<RestVehiclebrandmodel>(`${this.resourceUrl}/${this.getVehiclebrandmodelIdentifier(vehiclebrandmodel)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestVehiclebrandmodel>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestVehiclebrandmodel[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getVehiclebrandmodelIdentifier(vehiclebrandmodel: Pick<IVehiclebrandmodel, 'id'>): number {
    return vehiclebrandmodel.id;
  }

  compareVehiclebrandmodel(o1: Pick<IVehiclebrandmodel, 'id'> | null, o2: Pick<IVehiclebrandmodel, 'id'> | null): boolean {
    return o1 && o2 ? this.getVehiclebrandmodelIdentifier(o1) === this.getVehiclebrandmodelIdentifier(o2) : o1 === o2;
  }

  addVehiclebrandmodelToCollectionIfMissing<Type extends Pick<IVehiclebrandmodel, 'id'>>(
    vehiclebrandmodelCollection: Type[],
    ...vehiclebrandmodelsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const vehiclebrandmodels: Type[] = vehiclebrandmodelsToCheck.filter(isPresent);
    if (vehiclebrandmodels.length > 0) {
      const vehiclebrandmodelCollectionIdentifiers = vehiclebrandmodelCollection.map(vehiclebrandmodelItem =>
        this.getVehiclebrandmodelIdentifier(vehiclebrandmodelItem),
      );
      const vehiclebrandmodelsToAdd = vehiclebrandmodels.filter(vehiclebrandmodelItem => {
        const vehiclebrandmodelIdentifier = this.getVehiclebrandmodelIdentifier(vehiclebrandmodelItem);
        if (vehiclebrandmodelCollectionIdentifiers.includes(vehiclebrandmodelIdentifier)) {
          return false;
        }
        vehiclebrandmodelCollectionIdentifiers.push(vehiclebrandmodelIdentifier);
        return true;
      });
      return [...vehiclebrandmodelsToAdd, ...vehiclebrandmodelCollection];
    }
    return vehiclebrandmodelCollection;
  }

  protected convertDateFromClient<T extends IVehiclebrandmodel | NewVehiclebrandmodel | PartialUpdateVehiclebrandmodel>(
    vehiclebrandmodel: T,
  ): RestOf<T> {
    return {
      ...vehiclebrandmodel,
      lmd: vehiclebrandmodel.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restVehiclebrandmodel: RestVehiclebrandmodel): IVehiclebrandmodel {
    return {
      ...restVehiclebrandmodel,
      lmd: restVehiclebrandmodel.lmd ? dayjs(restVehiclebrandmodel.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestVehiclebrandmodel>): HttpResponse<IVehiclebrandmodel> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestVehiclebrandmodel[]>): HttpResponse<IVehiclebrandmodel[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
