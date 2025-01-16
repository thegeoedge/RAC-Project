import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICustomervehicle } from '../customervehicle.model';
import { CustomervehicleService } from '../service/customervehicle.service';

const customervehicleResolve = (route: ActivatedRouteSnapshot): Observable<null | ICustomervehicle> => {
  const id = route.params.id;
  if (id) {
    return inject(CustomervehicleService)
      .find(id)
      .pipe(
        mergeMap((customervehicle: HttpResponse<ICustomervehicle>) => {
          if (customervehicle.body) {
            return of(customervehicle.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default customervehicleResolve;
