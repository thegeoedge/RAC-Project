import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAutocareappointmenttype } from '../autocareappointmenttype.model';
import { AutocareappointmenttypeService } from '../service/autocareappointmenttype.service';

const autocareappointmenttypeResolve = (route: ActivatedRouteSnapshot): Observable<null | IAutocareappointmenttype> => {
  const id = route.params['id'];
  if (id) {
    return inject(AutocareappointmenttypeService)
      .find(id)
      .pipe(
        mergeMap((autocareappointmenttype: HttpResponse<IAutocareappointmenttype>) => {
          if (autocareappointmenttype.body) {
            return of(autocareappointmenttype.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default autocareappointmenttypeResolve;
