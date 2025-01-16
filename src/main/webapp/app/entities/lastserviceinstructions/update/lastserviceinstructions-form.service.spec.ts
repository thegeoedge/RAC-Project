import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../lastserviceinstructions.test-samples';

import { LastserviceinstructionsFormService } from './lastserviceinstructions-form.service';

describe('Lastserviceinstructions Form Service', () => {
  let service: LastserviceinstructionsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LastserviceinstructionsFormService);
  });

  describe('Service methods', () => {
    describe('createLastserviceinstructionsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createLastserviceinstructionsFormGroup();

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

      it('passing ILastserviceinstructions should create a new form with FormGroup', () => {
        const formGroup = service.createLastserviceinstructionsFormGroup(sampleWithRequiredData);

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

    describe('getLastserviceinstructions', () => {
      it('should return NewLastserviceinstructions for default Lastserviceinstructions initial value', () => {
        const formGroup = service.createLastserviceinstructionsFormGroup(sampleWithNewData);

        const lastserviceinstructions = service.getLastserviceinstructions(formGroup) as any;

        expect(lastserviceinstructions).toMatchObject(sampleWithNewData);
      });

      it('should return NewLastserviceinstructions for empty Lastserviceinstructions initial value', () => {
        const formGroup = service.createLastserviceinstructionsFormGroup();

        const lastserviceinstructions = service.getLastserviceinstructions(formGroup) as any;

        expect(lastserviceinstructions).toMatchObject({});
      });

      it('should return ILastserviceinstructions', () => {
        const formGroup = service.createLastserviceinstructionsFormGroup(sampleWithRequiredData);

        const lastserviceinstructions = service.getLastserviceinstructions(formGroup) as any;

        expect(lastserviceinstructions).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ILastserviceinstructions should not enable id FormControl', () => {
        const formGroup = service.createLastserviceinstructionsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewLastserviceinstructions should disable id FormControl', () => {
        const formGroup = service.createLastserviceinstructionsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
