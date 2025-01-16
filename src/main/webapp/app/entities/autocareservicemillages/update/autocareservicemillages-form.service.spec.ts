import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../autocareservicemillages.test-samples';

import { AutocareservicemillagesFormService } from './autocareservicemillages-form.service';

describe('Autocareservicemillages Form Service', () => {
  let service: AutocareservicemillagesFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutocareservicemillagesFormService);
  });

  describe('Service methods', () => {
    describe('createAutocareservicemillagesFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAutocareservicemillagesFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            millage: expect.any(Object),
          }),
        );
      });

      it('passing IAutocareservicemillages should create a new form with FormGroup', () => {
        const formGroup = service.createAutocareservicemillagesFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            millage: expect.any(Object),
          }),
        );
      });
    });

    describe('getAutocareservicemillages', () => {
      it('should return NewAutocareservicemillages for default Autocareservicemillages initial value', () => {
        const formGroup = service.createAutocareservicemillagesFormGroup(sampleWithNewData);

        const autocareservicemillages = service.getAutocareservicemillages(formGroup) as any;

        expect(autocareservicemillages).toMatchObject(sampleWithNewData);
      });

      it('should return NewAutocareservicemillages for empty Autocareservicemillages initial value', () => {
        const formGroup = service.createAutocareservicemillagesFormGroup();

        const autocareservicemillages = service.getAutocareservicemillages(formGroup) as any;

        expect(autocareservicemillages).toMatchObject({});
      });

      it('should return IAutocareservicemillages', () => {
        const formGroup = service.createAutocareservicemillagesFormGroup(sampleWithRequiredData);

        const autocareservicemillages = service.getAutocareservicemillages(formGroup) as any;

        expect(autocareservicemillages).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAutocareservicemillages should not enable id FormControl', () => {
        const formGroup = service.createAutocareservicemillagesFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAutocareservicemillages should disable id FormControl', () => {
        const formGroup = service.createAutocareservicemillagesFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
