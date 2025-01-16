import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { LocationbasedstockComponent } from './list/locationbasedstock.component';
import { LocationbasedstockDetailComponent } from './detail/locationbasedstock-detail.component';
import { LocationbasedstockUpdateComponent } from './update/locationbasedstock-update.component';
import LocationbasedstockResolve from './route/locationbasedstock-routing-resolve.service';

const locationbasedstockRoute: Routes = [
  {
    path: '',
    component: LocationbasedstockComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LocationbasedstockDetailComponent,
    resolve: {
      locationbasedstock: LocationbasedstockResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LocationbasedstockUpdateComponent,
    resolve: {
      locationbasedstock: LocationbasedstockResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LocationbasedstockUpdateComponent,
    resolve: {
      locationbasedstock: LocationbasedstockResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default locationbasedstockRoute;
