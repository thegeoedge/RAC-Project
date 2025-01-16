import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IVehiclecategory } from '../vehiclecategory.model';
import { VehiclecategoryService } from '../service/vehiclecategory.service';

import vehiclecategoryResolve from './vehiclecategory-routing-resolve.service';

describe('Vehiclecategory routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: VehiclecategoryService;
  let resultVehiclecategory: IVehiclecategory | null | undefined;

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
    service = TestBed.inject(VehiclecategoryService);
    resultVehiclecategory = undefined;
  });

  describe('resolve', () => {
    it('should return IVehiclecategory returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        vehiclecategoryResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultVehiclecategory = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultVehiclecategory).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        vehiclecategoryResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultVehiclecategory = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultVehiclecategory).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IVehiclecategory>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        vehiclecategoryResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultVehiclecategory = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultVehiclecategory).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
