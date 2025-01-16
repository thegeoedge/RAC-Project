import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../autocarehoist.test-samples';

import { AutocarehoistFormService } from './autocarehoist-form.service';

describe('Autocarehoist Form Service', () => {
  let service: AutocarehoistFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutocarehoistFormService);
  });

  describe('Service methods', () => {
    describe('createAutocarehoistFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAutocarehoistFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            hoistname: expect.any(Object),
            hoistcode: expect.any(Object),
            hoisttypeid: expect.any(Object),
            hoisttypename: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
          }),
        );
      });

      it('passing IAutocarehoist should create a new form with FormGroup', () => {
        const formGroup = service.createAutocarehoistFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            hoistname: expect.any(Object),
            hoistcode: expect.any(Object),
            hoisttypeid: expect.any(Object),
            hoisttypename: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
          }),
        );
      });
    });

    describe('getAutocarehoist', () => {
      it('should return NewAutocarehoist for default Autocarehoist initial value', () => {
        const formGroup = service.createAutocarehoistFormGroup(sampleWithNewData);

        const autocarehoist = service.getAutocarehoist(formGroup) as any;

        expect(autocarehoist).toMatchObject(sampleWithNewData);
      });

      it('should return NewAutocarehoist for empty Autocarehoist initial value', () => {
        const formGroup = service.createAutocarehoistFormGroup();

        const autocarehoist = service.getAutocarehoist(formGroup) as any;

        expect(autocarehoist).toMatchObject({});
      });

      it('should return IAutocarehoist', () => {
        const formGroup = service.createAutocarehoistFormGroup(sampleWithRequiredData);

        const autocarehoist = service.getAutocarehoist(formGroup) as any;

        expect(autocarehoist).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAutocarehoist should not enable id FormControl', () => {
        const formGroup = service.createAutocarehoistFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAutocarehoist should disable id FormControl', () => {
        const formGroup = service.createAutocarehoistFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
