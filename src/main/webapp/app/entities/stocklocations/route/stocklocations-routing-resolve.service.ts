import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IStocklocations } from '../stocklocations.model';
import { StocklocationsService } from '../service/stocklocations.service';

const stocklocationsResolve = (route: ActivatedRouteSnapshot): Observable<null | IStocklocations> => {
  const id = route.params['id'];
  if (id) {
    return inject(StocklocationsService)
      .find(id)
      .pipe(
        mergeMap((stocklocations: HttpResponse<IStocklocations>) => {
          if (stocklocations.body) {
            return of(stocklocations.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default stocklocationsResolve;
