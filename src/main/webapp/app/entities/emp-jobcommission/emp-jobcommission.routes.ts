import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { EmpJobcommissionComponent } from './list/emp-jobcommission.component';
import { EmpJobcommissionDetailComponent } from './detail/emp-jobcommission-detail.component';
import { EmpJobcommissionUpdateComponent } from './update/emp-jobcommission-update.component';
import EmpJobcommissionResolve from './route/emp-jobcommission-routing-resolve.service';

const empJobcommissionRoute: Routes = [
  {
    path: '',
    component: EmpJobcommissionComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EmpJobcommissionDetailComponent,
    resolve: {
      empJobcommission: EmpJobcommissionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EmpJobcommissionUpdateComponent,
    resolve: {
      empJobcommission: EmpJobcommissionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EmpJobcommissionUpdateComponent,
    resolve: {
      empJobcommission: EmpJobcommissionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default empJobcommissionRoute;
