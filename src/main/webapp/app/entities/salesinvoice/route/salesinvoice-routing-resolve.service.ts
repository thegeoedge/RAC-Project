import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISalesinvoice } from '../salesinvoice.model';
import { SalesinvoiceService } from '../service/salesinvoice.service';

const salesinvoiceResolve = (route: ActivatedRouteSnapshot): Observable<null | ISalesinvoice> => {
  const id = route.params['id'];
  if (id) {
    return inject(SalesinvoiceService)
      .find(id)
      .pipe(
        mergeMap((salesinvoice: HttpResponse<ISalesinvoice>) => {
          if (salesinvoice.body) {
            return of(salesinvoice.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default salesinvoiceResolve;
