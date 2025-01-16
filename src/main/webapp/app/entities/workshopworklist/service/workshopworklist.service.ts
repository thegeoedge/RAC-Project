import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IWorkshopworklist, NewWorkshopworklist } from '../workshopworklist.model';

export type PartialUpdateWorkshopworklist = Partial<IWorkshopworklist> & Pick<IWorkshopworklist, 'id'>;

type RestOf<T extends IWorkshopworklist | NewWorkshopworklist> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestWorkshopworklist = RestOf<IWorkshopworklist>;

export type NewRestWorkshopworklist = RestOf<NewWorkshopworklist>;

export type PartialUpdateRestWorkshopworklist = RestOf<PartialUpdateWorkshopworklist>;

export type EntityResponseType = HttpResponse<IWorkshopworklist>;
export type EntityArrayResponseType = HttpResponse<IWorkshopworklist[]>;

@Injectable({ providedIn: 'root' })
export class WorkshopworklistService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/workshopworklists');

  create(workshopworklist: NewWorkshopworklist): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(workshopworklist);
    return this.http
      .post<RestWorkshopworklist>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(workshopworklist: IWorkshopworklist): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(workshopworklist);
    return this.http
      .put<RestWorkshopworklist>(`${this.resourceUrl}/${this.getWorkshopworklistIdentifier(workshopworklist)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(workshopworklist: PartialUpdateWorkshopworklist): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(workshopworklist);
    return this.http
      .patch<RestWorkshopworklist>(`${this.resourceUrl}/${this.getWorkshopworklistIdentifier(workshopworklist)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestWorkshopworklist>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestWorkshopworklist[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getWorkshopworklistIdentifier(workshopworklist: Pick<IWorkshopworklist, 'id'>): number {
    return workshopworklist.id;
  }

  compareWorkshopworklist(o1: Pick<IWorkshopworklist, 'id'> | null, o2: Pick<IWorkshopworklist, 'id'> | null): boolean {
    return o1 && o2 ? this.getWorkshopworklistIdentifier(o1) === this.getWorkshopworklistIdentifier(o2) : o1 === o2;
  }

  addWorkshopworklistToCollectionIfMissing<Type extends Pick<IWorkshopworklist, 'id'>>(
    workshopworklistCollection: Type[],
    ...workshopworklistsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const workshopworklists: Type[] = workshopworklistsToCheck.filter(isPresent);
    if (workshopworklists.length > 0) {
      const workshopworklistCollectionIdentifiers = workshopworklistCollection.map(workshopworklistItem =>
        this.getWorkshopworklistIdentifier(workshopworklistItem),
      );
      const workshopworklistsToAdd = workshopworklists.filter(workshopworklistItem => {
        const workshopworklistIdentifier = this.getWorkshopworklistIdentifier(workshopworklistItem);
        if (workshopworklistCollectionIdentifiers.includes(workshopworklistIdentifier)) {
          return false;
        }
        workshopworklistCollectionIdentifiers.push(workshopworklistIdentifier);
        return true;
      });
      return [...workshopworklistsToAdd, ...workshopworklistCollection];
    }
    return workshopworklistCollection;
  }

  protected convertDateFromClient<T extends IWorkshopworklist | NewWorkshopworklist | PartialUpdateWorkshopworklist>(
    workshopworklist: T,
  ): RestOf<T> {
    return {
      ...workshopworklist,
      lmd: workshopworklist.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restWorkshopworklist: RestWorkshopworklist): IWorkshopworklist {
    return {
      ...restWorkshopworklist,
      lmd: restWorkshopworklist.lmd ? dayjs(restWorkshopworklist.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestWorkshopworklist>): HttpResponse<IWorkshopworklist> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestWorkshopworklist[]>): HttpResponse<IWorkshopworklist[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
