import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IEmpJobcommission } from '../emp-jobcommission.model';
import { EmpJobcommissionService } from '../service/emp-jobcommission.service';

import empJobcommissionResolve from './emp-jobcommission-routing-resolve.service';

describe('EmpJobcommission routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: EmpJobcommissionService;
  let resultEmpJobcommission: IEmpJobcommission | null | undefined;

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
    service = TestBed.inject(EmpJobcommissionService);
    resultEmpJobcommission = undefined;
  });

  describe('resolve', () => {
    it('should return IEmpJobcommission returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        empJobcommissionResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultEmpJobcommission = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultEmpJobcommission).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        empJobcommissionResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultEmpJobcommission = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultEmpJobcommission).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IEmpJobcommission>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        empJobcommissionResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultEmpJobcommission = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultEmpJobcommission).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
