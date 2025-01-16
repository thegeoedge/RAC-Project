import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IWorkshopvehiclework } from '../workshopvehiclework.model';
import { WorkshopvehicleworkService } from '../service/workshopvehiclework.service';

import workshopvehicleworkResolve from './workshopvehiclework-routing-resolve.service';

describe('Workshopvehiclework routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: WorkshopvehicleworkService;
  let resultWorkshopvehiclework: IWorkshopvehiclework | null | undefined;

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
    service = TestBed.inject(WorkshopvehicleworkService);
    resultWorkshopvehiclework = undefined;
  });

  describe('resolve', () => {
    it('should return IWorkshopvehiclework returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        workshopvehicleworkResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultWorkshopvehiclework = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultWorkshopvehiclework).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        workshopvehicleworkResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultWorkshopvehiclework = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultWorkshopvehiclework).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IWorkshopvehiclework>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        workshopvehicleworkResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultWorkshopvehiclework = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultWorkshopvehiclework).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
