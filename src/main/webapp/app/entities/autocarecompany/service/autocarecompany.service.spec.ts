import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAutocarecompany } from '../autocarecompany.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../autocarecompany.test-samples';

import { AutocarecompanyService, RestAutocarecompany } from './autocarecompany.service';

const requireRestSample: RestAutocarecompany = {
  ...sampleWithRequiredData,
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Autocarecompany Service', () => {
  let service: AutocarecompanyService;
  let httpMock: HttpTestingController;
  let expectedResult: IAutocarecompany | IAutocarecompany[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AutocarecompanyService);
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

    it('should create a Autocarecompany', () => {
      const autocarecompany = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(autocarecompany).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Autocarecompany', () => {
      const autocarecompany = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(autocarecompany).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Autocarecompany', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Autocarecompany', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Autocarecompany', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAutocarecompanyToCollectionIfMissing', () => {
      it('should add a Autocarecompany to an empty array', () => {
        const autocarecompany: IAutocarecompany = sampleWithRequiredData;
        expectedResult = service.addAutocarecompanyToCollectionIfMissing([], autocarecompany);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocarecompany);
      });

      it('should not add a Autocarecompany to an array that contains it', () => {
        const autocarecompany: IAutocarecompany = sampleWithRequiredData;
        const autocarecompanyCollection: IAutocarecompany[] = [
          {
            ...autocarecompany,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAutocarecompanyToCollectionIfMissing(autocarecompanyCollection, autocarecompany);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Autocarecompany to an array that doesn't contain it", () => {
        const autocarecompany: IAutocarecompany = sampleWithRequiredData;
        const autocarecompanyCollection: IAutocarecompany[] = [sampleWithPartialData];
        expectedResult = service.addAutocarecompanyToCollectionIfMissing(autocarecompanyCollection, autocarecompany);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocarecompany);
      });

      it('should add only unique Autocarecompany to an array', () => {
        const autocarecompanyArray: IAutocarecompany[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const autocarecompanyCollection: IAutocarecompany[] = [sampleWithRequiredData];
        expectedResult = service.addAutocarecompanyToCollectionIfMissing(autocarecompanyCollection, ...autocarecompanyArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const autocarecompany: IAutocarecompany = sampleWithRequiredData;
        const autocarecompany2: IAutocarecompany = sampleWithPartialData;
        expectedResult = service.addAutocarecompanyToCollectionIfMissing([], autocarecompany, autocarecompany2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocarecompany);
        expect(expectedResult).toContain(autocarecompany2);
      });

      it('should accept null and undefined values', () => {
        const autocarecompany: IAutocarecompany = sampleWithRequiredData;
        expectedResult = service.addAutocarecompanyToCollectionIfMissing([], null, autocarecompany, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocarecompany);
      });

      it('should return initial array if no Autocarecompany is added', () => {
        const autocarecompanyCollection: IAutocarecompany[] = [sampleWithRequiredData];
        expectedResult = service.addAutocarecompanyToCollectionIfMissing(autocarecompanyCollection, undefined, null);
        expect(expectedResult).toEqual(autocarecompanyCollection);
      });
    });

    describe('compareAutocarecompany', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAutocarecompany(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAutocarecompany(entity1, entity2);
        const compareResult2 = service.compareAutocarecompany(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAutocarecompany(entity1, entity2);
        const compareResult2 = service.compareAutocarecompany(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAutocarecompany(entity1, entity2);
        const compareResult2 = service.compareAutocarecompany(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
