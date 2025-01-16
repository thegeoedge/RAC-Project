import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { BankbranchComponent } from './list/bankbranch.component';
import { BankbranchDetailComponent } from './detail/bankbranch-detail.component';
import { BankbranchUpdateComponent } from './update/bankbranch-update.component';
import BankbranchResolve from './route/bankbranch-routing-resolve.service';

const bankbranchRoute: Routes = [
  {
    path: '',
    component: BankbranchComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BankbranchDetailComponent,
    resolve: {
      bankbranch: BankbranchResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BankbranchUpdateComponent,
    resolve: {
      bankbranch: BankbranchResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BankbranchUpdateComponent,
    resolve: {
      bankbranch: BankbranchResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default bankbranchRoute;
