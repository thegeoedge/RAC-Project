import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IWorkshopvehiclework, NewWorkshopvehiclework } from '../workshopvehiclework.model';

export type PartialUpdateWorkshopvehiclework = Partial<IWorkshopvehiclework> & Pick<IWorkshopvehiclework, 'id'>;

type RestOf<T extends IWorkshopvehiclework | NewWorkshopvehiclework> = Omit<T, 'addeddate' | 'calldate' | 'lmd'> & {
  addeddate?: string | null;
  calldate?: string | null;
  lmd?: string | null;
};

export type RestWorkshopvehiclework = RestOf<IWorkshopvehiclework>;

export type NewRestWorkshopvehiclework = RestOf<NewWorkshopvehiclework>;

export type PartialUpdateRestWorkshopvehiclework = RestOf<PartialUpdateWorkshopvehiclework>;

export type EntityResponseType = HttpResponse<IWorkshopvehiclework>;
export type EntityArrayResponseType = HttpResponse<IWorkshopvehiclework[]>;

@Injectable({ providedIn: 'root' })
export class WorkshopvehicleworkService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/workshopvehicleworks');

  create(workshopvehiclework: NewWorkshopvehiclework): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(workshopvehiclework);
    return this.http
      .post<RestWorkshopvehiclework>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(workshopvehiclework: IWorkshopvehiclework): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(workshopvehiclework);
    return this.http
      .put<RestWorkshopvehiclework>(`${this.resourceUrl}/${this.getWorkshopvehicleworkIdentifier(workshopvehiclework)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(workshopvehiclework: PartialUpdateWorkshopvehiclework): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(workshopvehiclework);
    return this.http
      .patch<RestWorkshopvehiclework>(`${this.resourceUrl}/${this.getWorkshopvehicleworkIdentifier(workshopvehiclework)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestWorkshopvehiclework>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestWorkshopvehiclework[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getWorkshopvehicleworkIdentifier(workshopvehiclework: Pick<IWorkshopvehiclework, 'id'>): number {
    return workshopvehiclework.id;
  }

  compareWorkshopvehiclework(o1: Pick<IWorkshopvehiclework, 'id'> | null, o2: Pick<IWorkshopvehiclework, 'id'> | null): boolean {
    return o1 && o2 ? this.getWorkshopvehicleworkIdentifier(o1) === this.getWorkshopvehicleworkIdentifier(o2) : o1 === o2;
  }

  addWorkshopvehicleworkToCollectionIfMissing<Type extends Pick<IWorkshopvehiclework, 'id'>>(
    workshopvehicleworkCollection: Type[],
    ...workshopvehicleworksToCheck: (Type | null | undefined)[]
  ): Type[] {
    const workshopvehicleworks: Type[] = workshopvehicleworksToCheck.filter(isPresent);
    if (workshopvehicleworks.length > 0) {
      const workshopvehicleworkCollectionIdentifiers = workshopvehicleworkCollection.map(workshopvehicleworkItem =>
        this.getWorkshopvehicleworkIdentifier(workshopvehicleworkItem),
      );
      const workshopvehicleworksToAdd = workshopvehicleworks.filter(workshopvehicleworkItem => {
        const workshopvehicleworkIdentifier = this.getWorkshopvehicleworkIdentifier(workshopvehicleworkItem);
        if (workshopvehicleworkCollectionIdentifiers.includes(workshopvehicleworkIdentifier)) {
          return false;
        }
        workshopvehicleworkCollectionIdentifiers.push(workshopvehicleworkIdentifier);
        return true;
      });
      return [...workshopvehicleworksToAdd, ...workshopvehicleworkCollection];
    }
    return workshopvehicleworkCollection;
  }

  protected convertDateFromClient<T extends IWorkshopvehiclework | NewWorkshopvehiclework | PartialUpdateWorkshopvehiclework>(
    workshopvehiclework: T,
  ): RestOf<T> {
    return {
      ...workshopvehiclework,
      addeddate: workshopvehiclework.addeddate?.toJSON() ?? null,
      calldate: workshopvehiclework.calldate?.toJSON() ?? null,
      lmd: workshopvehiclework.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restWorkshopvehiclework: RestWorkshopvehiclework): IWorkshopvehiclework {
    return {
      ...restWorkshopvehiclework,
      addeddate: restWorkshopvehiclework.addeddate ? dayjs(restWorkshopvehiclework.addeddate) : undefined,
      calldate: restWorkshopvehiclework.calldate ? dayjs(restWorkshopvehiclework.calldate) : undefined,
      lmd: restWorkshopvehiclework.lmd ? dayjs(restWorkshopvehiclework.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestWorkshopvehiclework>): HttpResponse<IWorkshopvehiclework> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestWorkshopvehiclework[]>): HttpResponse<IWorkshopvehiclework[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
