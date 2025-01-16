import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAutocareappointmenttype } from '../autocareappointmenttype.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../autocareappointmenttype.test-samples';

import { AutocareappointmenttypeService } from './autocareappointmenttype.service';

const requireRestSample: IAutocareappointmenttype = {
  ...sampleWithRequiredData,
};

describe('Autocareappointmenttype Service', () => {
  let service: AutocareappointmenttypeService;
  let httpMock: HttpTestingController;
  let expectedResult: IAutocareappointmenttype | IAutocareappointmenttype[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AutocareappointmenttypeService);
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

    it('should create a Autocareappointmenttype', () => {
      const autocareappointmenttype = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(autocareappointmenttype).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Autocareappointmenttype', () => {
      const autocareappointmenttype = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(autocareappointmenttype).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Autocareappointmenttype', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Autocareappointmenttype', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Autocareappointmenttype', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAutocareappointmenttypeToCollectionIfMissing', () => {
      it('should add a Autocareappointmenttype to an empty array', () => {
        const autocareappointmenttype: IAutocareappointmenttype = sampleWithRequiredData;
        expectedResult = service.addAutocareappointmenttypeToCollectionIfMissing([], autocareappointmenttype);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocareappointmenttype);
      });

      it('should not add a Autocareappointmenttype to an array that contains it', () => {
        const autocareappointmenttype: IAutocareappointmenttype = sampleWithRequiredData;
        const autocareappointmenttypeCollection: IAutocareappointmenttype[] = [
          {
            ...autocareappointmenttype,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAutocareappointmenttypeToCollectionIfMissing(
          autocareappointmenttypeCollection,
          autocareappointmenttype,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Autocareappointmenttype to an array that doesn't contain it", () => {
        const autocareappointmenttype: IAutocareappointmenttype = sampleWithRequiredData;
        const autocareappointmenttypeCollection: IAutocareappointmenttype[] = [sampleWithPartialData];
        expectedResult = service.addAutocareappointmenttypeToCollectionIfMissing(
          autocareappointmenttypeCollection,
          autocareappointmenttype,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocareappointmenttype);
      });

      it('should add only unique Autocareappointmenttype to an array', () => {
        const autocareappointmenttypeArray: IAutocareappointmenttype[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const autocareappointmenttypeCollection: IAutocareappointmenttype[] = [sampleWithRequiredData];
        expectedResult = service.addAutocareappointmenttypeToCollectionIfMissing(
          autocareappointmenttypeCollection,
          ...autocareappointmenttypeArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const autocareappointmenttype: IAutocareappointmenttype = sampleWithRequiredData;
        const autocareappointmenttype2: IAutocareappointmenttype = sampleWithPartialData;
        expectedResult = service.addAutocareappointmenttypeToCollectionIfMissing([], autocareappointmenttype, autocareappointmenttype2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocareappointmenttype);
        expect(expectedResult).toContain(autocareappointmenttype2);
      });

      it('should accept null and undefined values', () => {
        const autocareappointmenttype: IAutocareappointmenttype = sampleWithRequiredData;
        expectedResult = service.addAutocareappointmenttypeToCollectionIfMissing([], null, autocareappointmenttype, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocareappointmenttype);
      });

      it('should return initial array if no Autocareappointmenttype is added', () => {
        const autocareappointmenttypeCollection: IAutocareappointmenttype[] = [sampleWithRequiredData];
        expectedResult = service.addAutocareappointmenttypeToCollectionIfMissing(autocareappointmenttypeCollection, undefined, null);
        expect(expectedResult).toEqual(autocareappointmenttypeCollection);
      });
    });

    describe('compareAutocareappointmenttype', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAutocareappointmenttype(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAutocareappointmenttype(entity1, entity2);
        const compareResult2 = service.compareAutocareappointmenttype(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAutocareappointmenttype(entity1, entity2);
        const compareResult2 = service.compareAutocareappointmenttype(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAutocareappointmenttype(entity1, entity2);
        const compareResult2 = service.compareAutocareappointmenttype(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
