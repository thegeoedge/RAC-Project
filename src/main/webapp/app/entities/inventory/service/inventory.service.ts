import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IInventory, NewInventory } from '../inventory.model';

export type PartialUpdateInventory = Partial<IInventory> & Pick<IInventory, 'id'>;

type RestOf<T extends IInventory | NewInventory> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestInventory = RestOf<IInventory>;

export type NewRestInventory = RestOf<NewInventory>;

export type PartialUpdateRestInventory = RestOf<PartialUpdateInventory>;

export type EntityResponseType = HttpResponse<IInventory>;
export type EntityArrayResponseType = HttpResponse<IInventory[]>;

@Injectable({ providedIn: 'root' })
export class InventoryService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/inventories');

  create(inventory: NewInventory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(inventory);
    return this.http
      .post<RestInventory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(inventory: IInventory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(inventory);
    return this.http
      .put<RestInventory>(`${this.resourceUrl}/${this.getInventoryIdentifier(inventory)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(inventory: PartialUpdateInventory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(inventory);
    return this.http
      .patch<RestInventory>(`${this.resourceUrl}/${this.getInventoryIdentifier(inventory)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestInventory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestInventory[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getInventoryIdentifier(inventory: Pick<IInventory, 'id'>): number {
    return inventory.id;
  }

  compareInventory(o1: Pick<IInventory, 'id'> | null, o2: Pick<IInventory, 'id'> | null): boolean {
    return o1 && o2 ? this.getInventoryIdentifier(o1) === this.getInventoryIdentifier(o2) : o1 === o2;
  }

  addInventoryToCollectionIfMissing<Type extends Pick<IInventory, 'id'>>(
    inventoryCollection: Type[],
    ...inventoriesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const inventories: Type[] = inventoriesToCheck.filter(isPresent);
    if (inventories.length > 0) {
      const inventoryCollectionIdentifiers = inventoryCollection.map(inventoryItem => this.getInventoryIdentifier(inventoryItem));
      const inventoriesToAdd = inventories.filter(inventoryItem => {
        const inventoryIdentifier = this.getInventoryIdentifier(inventoryItem);
        if (inventoryCollectionIdentifiers.includes(inventoryIdentifier)) {
          return false;
        }
        inventoryCollectionIdentifiers.push(inventoryIdentifier);
        return true;
      });
      return [...inventoriesToAdd, ...inventoryCollection];
    }
    return inventoryCollection;
  }

  protected convertDateFromClient<T extends IInventory | NewInventory | PartialUpdateInventory>(inventory: T): RestOf<T> {
    return {
      ...inventory,
      lmd: inventory.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restInventory: RestInventory): IInventory {
    return {
      ...restInventory,
      lmd: restInventory.lmd ? dayjs(restInventory.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestInventory>): HttpResponse<IInventory> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestInventory[]>): HttpResponse<IInventory[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
