import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IAutocarejobcategory } from '../autocarejobcategory.model';
import { AutocarejobcategoryService } from '../service/autocarejobcategory.service';

import autocarejobcategoryResolve from './autocarejobcategory-routing-resolve.service';

describe('Autocarejobcategory routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: AutocarejobcategoryService;
  let resultAutocarejobcategory: IAutocarejobcategory | null | undefined;

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
    service = TestBed.inject(AutocarejobcategoryService);
    resultAutocarejobcategory = undefined;
  });

  describe('resolve', () => {
    it('should return IAutocarejobcategory returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        autocarejobcategoryResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultAutocarejobcategory = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAutocarejobcategory).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        autocarejobcategoryResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultAutocarejobcategory = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultAutocarejobcategory).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IAutocarejobcategory>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        autocarejobcategoryResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultAutocarejobcategory = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAutocarejobcategory).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
