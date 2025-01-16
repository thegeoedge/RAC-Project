import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IServicesubcategory } from '../servicesubcategory.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../servicesubcategory.test-samples';

import { ServicesubcategoryService, RestServicesubcategory } from './servicesubcategory.service';

const requireRestSample: RestServicesubcategory = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Servicesubcategory Service', () => {
  let service: ServicesubcategoryService;
  let httpMock: HttpTestingController;
  let expectedResult: IServicesubcategory | IServicesubcategory[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ServicesubcategoryService);
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

    it('should create a Servicesubcategory', () => {
      const servicesubcategory = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(servicesubcategory).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Servicesubcategory', () => {
      const servicesubcategory = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(servicesubcategory).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Servicesubcategory', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Servicesubcategory', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Servicesubcategory', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addServicesubcategoryToCollectionIfMissing', () => {
      it('should add a Servicesubcategory to an empty array', () => {
        const servicesubcategory: IServicesubcategory = sampleWithRequiredData;
        expectedResult = service.addServicesubcategoryToCollectionIfMissing([], servicesubcategory);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(servicesubcategory);
      });

      it('should not add a Servicesubcategory to an array that contains it', () => {
        const servicesubcategory: IServicesubcategory = sampleWithRequiredData;
        const servicesubcategoryCollection: IServicesubcategory[] = [
          {
            ...servicesubcategory,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addServicesubcategoryToCollectionIfMissing(servicesubcategoryCollection, servicesubcategory);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Servicesubcategory to an array that doesn't contain it", () => {
        const servicesubcategory: IServicesubcategory = sampleWithRequiredData;
        const servicesubcategoryCollection: IServicesubcategory[] = [sampleWithPartialData];
        expectedResult = service.addServicesubcategoryToCollectionIfMissing(servicesubcategoryCollection, servicesubcategory);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(servicesubcategory);
      });

      it('should add only unique Servicesubcategory to an array', () => {
        const servicesubcategoryArray: IServicesubcategory[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const servicesubcategoryCollection: IServicesubcategory[] = [sampleWithRequiredData];
        expectedResult = service.addServicesubcategoryToCollectionIfMissing(servicesubcategoryCollection, ...servicesubcategoryArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const servicesubcategory: IServicesubcategory = sampleWithRequiredData;
        const servicesubcategory2: IServicesubcategory = sampleWithPartialData;
        expectedResult = service.addServicesubcategoryToCollectionIfMissing([], servicesubcategory, servicesubcategory2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(servicesubcategory);
        expect(expectedResult).toContain(servicesubcategory2);
      });

      it('should accept null and undefined values', () => {
        const servicesubcategory: IServicesubcategory = sampleWithRequiredData;
        expectedResult = service.addServicesubcategoryToCollectionIfMissing([], null, servicesubcategory, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(servicesubcategory);
      });

      it('should return initial array if no Servicesubcategory is added', () => {
        const servicesubcategoryCollection: IServicesubcategory[] = [sampleWithRequiredData];
        expectedResult = service.addServicesubcategoryToCollectionIfMissing(servicesubcategoryCollection, undefined, null);
        expect(expectedResult).toEqual(servicesubcategoryCollection);
      });
    });

    describe('compareServicesubcategory', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareServicesubcategory(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareServicesubcategory(entity1, entity2);
        const compareResult2 = service.compareServicesubcategory(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareServicesubcategory(entity1, entity2);
        const compareResult2 = service.compareServicesubcategory(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareServicesubcategory(entity1, entity2);
        const compareResult2 = service.compareServicesubcategory(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
