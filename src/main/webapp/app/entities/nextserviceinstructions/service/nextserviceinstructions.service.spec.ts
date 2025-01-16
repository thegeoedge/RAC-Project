import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { INextserviceinstructions } from '../nextserviceinstructions.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../nextserviceinstructions.test-samples';

import { NextserviceinstructionsService, RestNextserviceinstructions } from './nextserviceinstructions.service';

const requireRestSample: RestNextserviceinstructions = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Nextserviceinstructions Service', () => {
  let service: NextserviceinstructionsService;
  let httpMock: HttpTestingController;
  let expectedResult: INextserviceinstructions | INextserviceinstructions[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(NextserviceinstructionsService);
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

    it('should create a Nextserviceinstructions', () => {
      const nextserviceinstructions = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(nextserviceinstructions).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Nextserviceinstructions', () => {
      const nextserviceinstructions = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(nextserviceinstructions).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Nextserviceinstructions', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Nextserviceinstructions', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Nextserviceinstructions', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addNextserviceinstructionsToCollectionIfMissing', () => {
      it('should add a Nextserviceinstructions to an empty array', () => {
        const nextserviceinstructions: INextserviceinstructions = sampleWithRequiredData;
        expectedResult = service.addNextserviceinstructionsToCollectionIfMissing([], nextserviceinstructions);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(nextserviceinstructions);
      });

      it('should not add a Nextserviceinstructions to an array that contains it', () => {
        const nextserviceinstructions: INextserviceinstructions = sampleWithRequiredData;
        const nextserviceinstructionsCollection: INextserviceinstructions[] = [
          {
            ...nextserviceinstructions,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addNextserviceinstructionsToCollectionIfMissing(
          nextserviceinstructionsCollection,
          nextserviceinstructions,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Nextserviceinstructions to an array that doesn't contain it", () => {
        const nextserviceinstructions: INextserviceinstructions = sampleWithRequiredData;
        const nextserviceinstructionsCollection: INextserviceinstructions[] = [sampleWithPartialData];
        expectedResult = service.addNextserviceinstructionsToCollectionIfMissing(
          nextserviceinstructionsCollection,
          nextserviceinstructions,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(nextserviceinstructions);
      });

      it('should add only unique Nextserviceinstructions to an array', () => {
        const nextserviceinstructionsArray: INextserviceinstructions[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const nextserviceinstructionsCollection: INextserviceinstructions[] = [sampleWithRequiredData];
        expectedResult = service.addNextserviceinstructionsToCollectionIfMissing(
          nextserviceinstructionsCollection,
          ...nextserviceinstructionsArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const nextserviceinstructions: INextserviceinstructions = sampleWithRequiredData;
        const nextserviceinstructions2: INextserviceinstructions = sampleWithPartialData;
        expectedResult = service.addNextserviceinstructionsToCollectionIfMissing([], nextserviceinstructions, nextserviceinstructions2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(nextserviceinstructions);
        expect(expectedResult).toContain(nextserviceinstructions2);
      });

      it('should accept null and undefined values', () => {
        const nextserviceinstructions: INextserviceinstructions = sampleWithRequiredData;
        expectedResult = service.addNextserviceinstructionsToCollectionIfMissing([], null, nextserviceinstructions, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(nextserviceinstructions);
      });

      it('should return initial array if no Nextserviceinstructions is added', () => {
        const nextserviceinstructionsCollection: INextserviceinstructions[] = [sampleWithRequiredData];
        expectedResult = service.addNextserviceinstructionsToCollectionIfMissing(nextserviceinstructionsCollection, undefined, null);
        expect(expectedResult).toEqual(nextserviceinstructionsCollection);
      });
    });

    describe('compareNextserviceinstructions', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareNextserviceinstructions(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareNextserviceinstructions(entity1, entity2);
        const compareResult2 = service.compareNextserviceinstructions(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareNextserviceinstructions(entity1, entity2);
        const compareResult2 = service.compareNextserviceinstructions(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareNextserviceinstructions(entity1, entity2);
        const compareResult2 = service.compareNextserviceinstructions(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
