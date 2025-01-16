import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAutojobempallocation } from '../autojobempallocation.model';
import { AutojobempallocationService } from '../service/autojobempallocation.service';

const autojobempallocationResolve = (route: ActivatedRouteSnapshot): Observable<null | IAutojobempallocation> => {
  const id = route.params['id'];
  if (id) {
    return inject(AutojobempallocationService)
      .find(id)
      .pipe(
        mergeMap((autojobempallocation: HttpResponse<IAutojobempallocation>) => {
          if (autojobempallocation.body) {
            return of(autojobempallocation.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default autojobempallocationResolve;
