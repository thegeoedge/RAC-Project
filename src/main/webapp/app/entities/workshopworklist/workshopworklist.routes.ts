import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { WorkshopworklistComponent } from './list/workshopworklist.component';
import { WorkshopworklistDetailComponent } from './detail/workshopworklist-detail.component';
import { WorkshopworklistUpdateComponent } from './update/workshopworklist-update.component';
import WorkshopworklistResolve from './route/workshopworklist-routing-resolve.service';

const workshopworklistRoute: Routes = [
  {
    path: '',
    component: WorkshopworklistComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: WorkshopworklistDetailComponent,
    resolve: {
      workshopworklist: WorkshopworklistResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: WorkshopworklistUpdateComponent,
    resolve: {
      workshopworklist: WorkshopworklistResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: WorkshopworklistUpdateComponent,
    resolve: {
      workshopworklist: WorkshopworklistResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default workshopworklistRoute;
