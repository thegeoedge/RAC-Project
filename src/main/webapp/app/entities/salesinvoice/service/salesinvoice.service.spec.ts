import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ISalesinvoice } from '../salesinvoice.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../salesinvoice.test-samples';

import { SalesinvoiceService, RestSalesinvoice } from './salesinvoice.service';

const requireRestSample: RestSalesinvoice = {
  ...sampleWithRequiredData,
  invoicedate: sampleWithRequiredData.invoicedate?.toJSON(),
  createddate: sampleWithRequiredData.createddate?.toJSON(),
  delieverydate: sampleWithRequiredData.delieverydate?.toJSON(),
  lmd: sampleWithRequiredData.lmd?.toJSON(),
  invcanceldate: sampleWithRequiredData.invcanceldate?.toJSON(),
};

describe('Salesinvoice Service', () => {
  let service: SalesinvoiceService;
  let httpMock: HttpTestingController;
  let expectedResult: ISalesinvoice | ISalesinvoice[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SalesinvoiceService);
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

    it('should create a Salesinvoice', () => {
      const salesinvoice = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(salesinvoice).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Salesinvoice', () => {
      const salesinvoice = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(salesinvoice).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Salesinvoice', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Salesinvoice', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Salesinvoice', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSalesinvoiceToCollectionIfMissing', () => {
      it('should add a Salesinvoice to an empty array', () => {
        const salesinvoice: ISalesinvoice = sampleWithRequiredData;
        expectedResult = service.addSalesinvoiceToCollectionIfMissing([], salesinvoice);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(salesinvoice);
      });

      it('should not add a Salesinvoice to an array that contains it', () => {
        const salesinvoice: ISalesinvoice = sampleWithRequiredData;
        const salesinvoiceCollection: ISalesinvoice[] = [
          {
            ...salesinvoice,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSalesinvoiceToCollectionIfMissing(salesinvoiceCollection, salesinvoice);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Salesinvoice to an array that doesn't contain it", () => {
        const salesinvoice: ISalesinvoice = sampleWithRequiredData;
        const salesinvoiceCollection: ISalesinvoice[] = [sampleWithPartialData];
        expectedResult = service.addSalesinvoiceToCollectionIfMissing(salesinvoiceCollection, salesinvoice);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(salesinvoice);
      });

      it('should add only unique Salesinvoice to an array', () => {
        const salesinvoiceArray: ISalesinvoice[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const salesinvoiceCollection: ISalesinvoice[] = [sampleWithRequiredData];
        expectedResult = service.addSalesinvoiceToCollectionIfMissing(salesinvoiceCollection, ...salesinvoiceArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const salesinvoice: ISalesinvoice = sampleWithRequiredData;
        const salesinvoice2: ISalesinvoice = sampleWithPartialData;
        expectedResult = service.addSalesinvoiceToCollectionIfMissing([], salesinvoice, salesinvoice2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(salesinvoice);
        expect(expectedResult).toContain(salesinvoice2);
      });

      it('should accept null and undefined values', () => {
        const salesinvoice: ISalesinvoice = sampleWithRequiredData;
        expectedResult = service.addSalesinvoiceToCollectionIfMissing([], null, salesinvoice, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(salesinvoice);
      });

      it('should return initial array if no Salesinvoice is added', () => {
        const salesinvoiceCollection: ISalesinvoice[] = [sampleWithRequiredData];
        expectedResult = service.addSalesinvoiceToCollectionIfMissing(salesinvoiceCollection, undefined, null);
        expect(expectedResult).toEqual(salesinvoiceCollection);
      });
    });

    describe('compareSalesinvoice', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSalesinvoice(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareSalesinvoice(entity1, entity2);
        const compareResult2 = service.compareSalesinvoice(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareSalesinvoice(entity1, entity2);
        const compareResult2 = service.compareSalesinvoice(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareSalesinvoice(entity1, entity2);
        const compareResult2 = service.compareSalesinvoice(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
