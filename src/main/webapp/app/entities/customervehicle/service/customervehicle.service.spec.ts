import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ICustomervehicle } from '../customervehicle.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../customervehicle.test-samples';

import { CustomervehicleService, RestCustomervehicle } from './customervehicle.service';

const requireRestSample: RestCustomervehicle = {
  ...sampleWithRequiredData,
  lastservicedate: sampleWithRequiredData.lastservicedate?.format(DATE_FORMAT),
  nextservicedate: sampleWithRequiredData.nextservicedate?.format(DATE_FORMAT),
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Customervehicle Service', () => {
  let service: CustomervehicleService;
  let httpMock: HttpTestingController;
  let expectedResult: ICustomervehicle | ICustomervehicle[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(CustomervehicleService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Customervehicle', () => {
      const customervehicle = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(customervehicle).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Customervehicle', () => {
      const customervehicle = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(customervehicle).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Customervehicle', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Customervehicle', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Customervehicle', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCustomervehicleToCollectionIfMissing', () => {
      it('should add a Customervehicle to an empty array', () => {
        const customervehicle: ICustomervehicle = sampleWithRequiredData;
        expectedResult = service.addCustomervehicleToCollectionIfMissing([], customervehicle);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(customervehicle);
      });

      it('should not add a Customervehicle to an array that contains it', () => {
        const customervehicle: ICustomervehicle = sampleWithRequiredData;
        const customervehicleCollection: ICustomervehicle[] = [
          {
            ...customervehicle,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCustomervehicleToCollectionIfMissing(customervehicleCollection, customervehicle);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Customervehicle to an array that doesn't contain it", () => {
        const customervehicle: ICustomervehicle = sampleWithRequiredData;
        const customervehicleCollection: ICustomervehicle[] = [sampleWithPartialData];
        expectedResult = service.addCustomervehicleToCollectionIfMissing(customervehicleCollection, customervehicle);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(customervehicle);
      });

      it('should add only unique Customervehicle to an array', () => {
        const customervehicleArray: ICustomervehicle[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const customervehicleCollection: ICustomervehicle[] = [sampleWithRequiredData];
        expectedResult = service.addCustomervehicleToCollectionIfMissing(customervehicleCollection, ...customervehicleArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const customervehicle: ICustomervehicle = sampleWithRequiredData;
        const customervehicle2: ICustomervehicle = sampleWithPartialData;
        expectedResult = service.addCustomervehicleToCollectionIfMissing([], customervehicle, customervehicle2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(customervehicle);
        expect(expectedResult).toContain(customervehicle2);
      });

      it('should accept null and undefined values', () => {
        const customervehicle: ICustomervehicle = sampleWithRequiredData;
        expectedResult = service.addCustomervehicleToCollectionIfMissing([], null, customervehicle, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(customervehicle);
      });

      it('should return initial array if no Customervehicle is added', () => {
        const customervehicleCollection: ICustomervehicle[] = [sampleWithRequiredData];
        expectedResult = service.addCustomervehicleToCollectionIfMissing(customervehicleCollection, undefined, null);
        expect(expectedResult).toEqual(customervehicleCollection);
      });
    });

    describe('compareCustomervehicle', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCustomervehicle(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCustomervehicle(entity1, entity2);
        const compareResult2 = service.compareCustomervehicle(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCustomervehicle(entity1, entity2);
        const compareResult2 = service.compareCustomervehicle(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCustomervehicle(entity1, entity2);
        const compareResult2 = service.compareCustomervehicle(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
