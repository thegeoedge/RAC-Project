import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBillingserviceoption } from '../billingserviceoption.model';
import { BillingserviceoptionService } from '../service/billingserviceoption.service';

const billingserviceoptionResolve = (route: ActivatedRouteSnapshot): Observable<null | IBillingserviceoption> => {
  const id = route.params['id'];
  if (id) {
    return inject(BillingserviceoptionService)
      .find(id)
      .pipe(
        mergeMap((billingserviceoption: HttpResponse<IBillingserviceoption>) => {
          if (billingserviceoption.body) {
            return of(billingserviceoption.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default billingserviceoptionResolve;
