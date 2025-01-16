import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAutocareservicemillages } from '../autocareservicemillages.model';
import { AutocareservicemillagesService } from '../service/autocareservicemillages.service';

const autocareservicemillagesResolve = (route: ActivatedRouteSnapshot): Observable<null | IAutocareservicemillages> => {
  const id = route.params['id'];
  if (id) {
    return inject(AutocareservicemillagesService)
      .find(id)
      .pipe(
        mergeMap((autocareservicemillages: HttpResponse<IAutocareservicemillages>) => {
          if (autocareservicemillages.body) {
            return of(autocareservicemillages.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default autocareservicemillagesResolve;
