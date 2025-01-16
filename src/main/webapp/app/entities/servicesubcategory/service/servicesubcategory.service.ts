import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IServicesubcategory, NewServicesubcategory } from '../servicesubcategory.model';

export type PartialUpdateServicesubcategory = Partial<IServicesubcategory> & Pick<IServicesubcategory, 'id'>;

type RestOf<T extends IServicesubcategory | NewServicesubcategory> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestServicesubcategory = RestOf<IServicesubcategory>;

export type NewRestServicesubcategory = RestOf<NewServicesubcategory>;

export type PartialUpdateRestServicesubcategory = RestOf<PartialUpdateServicesubcategory>;

export type EntityResponseType = HttpResponse<IServicesubcategory>;
export type EntityArrayResponseType = HttpResponse<IServicesubcategory[]>;

@Injectable({ providedIn: 'root' })
export class ServicesubcategoryService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/servicesubcategories');

  create(servicesubcategory: NewServicesubcategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(servicesubcategory);
    return this.http
      .post<RestServicesubcategory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(servicesubcategory: IServicesubcategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(servicesubcategory);
    return this.http
      .put<RestServicesubcategory>(`${this.resourceUrl}/${this.getServicesubcategoryIdentifier(servicesubcategory)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(servicesubcategory: PartialUpdateServicesubcategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(servicesubcategory);
    return this.http
      .patch<RestServicesubcategory>(`${this.resourceUrl}/${this.getServicesubcategoryIdentifier(servicesubcategory)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestServicesubcategory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestServicesubcategory[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getServicesubcategoryIdentifier(servicesubcategory: Pick<IServicesubcategory, 'id'>): number {
    return servicesubcategory.id;
  }

  compareServicesubcategory(o1: Pick<IServicesubcategory, 'id'> | null, o2: Pick<IServicesubcategory, 'id'> | null): boolean {
    return o1 && o2 ? this.getServicesubcategoryIdentifier(o1) === this.getServicesubcategoryIdentifier(o2) : o1 === o2;
  }

  addServicesubcategoryToCollectionIfMissing<Type extends Pick<IServicesubcategory, 'id'>>(
    servicesubcategoryCollection: Type[],
    ...servicesubcategoriesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const servicesubcategories: Type[] = servicesubcategoriesToCheck.filter(isPresent);
    if (servicesubcategories.length > 0) {
      const servicesubcategoryCollectionIdentifiers = servicesubcategoryCollection.map(servicesubcategoryItem =>
        this.getServicesubcategoryIdentifier(servicesubcategoryItem),
      );
      const servicesubcategoriesToAdd = servicesubcategories.filter(servicesubcategoryItem => {
        const servicesubcategoryIdentifier = this.getServicesubcategoryIdentifier(servicesubcategoryItem);
        if (servicesubcategoryCollectionIdentifiers.includes(servicesubcategoryIdentifier)) {
          return false;
        }
        servicesubcategoryCollectionIdentifiers.push(servicesubcategoryIdentifier);
        return true;
      });
      return [...servicesubcategoriesToAdd, ...servicesubcategoryCollection];
    }
    return servicesubcategoryCollection;
  }

  protected convertDateFromClient<T extends IServicesubcategory | NewServicesubcategory | PartialUpdateServicesubcategory>(
    servicesubcategory: T,
  ): RestOf<T> {
    return {
      ...servicesubcategory,
      lmd: servicesubcategory.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restServicesubcategory: RestServicesubcategory): IServicesubcategory {
    return {
      ...restServicesubcategory,
      lmd: restServicesubcategory.lmd ? dayjs(restServicesubcategory.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestServicesubcategory>): HttpResponse<IServicesubcategory> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestServicesubcategory[]>): HttpResponse<IServicesubcategory[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
