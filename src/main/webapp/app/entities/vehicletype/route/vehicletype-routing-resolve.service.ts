import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IVehicletype } from '../vehicletype.model';
import { VehicletypeService } from '../service/vehicletype.service';

const vehicletypeResolve = (route: ActivatedRouteSnapshot): Observable<null | IVehicletype> => {
  const id = route.params['id'];
  if (id) {
    return inject(VehicletypeService)
      .find(id)
      .pipe(
        mergeMap((vehicletype: HttpResponse<IVehicletype>) => {
          if (vehicletype.body) {
            return of(vehicletype.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default vehicletypeResolve;
