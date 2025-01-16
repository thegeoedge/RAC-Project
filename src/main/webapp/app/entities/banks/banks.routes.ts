import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { BanksComponent } from './list/banks.component';
import { BanksDetailComponent } from './detail/banks-detail.component';
import { BanksUpdateComponent } from './update/banks-update.component';
import BanksResolve from './route/banks-routing-resolve.service';

const banksRoute: Routes = [
  {
    path: '',
    component: BanksComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BanksDetailComponent,
    resolve: {
      banks: BanksResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BanksUpdateComponent,
    resolve: {
      banks: BanksResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BanksUpdateComponent,
    resolve: {
      banks: BanksResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default banksRoute;
