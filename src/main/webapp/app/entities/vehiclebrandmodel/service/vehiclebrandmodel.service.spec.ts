import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IVehiclebrandmodel } from '../vehiclebrandmodel.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../vehiclebrandmodel.test-samples';

import { VehiclebrandmodelService, RestVehiclebrandmodel } from './vehiclebrandmodel.service';

const requireRestSample: RestVehiclebrandmodel = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Vehiclebrandmodel Service', () => {
  let service: VehiclebrandmodelService;
  let httpMock: HttpTestingController;
  let expectedResult: IVehiclebrandmodel | IVehiclebrandmodel[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(VehiclebrandmodelService);
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

    it('should create a Vehiclebrandmodel', () => {
      const vehiclebrandmodel = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(vehiclebrandmodel).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Vehiclebrandmodel', () => {
      const vehiclebrandmodel = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(vehiclebrandmodel).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Vehiclebrandmodel', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Vehiclebrandmodel', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Vehiclebrandmodel', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addVehiclebrandmodelToCollectionIfMissing', () => {
      it('should add a Vehiclebrandmodel to an empty array', () => {
        const vehiclebrandmodel: IVehiclebrandmodel = sampleWithRequiredData;
        expectedResult = service.addVehiclebrandmodelToCollectionIfMissing([], vehiclebrandmodel);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(vehiclebrandmodel);
      });

      it('should not add a Vehiclebrandmodel to an array that contains it', () => {
        const vehiclebrandmodel: IVehiclebrandmodel = sampleWithRequiredData;
        const vehiclebrandmodelCollection: IVehiclebrandmodel[] = [
          {
            ...vehiclebrandmodel,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addVehiclebrandmodelToCollectionIfMissing(vehiclebrandmodelCollection, vehiclebrandmodel);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Vehiclebrandmodel to an array that doesn't contain it", () => {
        const vehiclebrandmodel: IVehiclebrandmodel = sampleWithRequiredData;
        const vehiclebrandmodelCollection: IVehiclebrandmodel[] = [sampleWithPartialData];
        expectedResult = service.addVehiclebrandmodelToCollectionIfMissing(vehiclebrandmodelCollection, vehiclebrandmodel);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(vehiclebrandmodel);
      });

      it('should add only unique Vehiclebrandmodel to an array', () => {
        const vehiclebrandmodelArray: IVehiclebrandmodel[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const vehiclebrandmodelCollection: IVehiclebrandmodel[] = [sampleWithRequiredData];
        expectedResult = service.addVehiclebrandmodelToCollectionIfMissing(vehiclebrandmodelCollection, ...vehiclebrandmodelArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const vehiclebrandmodel: IVehiclebrandmodel = sampleWithRequiredData;
        const vehiclebrandmodel2: IVehiclebrandmodel = sampleWithPartialData;
        expectedResult = service.addVehiclebrandmodelToCollectionIfMissing([], vehiclebrandmodel, vehiclebrandmodel2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(vehiclebrandmodel);
        expect(expectedResult).toContain(vehiclebrandmodel2);
      });

      it('should accept null and undefined values', () => {
        const vehiclebrandmodel: IVehiclebrandmodel = sampleWithRequiredData;
        expectedResult = service.addVehiclebrandmodelToCollectionIfMissing([], null, vehiclebrandmodel, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(vehiclebrandmodel);
      });

      it('should return initial array if no Vehiclebrandmodel is added', () => {
        const vehiclebrandmodelCollection: IVehiclebrandmodel[] = [sampleWithRequiredData];
        expectedResult = service.addVehiclebrandmodelToCollectionIfMissing(vehiclebrandmodelCollection, undefined, null);
        expect(expectedResult).toEqual(vehiclebrandmodelCollection);
      });
    });

    describe('compareVehiclebrandmodel', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareVehiclebrandmodel(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareVehiclebrandmodel(entity1, entity2);
        const compareResult2 = service.compareVehiclebrandmodel(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareVehiclebrandmodel(entity1, entity2);
        const compareResult2 = service.compareVehiclebrandmodel(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareVehiclebrandmodel(entity1, entity2);
        const compareResult2 = service.compareVehiclebrandmodel(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
