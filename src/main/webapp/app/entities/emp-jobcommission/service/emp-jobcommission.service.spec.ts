import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEmpJobcommission } from '../emp-jobcommission.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../emp-jobcommission.test-samples';

import { EmpJobcommissionService, RestEmpJobcommission } from './emp-jobcommission.service';

const requireRestSample: RestEmpJobcommission = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('EmpJobcommission Service', () => {
  let service: EmpJobcommissionService;
  let httpMock: HttpTestingController;
  let expectedResult: IEmpJobcommission | IEmpJobcommission[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(EmpJobcommissionService);
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

    it('should create a EmpJobcommission', () => {
      const empJobcommission = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(empJobcommission).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a EmpJobcommission', () => {
      const empJobcommission = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(empJobcommission).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a EmpJobcommission', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of EmpJobcommission', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a EmpJobcommission', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addEmpJobcommissionToCollectionIfMissing', () => {
      it('should add a EmpJobcommission to an empty array', () => {
        const empJobcommission: IEmpJobcommission = sampleWithRequiredData;
        expectedResult = service.addEmpJobcommissionToCollectionIfMissing([], empJobcommission);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(empJobcommission);
      });

      it('should not add a EmpJobcommission to an array that contains it', () => {
        const empJobcommission: IEmpJobcommission = sampleWithRequiredData;
        const empJobcommissionCollection: IEmpJobcommission[] = [
          {
            ...empJobcommission,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addEmpJobcommissionToCollectionIfMissing(empJobcommissionCollection, empJobcommission);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a EmpJobcommission to an array that doesn't contain it", () => {
        const empJobcommission: IEmpJobcommission = sampleWithRequiredData;
        const empJobcommissionCollection: IEmpJobcommission[] = [sampleWithPartialData];
        expectedResult = service.addEmpJobcommissionToCollectionIfMissing(empJobcommissionCollection, empJobcommission);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(empJobcommission);
      });

      it('should add only unique EmpJobcommission to an array', () => {
        const empJobcommissionArray: IEmpJobcommission[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const empJobcommissionCollection: IEmpJobcommission[] = [sampleWithRequiredData];
        expectedResult = service.addEmpJobcommissionToCollectionIfMissing(empJobcommissionCollection, ...empJobcommissionArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const empJobcommission: IEmpJobcommission = sampleWithRequiredData;
        const empJobcommission2: IEmpJobcommission = sampleWithPartialData;
        expectedResult = service.addEmpJobcommissionToCollectionIfMissing([], empJobcommission, empJobcommission2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(empJobcommission);
        expect(expectedResult).toContain(empJobcommission2);
      });

      it('should accept null and undefined values', () => {
        const empJobcommission: IEmpJobcommission = sampleWithRequiredData;
        expectedResult = service.addEmpJobcommissionToCollectionIfMissing([], null, empJobcommission, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(empJobcommission);
      });

      it('should return initial array if no EmpJobcommission is added', () => {
        const empJobcommissionCollection: IEmpJobcommission[] = [sampleWithRequiredData];
        expectedResult = service.addEmpJobcommissionToCollectionIfMissing(empJobcommissionCollection, undefined, null);
        expect(expectedResult).toEqual(empJobcommissionCollection);
      });
    });

    describe('compareEmpJobcommission', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareEmpJobcommission(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareEmpJobcommission(entity1, entity2);
        const compareResult2 = service.compareEmpJobcommission(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareEmpJobcommission(entity1, entity2);
        const compareResult2 = service.compareEmpJobcommission(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareEmpJobcommission(entity1, entity2);
        const compareResult2 = service.compareEmpJobcommission(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
