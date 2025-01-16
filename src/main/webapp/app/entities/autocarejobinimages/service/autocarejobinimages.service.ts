import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAutocarejobinimages, NewAutocarejobinimages } from '../autocarejobinimages.model';

export type PartialUpdateAutocarejobinimages = Partial<IAutocarejobinimages> & Pick<IAutocarejobinimages, 'id'>;

export type EntityResponseType = HttpResponse<IAutocarejobinimages>;
export type EntityArrayResponseType = HttpResponse<IAutocarejobinimages[]>;

@Injectable({ providedIn: 'root' })
export class AutocarejobinimagesService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/autocarejobinimages');

  create(autocarejobinimages: NewAutocarejobinimages): Observable<EntityResponseType> {
    return this.http.post<IAutocarejobinimages>(this.resourceUrl, autocarejobinimages, { observe: 'response' });
  }

  update(autocarejobinimages: IAutocarejobinimages): Observable<EntityResponseType> {
    return this.http.put<IAutocarejobinimages>(
      `${this.resourceUrl}/${this.getAutocarejobinimagesIdentifier(autocarejobinimages)}`,
      autocarejobinimages,
      { observe: 'response' },
    );
  }

  partialUpdate(autocarejobinimages: PartialUpdateAutocarejobinimages): Observable<EntityResponseType> {
    return this.http.patch<IAutocarejobinimages>(
      `${this.resourceUrl}/${this.getAutocarejobinimagesIdentifier(autocarejobinimages)}`,
      autocarejobinimages,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAutocarejobinimages>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAutocarejobinimages[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAutocarejobinimagesIdentifier(autocarejobinimages: Pick<IAutocarejobinimages, 'id'>): number {
    return autocarejobinimages.id;
  }

  compareAutocarejobinimages(o1: Pick<IAutocarejobinimages, 'id'> | null, o2: Pick<IAutocarejobinimages, 'id'> | null): boolean {
    return o1 && o2 ? this.getAutocarejobinimagesIdentifier(o1) === this.getAutocarejobinimagesIdentifier(o2) : o1 === o2;
  }

  addAutocarejobinimagesToCollectionIfMissing<Type extends Pick<IAutocarejobinimages, 'id'>>(
    autocarejobinimagesCollection: Type[],
    ...autocarejobinimagesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const autocarejobinimages: Type[] = autocarejobinimagesToCheck.filter(isPresent);
    if (autocarejobinimages.length > 0) {
      const autocarejobinimagesCollectionIdentifiers = autocarejobinimagesCollection.map(autocarejobinimagesItem =>
        this.getAutocarejobinimagesIdentifier(autocarejobinimagesItem),
      );
      const autocarejobinimagesToAdd = autocarejobinimages.filter(autocarejobinimagesItem => {
        const autocarejobinimagesIdentifier = this.getAutocarejobinimagesIdentifier(autocarejobinimagesItem);
        if (autocarejobinimagesCollectionIdentifiers.includes(autocarejobinimagesIdentifier)) {
          return false;
        }
        autocarejobinimagesCollectionIdentifiers.push(autocarejobinimagesIdentifier);
        return true;
      });
      return [...autocarejobinimagesToAdd, ...autocarejobinimagesCollection];
    }
    return autocarejobinimagesCollection;
  }
}
