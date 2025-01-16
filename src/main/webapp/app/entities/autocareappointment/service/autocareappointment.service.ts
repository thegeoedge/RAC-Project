import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAutocareappointment, NewAutocareappointment } from '../autocareappointment.model';

export type PartialUpdateAutocareappointment = Partial<IAutocareappointment> & Pick<IAutocareappointment, 'id'>;

type RestOf<T extends IAutocareappointment | NewAutocareappointment> = Omit<
  T,
  'appointmentdate' | 'addeddate' | 'conformdate' | 'appointmenttime' | 'lmd'
> & {
  appointmentdate?: string | null;
  addeddate?: string | null;
  conformdate?: string | null;
  appointmenttime?: string | null;
  lmd?: string | null;
};

export type RestAutocareappointment = RestOf<IAutocareappointment>;

export type NewRestAutocareappointment = RestOf<NewAutocareappointment>;

export type PartialUpdateRestAutocareappointment = RestOf<PartialUpdateAutocareappointment>;

export type EntityResponseType = HttpResponse<IAutocareappointment>;
export type EntityArrayResponseType = HttpResponse<IAutocareappointment[]>;

@Injectable({ providedIn: 'root' })
export class AutocareappointmentService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/autocareappointments');

  create(autocareappointment: NewAutocareappointment): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocareappointment);
    return this.http
      .post<RestAutocareappointment>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(autocareappointment: IAutocareappointment): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocareappointment);
    return this.http
      .put<RestAutocareappointment>(`${this.resourceUrl}/${this.getAutocareappointmentIdentifier(autocareappointment)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(autocareappointment: PartialUpdateAutocareappointment): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autocareappointment);
    return this.http
      .patch<RestAutocareappointment>(`${this.resourceUrl}/${this.getAutocareappointmentIdentifier(autocareappointment)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestAutocareappointment>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAutocareappointment[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAutocareappointmentIdentifier(autocareappointment: Pick<IAutocareappointment, 'id'>): number {
    return autocareappointment.id;
  }

  compareAutocareappointment(o1: Pick<IAutocareappointment, 'id'> | null, o2: Pick<IAutocareappointment, 'id'> | null): boolean {
    return o1 && o2 ? this.getAutocareappointmentIdentifier(o1) === this.getAutocareappointmentIdentifier(o2) : o1 === o2;
  }

  addAutocareappointmentToCollectionIfMissing<Type extends Pick<IAutocareappointment, 'id'>>(
    autocareappointmentCollection: Type[],
    ...autocareappointmentsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const autocareappointments: Type[] = autocareappointmentsToCheck.filter(isPresent);
    if (autocareappointments.length > 0) {
      const autocareappointmentCollectionIdentifiers = autocareappointmentCollection.map(autocareappointmentItem =>
        this.getAutocareappointmentIdentifier(autocareappointmentItem),
      );
      const autocareappointmentsToAdd = autocareappointments.filter(autocareappointmentItem => {
        const autocareappointmentIdentifier = this.getAutocareappointmentIdentifier(autocareappointmentItem);
        if (autocareappointmentCollectionIdentifiers.includes(autocareappointmentIdentifier)) {
          return false;
        }
        autocareappointmentCollectionIdentifiers.push(autocareappointmentIdentifier);
        return true;
      });
      return [...autocareappointmentsToAdd, ...autocareappointmentCollection];
    }
    return autocareappointmentCollection;
  }

  protected convertDateFromClient<T extends IAutocareappointment | NewAutocareappointment | PartialUpdateAutocareappointment>(
    autocareappointment: T,
  ): RestOf<T> {
    return {
      ...autocareappointment,
      appointmentdate: autocareappointment.appointmentdate?.toJSON() ?? null,
      addeddate: autocareappointment.addeddate?.toJSON() ?? null,
      conformdate: autocareappointment.conformdate?.toJSON() ?? null,
      appointmenttime: autocareappointment.appointmenttime?.toJSON() ?? null,
      lmd: autocareappointment.lmd?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restAutocareappointment: RestAutocareappointment): IAutocareappointment {
    return {
      ...restAutocareappointment,
      appointmentdate: restAutocareappointment.appointmentdate ? dayjs(restAutocareappointment.appointmentdate) : undefined,
      addeddate: restAutocareappointment.addeddate ? dayjs(restAutocareappointment.addeddate) : undefined,
      conformdate: restAutocareappointment.conformdate ? dayjs(restAutocareappointment.conformdate) : undefined,
      appointmenttime: restAutocareappointment.appointmenttime ? dayjs(restAutocareappointment.appointmenttime) : undefined,
      lmd: restAutocareappointment.lmd ? dayjs(restAutocareappointment.lmd) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAutocareappointment>): HttpResponse<IAutocareappointment> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAutocareappointment[]>): HttpResponse<IAutocareappointment[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
