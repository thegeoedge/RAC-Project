import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IInventorybatches } from '../inventorybatches.model';
import { InventorybatchesService } from '../service/inventorybatches.service';

const inventorybatchesResolve = (route: ActivatedRouteSnapshot): Observable<null | IInventorybatches> => {
  const id = route.params['id'];
  if (id) {
    return inject(InventorybatchesService)
      .find(id)
      .pipe(
        mergeMap((inventorybatches: HttpResponse<IInventorybatches>) => {
          if (inventorybatches.body) {
            return of(inventorybatches.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default inventorybatchesResolve;
