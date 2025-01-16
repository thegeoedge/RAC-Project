import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { SalesorderComponent } from './list/salesorder.component';
import { SalesorderDetailComponent } from './detail/salesorder-detail.component';
import { SalesorderUpdateComponent } from './update/salesorder-update.component';
import SalesorderResolve from './route/salesorder-routing-resolve.service';

const salesorderRoute: Routes = [
  {
    path: '',
    component: SalesorderComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SalesorderDetailComponent,
    resolve: {
      salesorder: SalesorderResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SalesorderUpdateComponent,
    resolve: {
      salesorder: SalesorderResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SalesorderUpdateComponent,
    resolve: {
      salesorder: SalesorderResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default salesorderRoute;
