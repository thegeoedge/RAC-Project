import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAutocarecancelitemopt } from '../autocarecancelitemopt.model';
import { AutocarecancelitemoptService } from '../service/autocarecancelitemopt.service';

const autocarecancelitemoptResolve = (route: ActivatedRouteSnapshot): Observable<null | IAutocarecancelitemopt> => {
  const id = route.params['id'];
  if (id) {
    return inject(AutocarecancelitemoptService)
      .find(id)
      .pipe(
        mergeMap((autocarecancelitemopt: HttpResponse<IAutocarecancelitemopt>) => {
          if (autocarecancelitemopt.body) {
            return of(autocarecancelitemopt.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default autocarecancelitemoptResolve;
