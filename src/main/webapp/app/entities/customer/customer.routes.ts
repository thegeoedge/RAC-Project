import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { CustomerComponent } from './list/customer.component';
import { CustomerDetailComponent } from './detail/customer-detail.component';
import { CustomerUpdateComponent } from './update/customer-update.component';
import CustomerResolve from './route/customer-routing-resolve.service';

const customerRoute: Routes = [
  {
    path: '',
    component: CustomerComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CustomerDetailComponent,
    resolve: {
      customer: CustomerResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CustomerUpdateComponent,
    resolve: {
      customer: CustomerResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CustomerUpdateComponent,
    resolve: {
      customer: CustomerResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default customerRoute;
