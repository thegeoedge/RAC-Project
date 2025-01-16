import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAutocarecancelitemopt } from '../autocarecancelitemopt.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../autocarecancelitemopt.test-samples';

import { AutocarecancelitemoptService } from './autocarecancelitemopt.service';

const requireRestSample: IAutocarecancelitemopt = {
  ...sampleWithRequiredData,
};

describe('Autocarecancelitemopt Service', () => {
  let service: AutocarecancelitemoptService;
  let httpMock: HttpTestingController;
  let expectedResult: IAutocarecancelitemopt | IAutocarecancelitemopt[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AutocarecancelitemoptService);
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

    it('should create a Autocarecancelitemopt', () => {
      const autocarecancelitemopt = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(autocarecancelitemopt).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Autocarecancelitemopt', () => {
      const autocarecancelitemopt = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(autocarecancelitemopt).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Autocarecancelitemopt', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Autocarecancelitemopt', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Autocarecancelitemopt', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAutocarecancelitemoptToCollectionIfMissing', () => {
      it('should add a Autocarecancelitemopt to an empty array', () => {
        const autocarecancelitemopt: IAutocarecancelitemopt = sampleWithRequiredData;
        expectedResult = service.addAutocarecancelitemoptToCollectionIfMissing([], autocarecancelitemopt);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocarecancelitemopt);
      });

      it('should not add a Autocarecancelitemopt to an array that contains it', () => {
        const autocarecancelitemopt: IAutocarecancelitemopt = sampleWithRequiredData;
        const autocarecancelitemoptCollection: IAutocarecancelitemopt[] = [
          {
            ...autocarecancelitemopt,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAutocarecancelitemoptToCollectionIfMissing(autocarecancelitemoptCollection, autocarecancelitemopt);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Autocarecancelitemopt to an array that doesn't contain it", () => {
        const autocarecancelitemopt: IAutocarecancelitemopt = sampleWithRequiredData;
        const autocarecancelitemoptCollection: IAutocarecancelitemopt[] = [sampleWithPartialData];
        expectedResult = service.addAutocarecancelitemoptToCollectionIfMissing(autocarecancelitemoptCollection, autocarecancelitemopt);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocarecancelitemopt);
      });

      it('should add only unique Autocarecancelitemopt to an array', () => {
        const autocarecancelitemoptArray: IAutocarecancelitemopt[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const autocarecancelitemoptCollection: IAutocarecancelitemopt[] = [sampleWithRequiredData];
        expectedResult = service.addAutocarecancelitemoptToCollectionIfMissing(
          autocarecancelitemoptCollection,
          ...autocarecancelitemoptArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const autocarecancelitemopt: IAutocarecancelitemopt = sampleWithRequiredData;
        const autocarecancelitemopt2: IAutocarecancelitemopt = sampleWithPartialData;
        expectedResult = service.addAutocarecancelitemoptToCollectionIfMissing([], autocarecancelitemopt, autocarecancelitemopt2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocarecancelitemopt);
        expect(expectedResult).toContain(autocarecancelitemopt2);
      });

      it('should accept null and undefined values', () => {
        const autocarecancelitemopt: IAutocarecancelitemopt = sampleWithRequiredData;
        expectedResult = service.addAutocarecancelitemoptToCollectionIfMissing([], null, autocarecancelitemopt, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocarecancelitemopt);
      });

      it('should return initial array if no Autocarecancelitemopt is added', () => {
        const autocarecancelitemoptCollection: IAutocarecancelitemopt[] = [sampleWithRequiredData];
        expectedResult = service.addAutocarecancelitemoptToCollectionIfMissing(autocarecancelitemoptCollection, undefined, null);
        expect(expectedResult).toEqual(autocarecancelitemoptCollection);
      });
    });

    describe('compareAutocarecancelitemopt', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAutocarecancelitemopt(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAutocarecancelitemopt(entity1, entity2);
        const compareResult2 = service.compareAutocarecancelitemopt(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAutocarecancelitemopt(entity1, entity2);
        const compareResult2 = service.compareAutocarecancelitemopt(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAutocarecancelitemopt(entity1, entity2);
        const compareResult2 = service.compareAutocarecancelitemopt(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
