import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAutojobempallocation, NewAutojobempallocation } from '../autojobempallocation.model';

export type PartialUpdateAutojobempallocation = Partial<IAutojobempallocation> & Pick<IAutojobempallocation, 'id'>;

type RestOf<T extends IAutojobempallocation | NewAutojobempallocation> = Omit<
  T,
  'allocatetime' | 'lmd' | 'jobdate' | 'starttime' | 'endtime'
> & {
  allocatetime?: string | null;
  lmd?: string | null;
  jobdate?: string | null;
  starttime?: string | null;
  endtime?: string | null;
};

export type RestAutojobempallocation = RestOf<IAutojobempallocation>;

export type NewRestAutojobempallocation = RestOf<NewAutojobempallocation>;

export type PartialUpdateRestAutojobempallocation = RestOf<PartialUpdateAutojobempallocation>;

export type EntityResponseType = HttpResponse<IAutojobempallocation>;
export type EntityArrayResponseType = HttpResponse<IAutojobempallocation[]>;

@Injectable({ providedIn: 'root' })
export class AutojobempallocationService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/autojobempallocations');

  create(autojobempallocation: NewAutojobempallocation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autojobempallocation);
    return this.http
      .post<RestAutojobempallocation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(autojobempallocation: IAutojobempallocation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autojobempallocation);
    return this.http
      .put<RestAutojobempallocation>(`${this.resourceUrl}/${this.getAutojobempallocationIdentifier(autojobempallocation)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(autojobempallocation: PartialUpdateAutojobempallocation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autojobempallocation);
    return this.http
      .patch<RestAutojobempallocation>(`${this.resourceUrl}/${this.getAutojobempallocationIdentifier(autojobempallocation)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestAutojobempallocation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAutojobempallocation[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAutojobempallocationIdentifier(autojobempallocation: Pick<IAutojobempallocation, 'id'>): number {
    return autojobempallocation.id;
  }

  compareAutojobempallocation(o1: Pick<IAutojobempallocation, 'id'> | null, o2: Pick<IAutojobempallocation, 'id'> | null): boolean {
    return o1 && o2 ? this.getAutojobempallocationIdentifier(o1) === this.getAutojobempallocationIdentifier(o2) : o1 === o2;
  }

  addAutojobempallocationToCollectionIfMissing<Type extends Pick<IAutojobempallocation, 'id'>>(
    autojobempallocationCollection: Type[],
    ...autojobempallocationsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const autojobempallocations: Type[] = autojobempallocationsToCheck.filter(isPresent);
    if (autojobempallocations.length > 0) {
      const autojobempallocationCollectionIdentifiers = autojobempallocationCollection.map(autojobempallocationItem =>
        this.getAutojobempallocationIdentifier(autojobempallocationItem),
      );
      const autojobempallocationsToAdd = autojobempallocations.filter(autojobempallocationItem => {
        const autojobempallocationIdentifier = this.getAutojobempallocationIdentifier(autojobempallocationItem);
        if (autojobempallocationCollectionIdentifiers.includes(autojobempallocationIdentifier)) {
          return false;
        }
        autojobempallocationCollectionIdentifiers.push(autojobempallocationIdentifier);
        return true;
      });
      return [...autojobempallocationsToAdd, ...autojobempallocationCollection];
    }
    return autojobempallocationCollection;
  }

  protected convertDateFromClient<T extends IAutojobempallocation | NewAutojobempallocation | PartialUpdateAutojobempallocation>(
    autojobempallocation: T,
  ): RestOf<T> {
    return {
      ...autojobempallocation,
      allocatetime: autojobempallocation.allocatetime?.toJSON() ?? null,
      lmd: autojobempallocation.lmd?.toJSON() ?? null,
      jobdate: autojobempallocation.jobdate?.toJSON() ?? null,
      starttime: autojobempallocation.starttime?.toJSON() ?? null,
      endtime: autojobempallocation.endtime?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restAutojobempallocation: RestAutojobempallocation): IAutojobempallocation {
    return {
      ...restAutojobempallocation,
      allocatetime: restAutojobempallocation.allocatetime ? dayjs(restAutojobempallocation.allocatetime) : undefined,
      lmd: restAutojobempallocation.lmd ? dayjs(restAutojobempallocation.lmd) : undefined,
      jobdate: restAutojobempallocation.jobdate ? dayjs(restAutojobempallocation.jobdate) : undefined,
      starttime: restAutojobempallocation.starttime ? dayjs(restAutojobempallocation.starttime) : undefined,
      endtime: restAutojobempallocation.endtime ? dayjs(restAutojobempallocation.endtime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAutojobempallocation>): HttpResponse<IAutojobempallocation> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAutojobempallocation[]>): HttpResponse<IAutojobempallocation[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
