import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ITaxes } from '../taxes.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../taxes.test-samples';

import { TaxesService, RestTaxes } from './taxes.service';

const requireRestSample: RestTaxes = {
  ...sampleWithRequiredData,
  effectivefrom: sampleWithRequiredData.effectivefrom?.format(DATE_FORMAT),
  effectiveto: sampleWithRequiredData.effectiveto?.format(DATE_FORMAT),
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Taxes Service', () => {
  let service: TaxesService;
  let httpMock: HttpTestingController;
  let expectedResult: ITaxes | ITaxes[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TaxesService);
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

    it('should create a Taxes', () => {
      const taxes = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(taxes).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Taxes', () => {
      const taxes = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(taxes).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Taxes', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Taxes', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Taxes', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addTaxesToCollectionIfMissing', () => {
      it('should add a Taxes to an empty array', () => {
        const taxes: ITaxes = sampleWithRequiredData;
        expectedResult = service.addTaxesToCollectionIfMissing([], taxes);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(taxes);
      });

      it('should not add a Taxes to an array that contains it', () => {
        const taxes: ITaxes = sampleWithRequiredData;
        const taxesCollection: ITaxes[] = [
          {
            ...taxes,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTaxesToCollectionIfMissing(taxesCollection, taxes);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Taxes to an array that doesn't contain it", () => {
        const taxes: ITaxes = sampleWithRequiredData;
        const taxesCollection: ITaxes[] = [sampleWithPartialData];
        expectedResult = service.addTaxesToCollectionIfMissing(taxesCollection, taxes);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(taxes);
      });

      it('should add only unique Taxes to an array', () => {
        const taxesArray: ITaxes[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const taxesCollection: ITaxes[] = [sampleWithRequiredData];
        expectedResult = service.addTaxesToCollectionIfMissing(taxesCollection, ...taxesArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const taxes: ITaxes = sampleWithRequiredData;
        const taxes2: ITaxes = sampleWithPartialData;
        expectedResult = service.addTaxesToCollectionIfMissing([], taxes, taxes2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(taxes);
        expect(expectedResult).toContain(taxes2);
      });

      it('should accept null and undefined values', () => {
        const taxes: ITaxes = sampleWithRequiredData;
        expectedResult = service.addTaxesToCollectionIfMissing([], null, taxes, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(taxes);
      });

      it('should return initial array if no Taxes is added', () => {
        const taxesCollection: ITaxes[] = [sampleWithRequiredData];
        expectedResult = service.addTaxesToCollectionIfMissing(taxesCollection, undefined, null);
        expect(expectedResult).toEqual(taxesCollection);
      });
    });

    describe('compareTaxes', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTaxes(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareTaxes(entity1, entity2);
        const compareResult2 = service.compareTaxes(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareTaxes(entity1, entity2);
        const compareResult2 = service.compareTaxes(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareTaxes(entity1, entity2);
        const compareResult2 = service.compareTaxes(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
