import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AutocarejobinimagesComponent } from './list/autocarejobinimages.component';
import { AutocarejobinimagesDetailComponent } from './detail/autocarejobinimages-detail.component';
import { AutocarejobinimagesUpdateComponent } from './update/autocarejobinimages-update.component';
import AutocarejobinimagesResolve from './route/autocarejobinimages-routing-resolve.service';

const autocarejobinimagesRoute: Routes = [
  {
    path: '',
    component: AutocarejobinimagesComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AutocarejobinimagesDetailComponent,
    resolve: {
      autocarejobinimages: AutocarejobinimagesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AutocarejobinimagesUpdateComponent,
    resolve: {
      autocarejobinimages: AutocarejobinimagesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AutocarejobinimagesUpdateComponent,
    resolve: {
      autocarejobinimages: AutocarejobinimagesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default autocarejobinimagesRoute;
