import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IHoisttype } from '../hoisttype.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../hoisttype.test-samples';

import { HoisttypeService, RestHoisttype } from './hoisttype.service';

const requireRestSample: RestHoisttype = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Hoisttype Service', () => {
  let service: HoisttypeService;
  let httpMock: HttpTestingController;
  let expectedResult: IHoisttype | IHoisttype[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(HoisttypeService);
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

    it('should create a Hoisttype', () => {
      const hoisttype = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(hoisttype).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Hoisttype', () => {
      const hoisttype = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(hoisttype).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Hoisttype', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Hoisttype', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Hoisttype', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addHoisttypeToCollectionIfMissing', () => {
      it('should add a Hoisttype to an empty array', () => {
        const hoisttype: IHoisttype = sampleWithRequiredData;
        expectedResult = service.addHoisttypeToCollectionIfMissing([], hoisttype);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(hoisttype);
      });

      it('should not add a Hoisttype to an array that contains it', () => {
        const hoisttype: IHoisttype = sampleWithRequiredData;
        const hoisttypeCollection: IHoisttype[] = [
          {
            ...hoisttype,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addHoisttypeToCollectionIfMissing(hoisttypeCollection, hoisttype);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Hoisttype to an array that doesn't contain it", () => {
        const hoisttype: IHoisttype = sampleWithRequiredData;
        const hoisttypeCollection: IHoisttype[] = [sampleWithPartialData];
        expectedResult = service.addHoisttypeToCollectionIfMissing(hoisttypeCollection, hoisttype);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(hoisttype);
      });

      it('should add only unique Hoisttype to an array', () => {
        const hoisttypeArray: IHoisttype[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const hoisttypeCollection: IHoisttype[] = [sampleWithRequiredData];
        expectedResult = service.addHoisttypeToCollectionIfMissing(hoisttypeCollection, ...hoisttypeArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const hoisttype: IHoisttype = sampleWithRequiredData;
        const hoisttype2: IHoisttype = sampleWithPartialData;
        expectedResult = service.addHoisttypeToCollectionIfMissing([], hoisttype, hoisttype2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(hoisttype);
        expect(expectedResult).toContain(hoisttype2);
      });

      it('should accept null and undefined values', () => {
        const hoisttype: IHoisttype = sampleWithRequiredData;
        expectedResult = service.addHoisttypeToCollectionIfMissing([], null, hoisttype, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(hoisttype);
      });

      it('should return initial array if no Hoisttype is added', () => {
        const hoisttypeCollection: IHoisttype[] = [sampleWithRequiredData];
        expectedResult = service.addHoisttypeToCollectionIfMissing(hoisttypeCollection, undefined, null);
        expect(expectedResult).toEqual(hoisttypeCollection);
      });
    });

    describe('compareHoisttype', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareHoisttype(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareHoisttype(entity1, entity2);
        const compareResult2 = service.compareHoisttype(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareHoisttype(entity1, entity2);
        const compareResult2 = service.compareHoisttype(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareHoisttype(entity1, entity2);
        const compareResult2 = service.compareHoisttype(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
