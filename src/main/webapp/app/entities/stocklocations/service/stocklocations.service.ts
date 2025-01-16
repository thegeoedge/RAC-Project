import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IStocklocations, NewStocklocations } from '../stocklocations.model';

export type PartialUpdateStocklocations = Partial<IStocklocations> & Pick<IStocklocations, 'id'>;

export type EntityResponseType = HttpResponse<IStocklocations>;
export type EntityArrayResponseType = HttpResponse<IStocklocations[]>;

@Injectable({ providedIn: 'root' })
export class StocklocationsService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/stocklocations');

  create(stocklocations: NewStocklocations): Observable<EntityResponseType> {
    return this.http.post<IStocklocations>(this.resourceUrl, stocklocations, { observe: 'response' });
  }

  update(stocklocations: IStocklocations): Observable<EntityResponseType> {
    return this.http.put<IStocklocations>(`${this.resourceUrl}/${this.getStocklocationsIdentifier(stocklocations)}`, stocklocations, {
      observe: 'response',
    });
  }

  partialUpdate(stocklocations: PartialUpdateStocklocations): Observable<EntityResponseType> {
    return this.http.patch<IStocklocations>(`${this.resourceUrl}/${this.getStocklocationsIdentifier(stocklocations)}`, stocklocations, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStocklocations>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStocklocations[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getStocklocationsIdentifier(stocklocations: Pick<IStocklocations, 'id'>): number {
    return stocklocations.id;
  }

  compareStocklocations(o1: Pick<IStocklocations, 'id'> | null, o2: Pick<IStocklocations, 'id'> | null): boolean {
    return o1 && o2 ? this.getStocklocationsIdentifier(o1) === this.getStocklocationsIdentifier(o2) : o1 === o2;
  }

  addStocklocationsToCollectionIfMissing<Type extends Pick<IStocklocations, 'id'>>(
    stocklocationsCollection: Type[],
    ...stocklocationsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const stocklocations: Type[] = stocklocationsToCheck.filter(isPresent);
    if (stocklocations.length > 0) {
      const stocklocationsCollectionIdentifiers = stocklocationsCollection.map(stocklocationsItem =>
        this.getStocklocationsIdentifier(stocklocationsItem),
      );
      const stocklocationsToAdd = stocklocations.filter(stocklocationsItem => {
        const stocklocationsIdentifier = this.getStocklocationsIdentifier(stocklocationsItem);
        if (stocklocationsCollectionIdentifiers.includes(stocklocationsIdentifier)) {
          return false;
        }
        stocklocationsCollectionIdentifiers.push(stocklocationsIdentifier);
        return true;
      });
      return [...stocklocationsToAdd, ...stocklocationsCollection];
    }
    return stocklocationsCollection;
  }
}
