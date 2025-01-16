import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IServicecategory } from '../servicecategory.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../servicecategory.test-samples';

import { ServicecategoryService, RestServicecategory } from './servicecategory.service';

const requireRestSample: RestServicecategory = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Servicecategory Service', () => {
  let service: ServicecategoryService;
  let httpMock: HttpTestingController;
  let expectedResult: IServicecategory | IServicecategory[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ServicecategoryService);
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

    it('should create a Servicecategory', () => {
      const servicecategory = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(servicecategory).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Servicecategory', () => {
      const servicecategory = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(servicecategory).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Servicecategory', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Servicecategory', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Servicecategory', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addServicecategoryToCollectionIfMissing', () => {
      it('should add a Servicecategory to an empty array', () => {
        const servicecategory: IServicecategory = sampleWithRequiredData;
        expectedResult = service.addServicecategoryToCollectionIfMissing([], servicecategory);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(servicecategory);
      });

      it('should not add a Servicecategory to an array that contains it', () => {
        const servicecategory: IServicecategory = sampleWithRequiredData;
        const servicecategoryCollection: IServicecategory[] = [
          {
            ...servicecategory,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addServicecategoryToCollectionIfMissing(servicecategoryCollection, servicecategory);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Servicecategory to an array that doesn't contain it", () => {
        const servicecategory: IServicecategory = sampleWithRequiredData;
        const servicecategoryCollection: IServicecategory[] = [sampleWithPartialData];
        expectedResult = service.addServicecategoryToCollectionIfMissing(servicecategoryCollection, servicecategory);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(servicecategory);
      });

      it('should add only unique Servicecategory to an array', () => {
        const servicecategoryArray: IServicecategory[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const servicecategoryCollection: IServicecategory[] = [sampleWithRequiredData];
        expectedResult = service.addServicecategoryToCollectionIfMissing(servicecategoryCollection, ...servicecategoryArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const servicecategory: IServicecategory = sampleWithRequiredData;
        const servicecategory2: IServicecategory = sampleWithPartialData;
        expectedResult = service.addServicecategoryToCollectionIfMissing([], servicecategory, servicecategory2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(servicecategory);
        expect(expectedResult).toContain(servicecategory2);
      });

      it('should accept null and undefined values', () => {
        const servicecategory: IServicecategory = sampleWithRequiredData;
        expectedResult = service.addServicecategoryToCollectionIfMissing([], null, servicecategory, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(servicecategory);
      });

      it('should return initial array if no Servicecategory is added', () => {
        const servicecategoryCollection: IServicecategory[] = [sampleWithRequiredData];
        expectedResult = service.addServicecategoryToCollectionIfMissing(servicecategoryCollection, undefined, null);
        expect(expectedResult).toEqual(servicecategoryCollection);
      });
    });

    describe('compareServicecategory', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareServicecategory(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareServicecategory(entity1, entity2);
        const compareResult2 = service.compareServicecategory(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareServicecategory(entity1, entity2);
        const compareResult2 = service.compareServicecategory(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareServicecategory(entity1, entity2);
        const compareResult2 = service.compareServicecategory(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
