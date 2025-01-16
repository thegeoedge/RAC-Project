import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ISalesorder } from '../salesorder.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../salesorder.test-samples';

import { SalesorderService, RestSalesorder } from './salesorder.service';

const requireRestSample: RestSalesorder = {
  ...sampleWithRequiredData,
  orderdate: sampleWithRequiredData.orderdate?.toJSON(),
  createddate: sampleWithRequiredData.createddate?.toJSON(),
  lmd: sampleWithRequiredData.lmd?.toJSON(),
  advancepaymentreturndate: sampleWithRequiredData.advancepaymentreturndate?.toJSON(),
};

describe('Salesorder Service', () => {
  let service: SalesorderService;
  let httpMock: HttpTestingController;
  let expectedResult: ISalesorder | ISalesorder[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SalesorderService);
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

    it('should create a Salesorder', () => {
      const salesorder = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(salesorder).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Salesorder', () => {
      const salesorder = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(salesorder).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Salesorder', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Salesorder', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Salesorder', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSalesorderToCollectionIfMissing', () => {
      it('should add a Salesorder to an empty array', () => {
        const salesorder: ISalesorder = sampleWithRequiredData;
        expectedResult = service.addSalesorderToCollectionIfMissing([], salesorder);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(salesorder);
      });

      it('should not add a Salesorder to an array that contains it', () => {
        const salesorder: ISalesorder = sampleWithRequiredData;
        const salesorderCollection: ISalesorder[] = [
          {
            ...salesorder,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSalesorderToCollectionIfMissing(salesorderCollection, salesorder);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Salesorder to an array that doesn't contain it", () => {
        const salesorder: ISalesorder = sampleWithRequiredData;
        const salesorderCollection: ISalesorder[] = [sampleWithPartialData];
        expectedResult = service.addSalesorderToCollectionIfMissing(salesorderCollection, salesorder);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(salesorder);
      });

      it('should add only unique Salesorder to an array', () => {
        const salesorderArray: ISalesorder[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const salesorderCollection: ISalesorder[] = [sampleWithRequiredData];
        expectedResult = service.addSalesorderToCollectionIfMissing(salesorderCollection, ...salesorderArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const salesorder: ISalesorder = sampleWithRequiredData;
        const salesorder2: ISalesorder = sampleWithPartialData;
        expectedResult = service.addSalesorderToCollectionIfMissing([], salesorder, salesorder2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(salesorder);
        expect(expectedResult).toContain(salesorder2);
      });

      it('should accept null and undefined values', () => {
        const salesorder: ISalesorder = sampleWithRequiredData;
        expectedResult = service.addSalesorderToCollectionIfMissing([], null, salesorder, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(salesorder);
      });

      it('should return initial array if no Salesorder is added', () => {
        const salesorderCollection: ISalesorder[] = [sampleWithRequiredData];
        expectedResult = service.addSalesorderToCollectionIfMissing(salesorderCollection, undefined, null);
        expect(expectedResult).toEqual(salesorderCollection);
      });
    });

    describe('compareSalesorder', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSalesorder(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareSalesorder(entity1, entity2);
        const compareResult2 = service.compareSalesorder(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareSalesorder(entity1, entity2);
        const compareResult2 = service.compareSalesorder(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareSalesorder(entity1, entity2);
        const compareResult2 = service.compareSalesorder(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
