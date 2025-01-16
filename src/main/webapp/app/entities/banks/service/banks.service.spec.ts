import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IBanks } from '../banks.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../banks.test-samples';

import { BanksService, RestBanks } from './banks.service';

const requireRestSample: RestBanks = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Banks Service', () => {
  let service: BanksService;
  let httpMock: HttpTestingController;
  let expectedResult: IBanks | IBanks[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(BanksService);
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

    it('should create a Banks', () => {
      const banks = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(banks).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Banks', () => {
      const banks = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(banks).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Banks', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Banks', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Banks', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addBanksToCollectionIfMissing', () => {
      it('should add a Banks to an empty array', () => {
        const banks: IBanks = sampleWithRequiredData;
        expectedResult = service.addBanksToCollectionIfMissing([], banks);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(banks);
      });

      it('should not add a Banks to an array that contains it', () => {
        const banks: IBanks = sampleWithRequiredData;
        const banksCollection: IBanks[] = [
          {
            ...banks,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addBanksToCollectionIfMissing(banksCollection, banks);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Banks to an array that doesn't contain it", () => {
        const banks: IBanks = sampleWithRequiredData;
        const banksCollection: IBanks[] = [sampleWithPartialData];
        expectedResult = service.addBanksToCollectionIfMissing(banksCollection, banks);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(banks);
      });

      it('should add only unique Banks to an array', () => {
        const banksArray: IBanks[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const banksCollection: IBanks[] = [sampleWithRequiredData];
        expectedResult = service.addBanksToCollectionIfMissing(banksCollection, ...banksArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const banks: IBanks = sampleWithRequiredData;
        const banks2: IBanks = sampleWithPartialData;
        expectedResult = service.addBanksToCollectionIfMissing([], banks, banks2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(banks);
        expect(expectedResult).toContain(banks2);
      });

      it('should accept null and undefined values', () => {
        const banks: IBanks = sampleWithRequiredData;
        expectedResult = service.addBanksToCollectionIfMissing([], null, banks, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(banks);
      });

      it('should return initial array if no Banks is added', () => {
        const banksCollection: IBanks[] = [sampleWithRequiredData];
        expectedResult = service.addBanksToCollectionIfMissing(banksCollection, undefined, null);
        expect(expectedResult).toEqual(banksCollection);
      });
    });

    describe('compareBanks', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareBanks(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareBanks(entity1, entity2);
        const compareResult2 = service.compareBanks(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareBanks(entity1, entity2);
        const compareResult2 = service.compareBanks(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareBanks(entity1, entity2);
        const compareResult2 = service.compareBanks(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
