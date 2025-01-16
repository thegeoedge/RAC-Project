import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IWorkshopworklist } from '../workshopworklist.model';
import { WorkshopworklistService } from '../service/workshopworklist.service';

const workshopworklistResolve = (route: ActivatedRouteSnapshot): Observable<null | IWorkshopworklist> => {
  const id = route.params['id'];
  if (id) {
    return inject(WorkshopworklistService)
      .find(id)
      .pipe(
        mergeMap((workshopworklist: HttpResponse<IWorkshopworklist>) => {
          if (workshopworklist.body) {
            return of(workshopworklist.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default workshopworklistResolve;
