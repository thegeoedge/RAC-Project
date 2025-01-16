import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AutocaretimetableComponent } from './list/autocaretimetable.component';
import { AutocaretimetableDetailComponent } from './detail/autocaretimetable-detail.component';
import { AutocaretimetableUpdateComponent } from './update/autocaretimetable-update.component';
import AutocaretimetableResolve from './route/autocaretimetable-routing-resolve.service';

const autocaretimetableRoute: Routes = [
  {
    path: '',
    component: AutocaretimetableComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AutocaretimetableDetailComponent,
    resolve: {
      autocaretimetable: AutocaretimetableResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AutocaretimetableUpdateComponent,
    resolve: {
      autocaretimetable: AutocaretimetableResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AutocaretimetableUpdateComponent,
    resolve: {
      autocaretimetable: AutocaretimetableResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default autocaretimetableRoute;
