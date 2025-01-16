import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { HoisttypeComponent } from './list/hoisttype.component';
import { HoisttypeDetailComponent } from './detail/hoisttype-detail.component';
import { HoisttypeUpdateComponent } from './update/hoisttype-update.component';
import HoisttypeResolve from './route/hoisttype-routing-resolve.service';

const hoisttypeRoute: Routes = [
  {
    path: '',
    component: HoisttypeComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: HoisttypeDetailComponent,
    resolve: {
      hoisttype: HoisttypeResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: HoisttypeUpdateComponent,
    resolve: {
      hoisttype: HoisttypeResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: HoisttypeUpdateComponent,
    resolve: {
      hoisttype: HoisttypeResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default hoisttypeRoute;
