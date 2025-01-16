import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ReceiptComponent } from './list/receipt.component';
import { ReceiptDetailComponent } from './detail/receipt-detail.component';
import { ReceiptUpdateComponent } from './update/receipt-update.component';
import ReceiptResolve from './route/receipt-routing-resolve.service';

const receiptRoute: Routes = [
  {
    path: '',
    component: ReceiptComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReceiptDetailComponent,
    resolve: {
      receipt: ReceiptResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReceiptUpdateComponent,
    resolve: {
      receipt: ReceiptResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReceiptUpdateComponent,
    resolve: {
      receipt: ReceiptResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default receiptRoute;
