import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { SalesinvoiceComponent } from './list/salesinvoice.component';
import { SalesinvoiceDetailComponent } from './detail/salesinvoice-detail.component';
import { SalesinvoiceUpdateComponent } from './update/salesinvoice-update.component';
import SalesinvoiceResolve from './route/salesinvoice-routing-resolve.service';

const salesinvoiceRoute: Routes = [
  {
    path: '',
    component: SalesinvoiceComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SalesinvoiceDetailComponent,
    resolve: {
      salesinvoice: SalesinvoiceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SalesinvoiceUpdateComponent,
    resolve: {
      salesinvoice: SalesinvoiceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SalesinvoiceUpdateComponent,
    resolve: {
      salesinvoice: SalesinvoiceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default salesinvoiceRoute;
