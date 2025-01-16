import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICompanybankaccount } from '../companybankaccount.model';
import { CompanybankaccountService } from '../service/companybankaccount.service';

const companybankaccountResolve = (route: ActivatedRouteSnapshot): Observable<null | ICompanybankaccount> => {
  const id = route.params['id'];
  if (id) {
    return inject(CompanybankaccountService)
      .find(id)
      .pipe(
        mergeMap((companybankaccount: HttpResponse<ICompanybankaccount>) => {
          if (companybankaccount.body) {
            return of(companybankaccount.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default companybankaccountResolve;
