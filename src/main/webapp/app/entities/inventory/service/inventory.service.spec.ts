import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IInventory } from '../inventory.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../inventory.test-samples';

import { InventoryService, RestInventory } from './inventory.service';

const requireRestSample: RestInventory = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Inventory Service', () => {
  let service: InventoryService;
  let httpMock: HttpTestingController;
  let expectedResult: IInventory | IInventory[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(InventoryService);
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

    it('should create a Inventory', () => {
      const inventory = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(inventory).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Inventory', () => {
      const inventory = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(inventory).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Inventory', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Inventory', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Inventory', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addInventoryToCollectionIfMissing', () => {
      it('should add a Inventory to an empty array', () => {
        const inventory: IInventory = sampleWithRequiredData;
        expectedResult = service.addInventoryToCollectionIfMissing([], inventory);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(inventory);
      });

      it('should not add a Inventory to an array that contains it', () => {
        const inventory: IInventory = sampleWithRequiredData;
        const inventoryCollection: IInventory[] = [
          {
            ...inventory,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addInventoryToCollectionIfMissing(inventoryCollection, inventory);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Inventory to an array that doesn't contain it", () => {
        const inventory: IInventory = sampleWithRequiredData;
        const inventoryCollection: IInventory[] = [sampleWithPartialData];
        expectedResult = service.addInventoryToCollectionIfMissing(inventoryCollection, inventory);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(inventory);
      });

      it('should add only unique Inventory to an array', () => {
        const inventoryArray: IInventory[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const inventoryCollection: IInventory[] = [sampleWithRequiredData];
        expectedResult = service.addInventoryToCollectionIfMissing(inventoryCollection, ...inventoryArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const inventory: IInventory = sampleWithRequiredData;
        const inventory2: IInventory = sampleWithPartialData;
        expectedResult = service.addInventoryToCollectionIfMissing([], inventory, inventory2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(inventory);
        expect(expectedResult).toContain(inventory2);
      });

      it('should accept null and undefined values', () => {
        const inventory: IInventory = sampleWithRequiredData;
        expectedResult = service.addInventoryToCollectionIfMissing([], null, inventory, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(inventory);
      });

      it('should return initial array if no Inventory is added', () => {
        const inventoryCollection: IInventory[] = [sampleWithRequiredData];
        expectedResult = service.addInventoryToCollectionIfMissing(inventoryCollection, undefined, null);
        expect(expectedResult).toEqual(inventoryCollection);
      });
    });

    describe('compareInventory', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareInventory(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareInventory(entity1, entity2);
        const compareResult2 = service.compareInventory(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareInventory(entity1, entity2);
        const compareResult2 = service.compareInventory(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareInventory(entity1, entity2);
        const compareResult2 = service.compareInventory(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
