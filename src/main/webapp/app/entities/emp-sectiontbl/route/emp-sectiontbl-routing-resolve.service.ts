import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEmpSectiontbl } from '../emp-sectiontbl.model';
import { EmpSectiontblService } from '../service/emp-sectiontbl.service';

const empSectiontblResolve = (route: ActivatedRouteSnapshot): Observable<null | IEmpSectiontbl> => {
  const id = route.params['id'];
  if (id) {
    return inject(EmpSectiontblService)
      .find(id)
      .pipe(
        mergeMap((empSectiontbl: HttpResponse<IEmpSectiontbl>) => {
          if (empSectiontbl.body) {
            return of(empSectiontbl.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default empSectiontblResolve;
