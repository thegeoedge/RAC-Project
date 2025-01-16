import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { BillingserviceoptionvaluesComponent } from './list/billingserviceoptionvalues.component';
import { BillingserviceoptionvaluesDetailComponent } from './detail/billingserviceoptionvalues-detail.component';
import { BillingserviceoptionvaluesUpdateComponent } from './update/billingserviceoptionvalues-update.component';
import BillingserviceoptionvaluesResolve from './route/billingserviceoptionvalues-routing-resolve.service';

const billingserviceoptionvaluesRoute: Routes = [
  {
    path: '',
    component: BillingserviceoptionvaluesComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BillingserviceoptionvaluesDetailComponent,
    resolve: {
      billingserviceoptionvalues: BillingserviceoptionvaluesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BillingserviceoptionvaluesUpdateComponent,
    resolve: {
      billingserviceoptionvalues: BillingserviceoptionvaluesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BillingserviceoptionvaluesUpdateComponent,
    resolve: {
      billingserviceoptionvalues: BillingserviceoptionvaluesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default billingserviceoptionvaluesRoute;
