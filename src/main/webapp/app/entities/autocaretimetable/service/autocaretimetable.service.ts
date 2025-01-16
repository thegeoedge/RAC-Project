import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAutocaretimetable, NewAutocaretimetable } from '../autocaretimetable.model';

export type PartialUpdateAutocaretimetable = Partial<IAutocaretimetable> & Pick<IAutocaretimetable, 'id'>;

type RestOf<T extends IAutocaretimetable | NewAutocaretimetable> = Omit<T, 'hoisttime'> & {
  hoisttime?: string | null;
};

export type RestAutocaretimetable = RestOf<IAutocaretimetable>;

export type NewRestAutocaretimetable = RestOf<NewAutocaretimetable>;

export type PartialUpdateRestAutocaretimetable = RestOf<PartialUpdateAutocaretimetable>;

export type EntityResponseType = HttpResponse<IAutocaretimetable>;
export type EntityArrayResponseType = HttpResponse<IAutocaretimetable[]>;

@Injectable({ providedIn: 'root' })
export class AutocaretimetableService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/autocaretimetables');

  create(autocaretimetable: NewAutocaretimetable): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocaretimetable);
    return this.http
      .post<RestAutocaretimetable>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(autocaretimetable: IAutocaretimetable): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocaretimetable);
    return this.http
      .put<RestAutocaretimetable>(`${this.resourceUrl}/${this.getAutocaretimetableIdentifier(autocaretimetable)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(autocaretimetable: PartialUpdateAutocaretimetable): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocaretimetable);
    return this.http
      .patch<RestAutocaretimetable>(`${this.resourceUrl}/${this.getAutocaretimetableIdentifier(autocaretimetable)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestAutocaretimetable>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAutocaretimetable[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAutocaretimetableIdentifier(autocaretimetable: Pick<IAutocaretimetable, 'id'>): number {
    return autocaretimetable.id;
  }

  compareAutocaretimetable(o1: Pick<IAutocaretimetable, 'id'> | null, o2: Pick<IAutocaretimetable, 'id'> | null): boolean {
    return o1 && o2 ? this.getAutocaretimetableIdentifier(o1) === this.getAutocaretimetableIdentifier(o2) : o1 === o2;
  }

  addAutocaretimetableToCollectionIfMissing<Type extends Pick<IAutocaretimetable, 'id'>>(
    autocaretimetableCollection: Type[],
    ...autocaretimetablesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const autocaretimetables: Type[] = autocaretimetablesToCheck.filter(isPresent);
    if (autocaretimetables.length > 0) {
      const autocaretimetableCollectionIdentifiers = autocaretimetableCollection.map(autocaretimetableItem =>
        this.getAutocaretimetableIdentifier(autocaretimetableItem),
      );
      const autocaretimetablesToAdd = autocaretimetables.filter(autocaretimetableItem => {
        const autocaretimetableIdentifier = this.getAutocaretimetableIdentifier(autocaretimetableItem);
        if (autocaretimetableCollectionIdentifiers.includes(autocaretimetableIdentifier)) {
          return false;
        }
        autocaretimetableCollectionIdentifiers.push(autocaretimetableIdentifier);
        return true;
      });
      return [...autocaretimetablesToAdd, ...autocaretimetableCollection];
    }
    return autocaretimetableCollection;
  }

  protected convertDateFromClient<T extends IAutocaretimetable | NewAutocaretimetable | PartialUpdateAutocaretimetable>(
    autocaretimetable: T,
  ): RestOf<T> {
    return {
      ...autocaretimetable,
      hoisttime: autocaretimetable.hoisttime?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restAutocaretimetable: RestAutocaretimetable): IAutocaretimetable {
    return {
      ...restAutocaretimetable,
      hoisttime: restAutocaretimetable.hoisttime ? dayjs(restAutocaretimetable.hoisttime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAutocaretimetable>): HttpResponse<IAutocaretimetable> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAutocaretimetable[]>): HttpResponse<IAutocaretimetable[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
