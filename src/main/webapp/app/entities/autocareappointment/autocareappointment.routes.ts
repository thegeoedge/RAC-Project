import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import AutocareappointmentResolve from './route/autocareappointment-routing-resolve.service';

const autocareappointmentRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/autocareappointment.component').then(m => m.AutocareappointmentComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/autocareappointment-detail.component').then(m => m.AutocareappointmentDetailComponent),
    resolve: {
      autocareappointment: AutocareappointmentResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/autocareappointment-update.component').then(m => m.AutocareappointmentUpdateComponent),
    resolve: {
      autocareappointment: AutocareappointmentResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/autocareappointment-update.component').then(m => m.AutocareappointmentUpdateComponent),
    resolve: {
      autocareappointment: AutocareappointmentResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default autocareappointmentRoute;
