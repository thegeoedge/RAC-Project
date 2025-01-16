import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IPaymentterm } from '../paymentterm.model';
import { PaymenttermService } from '../service/paymentterm.service';

const paymenttermResolve = (route: ActivatedRouteSnapshot): Observable<null | IPaymentterm> => {
  const id = route.params['id'];
  if (id) {
    return inject(PaymenttermService)
      .find(id)
      .pipe(
        mergeMap((paymentterm: HttpResponse<IPaymentterm>) => {
          if (paymentterm.body) {
            return of(paymentterm.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default paymenttermResolve;
