import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICommonserviceoption } from '../commonserviceoption.model';
import { CommonserviceoptionService } from '../service/commonserviceoption.service';

const commonserviceoptionResolve = (route: ActivatedRouteSnapshot): Observable<null | ICommonserviceoption> => {
  const id = route.params['id'];
  if (id) {
    return inject(CommonserviceoptionService)
      .find(id)
      .pipe(
        mergeMap((commonserviceoption: HttpResponse<ICommonserviceoption>) => {
          if (commonserviceoption.body) {
            return of(commonserviceoption.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default commonserviceoptionResolve;
