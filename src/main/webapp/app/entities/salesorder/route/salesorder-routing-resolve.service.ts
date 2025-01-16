import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISalesorder } from '../salesorder.model';
import { SalesorderService } from '../service/salesorder.service';

const salesorderResolve = (route: ActivatedRouteSnapshot): Observable<null | ISalesorder> => {
  const id = route.params['id'];
  if (id) {
    return inject(SalesorderService)
      .find(id)
      .pipe(
        mergeMap((salesorder: HttpResponse<ISalesorder>) => {
          if (salesorder.body) {
            return of(salesorder.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default salesorderResolve;
