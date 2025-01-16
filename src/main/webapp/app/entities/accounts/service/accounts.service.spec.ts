import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAccounts } from '../accounts.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../accounts.test-samples';

import { AccountsService, RestAccounts } from './accounts.service';

const requireRestSample: RestAccounts = {
  ...sampleWithRequiredData,
  date: sampleWithRequiredData.date?.toJSON(),
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Accounts Service', () => {
  let service: AccountsService;
  let httpMock: HttpTestingController;
  let expectedResult: IAccounts | IAccounts[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AccountsService);
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

    it('should create a Accounts', () => {
      const accounts = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(accounts).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Accounts', () => {
      const accounts = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(accounts).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Accounts', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Accounts', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Accounts', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAccountsToCollectionIfMissing', () => {
      it('should add a Accounts to an empty array', () => {
        const accounts: IAccounts = sampleWithRequiredData;
        expectedResult = service.addAccountsToCollectionIfMissing([], accounts);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(accounts);
      });

      it('should not add a Accounts to an array that contains it', () => {
        const accounts: IAccounts = sampleWithRequiredData;
        const accountsCollection: IAccounts[] = [
          {
            ...accounts,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAccountsToCollectionIfMissing(accountsCollection, accounts);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Accounts to an array that doesn't contain it", () => {
        const accounts: IAccounts = sampleWithRequiredData;
        const accountsCollection: IAccounts[] = [sampleWithPartialData];
        expectedResult = service.addAccountsToCollectionIfMissing(accountsCollection, accounts);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(accounts);
      });

      it('should add only unique Accounts to an array', () => {
        const accountsArray: IAccounts[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const accountsCollection: IAccounts[] = [sampleWithRequiredData];
        expectedResult = service.addAccountsToCollectionIfMissing(accountsCollection, ...accountsArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const accounts: IAccounts = sampleWithRequiredData;
        const accounts2: IAccounts = sampleWithPartialData;
        expectedResult = service.addAccountsToCollectionIfMissing([], accounts, accounts2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(accounts);
        expect(expectedResult).toContain(accounts2);
      });

      it('should accept null and undefined values', () => {
        const accounts: IAccounts = sampleWithRequiredData;
        expectedResult = service.addAccountsToCollectionIfMissing([], null, accounts, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(accounts);
      });

      it('should return initial array if no Accounts is added', () => {
        const accountsCollection: IAccounts[] = [sampleWithRequiredData];
        expectedResult = service.addAccountsToCollectionIfMissing(accountsCollection, undefined, null);
        expect(expectedResult).toEqual(accountsCollection);
      });
    });

    describe('compareAccounts', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAccounts(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAccounts(entity1, entity2);
        const compareResult2 = service.compareAccounts(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAccounts(entity1, entity2);
        const compareResult2 = service.compareAccounts(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAccounts(entity1, entity2);
        const compareResult2 = service.compareAccounts(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
