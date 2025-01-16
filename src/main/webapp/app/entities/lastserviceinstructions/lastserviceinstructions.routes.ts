import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { LastserviceinstructionsComponent } from './list/lastserviceinstructions.component';
import { LastserviceinstructionsDetailComponent } from './detail/lastserviceinstructions-detail.component';
import { LastserviceinstructionsUpdateComponent } from './update/lastserviceinstructions-update.component';
import LastserviceinstructionsResolve from './route/lastserviceinstructions-routing-resolve.service';

const lastserviceinstructionsRoute: Routes = [
  {
    path: '',
    component: LastserviceinstructionsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LastserviceinstructionsDetailComponent,
    resolve: {
      lastserviceinstructions: LastserviceinstructionsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LastserviceinstructionsUpdateComponent,
    resolve: {
      lastserviceinstructions: LastserviceinstructionsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LastserviceinstructionsUpdateComponent,
    resolve: {
      lastserviceinstructions: LastserviceinstructionsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default lastserviceinstructionsRoute;
