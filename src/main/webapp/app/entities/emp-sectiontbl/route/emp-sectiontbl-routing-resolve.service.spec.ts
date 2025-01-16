import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IEmpSectiontbl } from '../emp-sectiontbl.model';
import { EmpSectiontblService } from '../service/emp-sectiontbl.service';

import empSectiontblResolve from './emp-sectiontbl-routing-resolve.service';

describe('EmpSectiontbl routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: EmpSectiontblService;
  let resultEmpSectiontbl: IEmpSectiontbl | null | undefined;

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
    service = TestBed.inject(EmpSectiontblService);
    resultEmpSectiontbl = undefined;
  });

  describe('resolve', () => {
    it('should return IEmpSectiontbl returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        empSectiontblResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultEmpSectiontbl = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultEmpSectiontbl).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        empSectiontblResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultEmpSectiontbl = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultEmpSectiontbl).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IEmpSectiontbl>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        empSectiontblResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultEmpSectiontbl = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultEmpSectiontbl).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
