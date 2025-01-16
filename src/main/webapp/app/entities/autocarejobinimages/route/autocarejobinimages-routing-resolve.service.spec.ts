import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IAutocarejobinimages } from '../autocarejobinimages.model';
import { AutocarejobinimagesService } from '../service/autocarejobinimages.service';

import autocarejobinimagesResolve from './autocarejobinimages-routing-resolve.service';

describe('Autocarejobinimages routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: AutocarejobinimagesService;
  let resultAutocarejobinimages: IAutocarejobinimages | null | undefined;

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
    service = TestBed.inject(AutocarejobinimagesService);
    resultAutocarejobinimages = undefined;
  });

  describe('resolve', () => {
    it('should return IAutocarejobinimages returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        autocarejobinimagesResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultAutocarejobinimages = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAutocarejobinimages).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        autocarejobinimagesResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultAutocarejobinimages = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultAutocarejobinimages).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IAutocarejobinimages>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        autocarejobinimagesResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultAutocarejobinimages = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAutocarejobinimages).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
