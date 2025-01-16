import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IReceipt } from '../receipt.model';
import { ReceiptService } from '../service/receipt.service';

const receiptResolve = (route: ActivatedRouteSnapshot): Observable<null | IReceipt> => {
  const id = route.params['id'];
  if (id) {
    return inject(ReceiptService)
      .find(id)
      .pipe(
        mergeMap((receipt: HttpResponse<IReceipt>) => {
          if (receipt.body) {
            return of(receipt.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default receiptResolve;
