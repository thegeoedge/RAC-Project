import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IAutocareappointment } from '../autocareappointment.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../autocareappointment.test-samples';

import { AutocareappointmentService, RestAutocareappointment } from './autocareappointment.service';

const requireRestSample: RestAutocareappointment = {
  ...sampleWithRequiredData,
  appointmentdate: sampleWithRequiredData.appointmentdate?.toJSON(),
  addeddate: sampleWithRequiredData.addeddate?.toJSON(),
  conformdate: sampleWithRequiredData.conformdate?.toJSON(),
  appointmenttime: sampleWithRequiredData.appointmenttime?.toJSON(),
  lmd: sampleWithRequiredData.lmd?.toJSON(),
};

describe('Autocareappointment Service', () => {
  let service: AutocareappointmentService;
  let httpMock: HttpTestingController;
  let expectedResult: IAutocareappointment | IAutocareappointment[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(AutocareappointmentService);
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

    it('should create a Autocareappointment', () => {
      const autocareappointment = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(autocareappointment).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Autocareappointment', () => {
      const autocareappointment = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(autocareappointment).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Autocareappointment', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Autocareappointment', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Autocareappointment', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAutocareappointmentToCollectionIfMissing', () => {
      it('should add a Autocareappointment to an empty array', () => {
        const autocareappointment: IAutocareappointment = sampleWithRequiredData;
        expectedResult = service.addAutocareappointmentToCollectionIfMissing([], autocareappointment);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocareappointment);
      });

      it('should not add a Autocareappointment to an array that contains it', () => {
        const autocareappointment: IAutocareappointment = sampleWithRequiredData;
        const autocareappointmentCollection: IAutocareappointment[] = [
          {
            ...autocareappointment,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAutocareappointmentToCollectionIfMissing(autocareappointmentCollection, autocareappointment);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Autocareappointment to an array that doesn't contain it", () => {
        const autocareappointment: IAutocareappointment = sampleWithRequiredData;
        const autocareappointmentCollection: IAutocareappointment[] = [sampleWithPartialData];
        expectedResult = service.addAutocareappointmentToCollectionIfMissing(autocareappointmentCollection, autocareappointment);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocareappointment);
      });

      it('should add only unique Autocareappointment to an array', () => {
        const autocareappointmentArray: IAutocareappointment[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const autocareappointmentCollection: IAutocareappointment[] = [sampleWithRequiredData];
        expectedResult = service.addAutocareappointmentToCollectionIfMissing(autocareappointmentCollection, ...autocareappointmentArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const autocareappointment: IAutocareappointment = sampleWithRequiredData;
        const autocareappointment2: IAutocareappointment = sampleWithPartialData;
        expectedResult = service.addAutocareappointmentToCollectionIfMissing([], autocareappointment, autocareappointment2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autocareappointment);
        expect(expectedResult).toContain(autocareappointment2);
      });

      it('should accept null and undefined values', () => {
        const autocareappointment: IAutocareappointment = sampleWithRequiredData;
        expectedResult = service.addAutocareappointmentToCollectionIfMissing([], null, autocareappointment, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autocareappointment);
      });

      it('should return initial array if no Autocareappointment is added', () => {
        const autocareappointmentCollection: IAutocareappointment[] = [sampleWithRequiredData];
        expectedResult = service.addAutocareappointmentToCollectionIfMissing(autocareappointmentCollection, undefined, null);
        expect(expectedResult).toEqual(autocareappointmentCollection);
      });
    });

    describe('compareAutocareappointment', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAutocareappointment(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAutocareappointment(entity1, entity2);
        const compareResult2 = service.compareAutocareappointment(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAutocareappointment(entity1, entity2);
        const compareResult2 = service.compareAutocareappointment(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAutocareappointment(entity1, entity2);
        const compareResult2 = service.compareAutocareappointment(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
