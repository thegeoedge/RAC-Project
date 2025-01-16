import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAutocareappointmenttype, NewAutocareappointmenttype } from '../autocareappointmenttype.model';

export type PartialUpdateAutocareappointmenttype = Partial<IAutocareappointmenttype> & Pick<IAutocareappointmenttype, 'id'>;

export type EntityResponseType = HttpResponse<IAutocareappointmenttype>;
export type EntityArrayResponseType = HttpResponse<IAutocareappointmenttype[]>;

@Injectable({ providedIn: 'root' })
export class AutocareappointmenttypeService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/autocareappointmenttypes');

  create(autocareappointmenttype: NewAutocareappointmenttype): Observable<EntityResponseType> {
    return this.http.post<IAutocareappointmenttype>(this.resourceUrl, autocareappointmenttype, { observe: 'response' });
  }

  update(autocareappointmenttype: IAutocareappointmenttype): Observable<EntityResponseType> {
    return this.http.put<IAutocareappointmenttype>(
      `${this.resourceUrl}/${this.getAutocareappointmenttypeIdentifier(autocareappointmenttype)}`,
      autocareappointmenttype,
      { observe: 'response' },
    );
  }

  partialUpdate(autocareappointmenttype: PartialUpdateAutocareappointmenttype): Observable<EntityResponseType> {
    return this.http.patch<IAutocareappointmenttype>(
      `${this.resourceUrl}/${this.getAutocareappointmenttypeIdentifier(autocareappointmenttype)}`,
      autocareappointmenttype,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAutocareappointmenttype>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAutocareappointmenttype[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAutocareappointmenttypeIdentifier(autocareappointmenttype: Pick<IAutocareappointmenttype, 'id'>): number {
    return autocareappointmenttype.id;
  }

  compareAutocareappointmenttype(
    o1: Pick<IAutocareappointmenttype, 'id'> | null,
    o2: Pick<IAutocareappointmenttype, 'id'> | null,
  ): boolean {
    return o1 && o2 ? this.getAutocareappointmenttypeIdentifier(o1) === this.getAutocareappointmenttypeIdentifier(o2) : o1 === o2;
  }

  addAutocareappointmenttypeToCollectionIfMissing<Type extends Pick<IAutocareappointmenttype, 'id'>>(
    autocareappointmenttypeCollection: Type[],
    ...autocareappointmenttypesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const autocareappointmenttypes: Type[] = autocareappointmenttypesToCheck.filter(isPresent);
    if (autocareappointmenttypes.length > 0) {
      const autocareappointmenttypeCollectionIdentifiers = autocareappointmenttypeCollection.map(autocareappointmenttypeItem =>
        this.getAutocareappointmenttypeIdentifier(autocareappointmenttypeItem),
      );
      const autocareappointmenttypesToAdd = autocareappointmenttypes.filter(autocareappointmenttypeItem => {
        const autocareappointmenttypeIdentifier = this.getAutocareappointmenttypeIdentifier(autocareappointmenttypeItem);
        if (autocareappointmenttypeCollectionIdentifiers.includes(autocareappointmenttypeIdentifier)) {
          return false;
        }
        autocareappointmenttypeCollectionIdentifiers.push(autocareappointmenttypeIdentifier);
        return true;
      });
      return [...autocareappointmenttypesToAdd, ...autocareappointmenttypeCollection];
    }
    return autocareappointmenttypeCollection;
  }
}
