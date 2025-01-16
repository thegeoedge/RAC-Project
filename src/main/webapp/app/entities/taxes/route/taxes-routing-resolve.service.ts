import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITaxes } from '../taxes.model';
import { TaxesService } from '../service/taxes.service';

const taxesResolve = (route: ActivatedRouteSnapshot): Observable<null | ITaxes> => {
  const id = route.params['id'];
  if (id) {
    return inject(TaxesService)
      .find(id)
      .pipe(
        mergeMap((taxes: HttpResponse<ITaxes>) => {
          if (taxes.body) {
            return of(taxes.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default taxesResolve;
