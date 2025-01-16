import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { CommonserviceoptionComponent } from './list/commonserviceoption.component';
import { CommonserviceoptionDetailComponent } from './detail/commonserviceoption-detail.component';
import { CommonserviceoptionUpdateComponent } from './update/commonserviceoption-update.component';
import CommonserviceoptionResolve from './route/commonserviceoption-routing-resolve.service';

const commonserviceoptionRoute: Routes = [
  {
    path: '',
    component: CommonserviceoptionComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CommonserviceoptionDetailComponent,
    resolve: {
      commonserviceoption: CommonserviceoptionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CommonserviceoptionUpdateComponent,
    resolve: {
      commonserviceoption: CommonserviceoptionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CommonserviceoptionUpdateComponent,
    resolve: {
      commonserviceoption: CommonserviceoptionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default commonserviceoptionRoute;
