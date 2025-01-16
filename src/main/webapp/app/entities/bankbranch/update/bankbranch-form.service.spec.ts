import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../bankbranch.test-samples';

import { BankbranchFormService } from './bankbranch-form.service';

describe('Bankbranch Form Service', () => {
  let service: BankbranchFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BankbranchFormService);
  });

  describe('Service methods', () => {
    describe('createBankbranchFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createBankbranchFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            bankcode: expect.any(Object),
            branchcode: expect.any(Object),
            branchname: expect.any(Object),
          }),
        );
      });

      it('passing IBankbranch should create a new form with FormGroup', () => {
        const formGroup = service.createBankbranchFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            bankcode: expect.any(Object),
            branchcode: expect.any(Object),
            branchname: expect.any(Object),
          }),
        );
      });
    });

    describe('getBankbranch', () => {
      it('should return NewBankbranch for default Bankbranch initial value', () => {
        const formGroup = service.createBankbranchFormGroup(sampleWithNewData);

        const bankbranch = service.getBankbranch(formGroup) as any;

        expect(bankbranch).toMatchObject(sampleWithNewData);
      });

      it('should return NewBankbranch for empty Bankbranch initial value', () => {
        const formGroup = service.createBankbranchFormGroup();

        const bankbranch = service.getBankbranch(formGroup) as any;

        expect(bankbranch).toMatchObject({});
      });

      it('should return IBankbranch', () => {
        const formGroup = service.createBankbranchFormGroup(sampleWithRequiredData);

        const bankbranch = service.getBankbranch(formGroup) as any;

        expect(bankbranch).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IBankbranch should not enable id FormControl', () => {
        const formGroup = service.createBankbranchFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewBankbranch should disable id FormControl', () => {
        const formGroup = service.createBankbranchFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
