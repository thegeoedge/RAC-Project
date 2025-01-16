import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AutocarehoistComponent } from './list/autocarehoist.component';
import { AutocarehoistDetailComponent } from './detail/autocarehoist-detail.component';
import { AutocarehoistUpdateComponent } from './update/autocarehoist-update.component';
import AutocarehoistResolve from './route/autocarehoist-routing-resolve.service';

const autocarehoistRoute: Routes = [
  {
    path: '',
    component: AutocarehoistComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AutocarehoistDetailComponent,
    resolve: {
      autocarehoist: AutocarehoistResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AutocarehoistUpdateComponent,
    resolve: {
      autocarehoist: AutocarehoistResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AutocarehoistUpdateComponent,
    resolve: {
      autocarehoist: AutocarehoistResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default autocarehoistRoute;
