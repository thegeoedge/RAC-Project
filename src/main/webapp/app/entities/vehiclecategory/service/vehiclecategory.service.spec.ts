import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IVehiclecategory } from '../vehiclecategory.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../vehiclecategory.test-samples';

import { VehiclecategoryService, RestVehiclecategory } from './vehiclecategory.service';

const requireRestSample: RestVehiclecategory = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Vehiclecategory Service', () => {
  let service: VehiclecategoryService;
  let httpMock: HttpTestingController;
  let expectedResult: IVehiclecategory | IVehiclecategory[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(VehiclecategoryService);
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

    it('should create a Vehiclecategory', () => {
      const vehiclecategory = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(vehiclecategory).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Vehiclecategory', () => {
      const vehiclecategory = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(vehiclecategory).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Vehiclecategory', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Vehiclecategory', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Vehiclecategory', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addVehiclecategoryToCollectionIfMissing', () => {
      it('should add a Vehiclecategory to an empty array', () => {
        const vehiclecategory: IVehiclecategory = sampleWithRequiredData;
        expectedResult = service.addVehiclecategoryToCollectionIfMissing([], vehiclecategory);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(vehiclecategory);
      });

      it('should not add a Vehiclecategory to an array that contains it', () => {
        const vehiclecategory: IVehiclecategory = sampleWithRequiredData;
        const vehiclecategoryCollection: IVehiclecategory[] = [
          {
            ...vehiclecategory,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addVehiclecategoryToCollectionIfMissing(vehiclecategoryCollection, vehiclecategory);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Vehiclecategory to an array that doesn't contain it", () => {
        const vehiclecategory: IVehiclecategory = sampleWithRequiredData;
        const vehiclecategoryCollection: IVehiclecategory[] = [sampleWithPartialData];
        expectedResult = service.addVehiclecategoryToCollectionIfMissing(vehiclecategoryCollection, vehiclecategory);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(vehiclecategory);
      });

      it('should add only unique Vehiclecategory to an array', () => {
        const vehiclecategoryArray: IVehiclecategory[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const vehiclecategoryCollection: IVehiclecategory[] = [sampleWithRequiredData];
        expectedResult = service.addVehiclecategoryToCollectionIfMissing(vehiclecategoryCollection, ...vehiclecategoryArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const vehiclecategory: IVehiclecategory = sampleWithRequiredData;
        const vehiclecategory2: IVehiclecategory = sampleWithPartialData;
        expectedResult = service.addVehiclecategoryToCollectionIfMissing([], vehiclecategory, vehiclecategory2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(vehiclecategory);
        expect(expectedResult).toContain(vehiclecategory2);
      });

      it('should accept null and undefined values', () => {
        const vehiclecategory: IVehiclecategory = sampleWithRequiredData;
        expectedResult = service.addVehiclecategoryToCollectionIfMissing([], null, vehiclecategory, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(vehiclecategory);
      });

      it('should return initial array if no Vehiclecategory is added', () => {
        const vehiclecategoryCollection: IVehiclecategory[] = [sampleWithRequiredData];
        expectedResult = service.addVehiclecategoryToCollectionIfMissing(vehiclecategoryCollection, undefined, null);
        expect(expectedResult).toEqual(vehiclecategoryCollection);
      });
    });

    describe('compareVehiclecategory', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareVehiclecategory(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareVehiclecategory(entity1, entity2);
        const compareResult2 = service.compareVehiclecategory(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareVehiclecategory(entity1, entity2);
        const compareResult2 = service.compareVehiclecategory(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareVehiclecategory(entity1, entity2);
        const compareResult2 = service.compareVehiclecategory(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
