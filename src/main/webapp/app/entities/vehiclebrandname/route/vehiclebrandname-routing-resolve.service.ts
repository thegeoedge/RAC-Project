import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IVehiclebrandname } from '../vehiclebrandname.model';
import { VehiclebrandnameService } from '../service/vehiclebrandname.service';

const vehiclebrandnameResolve = (route: ActivatedRouteSnapshot): Observable<null | IVehiclebrandname> => {
  const id = route.params['id'];
  if (id) {
    return inject(VehiclebrandnameService)
      .find(id)
      .pipe(
        mergeMap((vehiclebrandname: HttpResponse<IVehiclebrandname>) => {
          if (vehiclebrandname.body) {
            return of(vehiclebrandname.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default vehiclebrandnameResolve;
