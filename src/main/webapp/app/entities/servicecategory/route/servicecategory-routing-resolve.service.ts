import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IServicecategory } from '../servicecategory.model';
import { ServicecategoryService } from '../service/servicecategory.service';

const servicecategoryResolve = (route: ActivatedRouteSnapshot): Observable<null | IServicecategory> => {
  const id = route.params['id'];
  if (id) {
    return inject(ServicecategoryService)
      .find(id)
      .pipe(
        mergeMap((servicecategory: HttpResponse<IServicecategory>) => {
          if (servicecategory.body) {
            return of(servicecategory.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default servicecategoryResolve;
