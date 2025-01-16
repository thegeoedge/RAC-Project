import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IVehicletype, NewVehicletype } from '../vehicletype.model';

export type PartialUpdateVehicletype = Partial<IVehicletype> & Pick<IVehicletype, 'id'>;

type RestOf<T extends IVehicletype | NewVehicletype> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestVehicletype = RestOf<IVehicletype>;

export type NewRestVehicletype = RestOf<NewVehicletype>;

export type PartialUpdateRestVehicletype = RestOf<PartialUpdateVehicletype>;

export type EntityResponseType = HttpResponse<IVehicletype>;
export type EntityArrayResponseType = HttpResponse<IVehicletype[]>;

@Injectable({ providedIn: 'root' })
export class VehicletypeService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/vehicletypes');

  create(vehicletype: NewVehicletype): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vehicletype);
    return this.http
      .post<RestVehicletype>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(vehicletype: IVehicletype): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vehicletype);
    return this.http
      .put<RestVehicletype>(`${this.resourceUrl}/${this.getVehicletypeIdentifier(vehicletype)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(vehicletype: PartialUpdateVehicletype): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vehicletype);
    return this.http
      .patch<RestVehicletype>(`${this.resourceUrl}/${this.getVehicletypeIdentifier(vehicletype)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestVehicletype>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestVehicletype[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getVehicletypeIdentifier(vehicletype: Pick<IVehicletype, 'id'>): number {
    return vehicletype.id;
  }

  compareVehicletype(o1: Pick<IVehicletype, 'id'> | null, o2: Pick<IVehicletype, 'id'> | null): boolean {
    return o1 && o2 ? this.getVehicletypeIdentifier(o1) === this.getVehicletypeIdentifier(o2) : o1 === o2;
  }

  addVehicletypeToCollectionIfMissing<Type extends Pick<IVehicletype, 'id'>>(
    vehicletypeCollection: Type[],
    ...vehicletypesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const vehicletypes: Type[] = vehicletypesToCheck.filter(isPresent);
    if (vehicletypes.length > 0) {
      const vehicletypeCollectionIdentifiers = vehicletypeCollection.map(vehicletypeItem => this.getVehicletypeIdentifier(vehicletypeItem));
      const vehicletypesToAdd = vehicletypes.filter(vehicletypeItem => {
        const vehicletypeIdentifier = this.getVehicletypeIdentifier(vehicletypeItem);
        if (vehicletypeCollectionIdentifiers.includes(vehicletypeIdentifier)) {
          return false;
        }
        vehicletypeCollectionIdentifiers.push(vehicletypeIdentifier);
        return true;
      });
      return [...vehicletypesToAdd, ...vehicletypeCollection];
    }
    return vehicletypeCollection;
  }

  protected convertDateFromClient<T extends IVehicletype | NewVehicletype | PartialUpdateVehicletype>(vehicletype: T): RestOf<T> {
    return {
      ...vehicletype,
      lmd: vehicletype.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restVehicletype: RestVehicletype): IVehicletype {
    return {
      ...restVehicletype,
      lmd: restVehicletype.lmd ? dayjs(restVehicletype.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestVehicletype>): HttpResponse<IVehicletype> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestVehicletype[]>): HttpResponse<IVehicletype[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
