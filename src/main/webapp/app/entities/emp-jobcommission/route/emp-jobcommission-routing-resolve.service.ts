import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEmpJobcommission } from '../emp-jobcommission.model';
import { EmpJobcommissionService } from '../service/emp-jobcommission.service';

const empJobcommissionResolve = (route: ActivatedRouteSnapshot): Observable<null | IEmpJobcommission> => {
  const id = route.params['id'];
  if (id) {
    return inject(EmpJobcommissionService)
      .find(id)
      .pipe(
        mergeMap((empJobcommission: HttpResponse<IEmpJobcommission>) => {
          if (empJobcommission.body) {
            return of(empJobcommission.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default empJobcommissionResolve;
