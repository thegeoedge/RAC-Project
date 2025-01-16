import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICompanybankaccount } from '../companybankaccount.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../companybankaccount.test-samples';

import { CompanybankaccountService, RestCompanybankaccount } from './companybankaccount.service';

const requireRestSample: RestCompanybankaccount = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Companybankaccount Service', () => {
  let service: CompanybankaccountService;
  let httpMock: HttpTestingController;
  let expectedResult: ICompanybankaccount | ICompanybankaccount[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CompanybankaccountService);
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

    it('should create a Companybankaccount', () => {
      const companybankaccount = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(companybankaccount).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Companybankaccount', () => {
      const companybankaccount = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(companybankaccount).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Companybankaccount', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Companybankaccount', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Companybankaccount', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCompanybankaccountToCollectionIfMissing', () => {
      it('should add a Companybankaccount to an empty array', () => {
        const companybankaccount: ICompanybankaccount = sampleWithRequiredData;
        expectedResult = service.addCompanybankaccountToCollectionIfMissing([], companybankaccount);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(companybankaccount);
      });

      it('should not add a Companybankaccount to an array that contains it', () => {
        const companybankaccount: ICompanybankaccount = sampleWithRequiredData;
        const companybankaccountCollection: ICompanybankaccount[] = [
          {
            ...companybankaccount,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCompanybankaccountToCollectionIfMissing(companybankaccountCollection, companybankaccount);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Companybankaccount to an array that doesn't contain it", () => {
        const companybankaccount: ICompanybankaccount = sampleWithRequiredData;
        const companybankaccountCollection: ICompanybankaccount[] = [sampleWithPartialData];
        expectedResult = service.addCompanybankaccountToCollectionIfMissing(companybankaccountCollection, companybankaccount);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(companybankaccount);
      });

      it('should add only unique Companybankaccount to an array', () => {
        const companybankaccountArray: ICompanybankaccount[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const companybankaccountCollection: ICompanybankaccount[] = [sampleWithRequiredData];
        expectedResult = service.addCompanybankaccountToCollectionIfMissing(companybankaccountCollection, ...companybankaccountArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const companybankaccount: ICompanybankaccount = sampleWithRequiredData;
        const companybankaccount2: ICompanybankaccount = sampleWithPartialData;
        expectedResult = service.addCompanybankaccountToCollectionIfMissing([], companybankaccount, companybankaccount2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(companybankaccount);
        expect(expectedResult).toContain(companybankaccount2);
      });

      it('should accept null and undefined values', () => {
        const companybankaccount: ICompanybankaccount = sampleWithRequiredData;
        expectedResult = service.addCompanybankaccountToCollectionIfMissing([], null, companybankaccount, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(companybankaccount);
      });

      it('should return initial array if no Companybankaccount is added', () => {
        const companybankaccountCollection: ICompanybankaccount[] = [sampleWithRequiredData];
        expectedResult = service.addCompanybankaccountToCollectionIfMissing(companybankaccountCollection, undefined, null);
        expect(expectedResult).toEqual(companybankaccountCollection);
      });
    });

    describe('compareCompanybankaccount', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCompanybankaccount(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCompanybankaccount(entity1, entity2);
        const compareResult2 = service.compareCompanybankaccount(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCompanybankaccount(entity1, entity2);
        const compareResult2 = service.compareCompanybankaccount(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCompanybankaccount(entity1, entity2);
        const compareResult2 = service.compareCompanybankaccount(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
