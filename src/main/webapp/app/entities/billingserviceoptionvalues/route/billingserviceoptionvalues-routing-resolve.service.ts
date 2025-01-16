import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBillingserviceoptionvalues } from '../billingserviceoptionvalues.model';
import { BillingserviceoptionvaluesService } from '../service/billingserviceoptionvalues.service';

const billingserviceoptionvaluesResolve = (route: ActivatedRouteSnapshot): Observable<null | IBillingserviceoptionvalues> => {
  const id = route.params['id'];
  if (id) {
    return inject(BillingserviceoptionvaluesService)
      .find(id)
      .pipe(
        mergeMap((billingserviceoptionvalues: HttpResponse<IBillingserviceoptionvalues>) => {
          if (billingserviceoptionvalues.body) {
            return of(billingserviceoptionvalues.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default billingserviceoptionvaluesResolve;
