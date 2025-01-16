import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IVehiclebrandmodel } from '../vehiclebrandmodel.model';
import { VehiclebrandmodelService } from '../service/vehiclebrandmodel.service';

import vehiclebrandmodelResolve from './vehiclebrandmodel-routing-resolve.service';

describe('Vehiclebrandmodel routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: VehiclebrandmodelService;
  let resultVehiclebrandmodel: IVehiclebrandmodel | null | undefined;

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
    service = TestBed.inject(VehiclebrandmodelService);
    resultVehiclebrandmodel = undefined;
  });

  describe('resolve', () => {
    it('should return IVehiclebrandmodel returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        vehiclebrandmodelResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultVehiclebrandmodel = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultVehiclebrandmodel).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        vehiclebrandmodelResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultVehiclebrandmodel = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultVehiclebrandmodel).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IVehiclebrandmodel>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        vehiclebrandmodelResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultVehiclebrandmodel = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultVehiclebrandmodel).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
