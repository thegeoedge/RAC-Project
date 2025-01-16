import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ServicecategoryComponent } from './list/servicecategory.component';
import { ServicecategoryDetailComponent } from './detail/servicecategory-detail.component';
import { ServicecategoryUpdateComponent } from './update/servicecategory-update.component';
import ServicecategoryResolve from './route/servicecategory-routing-resolve.service';

const servicecategoryRoute: Routes = [
  {
    path: '',
    component: ServicecategoryComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ServicecategoryDetailComponent,
    resolve: {
      servicecategory: ServicecategoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ServicecategoryUpdateComponent,
    resolve: {
      servicecategory: ServicecategoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ServicecategoryUpdateComponent,
    resolve: {
      servicecategory: ServicecategoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default servicecategoryRoute;
