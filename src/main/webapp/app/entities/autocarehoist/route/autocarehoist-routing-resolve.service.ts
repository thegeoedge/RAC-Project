import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAutocarehoist } from '../autocarehoist.model';
import { AutocarehoistService } from '../service/autocarehoist.service';

const autocarehoistResolve = (route: ActivatedRouteSnapshot): Observable<null | IAutocarehoist> => {
  const id = route.params['id'];
  if (id) {
    return inject(AutocarehoistService)
      .find(id)
      .pipe(
        mergeMap((autocarehoist: HttpResponse<IAutocarehoist>) => {
          if (autocarehoist.body) {
            return of(autocarehoist.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default autocarehoistResolve;
