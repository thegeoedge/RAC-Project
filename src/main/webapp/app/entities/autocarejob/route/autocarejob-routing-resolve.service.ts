import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAutocarejob } from '../autocarejob.model';
import { AutocarejobService } from '../service/autocarejob.service';

const autocarejobResolve = (route: ActivatedRouteSnapshot): Observable<null | IAutocarejob> => {
  const id = route.params['id'];
  if (id) {
    return inject(AutocarejobService)
      .find(id)
      .pipe(
        mergeMap((autocarejob: HttpResponse<IAutocarejob>) => {
          if (autocarejob.body) {
            return of(autocarejob.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default autocarejobResolve;
