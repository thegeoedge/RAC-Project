import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IAutocarecompany } from '../autocarecompany.model';
import { AutocarecompanyService } from '../service/autocarecompany.service';

import autocarecompanyResolve from './autocarecompany-routing-resolve.service';

describe('Autocarecompany routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: AutocarecompanyService;
  let resultAutocarecompany: IAutocarecompany | null | undefined;

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
    service = TestBed.inject(AutocarecompanyService);
    resultAutocarecompany = undefined;
  });

  describe('resolve', () => {
    it('should return IAutocarecompany returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        autocarecompanyResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultAutocarecompany = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAutocarecompany).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        autocarecompanyResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultAutocarecompany = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultAutocarecompany).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IAutocarecompany>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        autocarecompanyResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultAutocarecompany = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAutocarecompany).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
