import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { BillingserviceoptionComponent } from './list/billingserviceoption.component';
import { BillingserviceoptionDetailComponent } from './detail/billingserviceoption-detail.component';
import { BillingserviceoptionUpdateComponent } from './update/billingserviceoption-update.component';
import BillingserviceoptionResolve from './route/billingserviceoption-routing-resolve.service';

const billingserviceoptionRoute: Routes = [
  {
    path: '',
    component: BillingserviceoptionComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BillingserviceoptionDetailComponent,
    resolve: {
      billingserviceoption: BillingserviceoptionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BillingserviceoptionUpdateComponent,
    resolve: {
      billingserviceoption: BillingserviceoptionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BillingserviceoptionUpdateComponent,
    resolve: {
      billingserviceoption: BillingserviceoptionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default billingserviceoptionRoute;
