import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAutojobempallocation } from '../autojobempallocation.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../autojobempallocation.test-samples';

import { AutojobempallocationService, RestAutojobempallocation } from './autojobempallocation.service';

const requireRestSample: RestAutojobempallocation = {
  ...sampleWithRequiredData,
  allocatetime: sampleWithRequiredData.allocatetime?.toJSON(),
  lmd: sampleWithRequiredData.lmd?.toJSON(),
  jobdate: sampleWithRequiredData.jobdate?.toJSON(),
  starttime: sampleWithRequiredData.starttime?.toJSON(),
  endtime: sampleWithRequiredData.endtime?.toJSON(),
};

describe('Autojobempallocation Service', () => {
  let service: AutojobempallocationService;
  let httpMock: HttpTestingController;
  let expectedResult: IAutojobempallocation | IAutojobempallocation[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AutojobempallocationService);
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

    it('should create a Autojobempallocation', () => {
      const autojobempallocation = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(autojobempallocation).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Autojobempallocation', () => {
      const autojobempallocation = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(autojobempallocation).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Autojobempallocation', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Autojobempallocation', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Autojobempallocation', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAutojobempallocationToCollectionIfMissing', () => {
      it('should add a Autojobempallocation to an empty array', () => {
        const autojobempallocation: IAutojobempallocation = sampleWithRequiredData;
        expectedResult = service.addAutojobempallocationToCollectionIfMissing([], autojobempallocation);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autojobempallocation);
      });

      it('should not add a Autojobempallocation to an array that contains it', () => {
        const autojobempallocation: IAutojobempallocation = sampleWithRequiredData;
        const autojobempallocationCollection: IAutojobempallocation[] = [
          {
            ...autojobempallocation,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAutojobempallocationToCollectionIfMissing(autojobempallocationCollection, autojobempallocation);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Autojobempallocation to an array that doesn't contain it", () => {
        const autojobempallocation: IAutojobempallocation = sampleWithRequiredData;
        const autojobempallocationCollection: IAutojobempallocation[] = [sampleWithPartialData];
        expectedResult = service.addAutojobempallocationToCollectionIfMissing(autojobempallocationCollection, autojobempallocation);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autojobempallocation);
      });

      it('should add only unique Autojobempallocation to an array', () => {
        const autojobempallocationArray: IAutojobempallocation[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const autojobempallocationCollection: IAutojobempallocation[] = [sampleWithRequiredData];
        expectedResult = service.addAutojobempallocationToCollectionIfMissing(autojobempallocationCollection, ...autojobempallocationArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const autojobempallocation: IAutojobempallocation = sampleWithRequiredData;
        const autojobempallocation2: IAutojobempallocation = sampleWithPartialData;
        expectedResult = service.addAutojobempallocationToCollectionIfMissing([], autojobempallocation, autojobempallocation2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autojobempallocation);
        expect(expectedResult).toContain(autojobempallocation2);
      });

      it('should accept null and undefined values', () => {
        const autojobempallocation: IAutojobempallocation = sampleWithRequiredData;
        expectedResult = service.addAutojobempallocationToCollectionIfMissing([], null, autojobempallocation, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autojobempallocation);
      });

      it('should return initial array if no Autojobempallocation is added', () => {
        const autojobempallocationCollection: IAutojobempallocation[] = [sampleWithRequiredData];
        expectedResult = service.addAutojobempallocationToCollectionIfMissing(autojobempallocationCollection, undefined, null);
        expect(expectedResult).toEqual(autojobempallocationCollection);
      });
    });

    describe('compareAutojobempallocation', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAutojobempallocation(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAutojobempallocation(entity1, entity2);
        const compareResult2 = service.compareAutojobempallocation(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAutojobempallocation(entity1, entity2);
        const compareResult2 = service.compareAutojobempallocation(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAutojobempallocation(entity1, entity2);
        const compareResult2 = service.compareAutojobempallocation(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
