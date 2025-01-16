import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ILocationbasedstock } from '../locationbasedstock.model';
import { LocationbasedstockService } from '../service/locationbasedstock.service';

const locationbasedstockResolve = (route: ActivatedRouteSnapshot): Observable<null | ILocationbasedstock> => {
  const id = route.params['id'];
  if (id) {
    return inject(LocationbasedstockService)
      .find(id)
      .pipe(
        mergeMap((locationbasedstock: HttpResponse<ILocationbasedstock>) => {
          if (locationbasedstock.body) {
            return of(locationbasedstock.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default locationbasedstockResolve;
