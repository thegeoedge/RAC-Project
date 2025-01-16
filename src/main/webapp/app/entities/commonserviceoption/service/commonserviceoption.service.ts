import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICommonserviceoption, NewCommonserviceoption } from '../commonserviceoption.model';

export type PartialUpdateCommonserviceoption = Partial<ICommonserviceoption> & Pick<ICommonserviceoption, 'id'>;

type RestOf<T extends ICommonserviceoption | NewCommonserviceoption> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestCommonserviceoption = RestOf<ICommonserviceoption>;

export type NewRestCommonserviceoption = RestOf<NewCommonserviceoption>;

export type PartialUpdateRestCommonserviceoption = RestOf<PartialUpdateCommonserviceoption>;

export type EntityResponseType = HttpResponse<ICommonserviceoption>;
export type EntityArrayResponseType = HttpResponse<ICommonserviceoption[]>;

@Injectable({ providedIn: 'root' })
export class CommonserviceoptionService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/commonserviceoptions');

  create(commonserviceoption: NewCommonserviceoption): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(commonserviceoption);
    return this.http
      .post<RestCommonserviceoption>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(commonserviceoption: ICommonserviceoption): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(commonserviceoption);
    return this.http
      .put<RestCommonserviceoption>(`${this.resourceUrl}/${this.getCommonserviceoptionIdentifier(commonserviceoption)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(commonserviceoption: PartialUpdateCommonserviceoption): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(commonserviceoption);
    return this.http
      .patch<RestCommonserviceoption>(`${this.resourceUrl}/${this.getCommonserviceoptionIdentifier(commonserviceoption)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestCommonserviceoption>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestCommonserviceoption[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCommonserviceoptionIdentifier(commonserviceoption: Pick<ICommonserviceoption, 'id'>): number {
    return commonserviceoption.id;
  }

  compareCommonserviceoption(o1: Pick<ICommonserviceoption, 'id'> | null, o2: Pick<ICommonserviceoption, 'id'> | null): boolean {
    return o1 && o2 ? this.getCommonserviceoptionIdentifier(o1) === this.getCommonserviceoptionIdentifier(o2) : o1 === o2;
  }

  addCommonserviceoptionToCollectionIfMissing<Type extends Pick<ICommonserviceoption, 'id'>>(
    commonserviceoptionCollection: Type[],
    ...commonserviceoptionsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const commonserviceoptions: Type[] = commonserviceoptionsToCheck.filter(isPresent);
    if (commonserviceoptions.length > 0) {
      const commonserviceoptionCollectionIdentifiers = commonserviceoptionCollection.map(commonserviceoptionItem =>
        this.getCommonserviceoptionIdentifier(commonserviceoptionItem),
      );
      const commonserviceoptionsToAdd = commonserviceoptions.filter(commonserviceoptionItem => {
        const commonserviceoptionIdentifier = this.getCommonserviceoptionIdentifier(commonserviceoptionItem);
        if (commonserviceoptionCollectionIdentifiers.includes(commonserviceoptionIdentifier)) {
          return false;
        }
        commonserviceoptionCollectionIdentifiers.push(commonserviceoptionIdentifier);
        return true;
      });
      return [...commonserviceoptionsToAdd, ...commonserviceoptionCollection];
    }
    return commonserviceoptionCollection;
  }

  protected convertDateFromClient<T extends ICommonserviceoption | NewCommonserviceoption | PartialUpdateCommonserviceoption>(
    commonserviceoption: T,
  ): RestOf<T> {
    return {
      ...commonserviceoption,
      lmd: commonserviceoption.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restCommonserviceoption: RestCommonserviceoption): ICommonserviceoption {
    return {
      ...restCommonserviceoption,
      lmd: restCommonserviceoption.lmd ? dayjs(restCommonserviceoption.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestCommonserviceoption>): HttpResponse<ICommonserviceoption> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestCommonserviceoption[]>): HttpResponse<ICommonserviceoption[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
