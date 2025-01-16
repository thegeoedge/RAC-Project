import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAccounts } from '../accounts.model';
import { AccountsService } from '../service/accounts.service';

const accountsResolve = (route: ActivatedRouteSnapshot): Observable<null | IAccounts> => {
  const id = route.params['id'];
  if (id) {
    return inject(AccountsService)
      .find(id)
      .pipe(
        mergeMap((accounts: HttpResponse<IAccounts>) => {
          if (accounts.body) {
            return of(accounts.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default accountsResolve;
