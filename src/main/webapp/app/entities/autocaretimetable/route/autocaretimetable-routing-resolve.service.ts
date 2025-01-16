import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAutocaretimetable } from '../autocaretimetable.model';
import { AutocaretimetableService } from '../service/autocaretimetable.service';

const autocaretimetableResolve = (route: ActivatedRouteSnapshot): Observable<null | IAutocaretimetable> => {
  const id = route.params['id'];
  if (id) {
    return inject(AutocaretimetableService)
      .find(id)
      .pipe(
        mergeMap((autocaretimetable: HttpResponse<IAutocaretimetable>) => {
          if (autocaretimetable.body) {
            return of(autocaretimetable.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default autocaretimetableResolve;
