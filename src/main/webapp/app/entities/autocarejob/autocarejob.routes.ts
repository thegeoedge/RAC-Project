import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AutocarejobComponent } from './list/autocarejob.component';
import { AutocarejobDetailComponent } from './detail/autocarejob-detail.component';
import { AutocarejobUpdateComponent } from './update/autocarejob-update.component';
import AutocarejobResolve from './route/autocarejob-routing-resolve.service';

const autocarejobRoute: Routes = [
  {
    path: '',
    component: AutocarejobComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AutocarejobDetailComponent,
    resolve: {
      autocarejob: AutocarejobResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AutocarejobUpdateComponent,
    resolve: {
      autocarejob: AutocarejobResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AutocarejobUpdateComponent,
    resolve: {
      autocarejob: AutocarejobResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default autocarejobRoute;
