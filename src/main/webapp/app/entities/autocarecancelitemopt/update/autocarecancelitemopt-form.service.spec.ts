import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../autocarecancelitemopt.test-samples';

import { AutocarecancelitemoptFormService } from './autocarecancelitemopt-form.service';

describe('Autocarecancelitemopt Form Service', () => {
  let service: AutocarecancelitemoptFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutocarecancelitemoptFormService);
  });

  describe('Service methods', () => {
    describe('createAutocarecancelitemoptFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAutocarecancelitemoptFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            canceloption: expect.any(Object),
          }),
        );
      });

      it('passing IAutocarecancelitemopt should create a new form with FormGroup', () => {
        const formGroup = service.createAutocarecancelitemoptFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            canceloption: expect.any(Object),
          }),
        );
      });
    });

    describe('getAutocarecancelitemopt', () => {
      it('should return NewAutocarecancelitemopt for default Autocarecancelitemopt initial value', () => {
        const formGroup = service.createAutocarecancelitemoptFormGroup(sampleWithNewData);

        const autocarecancelitemopt = service.getAutocarecancelitemopt(formGroup) as any;

        expect(autocarecancelitemopt).toMatchObject(sampleWithNewData);
      });

      it('should return NewAutocarecancelitemopt for empty Autocarecancelitemopt initial value', () => {
        const formGroup = service.createAutocarecancelitemoptFormGroup();

        const autocarecancelitemopt = service.getAutocarecancelitemopt(formGroup) as any;

        expect(autocarecancelitemopt).toMatchObject({});
      });

      it('should return IAutocarecancelitemopt', () => {
        const formGroup = service.createAutocarecancelitemoptFormGroup(sampleWithRequiredData);

        const autocarecancelitemopt = service.getAutocarecancelitemopt(formGroup) as any;

        expect(autocarecancelitemopt).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAutocarecancelitemopt should not enable id FormControl', () => {
        const formGroup = service.createAutocarecancelitemoptFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAutocarecancelitemopt should disable id FormControl', () => {
        const formGroup = service.createAutocarecancelitemoptFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
