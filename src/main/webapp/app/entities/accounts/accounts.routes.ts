import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AccountsComponent } from './list/accounts.component';
import { AccountsDetailComponent } from './detail/accounts-detail.component';
import { AccountsUpdateComponent } from './update/accounts-update.component';
import AccountsResolve from './route/accounts-routing-resolve.service';

const accountsRoute: Routes = [
  {
    path: '',
    component: AccountsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AccountsDetailComponent,
    resolve: {
      accounts: AccountsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AccountsUpdateComponent,
    resolve: {
      accounts: AccountsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AccountsUpdateComponent,
    resolve: {
      accounts: AccountsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default accountsRoute;
