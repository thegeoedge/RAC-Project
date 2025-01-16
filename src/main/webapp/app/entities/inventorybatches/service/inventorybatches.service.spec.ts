import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IInventorybatches } from '../inventorybatches.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../inventorybatches.test-samples';

import { InventorybatchesService, RestInventorybatches } from './inventorybatches.service';

const requireRestSample: RestInventorybatches = {
  ...sampleWithRequiredData,
  txdate: sampleWithRequiredData.txdate?.toJSON(),
  lmd: sampleWithRequiredData.lmd?.toJSON(),
  manufacturedate: sampleWithRequiredData.manufacturedate?.toJSON(),
  expiredate: sampleWithRequiredData.expiredate?.toJSON(),
  addeddate: sampleWithRequiredData.addeddate?.toJSON(),
};

describe('Inventorybatches Service', () => {
  let service: InventorybatchesService;
  let httpMock: HttpTestingController;
  let expectedResult: IInventorybatches | IInventorybatches[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(InventorybatchesService);
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

    it('should create a Inventorybatches', () => {
      const inventorybatches = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(inventorybatches).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Inventorybatches', () => {
      const inventorybatches = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(inventorybatches).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Inventorybatches', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Inventorybatches', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Inventorybatches', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addInventorybatchesToCollectionIfMissing', () => {
      it('should add a Inventorybatches to an empty array', () => {
        const inventorybatches: IInventorybatches = sampleWithRequiredData;
        expectedResult = service.addInventorybatchesToCollectionIfMissing([], inventorybatches);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(inventorybatches);
      });

      it('should not add a Inventorybatches to an array that contains it', () => {
        const inventorybatches: IInventorybatches = sampleWithRequiredData;
        const inventorybatchesCollection: IInventorybatches[] = [
          {
            ...inventorybatches,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addInventorybatchesToCollectionIfMissing(inventorybatchesCollection, inventorybatches);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Inventorybatches to an array that doesn't contain it", () => {
        const inventorybatches: IInventorybatches = sampleWithRequiredData;
        const inventorybatchesCollection: IInventorybatches[] = [sampleWithPartialData];
        expectedResult = service.addInventorybatchesToCollectionIfMissing(inventorybatchesCollection, inventorybatches);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(inventorybatches);
      });

      it('should add only unique Inventorybatches to an array', () => {
        const inventorybatchesArray: IInventorybatches[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const inventorybatchesCollection: IInventorybatches[] = [sampleWithRequiredData];
        expectedResult = service.addInventorybatchesToCollectionIfMissing(inventorybatchesCollection, ...inventorybatchesArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const inventorybatches: IInventorybatches = sampleWithRequiredData;
        const inventorybatches2: IInventorybatches = sampleWithPartialData;
        expectedResult = service.addInventorybatchesToCollectionIfMissing([], inventorybatches, inventorybatches2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(inventorybatches);
        expect(expectedResult).toContain(inventorybatches2);
      });

      it('should accept null and undefined values', () => {
        const inventorybatches: IInventorybatches = sampleWithRequiredData;
        expectedResult = service.addInventorybatchesToCollectionIfMissing([], null, inventorybatches, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(inventorybatches);
      });

      it('should return initial array if no Inventorybatches is added', () => {
        const inventorybatchesCollection: IInventorybatches[] = [sampleWithRequiredData];
        expectedResult = service.addInventorybatchesToCollectionIfMissing(inventorybatchesCollection, undefined, null);
        expect(expectedResult).toEqual(inventorybatchesCollection);
      });
    });

    describe('compareInventorybatches', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareInventorybatches(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareInventorybatches(entity1, entity2);
        const compareResult2 = service.compareInventorybatches(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareInventorybatches(entity1, entity2);
        const compareResult2 = service.compareInventorybatches(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareInventorybatches(entity1, entity2);
        const compareResult2 = service.compareInventorybatches(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
