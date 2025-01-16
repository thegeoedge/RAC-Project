import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IStocklocations } from '../stocklocations.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../stocklocations.test-samples';

import { StocklocationsService } from './stocklocations.service';

const requireRestSample: IStocklocations = {
  ...sampleWithRequiredData,
};

describe('Stocklocations Service', () => {
  let service: StocklocationsService;
  let httpMock: HttpTestingController;
  let expectedResult: IStocklocations | IStocklocations[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(StocklocationsService);
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

    it('should create a Stocklocations', () => {
      const stocklocations = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(stocklocations).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Stocklocations', () => {
      const stocklocations = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(stocklocations).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Stocklocations', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Stocklocations', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Stocklocations', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addStocklocationsToCollectionIfMissing', () => {
      it('should add a Stocklocations to an empty array', () => {
        const stocklocations: IStocklocations = sampleWithRequiredData;
        expectedResult = service.addStocklocationsToCollectionIfMissing([], stocklocations);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(stocklocations);
      });

      it('should not add a Stocklocations to an array that contains it', () => {
        const stocklocations: IStocklocations = sampleWithRequiredData;
        const stocklocationsCollection: IStocklocations[] = [
          {
            ...stocklocations,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addStocklocationsToCollectionIfMissing(stocklocationsCollection, stocklocations);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Stocklocations to an array that doesn't contain it", () => {
        const stocklocations: IStocklocations = sampleWithRequiredData;
        const stocklocationsCollection: IStocklocations[] = [sampleWithPartialData];
        expectedResult = service.addStocklocationsToCollectionIfMissing(stocklocationsCollection, stocklocations);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(stocklocations);
      });

      it('should add only unique Stocklocations to an array', () => {
        const stocklocationsArray: IStocklocations[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const stocklocationsCollection: IStocklocations[] = [sampleWithRequiredData];
        expectedResult = service.addStocklocationsToCollectionIfMissing(stocklocationsCollection, ...stocklocationsArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const stocklocations: IStocklocations = sampleWithRequiredData;
        const stocklocations2: IStocklocations = sampleWithPartialData;
        expectedResult = service.addStocklocationsToCollectionIfMissing([], stocklocations, stocklocations2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(stocklocations);
        expect(expectedResult).toContain(stocklocations2);
      });

      it('should accept null and undefined values', () => {
        const stocklocations: IStocklocations = sampleWithRequiredData;
        expectedResult = service.addStocklocationsToCollectionIfMissing([], null, stocklocations, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(stocklocations);
      });

      it('should return initial array if no Stocklocations is added', () => {
        const stocklocationsCollection: IStocklocations[] = [sampleWithRequiredData];
        expectedResult = service.addStocklocationsToCollectionIfMissing(stocklocationsCollection, undefined, null);
        expect(expectedResult).toEqual(stocklocationsCollection);
      });
    });

    describe('compareStocklocations', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareStocklocations(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareStocklocations(entity1, entity2);
        const compareResult2 = service.compareStocklocations(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareStocklocations(entity1, entity2);
        const compareResult2 = service.compareStocklocations(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareStocklocations(entity1, entity2);
        const compareResult2 = service.compareStocklocations(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
