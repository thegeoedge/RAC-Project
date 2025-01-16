import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IServicecategory, NewServicecategory } from '../servicecategory.model';

export type PartialUpdateServicecategory = Partial<IServicecategory> & Pick<IServicecategory, 'id'>;

type RestOf<T extends IServicecategory | NewServicecategory> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestServicecategory = RestOf<IServicecategory>;

export type NewRestServicecategory = RestOf<NewServicecategory>;

export type PartialUpdateRestServicecategory = RestOf<PartialUpdateServicecategory>;

export type EntityResponseType = HttpResponse<IServicecategory>;
export type EntityArrayResponseType = HttpResponse<IServicecategory[]>;

@Injectable({ providedIn: 'root' })
export class ServicecategoryService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/servicecategories');

  create(servicecategory: NewServicecategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(servicecategory);
    return this.http
      .post<RestServicecategory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(servicecategory: IServicecategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(servicecategory);
    return this.http
      .put<RestServicecategory>(`${this.resourceUrl}/${this.getServicecategoryIdentifier(servicecategory)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(servicecategory: PartialUpdateServicecategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(servicecategory);
    return this.http
      .patch<RestServicecategory>(`${this.resourceUrl}/${this.getServicecategoryIdentifier(servicecategory)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestServicecategory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestServicecategory[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getServicecategoryIdentifier(servicecategory: Pick<IServicecategory, 'id'>): number {
    return servicecategory.id;
  }

  compareServicecategory(o1: Pick<IServicecategory, 'id'> | null, o2: Pick<IServicecategory, 'id'> | null): boolean {
    return o1 && o2 ? this.getServicecategoryIdentifier(o1) === this.getServicecategoryIdentifier(o2) : o1 === o2;
  }

  addServicecategoryToCollectionIfMissing<Type extends Pick<IServicecategory, 'id'>>(
    servicecategoryCollection: Type[],
    ...servicecategoriesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const servicecategories: Type[] = servicecategoriesToCheck.filter(isPresent);
    if (servicecategories.length > 0) {
      const servicecategoryCollectionIdentifiers = servicecategoryCollection.map(servicecategoryItem =>
        this.getServicecategoryIdentifier(servicecategoryItem),
      );
      const servicecategoriesToAdd = servicecategories.filter(servicecategoryItem => {
        const servicecategoryIdentifier = this.getServicecategoryIdentifier(servicecategoryItem);
        if (servicecategoryCollectionIdentifiers.includes(servicecategoryIdentifier)) {
          return false;
        }
        servicecategoryCollectionIdentifiers.push(servicecategoryIdentifier);
        return true;
      });
      return [...servicecategoriesToAdd, ...servicecategoryCollection];
    }
    return servicecategoryCollection;
  }

  protected convertDateFromClient<T extends IServicecategory | NewServicecategory | PartialUpdateServicecategory>(
    servicecategory: T,
  ): RestOf<T> {
    return {
      ...servicecategory,
      lmd: servicecategory.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restServicecategory: RestServicecategory): IServicecategory {
    return {
      ...restServicecategory,
      lmd: restServicecategory.lmd ? dayjs(restServicecategory.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestServicecategory>): HttpResponse<IServicecategory> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestServicecategory[]>): HttpResponse<IServicecategory[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
