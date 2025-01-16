import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AutocarecompanyComponent } from './list/autocarecompany.component';
import { AutocarecompanyDetailComponent } from './detail/autocarecompany-detail.component';
import { AutocarecompanyUpdateComponent } from './update/autocarecompany-update.component';
import AutocarecompanyResolve from './route/autocarecompany-routing-resolve.service';

const autocarecompanyRoute: Routes = [
  {
    path: '',
    component: AutocarecompanyComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AutocarecompanyDetailComponent,
    resolve: {
      autocarecompany: AutocarecompanyResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AutocarecompanyUpdateComponent,
    resolve: {
      autocarecompany: AutocarecompanyResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AutocarecompanyUpdateComponent,
    resolve: {
      autocarecompany: AutocarecompanyResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default autocarecompanyRoute;
