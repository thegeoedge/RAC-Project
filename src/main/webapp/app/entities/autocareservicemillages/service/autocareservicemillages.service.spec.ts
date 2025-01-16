import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAutocareservicemillages } from '../autocareservicemillages.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../autocareservicemillages.test-samples';

import { AutocareservicemillagesService } from './autocareservicemillages.service';

const requireRestSample: IAutocareservicemillages = {
  ...sampleWithRequiredData,
};

describe('Autocareservicemillages Service', () => {
  let service: AutocareservicemillagesService;
  let httpMock: HttpTestingController;
  let expectedResult: IAutocareservicemillages | IAutocareservicemillages[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AutocareservicemillagesService);
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

    it('should create a Autocareservicemillages', () => {
      const autocareservicemillages = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(autocareservicemillages).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Autocareservicemillages', () => {
      const autocareservicemillages = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(autocareservicemillages).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Autocareservicemillages', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Autocareservicemillages', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Autocareservicemillages', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAutocareservicemillagesToCollectionIfMissing', () => {
      it('should add a Autocareservicemillages to an empty array', () => {
        const autocareservicemillages: IAutocareservicemillages = sampleWithRequiredData;
        expectedResult = service.addAutocareservicemillagesToCollectionIfMissing([], autocareservicemillages);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocareservicemillages);
      });

      it('should not add a Autocareservicemillages to an array that contains it', () => {
        const autocareservicemillages: IAutocareservicemillages = sampleWithRequiredData;
        const autocareservicemillagesCollection: IAutocareservicemillages[] = [
          {
            ...autocareservicemillages,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAutocareservicemillagesToCollectionIfMissing(
          autocareservicemillagesCollection,
          autocareservicemillages,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Autocareservicemillages to an array that doesn't contain it", () => {
        const autocareservicemillages: IAutocareservicemillages = sampleWithRequiredData;
        const autocareservicemillagesCollection: IAutocareservicemillages[] = [sampleWithPartialData];
        expectedResult = service.addAutocareservicemillagesToCollectionIfMissing(
          autocareservicemillagesCollection,
          autocareservicemillages,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocareservicemillages);
      });

      it('should add only unique Autocareservicemillages to an array', () => {
        const autocareservicemillagesArray: IAutocareservicemillages[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const autocareservicemillagesCollection: IAutocareservicemillages[] = [sampleWithRequiredData];
        expectedResult = service.addAutocareservicemillagesToCollectionIfMissing(
          autocareservicemillagesCollection,
          ...autocareservicemillagesArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const autocareservicemillages: IAutocareservicemillages = sampleWithRequiredData;
        const autocareservicemillages2: IAutocareservicemillages = sampleWithPartialData;
        expectedResult = service.addAutocareservicemillagesToCollectionIfMissing([], autocareservicemillages, autocareservicemillages2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocareservicemillages);
        expect(expectedResult).toContain(autocareservicemillages2);
      });

      it('should accept null and undefined values', () => {
        const autocareservicemillages: IAutocareservicemillages = sampleWithRequiredData;
        expectedResult = service.addAutocareservicemillagesToCollectionIfMissing([], null, autocareservicemillages, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocareservicemillages);
      });

      it('should return initial array if no Autocareservicemillages is added', () => {
        const autocareservicemillagesCollection: IAutocareservicemillages[] = [sampleWithRequiredData];
        expectedResult = service.addAutocareservicemillagesToCollectionIfMissing(autocareservicemillagesCollection, undefined, null);
        expect(expectedResult).toEqual(autocareservicemillagesCollection);
      });
    });

    describe('compareAutocareservicemillages', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAutocareservicemillages(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAutocareservicemillages(entity1, entity2);
        const compareResult2 = service.compareAutocareservicemillages(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAutocareservicemillages(entity1, entity2);
        const compareResult2 = service.compareAutocareservicemillages(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAutocareservicemillages(entity1, entity2);
        const compareResult2 = service.compareAutocareservicemillages(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
