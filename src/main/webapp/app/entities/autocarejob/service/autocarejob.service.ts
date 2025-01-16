import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAutocarejob, NewAutocarejob } from '../autocarejob.model';

export type PartialUpdateAutocarejob = Partial<IAutocarejob> & Pick<IAutocarejob, 'id'>;

type RestOf<T extends IAutocarejob | NewAutocarejob> = Omit<T, 'nextservicedate' | 'jobopentime' | 'lmd' | 'jobclosetime' | 'jobdate'> & {
  nextservicedate?: string | null;
  jobopentime?: string | null;
  lmd?: string | null;
  jobclosetime?: string | null;
  jobdate?: string | null;
};

export type RestAutocarejob = RestOf<IAutocarejob>;

export type NewRestAutocarejob = RestOf<NewAutocarejob>;

export type PartialUpdateRestAutocarejob = RestOf<PartialUpdateAutocarejob>;

export type EntityResponseType = HttpResponse<IAutocarejob>;
export type EntityArrayResponseType = HttpResponse<IAutocarejob[]>;

@Injectable({ providedIn: 'root' })
export class AutocarejobService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/autocarejobs');

  create(autocarejob: NewAutocarejob): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocarejob);
    return this.http
      .post<RestAutocarejob>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(autocarejob: IAutocarejob): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocarejob);
    return this.http
      .put<RestAutocarejob>(`${this.resourceUrl}/${this.getAutocarejobIdentifier(autocarejob)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(autocarejob: PartialUpdateAutocarejob): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocarejob);
    return this.http
      .patch<RestAutocarejob>(`${this.resourceUrl}/${this.getAutocarejobIdentifier(autocarejob)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestAutocarejob>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAutocarejob[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAutocarejobIdentifier(autocarejob: Pick<IAutocarejob, 'id'>): number {
    return autocarejob.id;
  }

  compareAutocarejob(o1: Pick<IAutocarejob, 'id'> | null, o2: Pick<IAutocarejob, 'id'> | null): boolean {
    return o1 && o2 ? this.getAutocarejobIdentifier(o1) === this.getAutocarejobIdentifier(o2) : o1 === o2;
  }

  addAutocarejobToCollectionIfMissing<Type extends Pick<IAutocarejob, 'id'>>(
    autocarejobCollection: Type[],
    ...autocarejobsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const autocarejobs: Type[] = autocarejobsToCheck.filter(isPresent);
    if (autocarejobs.length > 0) {
      const autocarejobCollectionIdentifiers = autocarejobCollection.map(autocarejobItem => this.getAutocarejobIdentifier(autocarejobItem));
      const autocarejobsToAdd = autocarejobs.filter(autocarejobItem => {
        const autocarejobIdentifier = this.getAutocarejobIdentifier(autocarejobItem);
        if (autocarejobCollectionIdentifiers.includes(autocarejobIdentifier)) {
          return false;
        }
        autocarejobCollectionIdentifiers.push(autocarejobIdentifier);
        return true;
      });
      return [...autocarejobsToAdd, ...autocarejobCollection];
    }
    return autocarejobCollection;
  }

  protected convertDateFromClient<T extends IAutocarejob | NewAutocarejob | PartialUpdateAutocarejob>(autocarejob: T): RestOf<T> {
    return {
      ...autocarejob,
      nextservicedate: autocarejob.nextservicedate?.format(DATE_FORMAT) ?? null,
      jobopentime: autocarejob.jobopentime?.toJSON() ?? null,
      lmd: autocarejob.lmd?.toJSON() ?? null,
      jobclosetime: autocarejob.jobclosetime?.toJSON() ?? null,
      jobdate: autocarejob.jobdate?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restAutocarejob: RestAutocarejob): IAutocarejob {
    return {
      ...restAutocarejob,
      nextservicedate: restAutocarejob.nextservicedate ? dayjs(restAutocarejob.nextservicedate) : undefined,
      jobopentime: restAutocarejob.jobopentime ? dayjs(restAutocarejob.jobopentime) : undefined,
      lmd: restAutocarejob.lmd ? dayjs(restAutocarejob.lmd) : undefined,
      jobclosetime: restAutocarejob.jobclosetime ? dayjs(restAutocarejob.jobclosetime) : undefined,
      jobdate: restAutocarejob.jobdate ? dayjs(restAutocarejob.jobdate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAutocarejob>): HttpResponse<IAutocarejob> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAutocarejob[]>): HttpResponse<IAutocarejob[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
