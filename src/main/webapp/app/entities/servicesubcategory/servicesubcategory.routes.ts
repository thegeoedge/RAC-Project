import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ServicesubcategoryComponent } from './list/servicesubcategory.component';
import { ServicesubcategoryDetailComponent } from './detail/servicesubcategory-detail.component';
import { ServicesubcategoryUpdateComponent } from './update/servicesubcategory-update.component';
import ServicesubcategoryResolve from './route/servicesubcategory-routing-resolve.service';

const servicesubcategoryRoute: Routes = [
  {
    path: '',
    component: ServicesubcategoryComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ServicesubcategoryDetailComponent,
    resolve: {
      servicesubcategory: ServicesubcategoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ServicesubcategoryUpdateComponent,
    resolve: {
      servicesubcategory: ServicesubcategoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ServicesubcategoryUpdateComponent,
    resolve: {
      servicesubcategory: ServicesubcategoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default servicesubcategoryRoute;
