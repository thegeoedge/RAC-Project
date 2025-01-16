import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { EmpSectiontblComponent } from './list/emp-sectiontbl.component';
import { EmpSectiontblDetailComponent } from './detail/emp-sectiontbl-detail.component';
import { EmpSectiontblUpdateComponent } from './update/emp-sectiontbl-update.component';
import EmpSectiontblResolve from './route/emp-sectiontbl-routing-resolve.service';

const empSectiontblRoute: Routes = [
  {
    path: '',
    component: EmpSectiontblComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EmpSectiontblDetailComponent,
    resolve: {
      empSectiontbl: EmpSectiontblResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EmpSectiontblUpdateComponent,
    resolve: {
      empSectiontbl: EmpSectiontblResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EmpSectiontblUpdateComponent,
    resolve: {
      empSectiontbl: EmpSectiontblResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default empSectiontblRoute;
