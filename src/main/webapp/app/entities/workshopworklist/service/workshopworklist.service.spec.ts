import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IWorkshopworklist } from '../workshopworklist.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../workshopworklist.test-samples';

import { WorkshopworklistService, RestWorkshopworklist } from './workshopworklist.service';

const requireRestSample: RestWorkshopworklist = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Workshopworklist Service', () => {
  let service: WorkshopworklistService;
  let httpMock: HttpTestingController;
  let expectedResult: IWorkshopworklist | IWorkshopworklist[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(WorkshopworklistService);
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

    it('should create a Workshopworklist', () => {
      const workshopworklist = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(workshopworklist).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Workshopworklist', () => {
      const workshopworklist = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(workshopworklist).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Workshopworklist', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Workshopworklist', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Workshopworklist', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addWorkshopworklistToCollectionIfMissing', () => {
      it('should add a Workshopworklist to an empty array', () => {
        const workshopworklist: IWorkshopworklist = sampleWithRequiredData;
        expectedResult = service.addWorkshopworklistToCollectionIfMissing([], workshopworklist);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(workshopworklist);
      });

      it('should not add a Workshopworklist to an array that contains it', () => {
        const workshopworklist: IWorkshopworklist = sampleWithRequiredData;
        const workshopworklistCollection: IWorkshopworklist[] = [
          {
            ...workshopworklist,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addWorkshopworklistToCollectionIfMissing(workshopworklistCollection, workshopworklist);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Workshopworklist to an array that doesn't contain it", () => {
        const workshopworklist: IWorkshopworklist = sampleWithRequiredData;
        const workshopworklistCollection: IWorkshopworklist[] = [sampleWithPartialData];
        expectedResult = service.addWorkshopworklistToCollectionIfMissing(workshopworklistCollection, workshopworklist);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(workshopworklist);
      });

      it('should add only unique Workshopworklist to an array', () => {
        const workshopworklistArray: IWorkshopworklist[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const workshopworklistCollection: IWorkshopworklist[] = [sampleWithRequiredData];
        expectedResult = service.addWorkshopworklistToCollectionIfMissing(workshopworklistCollection, ...workshopworklistArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const workshopworklist: IWorkshopworklist = sampleWithRequiredData;
        const workshopworklist2: IWorkshopworklist = sampleWithPartialData;
        expectedResult = service.addWorkshopworklistToCollectionIfMissing([], workshopworklist, workshopworklist2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(workshopworklist);
        expect(expectedResult).toContain(workshopworklist2);
      });

      it('should accept null and undefined values', () => {
        const workshopworklist: IWorkshopworklist = sampleWithRequiredData;
        expectedResult = service.addWorkshopworklistToCollectionIfMissing([], null, workshopworklist, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(workshopworklist);
      });

      it('should return initial array if no Workshopworklist is added', () => {
        const workshopworklistCollection: IWorkshopworklist[] = [sampleWithRequiredData];
        expectedResult = service.addWorkshopworklistToCollectionIfMissing(workshopworklistCollection, undefined, null);
        expect(expectedResult).toEqual(workshopworklistCollection);
      });
    });

    describe('compareWorkshopworklist', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareWorkshopworklist(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareWorkshopworklist(entity1, entity2);
        const compareResult2 = service.compareWorkshopworklist(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareWorkshopworklist(entity1, entity2);
        const compareResult2 = service.compareWorkshopworklist(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareWorkshopworklist(entity1, entity2);
        const compareResult2 = service.compareWorkshopworklist(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
