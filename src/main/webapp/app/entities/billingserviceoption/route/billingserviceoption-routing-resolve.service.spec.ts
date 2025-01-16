import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IBillingserviceoption } from '../billingserviceoption.model';
import { BillingserviceoptionService } from '../service/billingserviceoption.service';

import billingserviceoptionResolve from './billingserviceoption-routing-resolve.service';

describe('Billingserviceoption routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: BillingserviceoptionService;
  let resultBillingserviceoption: IBillingserviceoption | null | undefined;

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
    service = TestBed.inject(BillingserviceoptionService);
    resultBillingserviceoption = undefined;
  });

  describe('resolve', () => {
    it('should return IBillingserviceoption returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        billingserviceoptionResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultBillingserviceoption = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBillingserviceoption).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        billingserviceoptionResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultBillingserviceoption = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultBillingserviceoption).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IBillingserviceoption>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        billingserviceoptionResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultBillingserviceoption = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBillingserviceoption).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
