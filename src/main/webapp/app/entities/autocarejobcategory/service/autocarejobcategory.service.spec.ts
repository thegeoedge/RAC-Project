import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAutocarejobcategory } from '../autocarejobcategory.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../autocarejobcategory.test-samples';

import { AutocarejobcategoryService, RestAutocarejobcategory } from './autocarejobcategory.service';

const requireRestSample: RestAutocarejobcategory = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Autocarejobcategory Service', () => {
  let service: AutocarejobcategoryService;
  let httpMock: HttpTestingController;
  let expectedResult: IAutocarejobcategory | IAutocarejobcategory[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AutocarejobcategoryService);
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

    it('should create a Autocarejobcategory', () => {
      const autocarejobcategory = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(autocarejobcategory).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Autocarejobcategory', () => {
      const autocarejobcategory = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(autocarejobcategory).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Autocarejobcategory', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Autocarejobcategory', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Autocarejobcategory', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAutocarejobcategoryToCollectionIfMissing', () => {
      it('should add a Autocarejobcategory to an empty array', () => {
        const autocarejobcategory: IAutocarejobcategory = sampleWithRequiredData;
        expectedResult = service.addAutocarejobcategoryToCollectionIfMissing([], autocarejobcategory);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocarejobcategory);
      });

      it('should not add a Autocarejobcategory to an array that contains it', () => {
        const autocarejobcategory: IAutocarejobcategory = sampleWithRequiredData;
        const autocarejobcategoryCollection: IAutocarejobcategory[] = [
          {
            ...autocarejobcategory,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAutocarejobcategoryToCollectionIfMissing(autocarejobcategoryCollection, autocarejobcategory);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Autocarejobcategory to an array that doesn't contain it", () => {
        const autocarejobcategory: IAutocarejobcategory = sampleWithRequiredData;
        const autocarejobcategoryCollection: IAutocarejobcategory[] = [sampleWithPartialData];
        expectedResult = service.addAutocarejobcategoryToCollectionIfMissing(autocarejobcategoryCollection, autocarejobcategory);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocarejobcategory);
      });

      it('should add only unique Autocarejobcategory to an array', () => {
        const autocarejobcategoryArray: IAutocarejobcategory[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const autocarejobcategoryCollection: IAutocarejobcategory[] = [sampleWithRequiredData];
        expectedResult = service.addAutocarejobcategoryToCollectionIfMissing(autocarejobcategoryCollection, ...autocarejobcategoryArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const autocarejobcategory: IAutocarejobcategory = sampleWithRequiredData;
        const autocarejobcategory2: IAutocarejobcategory = sampleWithPartialData;
        expectedResult = service.addAutocarejobcategoryToCollectionIfMissing([], autocarejobcategory, autocarejobcategory2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocarejobcategory);
        expect(expectedResult).toContain(autocarejobcategory2);
      });

      it('should accept null and undefined values', () => {
        const autocarejobcategory: IAutocarejobcategory = sampleWithRequiredData;
        expectedResult = service.addAutocarejobcategoryToCollectionIfMissing([], null, autocarejobcategory, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocarejobcategory);
      });

      it('should return initial array if no Autocarejobcategory is added', () => {
        const autocarejobcategoryCollection: IAutocarejobcategory[] = [sampleWithRequiredData];
        expectedResult = service.addAutocarejobcategoryToCollectionIfMissing(autocarejobcategoryCollection, undefined, null);
        expect(expectedResult).toEqual(autocarejobcategoryCollection);
      });
    });

    describe('compareAutocarejobcategory', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAutocarejobcategory(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAutocarejobcategory(entity1, entity2);
        const compareResult2 = service.compareAutocarejobcategory(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAutocarejobcategory(entity1, entity2);
        const compareResult2 = service.compareAutocarejobcategory(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAutocarejobcategory(entity1, entity2);
        const compareResult2 = service.compareAutocarejobcategory(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
