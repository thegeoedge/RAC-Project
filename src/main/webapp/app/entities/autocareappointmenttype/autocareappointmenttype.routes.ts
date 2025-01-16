import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AutocareappointmenttypeComponent } from './list/autocareappointmenttype.component';
import { AutocareappointmenttypeDetailComponent } from './detail/autocareappointmenttype-detail.component';
import { AutocareappointmenttypeUpdateComponent } from './update/autocareappointmenttype-update.component';
import AutocareappointmenttypeResolve from './route/autocareappointmenttype-routing-resolve.service';

const autocareappointmenttypeRoute: Routes = [
  {
    path: '',
    component: AutocareappointmenttypeComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AutocareappointmenttypeDetailComponent,
    resolve: {
      autocareappointmenttype: AutocareappointmenttypeResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AutocareappointmenttypeUpdateComponent,
    resolve: {
      autocareappointmenttype: AutocareappointmenttypeResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AutocareappointmenttypeUpdateComponent,
    resolve: {
      autocareappointmenttype: AutocareappointmenttypeResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default autocareappointmenttypeRoute;
