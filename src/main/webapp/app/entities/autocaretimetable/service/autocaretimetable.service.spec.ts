import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAutocaretimetable } from '../autocaretimetable.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../autocaretimetable.test-samples';

import { AutocaretimetableService, RestAutocaretimetable } from './autocaretimetable.service';

const requireRestSample: RestAutocaretimetable = {
  ...sampleWithRequiredData,
  hoisttime: sampleWithRequiredData.hoisttime?.toJSON(),
};

describe('Autocaretimetable Service', () => {
  let service: AutocaretimetableService;
  let httpMock: HttpTestingController;
  let expectedResult: IAutocaretimetable | IAutocaretimetable[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AutocaretimetableService);
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

    it('should create a Autocaretimetable', () => {
      const autocaretimetable = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(autocaretimetable).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Autocaretimetable', () => {
      const autocaretimetable = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(autocaretimetable).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Autocaretimetable', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Autocaretimetable', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Autocaretimetable', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAutocaretimetableToCollectionIfMissing', () => {
      it('should add a Autocaretimetable to an empty array', () => {
        const autocaretimetable: IAutocaretimetable = sampleWithRequiredData;
        expectedResult = service.addAutocaretimetableToCollectionIfMissing([], autocaretimetable);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocaretimetable);
      });

      it('should not add a Autocaretimetable to an array that contains it', () => {
        const autocaretimetable: IAutocaretimetable = sampleWithRequiredData;
        const autocaretimetableCollection: IAutocaretimetable[] = [
          {
            ...autocaretimetable,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAutocaretimetableToCollectionIfMissing(autocaretimetableCollection, autocaretimetable);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Autocaretimetable to an array that doesn't contain it", () => {
        const autocaretimetable: IAutocaretimetable = sampleWithRequiredData;
        const autocaretimetableCollection: IAutocaretimetable[] = [sampleWithPartialData];
        expectedResult = service.addAutocaretimetableToCollectionIfMissing(autocaretimetableCollection, autocaretimetable);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocaretimetable);
      });

      it('should add only unique Autocaretimetable to an array', () => {
        const autocaretimetableArray: IAutocaretimetable[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const autocaretimetableCollection: IAutocaretimetable[] = [sampleWithRequiredData];
        expectedResult = service.addAutocaretimetableToCollectionIfMissing(autocaretimetableCollection, ...autocaretimetableArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const autocaretimetable: IAutocaretimetable = sampleWithRequiredData;
        const autocaretimetable2: IAutocaretimetable = sampleWithPartialData;
        expectedResult = service.addAutocaretimetableToCollectionIfMissing([], autocaretimetable, autocaretimetable2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocaretimetable);
        expect(expectedResult).toContain(autocaretimetable2);
      });

      it('should accept null and undefined values', () => {
        const autocaretimetable: IAutocaretimetable = sampleWithRequiredData;
        expectedResult = service.addAutocaretimetableToCollectionIfMissing([], null, autocaretimetable, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocaretimetable);
      });

      it('should return initial array if no Autocaretimetable is added', () => {
        const autocaretimetableCollection: IAutocaretimetable[] = [sampleWithRequiredData];
        expectedResult = service.addAutocaretimetableToCollectionIfMissing(autocaretimetableCollection, undefined, null);
        expect(expectedResult).toEqual(autocaretimetableCollection);
      });
    });

    describe('compareAutocaretimetable', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAutocaretimetable(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAutocaretimetable(entity1, entity2);
        const compareResult2 = service.compareAutocaretimetable(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAutocaretimetable(entity1, entity2);
        const compareResult2 = service.compareAutocaretimetable(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAutocaretimetable(entity1, entity2);
        const compareResult2 = service.compareAutocaretimetable(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
