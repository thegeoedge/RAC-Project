import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IVehicletype } from '../vehicletype.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../vehicletype.test-samples';

import { VehicletypeService, RestVehicletype } from './vehicletype.service';

const requireRestSample: RestVehicletype = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Vehicletype Service', () => {
  let service: VehicletypeService;
  let httpMock: HttpTestingController;
  let expectedResult: IVehicletype | IVehicletype[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(VehicletypeService);
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

    it('should create a Vehicletype', () => {
      const vehicletype = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(vehicletype).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Vehicletype', () => {
      const vehicletype = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(vehicletype).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Vehicletype', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Vehicletype', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Vehicletype', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addVehicletypeToCollectionIfMissing', () => {
      it('should add a Vehicletype to an empty array', () => {
        const vehicletype: IVehicletype = sampleWithRequiredData;
        expectedResult = service.addVehicletypeToCollectionIfMissing([], vehicletype);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(vehicletype);
      });

      it('should not add a Vehicletype to an array that contains it', () => {
        const vehicletype: IVehicletype = sampleWithRequiredData;
        const vehicletypeCollection: IVehicletype[] = [
          {
            ...vehicletype,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addVehicletypeToCollectionIfMissing(vehicletypeCollection, vehicletype);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Vehicletype to an array that doesn't contain it", () => {
        const vehicletype: IVehicletype = sampleWithRequiredData;
        const vehicletypeCollection: IVehicletype[] = [sampleWithPartialData];
        expectedResult = service.addVehicletypeToCollectionIfMissing(vehicletypeCollection, vehicletype);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(vehicletype);
      });

      it('should add only unique Vehicletype to an array', () => {
        const vehicletypeArray: IVehicletype[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const vehicletypeCollection: IVehicletype[] = [sampleWithRequiredData];
        expectedResult = service.addVehicletypeToCollectionIfMissing(vehicletypeCollection, ...vehicletypeArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const vehicletype: IVehicletype = sampleWithRequiredData;
        const vehicletype2: IVehicletype = sampleWithPartialData;
        expectedResult = service.addVehicletypeToCollectionIfMissing([], vehicletype, vehicletype2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(vehicletype);
        expect(expectedResult).toContain(vehicletype2);
      });

      it('should accept null and undefined values', () => {
        const vehicletype: IVehicletype = sampleWithRequiredData;
        expectedResult = service.addVehicletypeToCollectionIfMissing([], null, vehicletype, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(vehicletype);
      });

      it('should return initial array if no Vehicletype is added', () => {
        const vehicletypeCollection: IVehicletype[] = [sampleWithRequiredData];
        expectedResult = service.addVehicletypeToCollectionIfMissing(vehicletypeCollection, undefined, null);
        expect(expectedResult).toEqual(vehicletypeCollection);
      });
    });

    describe('compareVehicletype', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareVehicletype(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareVehicletype(entity1, entity2);
        const compareResult2 = service.compareVehicletype(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareVehicletype(entity1, entity2);
        const compareResult2 = service.compareVehicletype(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareVehicletype(entity1, entity2);
        const compareResult2 = service.compareVehicletype(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
