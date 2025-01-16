import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../commonserviceoption.test-samples';

import { CommonserviceoptionFormService } from './commonserviceoption-form.service';

describe('Commonserviceoption Form Service', () => {
  let service: CommonserviceoptionFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommonserviceoptionFormService);
  });

  describe('Service methods', () => {
    describe('createCommonserviceoptionFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCommonserviceoptionFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            mainid: expect.any(Object),
            code: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            value: expect.any(Object),
            isactive: expect.any(Object),
            lmd: expect.any(Object),
            lmu: expect.any(Object),
          }),
        );
      });

      it('passing ICommonserviceoption should create a new form with FormGroup', () => {
        const formGroup = service.createCommonserviceoptionFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            mainid: expect.any(Object),
            code: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            value: expect.any(Object),
            isactive: expect.any(Object),
            lmd: expect.any(Object),
            lmu: expect.any(Object),
          }),
        );
      });
    });

    describe('getCommonserviceoption', () => {
      it('should return NewCommonserviceoption for default Commonserviceoption initial value', () => {
        const formGroup = service.createCommonserviceoptionFormGroup(sampleWithNewData);

        const commonserviceoption = service.getCommonserviceoption(formGroup) as any;

        expect(commonserviceoption).toMatchObject(sampleWithNewData);
      });

      it('should return NewCommonserviceoption for empty Commonserviceoption initial value', () => {
        const formGroup = service.createCommonserviceoptionFormGroup();

        const commonserviceoption = service.getCommonserviceoption(formGroup) as any;

        expect(commonserviceoption).toMatchObject({});
      });

      it('should return ICommonserviceoption', () => {
        const formGroup = service.createCommonserviceoptionFormGroup(sampleWithRequiredData);

        const commonserviceoption = service.getCommonserviceoption(formGroup) as any;

        expect(commonserviceoption).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICommonserviceoption should not enable id FormControl', () => {
        const formGroup = service.createCommonserviceoptionFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCommonserviceoption should disable id FormControl', () => {
        const formGroup = service.createCommonserviceoptionFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
