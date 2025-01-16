import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IInventory } from '../inventory.model';
import { InventoryService } from '../service/inventory.service';

const inventoryResolve = (route: ActivatedRouteSnapshot): Observable<null | IInventory> => {
  const id = route.params['id'];
  if (id) {
    return inject(InventoryService)
      .find(id)
      .pipe(
        mergeMap((inventory: HttpResponse<IInventory>) => {
          if (inventory.body) {
            return of(inventory.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default inventoryResolve;
