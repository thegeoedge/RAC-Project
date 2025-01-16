import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { PaymenttermComponent } from './list/paymentterm.component';
import { PaymenttermDetailComponent } from './detail/paymentterm-detail.component';
import { PaymenttermUpdateComponent } from './update/paymentterm-update.component';
import PaymenttermResolve from './route/paymentterm-routing-resolve.service';

const paymenttermRoute: Routes = [
  {
    path: '',
    component: PaymenttermComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PaymenttermDetailComponent,
    resolve: {
      paymentterm: PaymenttermResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PaymenttermUpdateComponent,
    resolve: {
      paymentterm: PaymenttermResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PaymenttermUpdateComponent,
    resolve: {
      paymentterm: PaymenttermResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default paymenttermRoute;
