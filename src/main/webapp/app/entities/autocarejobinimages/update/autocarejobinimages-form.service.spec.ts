import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../autocarejobinimages.test-samples';

import { AutocarejobinimagesFormService } from './autocarejobinimages-form.service';

describe('Autocarejobinimages Form Service', () => {
  let service: AutocarejobinimagesFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutocarejobinimagesFormService);
  });

  describe('Service methods', () => {
    describe('createAutocarejobinimagesFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAutocarejobinimagesFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            jobid: expect.any(Object),
            imagefolder: expect.any(Object),
            imagename: expect.any(Object),
          }),
        );
      });

      it('passing IAutocarejobinimages should create a new form with FormGroup', () => {
        const formGroup = service.createAutocarejobinimagesFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            jobid: expect.any(Object),
            imagefolder: expect.any(Object),
            imagename: expect.any(Object),
          }),
        );
      });
    });

    describe('getAutocarejobinimages', () => {
      it('should return NewAutocarejobinimages for default Autocarejobinimages initial value', () => {
        const formGroup = service.createAutocarejobinimagesFormGroup(sampleWithNewData);

        const autocarejobinimages = service.getAutocarejobinimages(formGroup) as any;

        expect(autocarejobinimages).toMatchObject(sampleWithNewData);
      });

      it('should return NewAutocarejobinimages for empty Autocarejobinimages initial value', () => {
        const formGroup = service.createAutocarejobinimagesFormGroup();

        const autocarejobinimages = service.getAutocarejobinimages(formGroup) as any;

        expect(autocarejobinimages).toMatchObject({});
      });

      it('should return IAutocarejobinimages', () => {
        const formGroup = service.createAutocarejobinimagesFormGroup(sampleWithRequiredData);

        const autocarejobinimages = service.getAutocarejobinimages(formGroup) as any;

        expect(autocarejobinimages).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAutocarejobinimages should not enable id FormControl', () => {
        const formGroup = service.createAutocarejobinimagesFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAutocarejobinimages should disable id FormControl', () => {
        const formGroup = service.createAutocarejobinimagesFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
