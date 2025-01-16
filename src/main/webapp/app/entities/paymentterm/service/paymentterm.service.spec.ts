import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IPaymentterm } from '../paymentterm.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../paymentterm.test-samples';

import { PaymenttermService } from './paymentterm.service';

const requireRestSample: IPaymentterm = {
  ...sampleWithRequiredData,
};

describe('Paymentterm Service', () => {
  let service: PaymenttermService;
  let httpMock: HttpTestingController;
  let expectedResult: IPaymentterm | IPaymentterm[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(PaymenttermService);
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

    it('should create a Paymentterm', () => {
      const paymentterm = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(paymentterm).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Paymentterm', () => {
      const paymentterm = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(paymentterm).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Paymentterm', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Paymentterm', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Paymentterm', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addPaymenttermToCollectionIfMissing', () => {
      it('should add a Paymentterm to an empty array', () => {
        const paymentterm: IPaymentterm = sampleWithRequiredData;
        expectedResult = service.addPaymenttermToCollectionIfMissing([], paymentterm);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(paymentterm);
      });

      it('should not add a Paymentterm to an array that contains it', () => {
        const paymentterm: IPaymentterm = sampleWithRequiredData;
        const paymenttermCollection: IPaymentterm[] = [
          {
            ...paymentterm,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addPaymenttermToCollectionIfMissing(paymenttermCollection, paymentterm);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Paymentterm to an array that doesn't contain it", () => {
        const paymentterm: IPaymentterm = sampleWithRequiredData;
        const paymenttermCollection: IPaymentterm[] = [sampleWithPartialData];
        expectedResult = service.addPaymenttermToCollectionIfMissing(paymenttermCollection, paymentterm);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(paymentterm);
      });

      it('should add only unique Paymentterm to an array', () => {
        const paymenttermArray: IPaymentterm[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const paymenttermCollection: IPaymentterm[] = [sampleWithRequiredData];
        expectedResult = service.addPaymenttermToCollectionIfMissing(paymenttermCollection, ...paymenttermArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const paymentterm: IPaymentterm = sampleWithRequiredData;
        const paymentterm2: IPaymentterm = sampleWithPartialData;
        expectedResult = service.addPaymenttermToCollectionIfMissing([], paymentterm, paymentterm2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(paymentterm);
        expect(expectedResult).toContain(paymentterm2);
      });

      it('should accept null and undefined values', () => {
        const paymentterm: IPaymentterm = sampleWithRequiredData;
        expectedResult = service.addPaymenttermToCollectionIfMissing([], null, paymentterm, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(paymentterm);
      });

      it('should return initial array if no Paymentterm is added', () => {
        const paymenttermCollection: IPaymentterm[] = [sampleWithRequiredData];
        expectedResult = service.addPaymenttermToCollectionIfMissing(paymenttermCollection, undefined, null);
        expect(expectedResult).toEqual(paymenttermCollection);
      });
    });

    describe('comparePaymentterm', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.comparePaymentterm(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.comparePaymentterm(entity1, entity2);
        const compareResult2 = service.comparePaymentterm(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.comparePaymentterm(entity1, entity2);
        const compareResult2 = service.comparePaymentterm(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.comparePaymentterm(entity1, entity2);
        const compareResult2 = service.comparePaymentterm(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
