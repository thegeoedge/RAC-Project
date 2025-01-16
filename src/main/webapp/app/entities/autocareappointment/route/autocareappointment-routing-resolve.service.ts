import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAutocareappointment } from '../autocareappointment.model';
import { AutocareappointmentService } from '../service/autocareappointment.service';

const autocareappointmentResolve = (route: ActivatedRouteSnapshot): Observable<null | IAutocareappointment> => {
  const id = route.params.id;
  if (id) {
    return inject(AutocareappointmentService)
      .find(id)
      .pipe(
        mergeMap((autocareappointment: HttpResponse<IAutocareappointment>) => {
          if (autocareappointment.body) {
            return of(autocareappointment.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default autocareappointmentResolve;
