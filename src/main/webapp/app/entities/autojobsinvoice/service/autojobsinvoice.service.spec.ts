import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAutojobsinvoice } from '../autojobsinvoice.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../autojobsinvoice.test-samples';

import { AutojobsinvoiceService, RestAutojobsinvoice } from './autojobsinvoice.service';

const requireRestSample: RestAutojobsinvoice = {
  ...sampleWithRequiredData,
  invoicedate: sampleWithRequiredData.invoicedate?.toJSON(),
  createddate: sampleWithRequiredData.createddate?.toJSON(),
  delieverydate: sampleWithRequiredData.delieverydate?.toJSON(),
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Autojobsinvoice Service', () => {
  let service: AutojobsinvoiceService;
  let httpMock: HttpTestingController;
  let expectedResult: IAutojobsinvoice | IAutojobsinvoice[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AutojobsinvoiceService);
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

    it('should create a Autojobsinvoice', () => {
      const autojobsinvoice = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(autojobsinvoice).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Autojobsinvoice', () => {
      const autojobsinvoice = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(autojobsinvoice).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Autojobsinvoice', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Autojobsinvoice', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Autojobsinvoice', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAutojobsinvoiceToCollectionIfMissing', () => {
      it('should add a Autojobsinvoice to an empty array', () => {
        const autojobsinvoice: IAutojobsinvoice = sampleWithRequiredData;
        expectedResult = service.addAutojobsinvoiceToCollectionIfMissing([], autojobsinvoice);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autojobsinvoice);
      });

      it('should not add a Autojobsinvoice to an array that contains it', () => {
        const autojobsinvoice: IAutojobsinvoice = sampleWithRequiredData;
        const autojobsinvoiceCollection: IAutojobsinvoice[] = [
          {
            ...autojobsinvoice,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAutojobsinvoiceToCollectionIfMissing(autojobsinvoiceCollection, autojobsinvoice);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Autojobsinvoice to an array that doesn't contain it", () => {
        const autojobsinvoice: IAutojobsinvoice = sampleWithRequiredData;
        const autojobsinvoiceCollection: IAutojobsinvoice[] = [sampleWithPartialData];
        expectedResult = service.addAutojobsinvoiceToCollectionIfMissing(autojobsinvoiceCollection, autojobsinvoice);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autojobsinvoice);
      });

      it('should add only unique Autojobsinvoice to an array', () => {
        const autojobsinvoiceArray: IAutojobsinvoice[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const autojobsinvoiceCollection: IAutojobsinvoice[] = [sampleWithRequiredData];
        expectedResult = service.addAutojobsinvoiceToCollectionIfMissing(autojobsinvoiceCollection, ...autojobsinvoiceArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const autojobsinvoice: IAutojobsinvoice = sampleWithRequiredData;
        const autojobsinvoice2: IAutojobsinvoice = sampleWithPartialData;
        expectedResult = service.addAutojobsinvoiceToCollectionIfMissing([], autojobsinvoice, autojobsinvoice2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autojobsinvoice);
        expect(expectedResult).toContain(autojobsinvoice2);
      });

      it('should accept null and undefined values', () => {
        const autojobsinvoice: IAutojobsinvoice = sampleWithRequiredData;
        expectedResult = service.addAutojobsinvoiceToCollectionIfMissing([], null, autojobsinvoice, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autojobsinvoice);
      });

      it('should return initial array if no Autojobsinvoice is added', () => {
        const autojobsinvoiceCollection: IAutojobsinvoice[] = [sampleWithRequiredData];
        expectedResult = service.addAutojobsinvoiceToCollectionIfMissing(autojobsinvoiceCollection, undefined, null);
        expect(expectedResult).toEqual(autojobsinvoiceCollection);
      });
    });

    describe('compareAutojobsinvoice', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAutojobsinvoice(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAutojobsinvoice(entity1, entity2);
        const compareResult2 = service.compareAutojobsinvoice(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAutojobsinvoice(entity1, entity2);
        const compareResult2 = service.compareAutojobsinvoice(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAutojobsinvoice(entity1, entity2);
        const compareResult2 = service.compareAutojobsinvoice(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
