import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { VehiclebrandmodelComponent } from './list/vehiclebrandmodel.component';
import { VehiclebrandmodelDetailComponent } from './detail/vehiclebrandmodel-detail.component';
import { VehiclebrandmodelUpdateComponent } from './update/vehiclebrandmodel-update.component';
import VehiclebrandmodelResolve from './route/vehiclebrandmodel-routing-resolve.service';

const vehiclebrandmodelRoute: Routes = [
  {
    path: '',
    component: VehiclebrandmodelComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VehiclebrandmodelDetailComponent,
    resolve: {
      vehiclebrandmodel: VehiclebrandmodelResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VehiclebrandmodelUpdateComponent,
    resolve: {
      vehiclebrandmodel: VehiclebrandmodelResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VehiclebrandmodelUpdateComponent,
    resolve: {
      vehiclebrandmodel: VehiclebrandmodelResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default vehiclebrandmodelRoute;
