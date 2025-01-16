import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../nextserviceinstructions.test-samples';

import { NextserviceinstructionsFormService } from './nextserviceinstructions-form.service';

describe('Nextserviceinstructions Form Service', () => {
  let service: NextserviceinstructionsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NextserviceinstructionsFormService);
  });

  describe('Service methods', () => {
    describe('createNextserviceinstructionsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createNextserviceinstructionsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            jobid: expect.any(Object),
            instruction: expect.any(Object),
            isactive: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            ignore: expect.any(Object),
            vehicleid: expect.any(Object),
            vehicleno: expect.any(Object),
          }),
        );
      });

      it('passing INextserviceinstructions should create a new form with FormGroup', () => {
        const formGroup = service.createNextserviceinstructionsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            jobid: expect.any(Object),
            instruction: expect.any(Object),
            isactive: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            ignore: expect.any(Object),
            vehicleid: expect.any(Object),
            vehicleno: expect.any(Object),
          }),
        );
      });
    });

    describe('getNextserviceinstructions', () => {
      it('should return NewNextserviceinstructions for default Nextserviceinstructions initial value', () => {
        const formGroup = service.createNextserviceinstructionsFormGroup(sampleWithNewData);

        const nextserviceinstructions = service.getNextserviceinstructions(formGroup) as any;

        expect(nextserviceinstructions).toMatchObject(sampleWithNewData);
      });

      it('should return NewNextserviceinstructions for empty Nextserviceinstructions initial value', () => {
        const formGroup = service.createNextserviceinstructionsFormGroup();

        const nextserviceinstructions = service.getNextserviceinstructions(formGroup) as any;

        expect(nextserviceinstructions).toMatchObject({});
      });

      it('should return INextserviceinstructions', () => {
        const formGroup = service.createNextserviceinstructionsFormGroup(sampleWithRequiredData);

        const nextserviceinstructions = service.getNextserviceinstructions(formGroup) as any;

        expect(nextserviceinstructions).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing INextserviceinstructions should not enable id FormControl', () => {
        const formGroup = service.createNextserviceinstructionsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewNextserviceinstructions should disable id FormControl', () => {
        const formGroup = service.createNextserviceinstructionsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
