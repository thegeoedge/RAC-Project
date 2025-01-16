import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAutojobsinvoice } from '../autojobsinvoice.model';
import { AutojobsinvoiceService } from '../service/autojobsinvoice.service';

const autojobsinvoiceResolve = (route: ActivatedRouteSnapshot): Observable<null | IAutojobsinvoice> => {
  const id = route.params['id'];
  if (id) {
    return inject(AutojobsinvoiceService)
      .find(id)
      .pipe(
        mergeMap((autojobsinvoice: HttpResponse<IAutojobsinvoice>) => {
          if (autojobsinvoice.body) {
            return of(autojobsinvoice.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default autojobsinvoiceResolve;
