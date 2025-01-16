import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AutocarejobcategoryComponent } from './list/autocarejobcategory.component';
import { AutocarejobcategoryDetailComponent } from './detail/autocarejobcategory-detail.component';
import { AutocarejobcategoryUpdateComponent } from './update/autocarejobcategory-update.component';
import AutocarejobcategoryResolve from './route/autocarejobcategory-routing-resolve.service';

const autocarejobcategoryRoute: Routes = [
  {
    path: '',
    component: AutocarejobcategoryComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AutocarejobcategoryDetailComponent,
    resolve: {
      autocarejobcategory: AutocarejobcategoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AutocarejobcategoryUpdateComponent,
    resolve: {
      autocarejobcategory: AutocarejobcategoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AutocarejobcategoryUpdateComponent,
    resolve: {
      autocarejobcategory: AutocarejobcategoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default autocarejobcategoryRoute;
