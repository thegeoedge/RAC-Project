import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../autocarejobcategory.test-samples';

import { AutocarejobcategoryFormService } from './autocarejobcategory-form.service';

describe('Autocarejobcategory Form Service', () => {
  let service: AutocarejobcategoryFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutocarejobcategoryFormService);
  });

  describe('Service methods', () => {
    describe('createAutocarejobcategoryFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAutocarejobcategoryFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            displayorder: expect.any(Object),
          }),
        );
      });

      it('passing IAutocarejobcategory should create a new form with FormGroup', () => {
        const formGroup = service.createAutocarejobcategoryFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            displayorder: expect.any(Object),
          }),
        );
      });
    });

    describe('getAutocarejobcategory', () => {
      it('should return NewAutocarejobcategory for default Autocarejobcategory initial value', () => {
        const formGroup = service.createAutocarejobcategoryFormGroup(sampleWithNewData);

        const autocarejobcategory = service.getAutocarejobcategory(formGroup) as any;

        expect(autocarejobcategory).toMatchObject(sampleWithNewData);
      });

      it('should return NewAutocarejobcategory for empty Autocarejobcategory initial value', () => {
        const formGroup = service.createAutocarejobcategoryFormGroup();

        const autocarejobcategory = service.getAutocarejobcategory(formGroup) as any;

        expect(autocarejobcategory).toMatchObject({});
      });

      it('should return IAutocarejobcategory', () => {
        const formGroup = service.createAutocarejobcategoryFormGroup(sampleWithRequiredData);

        const autocarejobcategory = service.getAutocarejobcategory(formGroup) as any;

        expect(autocarejobcategory).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAutocarejobcategory should not enable id FormControl', () => {
        const formGroup = service.createAutocarejobcategoryFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAutocarejobcategory should disable id FormControl', () => {
        const formGroup = service.createAutocarejobcategoryFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
