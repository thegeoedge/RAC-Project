import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IBillingserviceoptionvalues } from '../billingserviceoptionvalues.model';
import { BillingserviceoptionvaluesService } from '../service/billingserviceoptionvalues.service';

import billingserviceoptionvaluesResolve from './billingserviceoptionvalues-routing-resolve.service';

describe('Billingserviceoptionvalues routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: BillingserviceoptionvaluesService;
  let resultBillingserviceoptionvalues: IBillingserviceoptionvalues | null | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    service = TestBed.inject(BillingserviceoptionvaluesService);
    resultBillingserviceoptionvalues = undefined;
  });

  describe('resolve', () => {
    it('should return IBillingserviceoptionvalues returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        billingserviceoptionvaluesResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultBillingserviceoptionvalues = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBillingserviceoptionvalues).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        billingserviceoptionvaluesResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultBillingserviceoptionvalues = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultBillingserviceoptionvalues).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IBillingserviceoptionvalues>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        billingserviceoptionvaluesResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultBillingserviceoptionvalues = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBillingserviceoptionvalues).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
