import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICommonserviceoption } from '../commonserviceoption.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../commonserviceoption.test-samples';

import { CommonserviceoptionService, RestCommonserviceoption } from './commonserviceoption.service';

const requireRestSample: RestCommonserviceoption = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Commonserviceoption Service', () => {
  let service: CommonserviceoptionService;
  let httpMock: HttpTestingController;
  let expectedResult: ICommonserviceoption | ICommonserviceoption[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CommonserviceoptionService);
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

    it('should create a Commonserviceoption', () => {
      const commonserviceoption = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(commonserviceoption).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Commonserviceoption', () => {
      const commonserviceoption = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(commonserviceoption).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Commonserviceoption', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Commonserviceoption', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Commonserviceoption', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCommonserviceoptionToCollectionIfMissing', () => {
      it('should add a Commonserviceoption to an empty array', () => {
        const commonserviceoption: ICommonserviceoption = sampleWithRequiredData;
        expectedResult = service.addCommonserviceoptionToCollectionIfMissing([], commonserviceoption);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(commonserviceoption);
      });

      it('should not add a Commonserviceoption to an array that contains it', () => {
        const commonserviceoption: ICommonserviceoption = sampleWithRequiredData;
        const commonserviceoptionCollection: ICommonserviceoption[] = [
          {
            ...commonserviceoption,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCommonserviceoptionToCollectionIfMissing(commonserviceoptionCollection, commonserviceoption);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Commonserviceoption to an array that doesn't contain it", () => {
        const commonserviceoption: ICommonserviceoption = sampleWithRequiredData;
        const commonserviceoptionCollection: ICommonserviceoption[] = [sampleWithPartialData];
        expectedResult = service.addCommonserviceoptionToCollectionIfMissing(commonserviceoptionCollection, commonserviceoption);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(commonserviceoption);
      });

      it('should add only unique Commonserviceoption to an array', () => {
        const commonserviceoptionArray: ICommonserviceoption[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const commonserviceoptionCollection: ICommonserviceoption[] = [sampleWithRequiredData];
        expectedResult = service.addCommonserviceoptionToCollectionIfMissing(commonserviceoptionCollection, ...commonserviceoptionArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const commonserviceoption: ICommonserviceoption = sampleWithRequiredData;
        const commonserviceoption2: ICommonserviceoption = sampleWithPartialData;
        expectedResult = service.addCommonserviceoptionToCollectionIfMissing([], commonserviceoption, commonserviceoption2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(commonserviceoption);
        expect(expectedResult).toContain(commonserviceoption2);
      });

      it('should accept null and undefined values', () => {
        const commonserviceoption: ICommonserviceoption = sampleWithRequiredData;
        expectedResult = service.addCommonserviceoptionToCollectionIfMissing([], null, commonserviceoption, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(commonserviceoption);
      });

      it('should return initial array if no Commonserviceoption is added', () => {
        const commonserviceoptionCollection: ICommonserviceoption[] = [sampleWithRequiredData];
        expectedResult = service.addCommonserviceoptionToCollectionIfMissing(commonserviceoptionCollection, undefined, null);
        expect(expectedResult).toEqual(commonserviceoptionCollection);
      });
    });

    describe('compareCommonserviceoption', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCommonserviceoption(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCommonserviceoption(entity1, entity2);
        const compareResult2 = service.compareCommonserviceoption(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCommonserviceoption(entity1, entity2);
        const compareResult2 = service.compareCommonserviceoption(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCommonserviceoption(entity1, entity2);
        const compareResult2 = service.compareCommonserviceoption(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
