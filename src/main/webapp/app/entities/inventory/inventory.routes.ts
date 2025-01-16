import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { InventoryComponent } from './list/inventory.component';
import { InventoryDetailComponent } from './detail/inventory-detail.component';
import { InventoryUpdateComponent } from './update/inventory-update.component';
import InventoryResolve from './route/inventory-routing-resolve.service';

const inventoryRoute: Routes = [
  {
    path: '',
    component: InventoryComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InventoryDetailComponent,
    resolve: {
      inventory: InventoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InventoryUpdateComponent,
    resolve: {
      inventory: InventoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InventoryUpdateComponent,
    resolve: {
      inventory: InventoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default inventoryRoute;
