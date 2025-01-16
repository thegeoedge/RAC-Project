import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IWorkshopvehiclework } from '../workshopvehiclework.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../workshopvehiclework.test-samples';

import { WorkshopvehicleworkService, RestWorkshopvehiclework } from './workshopvehiclework.service';

const requireRestSample: RestWorkshopvehiclework = {
  ...sampleWithRequiredData,
  addeddate: sampleWithRequiredData.addeddate?.toJSON(),
  calldate: sampleWithRequiredData.calldate?.toJSON(),
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Workshopvehiclework Service', () => {
  let service: WorkshopvehicleworkService;
  let httpMock: HttpTestingController;
  let expectedResult: IWorkshopvehiclework | IWorkshopvehiclework[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(WorkshopvehicleworkService);
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

    it('should create a Workshopvehiclework', () => {
      const workshopvehiclework = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(workshopvehiclework).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Workshopvehiclework', () => {
      const workshopvehiclework = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(workshopvehiclework).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Workshopvehiclework', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Workshopvehiclework', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Workshopvehiclework', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addWorkshopvehicleworkToCollectionIfMissing', () => {
      it('should add a Workshopvehiclework to an empty array', () => {
        const workshopvehiclework: IWorkshopvehiclework = sampleWithRequiredData;
        expectedResult = service.addWorkshopvehicleworkToCollectionIfMissing([], workshopvehiclework);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(workshopvehiclework);
      });

      it('should not add a Workshopvehiclework to an array that contains it', () => {
        const workshopvehiclework: IWorkshopvehiclework = sampleWithRequiredData;
        const workshopvehicleworkCollection: IWorkshopvehiclework[] = [
          {
            ...workshopvehiclework,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addWorkshopvehicleworkToCollectionIfMissing(workshopvehicleworkCollection, workshopvehiclework);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Workshopvehiclework to an array that doesn't contain it", () => {
        const workshopvehiclework: IWorkshopvehiclework = sampleWithRequiredData;
        const workshopvehicleworkCollection: IWorkshopvehiclework[] = [sampleWithPartialData];
        expectedResult = service.addWorkshopvehicleworkToCollectionIfMissing(workshopvehicleworkCollection, workshopvehiclework);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(workshopvehiclework);
      });

      it('should add only unique Workshopvehiclework to an array', () => {
        const workshopvehicleworkArray: IWorkshopvehiclework[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const workshopvehicleworkCollection: IWorkshopvehiclework[] = [sampleWithRequiredData];
        expectedResult = service.addWorkshopvehicleworkToCollectionIfMissing(workshopvehicleworkCollection, ...workshopvehicleworkArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const workshopvehiclework: IWorkshopvehiclework = sampleWithRequiredData;
        const workshopvehiclework2: IWorkshopvehiclework = sampleWithPartialData;
        expectedResult = service.addWorkshopvehicleworkToCollectionIfMissing([], workshopvehiclework, workshopvehiclework2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(workshopvehiclework);
        expect(expectedResult).toContain(workshopvehiclework2);
      });

      it('should accept null and undefined values', () => {
        const workshopvehiclework: IWorkshopvehiclework = sampleWithRequiredData;
        expectedResult = service.addWorkshopvehicleworkToCollectionIfMissing([], null, workshopvehiclework, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(workshopvehiclework);
      });

      it('should return initial array if no Workshopvehiclework is added', () => {
        const workshopvehicleworkCollection: IWorkshopvehiclework[] = [sampleWithRequiredData];
        expectedResult = service.addWorkshopvehicleworkToCollectionIfMissing(workshopvehicleworkCollection, undefined, null);
        expect(expectedResult).toEqual(workshopvehicleworkCollection);
      });
    });

    describe('compareWorkshopvehiclework', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareWorkshopvehiclework(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareWorkshopvehiclework(entity1, entity2);
        const compareResult2 = service.compareWorkshopvehiclework(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareWorkshopvehiclework(entity1, entity2);
        const compareResult2 = service.compareWorkshopvehiclework(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareWorkshopvehiclework(entity1, entity2);
        const compareResult2 = service.compareWorkshopvehiclework(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
