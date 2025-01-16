import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../emp-sectiontbl.test-samples';

import { EmpSectiontblFormService } from './emp-sectiontbl-form.service';

describe('EmpSectiontbl Form Service', () => {
  let service: EmpSectiontblFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmpSectiontblFormService);
  });

  describe('Service methods', () => {
    describe('createEmpSectiontblFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createEmpSectiontblFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            empid: expect.any(Object),
            sectionid: expect.any(Object),
            lmd: expect.any(Object),
            lmu: expect.any(Object),
          }),
        );
      });

      it('passing IEmpSectiontbl should create a new form with FormGroup', () => {
        const formGroup = service.createEmpSectiontblFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            empid: expect.any(Object),
            sectionid: expect.any(Object),
            lmd: expect.any(Object),
            lmu: expect.any(Object),
          }),
        );
      });
    });

    describe('getEmpSectiontbl', () => {
      it('should return NewEmpSectiontbl for default EmpSectiontbl initial value', () => {
        const formGroup = service.createEmpSectiontblFormGroup(sampleWithNewData);

        const empSectiontbl = service.getEmpSectiontbl(formGroup) as any;

        expect(empSectiontbl).toMatchObject(sampleWithNewData);
      });

      it('should return NewEmpSectiontbl for empty EmpSectiontbl initial value', () => {
        const formGroup = service.createEmpSectiontblFormGroup();

        const empSectiontbl = service.getEmpSectiontbl(formGroup) as any;

        expect(empSectiontbl).toMatchObject({});
      });

      it('should return IEmpSectiontbl', () => {
        const formGroup = service.createEmpSectiontblFormGroup(sampleWithRequiredData);

        const empSectiontbl = service.getEmpSectiontbl(formGroup) as any;

        expect(empSectiontbl).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IEmpSectiontbl should not enable id FormControl', () => {
        const formGroup = service.createEmpSectiontblFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewEmpSectiontbl should disable id FormControl', () => {
        const formGroup = service.createEmpSectiontblFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
