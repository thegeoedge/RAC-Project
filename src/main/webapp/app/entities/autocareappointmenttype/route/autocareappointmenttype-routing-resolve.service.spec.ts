import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IAutocareappointmenttype } from '../autocareappointmenttype.model';
import { AutocareappointmenttypeService } from '../service/autocareappointmenttype.service';

import autocareappointmenttypeResolve from './autocareappointmenttype-routing-resolve.service';

describe('Autocareappointmenttype routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: AutocareappointmenttypeService;
  let resultAutocareappointmenttype: IAutocareappointmenttype | null | undefined;

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
    service = TestBed.inject(AutocareappointmenttypeService);
    resultAutocareappointmenttype = undefined;
  });

  describe('resolve', () => {
    it('should return IAutocareappointmenttype returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        autocareappointmenttypeResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultAutocareappointmenttype = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAutocareappointmenttype).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        autocareappointmenttypeResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultAutocareappointmenttype = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultAutocareappointmenttype).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IAutocareappointmenttype>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        autocareappointmenttypeResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultAutocareappointmenttype = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAutocareappointmenttype).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
