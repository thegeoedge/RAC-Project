import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ILastserviceinstructions } from '../lastserviceinstructions.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../lastserviceinstructions.test-samples';

import { LastserviceinstructionsService, RestLastserviceinstructions } from './lastserviceinstructions.service';

const requireRestSample: RestLastserviceinstructions = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Lastserviceinstructions Service', () => {
  let service: LastserviceinstructionsService;
  let httpMock: HttpTestingController;
  let expectedResult: ILastserviceinstructions | ILastserviceinstructions[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(LastserviceinstructionsService);
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

    it('should create a Lastserviceinstructions', () => {
      const lastserviceinstructions = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(lastserviceinstructions).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Lastserviceinstructions', () => {
      const lastserviceinstructions = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(lastserviceinstructions).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Lastserviceinstructions', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Lastserviceinstructions', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Lastserviceinstructions', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addLastserviceinstructionsToCollectionIfMissing', () => {
      it('should add a Lastserviceinstructions to an empty array', () => {
        const lastserviceinstructions: ILastserviceinstructions = sampleWithRequiredData;
        expectedResult = service.addLastserviceinstructionsToCollectionIfMissing([], lastserviceinstructions);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(lastserviceinstructions);
      });

      it('should not add a Lastserviceinstructions to an array that contains it', () => {
        const lastserviceinstructions: ILastserviceinstructions = sampleWithRequiredData;
        const lastserviceinstructionsCollection: ILastserviceinstructions[] = [
          {
            ...lastserviceinstructions,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addLastserviceinstructionsToCollectionIfMissing(
          lastserviceinstructionsCollection,
          lastserviceinstructions,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Lastserviceinstructions to an array that doesn't contain it", () => {
        const lastserviceinstructions: ILastserviceinstructions = sampleWithRequiredData;
        const lastserviceinstructionsCollection: ILastserviceinstructions[] = [sampleWithPartialData];
        expectedResult = service.addLastserviceinstructionsToCollectionIfMissing(
          lastserviceinstructionsCollection,
          lastserviceinstructions,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(lastserviceinstructions);
      });

      it('should add only unique Lastserviceinstructions to an array', () => {
        const lastserviceinstructionsArray: ILastserviceinstructions[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const lastserviceinstructionsCollection: ILastserviceinstructions[] = [sampleWithRequiredData];
        expectedResult = service.addLastserviceinstructionsToCollectionIfMissing(
          lastserviceinstructionsCollection,
          ...lastserviceinstructionsArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const lastserviceinstructions: ILastserviceinstructions = sampleWithRequiredData;
        const lastserviceinstructions2: ILastserviceinstructions = sampleWithPartialData;
        expectedResult = service.addLastserviceinstructionsToCollectionIfMissing([], lastserviceinstructions, lastserviceinstructions2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(lastserviceinstructions);
        expect(expectedResult).toContain(lastserviceinstructions2);
      });

      it('should accept null and undefined values', () => {
        const lastserviceinstructions: ILastserviceinstructions = sampleWithRequiredData;
        expectedResult = service.addLastserviceinstructionsToCollectionIfMissing([], null, lastserviceinstructions, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(lastserviceinstructions);
      });

      it('should return initial array if no Lastserviceinstructions is added', () => {
        const lastserviceinstructionsCollection: ILastserviceinstructions[] = [sampleWithRequiredData];
        expectedResult = service.addLastserviceinstructionsToCollectionIfMissing(lastserviceinstructionsCollection, undefined, null);
        expect(expectedResult).toEqual(lastserviceinstructionsCollection);
      });
    });

    describe('compareLastserviceinstructions', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareLastserviceinstructions(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareLastserviceinstructions(entity1, entity2);
        const compareResult2 = service.compareLastserviceinstructions(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareLastserviceinstructions(entity1, entity2);
        const compareResult2 = service.compareLastserviceinstructions(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareLastserviceinstructions(entity1, entity2);
        const compareResult2 = service.compareLastserviceinstructions(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
