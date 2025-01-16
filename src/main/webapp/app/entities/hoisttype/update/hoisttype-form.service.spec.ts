import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../hoisttype.test-samples';

import { HoisttypeFormService } from './hoisttype-form.service';

describe('Hoisttype Form Service', () => {
  let service: HoisttypeFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HoisttypeFormService);
  });

  describe('Service methods', () => {
    describe('createHoisttypeFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createHoisttypeFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            hoisttype: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
          }),
        );
      });

      it('passing IHoisttype should create a new form with FormGroup', () => {
        const formGroup = service.createHoisttypeFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            hoisttype: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
          }),
        );
      });
    });

    describe('getHoisttype', () => {
      it('should return NewHoisttype for default Hoisttype initial value', () => {
        const formGroup = service.createHoisttypeFormGroup(sampleWithNewData);

        const hoisttype = service.getHoisttype(formGroup) as any;

        expect(hoisttype).toMatchObject(sampleWithNewData);
      });

      it('should return NewHoisttype for empty Hoisttype initial value', () => {
        const formGroup = service.createHoisttypeFormGroup();

        const hoisttype = service.getHoisttype(formGroup) as any;

        expect(hoisttype).toMatchObject({});
      });

      it('should return IHoisttype', () => {
        const formGroup = service.createHoisttypeFormGroup(sampleWithRequiredData);

        const hoisttype = service.getHoisttype(formGroup) as any;

        expect(hoisttype).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IHoisttype should not enable id FormControl', () => {
        const formGroup = service.createHoisttypeFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewHoisttype should disable id FormControl', () => {
        const formGroup = service.createHoisttypeFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
