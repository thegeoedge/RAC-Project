import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEmpJobcommission, NewEmpJobcommission } from '../emp-jobcommission.model';

export type PartialUpdateEmpJobcommission = Partial<IEmpJobcommission> & Pick<IEmpJobcommission, 'id'>;

type RestOf<T extends IEmpJobcommission | NewEmpJobcommission> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestEmpJobcommission = RestOf<IEmpJobcommission>;

export type NewRestEmpJobcommission = RestOf<NewEmpJobcommission>;

export type PartialUpdateRestEmpJobcommission = RestOf<PartialUpdateEmpJobcommission>;

export type EntityResponseType = HttpResponse<IEmpJobcommission>;
export type EntityArrayResponseType = HttpResponse<IEmpJobcommission[]>;

@Injectable({ providedIn: 'root' })
export class EmpJobcommissionService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/emp-jobcommissions');

  create(empJobcommission: NewEmpJobcommission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(empJobcommission);
    return this.http
      .post<RestEmpJobcommission>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(empJobcommission: IEmpJobcommission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(empJobcommission);
    return this.http
      .put<RestEmpJobcommission>(`${this.resourceUrl}/${this.getEmpJobcommissionIdentifier(empJobcommission)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(empJobcommission: PartialUpdateEmpJobcommission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(empJobcommission);
    return this.http
      .patch<RestEmpJobcommission>(`${this.resourceUrl}/${this.getEmpJobcommissionIdentifier(empJobcommission)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestEmpJobcommission>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestEmpJobcommission[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getEmpJobcommissionIdentifier(empJobcommission: Pick<IEmpJobcommission, 'id'>): number {
    return empJobcommission.id;
  }

  compareEmpJobcommission(o1: Pick<IEmpJobcommission, 'id'> | null, o2: Pick<IEmpJobcommission, 'id'> | null): boolean {
    return o1 && o2 ? this.getEmpJobcommissionIdentifier(o1) === this.getEmpJobcommissionIdentifier(o2) : o1 === o2;
  }

  addEmpJobcommissionToCollectionIfMissing<Type extends Pick<IEmpJobcommission, 'id'>>(
    empJobcommissionCollection: Type[],
    ...empJobcommissionsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const empJobcommissions: Type[] = empJobcommissionsToCheck.filter(isPresent);
    if (empJobcommissions.length > 0) {
      const empJobcommissionCollectionIdentifiers = empJobcommissionCollection.map(empJobcommissionItem =>
        this.getEmpJobcommissionIdentifier(empJobcommissionItem),
      );
      const empJobcommissionsToAdd = empJobcommissions.filter(empJobcommissionItem => {
        const empJobcommissionIdentifier = this.getEmpJobcommissionIdentifier(empJobcommissionItem);
        if (empJobcommissionCollectionIdentifiers.includes(empJobcommissionIdentifier)) {
          return false;
        }
        empJobcommissionCollectionIdentifiers.push(empJobcommissionIdentifier);
        return true;
      });
      return [...empJobcommissionsToAdd, ...empJobcommissionCollection];
    }
    return empJobcommissionCollection;
  }

  protected convertDateFromClient<T extends IEmpJobcommission | NewEmpJobcommission | PartialUpdateEmpJobcommission>(
    empJobcommission: T,
  ): RestOf<T> {
    return {
      ...empJobcommission,
      lmd: empJobcommission.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restEmpJobcommission: RestEmpJobcommission): IEmpJobcommission {
    return {
      ...restEmpJobcommission,
      lmd: restEmpJobcommission.lmd ? dayjs(restEmpJobcommission.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestEmpJobcommission>): HttpResponse<IEmpJobcommission> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestEmpJobcommission[]>): HttpResponse<IEmpJobcommission[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
