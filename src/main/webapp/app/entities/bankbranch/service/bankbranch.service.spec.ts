import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IBankbranch } from '../bankbranch.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../bankbranch.test-samples';

import { BankbranchService } from './bankbranch.service';

const requireRestSample: IBankbranch = {
  ...sampleWithRequiredData,
};

describe('Bankbranch Service', () => {
  let service: BankbranchService;
  let httpMock: HttpTestingController;
  let expectedResult: IBankbranch | IBankbranch[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(BankbranchService);
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

    it('should create a Bankbranch', () => {
      const bankbranch = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(bankbranch).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Bankbranch', () => {
      const bankbranch = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(bankbranch).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Bankbranch', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Bankbranch', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Bankbranch', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addBankbranchToCollectionIfMissing', () => {
      it('should add a Bankbranch to an empty array', () => {
        const bankbranch: IBankbranch = sampleWithRequiredData;
        expectedResult = service.addBankbranchToCollectionIfMissing([], bankbranch);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(bankbranch);
      });

      it('should not add a Bankbranch to an array that contains it', () => {
        const bankbranch: IBankbranch = sampleWithRequiredData;
        const bankbranchCollection: IBankbranch[] = [
          {
            ...bankbranch,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addBankbranchToCollectionIfMissing(bankbranchCollection, bankbranch);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Bankbranch to an array that doesn't contain it", () => {
        const bankbranch: IBankbranch = sampleWithRequiredData;
        const bankbranchCollection: IBankbranch[] = [sampleWithPartialData];
        expectedResult = service.addBankbranchToCollectionIfMissing(bankbranchCollection, bankbranch);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(bankbranch);
      });

      it('should add only unique Bankbranch to an array', () => {
        const bankbranchArray: IBankbranch[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const bankbranchCollection: IBankbranch[] = [sampleWithRequiredData];
        expectedResult = service.addBankbranchToCollectionIfMissing(bankbranchCollection, ...bankbranchArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const bankbranch: IBankbranch = sampleWithRequiredData;
        const bankbranch2: IBankbranch = sampleWithPartialData;
        expectedResult = service.addBankbranchToCollectionIfMissing([], bankbranch, bankbranch2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(bankbranch);
        expect(expectedResult).toContain(bankbranch2);
      });

      it('should accept null and undefined values', () => {
        const bankbranch: IBankbranch = sampleWithRequiredData;
        expectedResult = service.addBankbranchToCollectionIfMissing([], null, bankbranch, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(bankbranch);
      });

      it('should return initial array if no Bankbranch is added', () => {
        const bankbranchCollection: IBankbranch[] = [sampleWithRequiredData];
        expectedResult = service.addBankbranchToCollectionIfMissing(bankbranchCollection, undefined, null);
        expect(expectedResult).toEqual(bankbranchCollection);
      });
    });

    describe('compareBankbranch', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareBankbranch(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareBankbranch(entity1, entity2);
        const compareResult2 = service.compareBankbranch(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareBankbranch(entity1, entity2);
        const compareResult2 = service.compareBankbranch(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareBankbranch(entity1, entity2);
        const compareResult2 = service.compareBankbranch(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
