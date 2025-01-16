import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AutocareservicemillagesComponent } from './list/autocareservicemillages.component';
import { AutocareservicemillagesDetailComponent } from './detail/autocareservicemillages-detail.component';
import { AutocareservicemillagesUpdateComponent } from './update/autocareservicemillages-update.component';
import AutocareservicemillagesResolve from './route/autocareservicemillages-routing-resolve.service';

const autocareservicemillagesRoute: Routes = [
  {
    path: '',
    component: AutocareservicemillagesComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AutocareservicemillagesDetailComponent,
    resolve: {
      autocareservicemillages: AutocareservicemillagesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AutocareservicemillagesUpdateComponent,
    resolve: {
      autocareservicemillages: AutocareservicemillagesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AutocareservicemillagesUpdateComponent,
    resolve: {
      autocareservicemillages: AutocareservicemillagesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default autocareservicemillagesRoute;
