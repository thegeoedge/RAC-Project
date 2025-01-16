import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { INextserviceinstructions, NewNextserviceinstructions } from '../nextserviceinstructions.model';

export type PartialUpdateNextserviceinstructions = Partial<INextserviceinstructions> & Pick<INextserviceinstructions, 'id'>;

type RestOf<T extends INextserviceinstructions | NewNextserviceinstructions> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestNextserviceinstructions = RestOf<INextserviceinstructions>;

export type NewRestNextserviceinstructions = RestOf<NewNextserviceinstructions>;

export type PartialUpdateRestNextserviceinstructions = RestOf<PartialUpdateNextserviceinstructions>;

export type EntityResponseType = HttpResponse<INextserviceinstructions>;
export type EntityArrayResponseType = HttpResponse<INextserviceinstructions[]>;

@Injectable({ providedIn: 'root' })
export class NextserviceinstructionsService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/nextserviceinstructions');

  create(nextserviceinstructions: NewNextserviceinstructions): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(nextserviceinstructions);
    return this.http
      .post<RestNextserviceinstructions>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(nextserviceinstructions: INextserviceinstructions): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(nextserviceinstructions);
    return this.http
      .put<RestNextserviceinstructions>(`${this.resourceUrl}/${this.getNextserviceinstructionsIdentifier(nextserviceinstructions)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(nextserviceinstructions: PartialUpdateNextserviceinstructions): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(nextserviceinstructions);
    return this.http
      .patch<RestNextserviceinstructions>(
        `${this.resourceUrl}/${this.getNextserviceinstructionsIdentifier(nextserviceinstructions)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestNextserviceinstructions>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestNextserviceinstructions[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getNextserviceinstructionsIdentifier(nextserviceinstructions: Pick<INextserviceinstructions, 'id'>): number {
    return nextserviceinstructions.id;
  }

  compareNextserviceinstructions(
    o1: Pick<INextserviceinstructions, 'id'> | null,
    o2: Pick<INextserviceinstructions, 'id'> | null,
  ): boolean {
    return o1 && o2 ? this.getNextserviceinstructionsIdentifier(o1) === this.getNextserviceinstructionsIdentifier(o2) : o1 === o2;
  }

  addNextserviceinstructionsToCollectionIfMissing<Type extends Pick<INextserviceinstructions, 'id'>>(
    nextserviceinstructionsCollection: Type[],
    ...nextserviceinstructionsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const nextserviceinstructions: Type[] = nextserviceinstructionsToCheck.filter(isPresent);
    if (nextserviceinstructions.length > 0) {
      const nextserviceinstructionsCollectionIdentifiers = nextserviceinstructionsCollection.map(nextserviceinstructionsItem =>
        this.getNextserviceinstructionsIdentifier(nextserviceinstructionsItem),
      );
      const nextserviceinstructionsToAdd = nextserviceinstructions.filter(nextserviceinstructionsItem => {
        const nextserviceinstructionsIdentifier = this.getNextserviceinstructionsIdentifier(nextserviceinstructionsItem);
        if (nextserviceinstructionsCollectionIdentifiers.includes(nextserviceinstructionsIdentifier)) {
          return false;
        }
        nextserviceinstructionsCollectionIdentifiers.push(nextserviceinstructionsIdentifier);
        return true;
      });
      return [...nextserviceinstructionsToAdd, ...nextserviceinstructionsCollection];
    }
    return nextserviceinstructionsCollection;
  }

  protected convertDateFromClient<T extends INextserviceinstructions | NewNextserviceinstructions | PartialUpdateNextserviceinstructions>(
    nextserviceinstructions: T,
  ): RestOf<T> {
    return {
      ...nextserviceinstructions,
      lmd: nextserviceinstructions.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restNextserviceinstructions: RestNextserviceinstructions): INextserviceinstructions {
    return {
      ...restNextserviceinstructions,
      lmd: restNextserviceinstructions.lmd ? dayjs(restNextserviceinstructions.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestNextserviceinstructions>): HttpResponse<INextserviceinstructions> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestNextserviceinstructions[]>): HttpResponse<INextserviceinstructions[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
