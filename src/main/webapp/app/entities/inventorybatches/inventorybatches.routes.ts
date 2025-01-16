import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { InventorybatchesComponent } from './list/inventorybatches.component';
import { InventorybatchesDetailComponent } from './detail/inventorybatches-detail.component';
import { InventorybatchesUpdateComponent } from './update/inventorybatches-update.component';
import InventorybatchesResolve from './route/inventorybatches-routing-resolve.service';

const inventorybatchesRoute: Routes = [
  {
    path: '',
    component: InventorybatchesComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InventorybatchesDetailComponent,
    resolve: {
      inventorybatches: InventorybatchesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InventorybatchesUpdateComponent,
    resolve: {
      inventorybatches: InventorybatchesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InventorybatchesUpdateComponent,
    resolve: {
      inventorybatches: InventorybatchesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default inventorybatchesRoute;
