import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IVehiclebrandname, NewVehiclebrandname } from '../vehiclebrandname.model';

export type PartialUpdateVehiclebrandname = Partial<IVehiclebrandname> & Pick<IVehiclebrandname, 'id'>;

type RestOf<T extends IVehiclebrandname | NewVehiclebrandname> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestVehiclebrandname = RestOf<IVehiclebrandname>;

export type NewRestVehiclebrandname = RestOf<NewVehiclebrandname>;

export type PartialUpdateRestVehiclebrandname = RestOf<PartialUpdateVehiclebrandname>;

export type EntityResponseType = HttpResponse<IVehiclebrandname>;
export type EntityArrayResponseType = HttpResponse<IVehiclebrandname[]>;

@Injectable({ providedIn: 'root' })
export class VehiclebrandnameService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/vehiclebrandnames');

  create(vehiclebrandname: NewVehiclebrandname): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vehiclebrandname);
    return this.http
      .post<RestVehiclebrandname>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(vehiclebrandname: IVehiclebrandname): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vehiclebrandname);
    return this.http
      .put<RestVehiclebrandname>(`${this.resourceUrl}/${this.getVehiclebrandnameIdentifier(vehiclebrandname)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(vehiclebrandname: PartialUpdateVehiclebrandname): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vehiclebrandname);
    return this.http
      .patch<RestVehiclebrandname>(`${this.resourceUrl}/${this.getVehiclebrandnameIdentifier(vehiclebrandname)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestVehiclebrandname>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestVehiclebrandname[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getVehiclebrandnameIdentifier(vehiclebrandname: Pick<IVehiclebrandname, 'id'>): number {
    return vehiclebrandname.id;
  }

  compareVehiclebrandname(o1: Pick<IVehiclebrandname, 'id'> | null, o2: Pick<IVehiclebrandname, 'id'> | null): boolean {
    return o1 && o2 ? this.getVehiclebrandnameIdentifier(o1) === this.getVehiclebrandnameIdentifier(o2) : o1 === o2;
  }

  addVehiclebrandnameToCollectionIfMissing<Type extends Pick<IVehiclebrandname, 'id'>>(
    vehiclebrandnameCollection: Type[],
    ...vehiclebrandnamesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const vehiclebrandnames: Type[] = vehiclebrandnamesToCheck.filter(isPresent);
    if (vehiclebrandnames.length > 0) {
      const vehiclebrandnameCollectionIdentifiers = vehiclebrandnameCollection.map(vehiclebrandnameItem =>
        this.getVehiclebrandnameIdentifier(vehiclebrandnameItem),
      );
      const vehiclebrandnamesToAdd = vehiclebrandnames.filter(vehiclebrandnameItem => {
        const vehiclebrandnameIdentifier = this.getVehiclebrandnameIdentifier(vehiclebrandnameItem);
        if (vehiclebrandnameCollectionIdentifiers.includes(vehiclebrandnameIdentifier)) {
          return false;
        }
        vehiclebrandnameCollectionIdentifiers.push(vehiclebrandnameIdentifier);
        return true;
      });
      return [...vehiclebrandnamesToAdd, ...vehiclebrandnameCollection];
    }
    return vehiclebrandnameCollection;
  }

  protected convertDateFromClient<T extends IVehiclebrandname | NewVehiclebrandname | PartialUpdateVehiclebrandname>(
    vehiclebrandname: T,
  ): RestOf<T> {
    return {
      ...vehiclebrandname,
      lmd: vehiclebrandname.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restVehiclebrandname: RestVehiclebrandname): IVehiclebrandname {
    return {
      ...restVehiclebrandname,
      lmd: restVehiclebrandname.lmd ? dayjs(restVehiclebrandname.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestVehiclebrandname>): HttpResponse<IVehiclebrandname> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestVehiclebrandname[]>): HttpResponse<IVehiclebrandname[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
