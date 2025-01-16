import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { ILocationbasedstock } from '../locationbasedstock.model';
import { LocationbasedstockService } from '../service/locationbasedstock.service';

import locationbasedstockResolve from './locationbasedstock-routing-resolve.service';

describe('Locationbasedstock routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: LocationbasedstockService;
  let resultLocationbasedstock: ILocationbasedstock | null | undefined;

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
    service = TestBed.inject(LocationbasedstockService);
    resultLocationbasedstock = undefined;
  });

  describe('resolve', () => {
    it('should return ILocationbasedstock returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        locationbasedstockResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultLocationbasedstock = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultLocationbasedstock).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        locationbasedstockResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultLocationbasedstock = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultLocationbasedstock).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<ILocationbasedstock>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        locationbasedstockResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultLocationbasedstock = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultLocationbasedstock).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
