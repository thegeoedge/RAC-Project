import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { VehiclebrandnameComponent } from './list/vehiclebrandname.component';
import { VehiclebrandnameDetailComponent } from './detail/vehiclebrandname-detail.component';
import { VehiclebrandnameUpdateComponent } from './update/vehiclebrandname-update.component';
import VehiclebrandnameResolve from './route/vehiclebrandname-routing-resolve.service';

const vehiclebrandnameRoute: Routes = [
  {
    path: '',
    component: VehiclebrandnameComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VehiclebrandnameDetailComponent,
    resolve: {
      vehiclebrandname: VehiclebrandnameResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VehiclebrandnameUpdateComponent,
    resolve: {
      vehiclebrandname: VehiclebrandnameResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VehiclebrandnameUpdateComponent,
    resolve: {
      vehiclebrandname: VehiclebrandnameResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default vehiclebrandnameRoute;
