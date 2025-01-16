import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { StocklocationsComponent } from './list/stocklocations.component';
import { StocklocationsDetailComponent } from './detail/stocklocations-detail.component';
import { StocklocationsUpdateComponent } from './update/stocklocations-update.component';
import StocklocationsResolve from './route/stocklocations-routing-resolve.service';

const stocklocationsRoute: Routes = [
  {
    path: '',
    component: StocklocationsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StocklocationsDetailComponent,
    resolve: {
      stocklocations: StocklocationsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StocklocationsUpdateComponent,
    resolve: {
      stocklocations: StocklocationsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StocklocationsUpdateComponent,
    resolve: {
      stocklocations: StocklocationsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default stocklocationsRoute;
