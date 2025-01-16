import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { WorkshopvehicleworkComponent } from './list/workshopvehiclework.component';
import { WorkshopvehicleworkDetailComponent } from './detail/workshopvehiclework-detail.component';
import { WorkshopvehicleworkUpdateComponent } from './update/workshopvehiclework-update.component';
import WorkshopvehicleworkResolve from './route/workshopvehiclework-routing-resolve.service';

const workshopvehicleworkRoute: Routes = [
  {
    path: '',
    component: WorkshopvehicleworkComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: WorkshopvehicleworkDetailComponent,
    resolve: {
      workshopvehiclework: WorkshopvehicleworkResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: WorkshopvehicleworkUpdateComponent,
    resolve: {
      workshopvehiclework: WorkshopvehicleworkResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: WorkshopvehicleworkUpdateComponent,
    resolve: {
      workshopvehiclework: WorkshopvehicleworkResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default workshopvehicleworkRoute;
