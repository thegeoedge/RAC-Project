import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IServicesubcategory } from '../servicesubcategory.model';
import { ServicesubcategoryService } from '../service/servicesubcategory.service';

const servicesubcategoryResolve = (route: ActivatedRouteSnapshot): Observable<null | IServicesubcategory> => {
  const id = route.params['id'];
  if (id) {
    return inject(ServicesubcategoryService)
      .find(id)
      .pipe(
        mergeMap((servicesubcategory: HttpResponse<IServicesubcategory>) => {
          if (servicesubcategory.body) {
            return of(servicesubcategory.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default servicesubcategoryResolve;
