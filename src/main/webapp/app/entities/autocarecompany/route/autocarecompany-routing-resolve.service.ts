import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAutocarecompany } from '../autocarecompany.model';
import { AutocarecompanyService } from '../service/autocarecompany.service';

const autocarecompanyResolve = (route: ActivatedRouteSnapshot): Observable<null | IAutocarecompany> => {
  const id = route.params['id'];
  if (id) {
    return inject(AutocarecompanyService)
      .find(id)
      .pipe(
        mergeMap((autocarecompany: HttpResponse<IAutocarecompany>) => {
          if (autocarecompany.body) {
            return of(autocarecompany.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default autocarecompanyResolve;
