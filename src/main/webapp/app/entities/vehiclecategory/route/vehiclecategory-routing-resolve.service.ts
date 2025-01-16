import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IVehiclecategory } from '../vehiclecategory.model';
import { VehiclecategoryService } from '../service/vehiclecategory.service';

const vehiclecategoryResolve = (route: ActivatedRouteSnapshot): Observable<null | IVehiclecategory> => {
  const id = route.params['id'];
  if (id) {
    return inject(VehiclecategoryService)
      .find(id)
      .pipe(
        mergeMap((vehiclecategory: HttpResponse<IVehiclecategory>) => {
          if (vehiclecategory.body) {
            return of(vehiclecategory.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default vehiclecategoryResolve;
