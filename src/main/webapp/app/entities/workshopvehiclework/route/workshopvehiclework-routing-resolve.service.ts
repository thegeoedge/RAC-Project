import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IWorkshopvehiclework } from '../workshopvehiclework.model';
import { WorkshopvehicleworkService } from '../service/workshopvehiclework.service';

const workshopvehicleworkResolve = (route: ActivatedRouteSnapshot): Observable<null | IWorkshopvehiclework> => {
  const id = route.params['id'];
  if (id) {
    return inject(WorkshopvehicleworkService)
      .find(id)
      .pipe(
        mergeMap((workshopvehiclework: HttpResponse<IWorkshopvehiclework>) => {
          if (workshopvehiclework.body) {
            return of(workshopvehiclework.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default workshopvehicleworkResolve;
