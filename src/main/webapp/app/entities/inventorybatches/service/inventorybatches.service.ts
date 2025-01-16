import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IInventorybatches, NewInventorybatches } from '../inventorybatches.model';

export type PartialUpdateInventorybatches = Partial<IInventorybatches> & Pick<IInventorybatches, 'id'>;

type RestOf<T extends IInventorybatches | NewInventorybatches> = Omit<
  T,
  'txdate' | 'lmd' | 'manufacturedate' | 'expiredate' | 'addeddate'
> & {
  txdate?: string | null;
  lmd?: string | null;
  manufacturedate?: string | null;
  expiredate?: string | null;
  addeddate?: string | null;
};

export type RestInventorybatches = RestOf<IInventorybatches>;

export type NewRestInventorybatches = RestOf<NewInventorybatches>;

export type PartialUpdateRestInventorybatches = RestOf<PartialUpdateInventorybatches>;

export type EntityResponseType = HttpResponse<IInventorybatches>;
export type EntityArrayResponseType = HttpResponse<IInventorybatches[]>;

@Injectable({ providedIn: 'root' })
export class InventorybatchesService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/inventorybatches');

  create(inventorybatches: NewInventorybatches): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(inventorybatches);
    return this.http
      .post<RestInventorybatches>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(inventorybatches: IInventorybatches): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(inventorybatches);
    return this.http
      .put<RestInventorybatches>(`${this.resourceUrl}/${this.getInventorybatchesIdentifier(inventorybatches)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(inventorybatches: PartialUpdateInventorybatches): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(inventorybatches);
    return this.http
      .patch<RestInventorybatches>(`${this.resourceUrl}/${this.getInventorybatchesIdentifier(inventorybatches)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestInventorybatches>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestInventorybatches[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getInventorybatchesIdentifier(inventorybatches: Pick<IInventorybatches, 'id'>): number {
    return inventorybatches.id;
  }

  compareInventorybatches(o1: Pick<IInventorybatches, 'id'> | null, o2: Pick<IInventorybatches, 'id'> | null): boolean {
    return o1 && o2 ? this.getInventorybatchesIdentifier(o1) === this.getInventorybatchesIdentifier(o2) : o1 === o2;
  }

  addInventorybatchesToCollectionIfMissing<Type extends Pick<IInventorybatches, 'id'>>(
    inventorybatchesCollection: Type[],
    ...inventorybatchesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const inventorybatches: Type[] = inventorybatchesToCheck.filter(isPresent);
    if (inventorybatches.length > 0) {
      const inventorybatchesCollectionIdentifiers = inventorybatchesCollection.map(inventorybatchesItem =>
        this.getInventorybatchesIdentifier(inventorybatchesItem),
      );
      const inventorybatchesToAdd = inventorybatches.filter(inventorybatchesItem => {
        const inventorybatchesIdentifier = this.getInventorybatchesIdentifier(inventorybatchesItem);
        if (inventorybatchesCollectionIdentifiers.includes(inventorybatchesIdentifier)) {
          return false;
        }
        inventorybatchesCollectionIdentifiers.push(inventorybatchesIdentifier);
        return true;
      });
      return [...inventorybatchesToAdd, ...inventorybatchesCollection];
    }
    return inventorybatchesCollection;
  }

  protected convertDateFromClient<T extends IInventorybatches | NewInventorybatches | PartialUpdateInventorybatches>(
    inventorybatches: T,
  ): RestOf<T> {
    return {
      ...inventorybatches,
      txdate: inventorybatches.txdate?.toJSON() ?? null,
      lmd: inventorybatches.lmd?.toJSON() ?? null,
      manufacturedate: inventorybatches.manufacturedate?.toJSON() ?? null,
      expiredate: inventorybatches.expiredate?.toJSON() ?? null,
      addeddate: inventorybatches.addeddate?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restInventorybatches: RestInventorybatches): IInventorybatches {
    return {
      ...restInventorybatches,
      txdate: restInventorybatches.txdate ? dayjs(restInventorybatches.txdate) : undefined,
      lmd: restInventorybatches.lmd ? dayjs(restInventorybatches.lmd) : undefined,
      manufacturedate: restInventorybatches.manufacturedate ? dayjs(restInventorybatches.manufacturedate) : undefined,
      expiredate: restInventorybatches.expiredate ? dayjs(restInventorybatches.expiredate) : undefined,
      addeddate: restInventorybatches.addeddate ? dayjs(restInventorybatches.addeddate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestInventorybatches>): HttpResponse<IInventorybatches> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestInventorybatches[]>): HttpResponse<IInventorybatches[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
