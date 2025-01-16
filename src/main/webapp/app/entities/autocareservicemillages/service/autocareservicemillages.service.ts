import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAutocareservicemillages, NewAutocareservicemillages } from '../autocareservicemillages.model';

export type PartialUpdateAutocareservicemillages = Partial<IAutocareservicemillages> & Pick<IAutocareservicemillages, 'id'>;

export type EntityResponseType = HttpResponse<IAutocareservicemillages>;
export type EntityArrayResponseType = HttpResponse<IAutocareservicemillages[]>;

@Injectable({ providedIn: 'root' })
export class AutocareservicemillagesService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/autocareservicemillages');

  create(autocareservicemillages: NewAutocareservicemillages): Observable<EntityResponseType> {
    return this.http.post<IAutocareservicemillages>(this.resourceUrl, autocareservicemillages, { observe: 'response' });
  }

  update(autocareservicemillages: IAutocareservicemillages): Observable<EntityResponseType> {
    return this.http.put<IAutocareservicemillages>(
      `${this.resourceUrl}/${this.getAutocareservicemillagesIdentifier(autocareservicemillages)}`,
      autocareservicemillages,
      { observe: 'response' },
    );
  }

  partialUpdate(autocareservicemillages: PartialUpdateAutocareservicemillages): Observable<EntityResponseType> {
    return this.http.patch<IAutocareservicemillages>(
      `${this.resourceUrl}/${this.getAutocareservicemillagesIdentifier(autocareservicemillages)}`,
      autocareservicemillages,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAutocareservicemillages>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAutocareservicemillages[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAutocareservicemillagesIdentifier(autocareservicemillages: Pick<IAutocareservicemillages, 'id'>): number {
    return autocareservicemillages.id;
  }

  compareAutocareservicemillages(
    o1: Pick<IAutocareservicemillages, 'id'> | null,
    o2: Pick<IAutocareservicemillages, 'id'> | null,
  ): boolean {
    return o1 && o2 ? this.getAutocareservicemillagesIdentifier(o1) === this.getAutocareservicemillagesIdentifier(o2) : o1 === o2;
  }

  addAutocareservicemillagesToCollectionIfMissing<Type extends Pick<IAutocareservicemillages, 'id'>>(
    autocareservicemillagesCollection: Type[],
    ...autocareservicemillagesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const autocareservicemillages: Type[] = autocareservicemillagesToCheck.filter(isPresent);
    if (autocareservicemillages.length > 0) {
      const autocareservicemillagesCollectionIdentifiers = autocareservicemillagesCollection.map(autocareservicemillagesItem =>
        this.getAutocareservicemillagesIdentifier(autocareservicemillagesItem),
      );
      const autocareservicemillagesToAdd = autocareservicemillages.filter(autocareservicemillagesItem => {
        const autocareservicemillagesIdentifier = this.getAutocareservicemillagesIdentifier(autocareservicemillagesItem);
        if (autocareservicemillagesCollectionIdentifiers.includes(autocareservicemillagesIdentifier)) {
          return false;
        }
        autocareservicemillagesCollectionIdentifiers.push(autocareservicemillagesIdentifier);
        return true;
      });
      return [...autocareservicemillagesToAdd, ...autocareservicemillagesCollection];
    }
    return autocareservicemillagesCollection;
  }
}
