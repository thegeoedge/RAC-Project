import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAutocarejobcategory, NewAutocarejobcategory } from '../autocarejobcategory.model';

export type PartialUpdateAutocarejobcategory = Partial<IAutocarejobcategory> & Pick<IAutocarejobcategory, 'id'>;

type RestOf<T extends IAutocarejobcategory | NewAutocarejobcategory> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestAutocarejobcategory = RestOf<IAutocarejobcategory>;

export type NewRestAutocarejobcategory = RestOf<NewAutocarejobcategory>;

export type PartialUpdateRestAutocarejobcategory = RestOf<PartialUpdateAutocarejobcategory>;

export type EntityResponseType = HttpResponse<IAutocarejobcategory>;
export type EntityArrayResponseType = HttpResponse<IAutocarejobcategory[]>;

@Injectable({ providedIn: 'root' })
export class AutocarejobcategoryService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/autocarejobcategories');

  create(autocarejobcategory: NewAutocarejobcategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocarejobcategory);
    return this.http
      .post<RestAutocarejobcategory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(autocarejobcategory: IAutocarejobcategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocarejobcategory);
    return this.http
      .put<RestAutocarejobcategory>(`${this.resourceUrl}/${this.getAutocarejobcategoryIdentifier(autocarejobcategory)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(autocarejobcategory: PartialUpdateAutocarejobcategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocarejobcategory);
    return this.http
      .patch<RestAutocarejobcategory>(`${this.resourceUrl}/${this.getAutocarejobcategoryIdentifier(autocarejobcategory)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestAutocarejobcategory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAutocarejobcategory[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAutocarejobcategoryIdentifier(autocarejobcategory: Pick<IAutocarejobcategory, 'id'>): number {
    return autocarejobcategory.id;
  }

  compareAutocarejobcategory(o1: Pick<IAutocarejobcategory, 'id'> | null, o2: Pick<IAutocarejobcategory, 'id'> | null): boolean {
    return o1 && o2 ? this.getAutocarejobcategoryIdentifier(o1) === this.getAutocarejobcategoryIdentifier(o2) : o1 === o2;
  }

  addAutocarejobcategoryToCollectionIfMissing<Type extends Pick<IAutocarejobcategory, 'id'>>(
    autocarejobcategoryCollection: Type[],
    ...autocarejobcategoriesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const autocarejobcategories: Type[] = autocarejobcategoriesToCheck.filter(isPresent);
    if (autocarejobcategories.length > 0) {
      const autocarejobcategoryCollectionIdentifiers = autocarejobcategoryCollection.map(autocarejobcategoryItem =>
        this.getAutocarejobcategoryIdentifier(autocarejobcategoryItem),
      );
      const autocarejobcategoriesToAdd = autocarejobcategories.filter(autocarejobcategoryItem => {
        const autocarejobcategoryIdentifier = this.getAutocarejobcategoryIdentifier(autocarejobcategoryItem);
        if (autocarejobcategoryCollectionIdentifiers.includes(autocarejobcategoryIdentifier)) {
          return false;
        }
        autocarejobcategoryCollectionIdentifiers.push(autocarejobcategoryIdentifier);
        return true;
      });
      return [...autocarejobcategoriesToAdd, ...autocarejobcategoryCollection];
    }
    return autocarejobcategoryCollection;
  }

  protected convertDateFromClient<T extends IAutocarejobcategory | NewAutocarejobcategory | PartialUpdateAutocarejobcategory>(
    autocarejobcategory: T,
  ): RestOf<T> {
    return {
      ...autocarejobcategory,
      lmd: autocarejobcategory.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restAutocarejobcategory: RestAutocarejobcategory): IAutocarejobcategory {
    return {
      ...restAutocarejobcategory,
      lmd: restAutocarejobcategory.lmd ? dayjs(restAutocarejobcategory.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAutocarejobcategory>): HttpResponse<IAutocarejobcategory> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAutocarejobcategory[]>): HttpResponse<IAutocarejobcategory[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
