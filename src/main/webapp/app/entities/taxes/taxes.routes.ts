import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { TaxesComponent } from './list/taxes.component';
import { TaxesDetailComponent } from './detail/taxes-detail.component';
import { TaxesUpdateComponent } from './update/taxes-update.component';
import TaxesResolve from './route/taxes-routing-resolve.service';

const taxesRoute: Routes = [
  {
    path: '',
    component: TaxesComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TaxesDetailComponent,
    resolve: {
      taxes: TaxesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TaxesUpdateComponent,
    resolve: {
      taxes: TaxesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TaxesUpdateComponent,
    resolve: {
      taxes: TaxesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default taxesRoute;
