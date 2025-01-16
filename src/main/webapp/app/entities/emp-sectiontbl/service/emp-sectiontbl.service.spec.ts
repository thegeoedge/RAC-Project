import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEmpSectiontbl } from '../emp-sectiontbl.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../emp-sectiontbl.test-samples';

import { EmpSectiontblService, RestEmpSectiontbl } from './emp-sectiontbl.service';

const requireRestSample: RestEmpSectiontbl = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('EmpSectiontbl Service', () => {
  let service: EmpSectiontblService;
  let httpMock: HttpTestingController;
  let expectedResult: IEmpSectiontbl | IEmpSectiontbl[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(EmpSectiontblService);
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

    it('should create a EmpSectiontbl', () => {
      const empSectiontbl = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(empSectiontbl).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a EmpSectiontbl', () => {
      const empSectiontbl = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(empSectiontbl).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a EmpSectiontbl', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of EmpSectiontbl', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a EmpSectiontbl', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addEmpSectiontblToCollectionIfMissing', () => {
      it('should add a EmpSectiontbl to an empty array', () => {
        const empSectiontbl: IEmpSectiontbl = sampleWithRequiredData;
        expectedResult = service.addEmpSectiontblToCollectionIfMissing([], empSectiontbl);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(empSectiontbl);
      });

      it('should not add a EmpSectiontbl to an array that contains it', () => {
        const empSectiontbl: IEmpSectiontbl = sampleWithRequiredData;
        const empSectiontblCollection: IEmpSectiontbl[] = [
          {
            ...empSectiontbl,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addEmpSectiontblToCollectionIfMissing(empSectiontblCollection, empSectiontbl);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a EmpSectiontbl to an array that doesn't contain it", () => {
        const empSectiontbl: IEmpSectiontbl = sampleWithRequiredData;
        const empSectiontblCollection: IEmpSectiontbl[] = [sampleWithPartialData];
        expectedResult = service.addEmpSectiontblToCollectionIfMissing(empSectiontblCollection, empSectiontbl);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(empSectiontbl);
      });

      it('should add only unique EmpSectiontbl to an array', () => {
        const empSectiontblArray: IEmpSectiontbl[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const empSectiontblCollection: IEmpSectiontbl[] = [sampleWithRequiredData];
        expectedResult = service.addEmpSectiontblToCollectionIfMissing(empSectiontblCollection, ...empSectiontblArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const empSectiontbl: IEmpSectiontbl = sampleWithRequiredData;
        const empSectiontbl2: IEmpSectiontbl = sampleWithPartialData;
        expectedResult = service.addEmpSectiontblToCollectionIfMissing([], empSectiontbl, empSectiontbl2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(empSectiontbl);
        expect(expectedResult).toContain(empSectiontbl2);
      });

      it('should accept null and undefined values', () => {
        const empSectiontbl: IEmpSectiontbl = sampleWithRequiredData;
        expectedResult = service.addEmpSectiontblToCollectionIfMissing([], null, empSectiontbl, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(empSectiontbl);
      });

      it('should return initial array if no EmpSectiontbl is added', () => {
        const empSectiontblCollection: IEmpSectiontbl[] = [sampleWithRequiredData];
        expectedResult = service.addEmpSectiontblToCollectionIfMissing(empSectiontblCollection, undefined, null);
        expect(expectedResult).toEqual(empSectiontblCollection);
      });
    });

    describe('compareEmpSectiontbl', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareEmpSectiontbl(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareEmpSectiontbl(entity1, entity2);
        const compareResult2 = service.compareEmpSectiontbl(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareEmpSectiontbl(entity1, entity2);
        const compareResult2 = service.compareEmpSectiontbl(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareEmpSectiontbl(entity1, entity2);
        const compareResult2 = service.compareEmpSectiontbl(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
