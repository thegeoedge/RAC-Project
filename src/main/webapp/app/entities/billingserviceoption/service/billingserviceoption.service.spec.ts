import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IBillingserviceoption } from '../billingserviceoption.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../billingserviceoption.test-samples';

import { BillingserviceoptionService, RestBillingserviceoption } from './billingserviceoption.service';

const requireRestSample: RestBillingserviceoption = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Billingserviceoption Service', () => {
  let service: BillingserviceoptionService;
  let httpMock: HttpTestingController;
  let expectedResult: IBillingserviceoption | IBillingserviceoption[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(BillingserviceoptionService);
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

    it('should create a Billingserviceoption', () => {
      const billingserviceoption = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(billingserviceoption).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Billingserviceoption', () => {
      const billingserviceoption = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(billingserviceoption).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Billingserviceoption', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Billingserviceoption', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Billingserviceoption', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addBillingserviceoptionToCollectionIfMissing', () => {
      it('should add a Billingserviceoption to an empty array', () => {
        const billingserviceoption: IBillingserviceoption = sampleWithRequiredData;
        expectedResult = service.addBillingserviceoptionToCollectionIfMissing([], billingserviceoption);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(billingserviceoption);
      });

      it('should not add a Billingserviceoption to an array that contains it', () => {
        const billingserviceoption: IBillingserviceoption = sampleWithRequiredData;
        const billingserviceoptionCollection: IBillingserviceoption[] = [
          {
            ...billingserviceoption,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addBillingserviceoptionToCollectionIfMissing(billingserviceoptionCollection, billingserviceoption);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Billingserviceoption to an array that doesn't contain it", () => {
        const billingserviceoption: IBillingserviceoption = sampleWithRequiredData;
        const billingserviceoptionCollection: IBillingserviceoption[] = [sampleWithPartialData];
        expectedResult = service.addBillingserviceoptionToCollectionIfMissing(billingserviceoptionCollection, billingserviceoption);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(billingserviceoption);
      });

      it('should add only unique Billingserviceoption to an array', () => {
        const billingserviceoptionArray: IBillingserviceoption[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const billingserviceoptionCollection: IBillingserviceoption[] = [sampleWithRequiredData];
        expectedResult = service.addBillingserviceoptionToCollectionIfMissing(billingserviceoptionCollection, ...billingserviceoptionArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const billingserviceoption: IBillingserviceoption = sampleWithRequiredData;
        const billingserviceoption2: IBillingserviceoption = sampleWithPartialData;
        expectedResult = service.addBillingserviceoptionToCollectionIfMissing([], billingserviceoption, billingserviceoption2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(billingserviceoption);
        expect(expectedResult).toContain(billingserviceoption2);
      });

      it('should accept null and undefined values', () => {
        const billingserviceoption: IBillingserviceoption = sampleWithRequiredData;
        expectedResult = service.addBillingserviceoptionToCollectionIfMissing([], null, billingserviceoption, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(billingserviceoption);
      });

      it('should return initial array if no Billingserviceoption is added', () => {
        const billingserviceoptionCollection: IBillingserviceoption[] = [sampleWithRequiredData];
        expectedResult = service.addBillingserviceoptionToCollectionIfMissing(billingserviceoptionCollection, undefined, null);
        expect(expectedResult).toEqual(billingserviceoptionCollection);
      });
    });

    describe('compareBillingserviceoption', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareBillingserviceoption(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareBillingserviceoption(entity1, entity2);
        const compareResult2 = service.compareBillingserviceoption(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareBillingserviceoption(entity1, entity2);
        const compareResult2 = service.compareBillingserviceoption(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareBillingserviceoption(entity1, entity2);
        const compareResult2 = service.compareBillingserviceoption(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
