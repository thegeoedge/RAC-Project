import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { VehiclecategoryComponent } from './list/vehiclecategory.component';
import { VehiclecategoryDetailComponent } from './detail/vehiclecategory-detail.component';
import { VehiclecategoryUpdateComponent } from './update/vehiclecategory-update.component';
import VehiclecategoryResolve from './route/vehiclecategory-routing-resolve.service';

const vehiclecategoryRoute: Routes = [
  {
    path: '',
    component: VehiclecategoryComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VehiclecategoryDetailComponent,
    resolve: {
      vehiclecategory: VehiclecategoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VehiclecategoryUpdateComponent,
    resolve: {
      vehiclecategory: VehiclecategoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VehiclecategoryUpdateComponent,
    resolve: {
      vehiclecategory: VehiclecategoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default vehiclecategoryRoute;
