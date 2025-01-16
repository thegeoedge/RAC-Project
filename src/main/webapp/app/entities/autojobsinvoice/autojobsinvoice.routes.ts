import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AutojobsinvoiceComponent } from './list/autojobsinvoice.component';
import { AutojobsinvoiceDetailComponent } from './detail/autojobsinvoice-detail.component';
import { AutojobsinvoiceUpdateComponent } from './update/autojobsinvoice-update.component';
import AutojobsinvoiceResolve from './route/autojobsinvoice-routing-resolve.service';

const autojobsinvoiceRoute: Routes = [
  {
    path: '',
    component: AutojobsinvoiceComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AutojobsinvoiceDetailComponent,
    resolve: {
      autojobsinvoice: AutojobsinvoiceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AutojobsinvoiceUpdateComponent,
    resolve: {
      autojobsinvoice: AutojobsinvoiceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AutojobsinvoiceUpdateComponent,
    resolve: {
      autojobsinvoice: AutojobsinvoiceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default autojobsinvoiceRoute;
