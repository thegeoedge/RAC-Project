import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBankbranch } from '../bankbranch.model';
import { BankbranchService } from '../service/bankbranch.service';

const bankbranchResolve = (route: ActivatedRouteSnapshot): Observable<null | IBankbranch> => {
  const id = route.params['id'];
  if (id) {
    return inject(BankbranchService)
      .find(id)
      .pipe(
        mergeMap((bankbranch: HttpResponse<IBankbranch>) => {
          if (bankbranch.body) {
            return of(bankbranch.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default bankbranchResolve;
