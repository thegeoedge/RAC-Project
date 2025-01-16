import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../autojobempallocation.test-samples';

import { AutojobempallocationFormService } from './autojobempallocation-form.service';

describe('Autojobempallocation Form Service', () => {
  let service: AutojobempallocationFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutojobempallocationFormService);
  });

  describe('Service methods', () => {
    describe('createAutojobempallocationFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAutojobempallocationFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            jobid: expect.any(Object),
            categoryid: expect.any(Object),
            empposition: expect.any(Object),
            empid: expect.any(Object),
            empname: expect.any(Object),
            allocatetime: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            jobdate: expect.any(Object),
            commission: expect.any(Object),
            iscommissionpaid: expect.any(Object),
            starttime: expect.any(Object),
            endtime: expect.any(Object),
          }),
        );
      });

      it('passing IAutojobempallocation should create a new form with FormGroup', () => {
        const formGroup = service.createAutojobempallocationFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            jobid: expect.any(Object),
            categoryid: expect.any(Object),
            empposition: expect.any(Object),
            empid: expect.any(Object),
            empname: expect.any(Object),
            allocatetime: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            jobdate: expect.any(Object),
            commission: expect.any(Object),
            iscommissionpaid: expect.any(Object),
            starttime: expect.any(Object),
            endtime: expect.any(Object),
          }),
        );
      });
    });

    describe('getAutojobempallocation', () => {
      it('should return NewAutojobempallocation for default Autojobempallocation initial value', () => {
        const formGroup = service.createAutojobempallocationFormGroup(sampleWithNewData);

        const autojobempallocation = service.getAutojobempallocation(formGroup) as any;

        expect(autojobempallocation).toMatchObject(sampleWithNewData);
      });

      it('should return NewAutojobempallocation for empty Autojobempallocation initial value', () => {
        const formGroup = service.createAutojobempallocationFormGroup();

        const autojobempallocation = service.getAutojobempallocation(formGroup) as any;

        expect(autojobempallocation).toMatchObject({});
      });

      it('should return IAutojobempallocation', () => {
        const formGroup = service.createAutojobempallocationFormGroup(sampleWithRequiredData);

        const autojobempallocation = service.getAutojobempallocation(formGroup) as any;

        expect(autojobempallocation).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAutojobempallocation should not enable id FormControl', () => {
        const formGroup = service.createAutojobempallocationFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAutojobempallocation should disable id FormControl', () => {
        const formGroup = service.createAutojobempallocationFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
