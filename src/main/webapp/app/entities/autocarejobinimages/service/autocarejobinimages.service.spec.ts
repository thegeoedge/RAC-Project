import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAutocarejobinimages } from '../autocarejobinimages.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../autocarejobinimages.test-samples';

import { AutocarejobinimagesService } from './autocarejobinimages.service';

const requireRestSample: IAutocarejobinimages = {
  ...sampleWithRequiredData,
};

describe('Autocarejobinimages Service', () => {
  let service: AutocarejobinimagesService;
  let httpMock: HttpTestingController;
  let expectedResult: IAutocarejobinimages | IAutocarejobinimages[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AutocarejobinimagesService);
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

    it('should create a Autocarejobinimages', () => {
      const autocarejobinimages = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(autocarejobinimages).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Autocarejobinimages', () => {
      const autocarejobinimages = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(autocarejobinimages).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Autocarejobinimages', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Autocarejobinimages', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Autocarejobinimages', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAutocarejobinimagesToCollectionIfMissing', () => {
      it('should add a Autocarejobinimages to an empty array', () => {
        const autocarejobinimages: IAutocarejobinimages = sampleWithRequiredData;
        expectedResult = service.addAutocarejobinimagesToCollectionIfMissing([], autocarejobinimages);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocarejobinimages);
      });

      it('should not add a Autocarejobinimages to an array that contains it', () => {
        const autocarejobinimages: IAutocarejobinimages = sampleWithRequiredData;
        const autocarejobinimagesCollection: IAutocarejobinimages[] = [
          {
            ...autocarejobinimages,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAutocarejobinimagesToCollectionIfMissing(autocarejobinimagesCollection, autocarejobinimages);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Autocarejobinimages to an array that doesn't contain it", () => {
        const autocarejobinimages: IAutocarejobinimages = sampleWithRequiredData;
        const autocarejobinimagesCollection: IAutocarejobinimages[] = [sampleWithPartialData];
        expectedResult = service.addAutocarejobinimagesToCollectionIfMissing(autocarejobinimagesCollection, autocarejobinimages);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocarejobinimages);
      });

      it('should add only unique Autocarejobinimages to an array', () => {
        const autocarejobinimagesArray: IAutocarejobinimages[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const autocarejobinimagesCollection: IAutocarejobinimages[] = [sampleWithRequiredData];
        expectedResult = service.addAutocarejobinimagesToCollectionIfMissing(autocarejobinimagesCollection, ...autocarejobinimagesArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const autocarejobinimages: IAutocarejobinimages = sampleWithRequiredData;
        const autocarejobinimages2: IAutocarejobinimages = sampleWithPartialData;
        expectedResult = service.addAutocarejobinimagesToCollectionIfMissing([], autocarejobinimages, autocarejobinimages2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocarejobinimages);
        expect(expectedResult).toContain(autocarejobinimages2);
      });

      it('should accept null and undefined values', () => {
        const autocarejobinimages: IAutocarejobinimages = sampleWithRequiredData;
        expectedResult = service.addAutocarejobinimagesToCollectionIfMissing([], null, autocarejobinimages, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocarejobinimages);
      });

      it('should return initial array if no Autocarejobinimages is added', () => {
        const autocarejobinimagesCollection: IAutocarejobinimages[] = [sampleWithRequiredData];
        expectedResult = service.addAutocarejobinimagesToCollectionIfMissing(autocarejobinimagesCollection, undefined, null);
        expect(expectedResult).toEqual(autocarejobinimagesCollection);
      });
    });

    describe('compareAutocarejobinimages', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAutocarejobinimages(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAutocarejobinimages(entity1, entity2);
        const compareResult2 = service.compareAutocarejobinimages(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAutocarejobinimages(entity1, entity2);
        const compareResult2 = service.compareAutocarejobinimages(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAutocarejobinimages(entity1, entity2);
        const compareResult2 = service.compareAutocarejobinimages(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
