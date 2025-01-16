import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ILastserviceinstructions, NewLastserviceinstructions } from '../lastserviceinstructions.model';

export type PartialUpdateLastserviceinstructions = Partial<ILastserviceinstructions> & Pick<ILastserviceinstructions, 'id'>;

type RestOf<T extends ILastserviceinstructions | NewLastserviceinstructions> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestLastserviceinstructions = RestOf<ILastserviceinstructions>;

export type NewRestLastserviceinstructions = RestOf<NewLastserviceinstructions>;

export type PartialUpdateRestLastserviceinstructions = RestOf<PartialUpdateLastserviceinstructions>;

export type EntityResponseType = HttpResponse<ILastserviceinstructions>;
export type EntityArrayResponseType = HttpResponse<ILastserviceinstructions[]>;

@Injectable({ providedIn: 'root' })
export class LastserviceinstructionsService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/lastserviceinstructions');

  create(lastserviceinstructions: NewLastserviceinstructions): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(lastserviceinstructions);
    return this.http
      .post<RestLastserviceinstructions>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(lastserviceinstructions: ILastserviceinstructions): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(lastserviceinstructions);
    return this.http
      .put<RestLastserviceinstructions>(`${this.resourceUrl}/${this.getLastserviceinstructionsIdentifier(lastserviceinstructions)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(lastserviceinstructions: PartialUpdateLastserviceinstructions): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(lastserviceinstructions);
    return this.http
      .patch<RestLastserviceinstructions>(
        `${this.resourceUrl}/${this.getLastserviceinstructionsIdentifier(lastserviceinstructions)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestLastserviceinstructions>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestLastserviceinstructions[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getLastserviceinstructionsIdentifier(lastserviceinstructions: Pick<ILastserviceinstructions, 'id'>): number {
    return lastserviceinstructions.id;
  }

  compareLastserviceinstructions(
    o1: Pick<ILastserviceinstructions, 'id'> | null,
    o2: Pick<ILastserviceinstructions, 'id'> | null,
  ): boolean {
    return o1 && o2 ? this.getLastserviceinstructionsIdentifier(o1) === this.getLastserviceinstructionsIdentifier(o2) : o1 === o2;
  }

  addLastserviceinstructionsToCollectionIfMissing<Type extends Pick<ILastserviceinstructions, 'id'>>(
    lastserviceinstructionsCollection: Type[],
    ...lastserviceinstructionsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const lastserviceinstructions: Type[] = lastserviceinstructionsToCheck.filter(isPresent);
    if (lastserviceinstructions.length > 0) {
      const lastserviceinstructionsCollectionIdentifiers = lastserviceinstructionsCollection.map(lastserviceinstructionsItem =>
        this.getLastserviceinstructionsIdentifier(lastserviceinstructionsItem),
      );
      const lastserviceinstructionsToAdd = lastserviceinstructions.filter(lastserviceinstructionsItem => {
        const lastserviceinstructionsIdentifier = this.getLastserviceinstructionsIdentifier(lastserviceinstructionsItem);
        if (lastserviceinstructionsCollectionIdentifiers.includes(lastserviceinstructionsIdentifier)) {
          return false;
        }
        lastserviceinstructionsCollectionIdentifiers.push(lastserviceinstructionsIdentifier);
        return true;
      });
      return [...lastserviceinstructionsToAdd, ...lastserviceinstructionsCollection];
    }
    return lastserviceinstructionsCollection;
  }

  protected convertDateFromClient<T extends ILastserviceinstructions | NewLastserviceinstructions | PartialUpdateLastserviceinstructions>(
    lastserviceinstructions: T,
  ): RestOf<T> {
    return {
      ...lastserviceinstructions,
      lmd: lastserviceinstructions.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restLastserviceinstructions: RestLastserviceinstructions): ILastserviceinstructions {
    return {
      ...restLastserviceinstructions,
      lmd: restLastserviceinstructions.lmd ? dayjs(restLastserviceinstructions.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestLastserviceinstructions>): HttpResponse<ILastserviceinstructions> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestLastserviceinstructions[]>): HttpResponse<ILastserviceinstructions[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
