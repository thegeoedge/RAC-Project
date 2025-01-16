import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ILastserviceinstructions } from '../lastserviceinstructions.model';
import { LastserviceinstructionsService } from '../service/lastserviceinstructions.service';

const lastserviceinstructionsResolve = (route: ActivatedRouteSnapshot): Observable<null | ILastserviceinstructions> => {
  const id = route.params['id'];
  if (id) {
    return inject(LastserviceinstructionsService)
      .find(id)
      .pipe(
        mergeMap((lastserviceinstructions: HttpResponse<ILastserviceinstructions>) => {
          if (lastserviceinstructions.body) {
            return of(lastserviceinstructions.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default lastserviceinstructionsResolve;
