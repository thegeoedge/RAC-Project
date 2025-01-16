import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { CompanybankaccountComponent } from './list/companybankaccount.component';
import { CompanybankaccountDetailComponent } from './detail/companybankaccount-detail.component';
import { CompanybankaccountUpdateComponent } from './update/companybankaccount-update.component';
import CompanybankaccountResolve from './route/companybankaccount-routing-resolve.service';

const companybankaccountRoute: Routes = [
  {
    path: '',
    component: CompanybankaccountComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CompanybankaccountDetailComponent,
    resolve: {
      companybankaccount: CompanybankaccountResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CompanybankaccountUpdateComponent,
    resolve: {
      companybankaccount: CompanybankaccountResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CompanybankaccountUpdateComponent,
    resolve: {
      companybankaccount: CompanybankaccountResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default companybankaccountRoute;
