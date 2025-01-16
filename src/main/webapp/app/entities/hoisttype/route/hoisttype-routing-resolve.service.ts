import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IHoisttype } from '../hoisttype.model';
import { HoisttypeService } from '../service/hoisttype.service';

const hoisttypeResolve = (route: ActivatedRouteSnapshot): Observable<null | IHoisttype> => {
  const id = route.params['id'];
  if (id) {
    return inject(HoisttypeService)
      .find(id)
      .pipe(
        mergeMap((hoisttype: HttpResponse<IHoisttype>) => {
          if (hoisttype.body) {
            return of(hoisttype.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default hoisttypeResolve;
