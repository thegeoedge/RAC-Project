import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAutocarehoist } from '../autocarehoist.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../autocarehoist.test-samples';

import { AutocarehoistService, RestAutocarehoist } from './autocarehoist.service';

const requireRestSample: RestAutocarehoist = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Autocarehoist Service', () => {
  let service: AutocarehoistService;
  let httpMock: HttpTestingController;
  let expectedResult: IAutocarehoist | IAutocarehoist[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AutocarehoistService);
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

    it('should create a Autocarehoist', () => {
      const autocarehoist = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(autocarehoist).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Autocarehoist', () => {
      const autocarehoist = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(autocarehoist).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Autocarehoist', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Autocarehoist', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Autocarehoist', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAutocarehoistToCollectionIfMissing', () => {
      it('should add a Autocarehoist to an empty array', () => {
        const autocarehoist: IAutocarehoist = sampleWithRequiredData;
        expectedResult = service.addAutocarehoistToCollectionIfMissing([], autocarehoist);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocarehoist);
      });

      it('should not add a Autocarehoist to an array that contains it', () => {
        const autocarehoist: IAutocarehoist = sampleWithRequiredData;
        const autocarehoistCollection: IAutocarehoist[] = [
          {
            ...autocarehoist,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAutocarehoistToCollectionIfMissing(autocarehoistCollection, autocarehoist);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Autocarehoist to an array that doesn't contain it", () => {
        const autocarehoist: IAutocarehoist = sampleWithRequiredData;
        const autocarehoistCollection: IAutocarehoist[] = [sampleWithPartialData];
        expectedResult = service.addAutocarehoistToCollectionIfMissing(autocarehoistCollection, autocarehoist);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocarehoist);
      });

      it('should add only unique Autocarehoist to an array', () => {
        const autocarehoistArray: IAutocarehoist[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const autocarehoistCollection: IAutocarehoist[] = [sampleWithRequiredData];
        expectedResult = service.addAutocarehoistToCollectionIfMissing(autocarehoistCollection, ...autocarehoistArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const autocarehoist: IAutocarehoist = sampleWithRequiredData;
        const autocarehoist2: IAutocarehoist = sampleWithPartialData;
        expectedResult = service.addAutocarehoistToCollectionIfMissing([], autocarehoist, autocarehoist2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocarehoist);
        expect(expectedResult).toContain(autocarehoist2);
      });

      it('should accept null and undefined values', () => {
        const autocarehoist: IAutocarehoist = sampleWithRequiredData;
        expectedResult = service.addAutocarehoistToCollectionIfMissing([], null, autocarehoist, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocarehoist);
      });

      it('should return initial array if no Autocarehoist is added', () => {
        const autocarehoistCollection: IAutocarehoist[] = [sampleWithRequiredData];
        expectedResult = service.addAutocarehoistToCollectionIfMissing(autocarehoistCollection, undefined, null);
        expect(expectedResult).toEqual(autocarehoistCollection);
      });
    });

    describe('compareAutocarehoist', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAutocarehoist(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAutocarehoist(entity1, entity2);
        const compareResult2 = service.compareAutocarehoist(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAutocarehoist(entity1, entity2);
        const compareResult2 = service.compareAutocarehoist(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAutocarehoist(entity1, entity2);
        const compareResult2 = service.compareAutocarehoist(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
