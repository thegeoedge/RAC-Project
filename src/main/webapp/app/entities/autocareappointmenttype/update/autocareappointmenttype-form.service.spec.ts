import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../autocareappointmenttype.test-samples';

import { AutocareappointmenttypeFormService } from './autocareappointmenttype-form.service';

describe('Autocareappointmenttype Form Service', () => {
  let service: AutocareappointmenttypeFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutocareappointmenttypeFormService);
  });

  describe('Service methods', () => {
    describe('createAutocareappointmenttypeFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAutocareappointmenttypeFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            typename: expect.any(Object),
          }),
        );
      });

      it('passing IAutocareappointmenttype should create a new form with FormGroup', () => {
        const formGroup = service.createAutocareappointmenttypeFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            typename: expect.any(Object),
          }),
        );
      });
    });

    describe('getAutocareappointmenttype', () => {
      it('should return NewAutocareappointmenttype for default Autocareappointmenttype initial value', () => {
        const formGroup = service.createAutocareappointmenttypeFormGroup(sampleWithNewData);

        const autocareappointmenttype = service.getAutocareappointmenttype(formGroup) as any;

        expect(autocareappointmenttype).toMatchObject(sampleWithNewData);
      });

      it('should return NewAutocareappointmenttype for empty Autocareappointmenttype initial value', () => {
        const formGroup = service.createAutocareappointmenttypeFormGroup();

        const autocareappointmenttype = service.getAutocareappointmenttype(formGroup) as any;

        expect(autocareappointmenttype).toMatchObject({});
      });

      it('should return IAutocareappointmenttype', () => {
        const formGroup = service.createAutocareappointmenttypeFormGroup(sampleWithRequiredData);

        const autocareappointmenttype = service.getAutocareappointmenttype(formGroup) as any;

        expect(autocareappointmenttype).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAutocareappointmenttype should not enable id FormControl', () => {
        const formGroup = service.createAutocareappointmenttypeFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAutocareappointmenttype should disable id FormControl', () => {
        const formGroup = service.createAutocareappointmenttypeFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
