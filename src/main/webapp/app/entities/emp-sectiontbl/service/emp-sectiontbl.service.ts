import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEmpSectiontbl, NewEmpSectiontbl } from '../emp-sectiontbl.model';

export type PartialUpdateEmpSectiontbl = Partial<IEmpSectiontbl> & Pick<IEmpSectiontbl, 'id'>;

type RestOf<T extends IEmpSectiontbl | NewEmpSectiontbl> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

export type RestEmpSectiontbl = RestOf<IEmpSectiontbl>;

export type NewRestEmpSectiontbl = RestOf<NewEmpSectiontbl>;

export type PartialUpdateRestEmpSectiontbl = RestOf<PartialUpdateEmpSectiontbl>;

export type EntityResponseType = HttpResponse<IEmpSectiontbl>;
export type EntityArrayResponseType = HttpResponse<IEmpSectiontbl[]>;

@Injectable({ providedIn: 'root' })
export class EmpSectiontblService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/emp-sectiontbls');

  create(empSectiontbl: NewEmpSectiontbl): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(empSectiontbl);
    return this.http
      .post<RestEmpSectiontbl>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(empSectiontbl: IEmpSectiontbl): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(empSectiontbl);
    return this.http
      .put<RestEmpSectiontbl>(`${this.resourceUrl}/${this.getEmpSectiontblIdentifier(empSectiontbl)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(empSectiontbl: PartialUpdateEmpSectiontbl): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(empSectiontbl);
    return this.http
      .patch<RestEmpSectiontbl>(`${this.resourceUrl}/${this.getEmpSectiontblIdentifier(empSectiontbl)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestEmpSectiontbl>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestEmpSectiontbl[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getEmpSectiontblIdentifier(empSectiontbl: Pick<IEmpSectiontbl, 'id'>): number {
    return empSectiontbl.id;
  }

  compareEmpSectiontbl(o1: Pick<IEmpSectiontbl, 'id'> | null, o2: Pick<IEmpSectiontbl, 'id'> | null): boolean {
    return o1 && o2 ? this.getEmpSectiontblIdentifier(o1) === this.getEmpSectiontblIdentifier(o2) : o1 === o2;
  }

  addEmpSectiontblToCollectionIfMissing<Type extends Pick<IEmpSectiontbl, 'id'>>(
    empSectiontblCollection: Type[],
    ...empSectiontblsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const empSectiontbls: Type[] = empSectiontblsToCheck.filter(isPresent);
    if (empSectiontbls.length > 0) {
      const empSectiontblCollectionIdentifiers = empSectiontblCollection.map(empSectiontblItem =>
        this.getEmpSectiontblIdentifier(empSectiontblItem),
      );
      const empSectiontblsToAdd = empSectiontbls.filter(empSectiontblItem => {
        const empSectiontblIdentifier = this.getEmpSectiontblIdentifier(empSectiontblItem);
        if (empSectiontblCollectionIdentifiers.includes(empSectiontblIdentifier)) {
          return false;
        }
        empSectiontblCollectionIdentifiers.push(empSectiontblIdentifier);
        return true;
      });
      return [...empSectiontblsToAdd, ...empSectiontblCollection];
    }
    return empSectiontblCollection;
  }

  protected convertDateFromClient<T extends IEmpSectiontbl | NewEmpSectiontbl | PartialUpdateEmpSectiontbl>(empSectiontbl: T): RestOf<T> {
    return {
      ...empSectiontbl,
      lmd: empSectiontbl.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restEmpSectiontbl: RestEmpSectiontbl): IEmpSectiontbl {
    return {
      ...restEmpSectiontbl,
      lmd: restEmpSectiontbl.lmd ? dayjs(restEmpSectiontbl.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestEmpSectiontbl>): HttpResponse<IEmpSectiontbl> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestEmpSectiontbl[]>): HttpResponse<IEmpSectiontbl[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
