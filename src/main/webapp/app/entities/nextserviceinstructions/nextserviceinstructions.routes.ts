import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { NextserviceinstructionsComponent } from './list/nextserviceinstructions.component';
import { NextserviceinstructionsDetailComponent } from './detail/nextserviceinstructions-detail.component';
import { NextserviceinstructionsUpdateComponent } from './update/nextserviceinstructions-update.component';
import NextserviceinstructionsResolve from './route/nextserviceinstructions-routing-resolve.service';

const nextserviceinstructionsRoute: Routes = [
  {
    path: '',
    component: NextserviceinstructionsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NextserviceinstructionsDetailComponent,
    resolve: {
      nextserviceinstructions: NextserviceinstructionsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NextserviceinstructionsUpdateComponent,
    resolve: {
      nextserviceinstructions: NextserviceinstructionsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NextserviceinstructionsUpdateComponent,
    resolve: {
      nextserviceinstructions: NextserviceinstructionsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default nextserviceinstructionsRoute;
