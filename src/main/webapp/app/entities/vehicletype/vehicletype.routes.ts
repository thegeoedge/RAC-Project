import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { VehicletypeComponent } from './list/vehicletype.component';
import { VehicletypeDetailComponent } from './detail/vehicletype-detail.component';
import { VehicletypeUpdateComponent } from './update/vehicletype-update.component';
import VehicletypeResolve from './route/vehicletype-routing-resolve.service';

const vehicletypeRoute: Routes = [
  {
    path: '',
    component: VehicletypeComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VehicletypeDetailComponent,
    resolve: {
      vehicletype: VehicletypeResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VehicletypeUpdateComponent,
    resolve: {
      vehicletype: VehicletypeResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VehicletypeUpdateComponent,
    resolve: {
      vehicletype: VehicletypeResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default vehicletypeRoute;
