import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AutojobempallocationComponent } from './list/autojobempallocation.component';
import { AutojobempallocationDetailComponent } from './detail/autojobempallocation-detail.component';
import { AutojobempallocationUpdateComponent } from './update/autojobempallocation-update.component';
import AutojobempallocationResolve from './route/autojobempallocation-routing-resolve.service';

const autojobempallocationRoute: Routes = [
  {
    path: '',
    component: AutojobempallocationComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AutojobempallocationDetailComponent,
    resolve: {
      autojobempallocation: AutojobempallocationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AutojobempallocationUpdateComponent,
    resolve: {
      autojobempallocation: AutojobempallocationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AutojobempallocationUpdateComponent,
    resolve: {
      autojobempallocation: AutojobempallocationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default autojobempallocationRoute;
