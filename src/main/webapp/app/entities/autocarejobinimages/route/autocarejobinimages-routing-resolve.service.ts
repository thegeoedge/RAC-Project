import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAutocarejobinimages } from '../autocarejobinimages.model';
import { AutocarejobinimagesService } from '../service/autocarejobinimages.service';

const autocarejobinimagesResolve = (route: ActivatedRouteSnapshot): Observable<null | IAutocarejobinimages> => {
  const id = route.params['id'];
  if (id) {
    return inject(AutocarejobinimagesService)
      .find(id)
      .pipe(
        mergeMap((autocarejobinimages: HttpResponse<IAutocarejobinimages>) => {
          if (autocarejobinimages.body) {
            return of(autocarejobinimages.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default autocarejobinimagesResolve;
