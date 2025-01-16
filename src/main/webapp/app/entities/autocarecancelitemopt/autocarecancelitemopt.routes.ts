import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AutocarecancelitemoptComponent } from './list/autocarecancelitemopt.component';
import { AutocarecancelitemoptDetailComponent } from './detail/autocarecancelitemopt-detail.component';
import { AutocarecancelitemoptUpdateComponent } from './update/autocarecancelitemopt-update.component';
import AutocarecancelitemoptResolve from './route/autocarecancelitemopt-routing-resolve.service';

const autocarecancelitemoptRoute: Routes = [
  {
    path: '',
    component: AutocarecancelitemoptComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AutocarecancelitemoptDetailComponent,
    resolve: {
      autocarecancelitemopt: AutocarecancelitemoptResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AutocarecancelitemoptUpdateComponent,
    resolve: {
      autocarecancelitemopt: AutocarecancelitemoptResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AutocarecancelitemoptUpdateComponent,
    resolve: {
      autocarecancelitemopt: AutocarecancelitemoptResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default autocarecancelitemoptRoute;
