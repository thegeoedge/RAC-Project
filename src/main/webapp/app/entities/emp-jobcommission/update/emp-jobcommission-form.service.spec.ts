import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../emp-jobcommission.test-samples';

import { EmpJobcommissionFormService } from './emp-jobcommission-form.service';

describe('EmpJobcommission Form Service', () => {
  let service: EmpJobcommissionFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmpJobcommissionFormService);
  });

  describe('Service methods', () => {
    describe('createEmpJobcommissionFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createEmpJobcommissionFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            vehcatid: expect.any(Object),
            autojobcatid: expect.any(Object),
            firstcom: expect.any(Object),
            secondcom: expect.any(Object),
            thirdcom: expect.any(Object),
            lmd: expect.any(Object),
            lmu: expect.any(Object),
          }),
        );
      });

      it('passing IEmpJobcommission should create a new form with FormGroup', () => {
        const formGroup = service.createEmpJobcommissionFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            vehcatid: expect.any(Object),
            autojobcatid: expect.any(Object),
            firstcom: expect.any(Object),
            secondcom: expect.any(Object),
            thirdcom: expect.any(Object),
            lmd: expect.any(Object),
            lmu: expect.any(Object),
          }),
        );
      });
    });

    describe('getEmpJobcommission', () => {
      it('should return NewEmpJobcommission for default EmpJobcommission initial value', () => {
        const formGroup = service.createEmpJobcommissionFormGroup(sampleWithNewData);

        const empJobcommission = service.getEmpJobcommission(formGroup) as any;

        expect(empJobcommission).toMatchObject(sampleWithNewData);
      });

      it('should return NewEmpJobcommission for empty EmpJobcommission initial value', () => {
        const formGroup = service.createEmpJobcommissionFormGroup();

        const empJobcommission = service.getEmpJobcommission(formGroup) as any;

        expect(empJobcommission).toMatchObject({});
      });

      it('should return IEmpJobcommission', () => {
        const formGroup = service.createEmpJobcommissionFormGroup(sampleWithRequiredData);

        const empJobcommission = service.getEmpJobcommission(formGroup) as any;

        expect(empJobcommission).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IEmpJobcommission should not enable id FormControl', () => {
        const formGroup = service.createEmpJobcommissionFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewEmpJobcommission should disable id FormControl', () => {
        const formGroup = service.createEmpJobcommissionFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
