import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IVehiclebrandmodel } from '../vehiclebrandmodel.model';
import { VehiclebrandmodelService } from '../service/vehiclebrandmodel.service';

const vehiclebrandmodelResolve = (route: ActivatedRouteSnapshot): Observable<null | IVehiclebrandmodel> => {
  const id = route.params['id'];
  if (id) {
    return inject(VehiclebrandmodelService)
      .find(id)
      .pipe(
        mergeMap((vehiclebrandmodel: HttpResponse<IVehiclebrandmodel>) => {
          if (vehiclebrandmodel.body) {
            return of(vehiclebrandmodel.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default vehiclebrandmodelResolve;
