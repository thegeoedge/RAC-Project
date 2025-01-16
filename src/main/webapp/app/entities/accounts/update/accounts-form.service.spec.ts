import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../accounts.test-samples';

import { AccountsFormService } from './accounts-form.service';

describe('Accounts Form Service', () => {
  let service: AccountsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccountsFormService);
  });

  describe('Service methods', () => {
    describe('createAccountsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAccountsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            date: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            type: expect.any(Object),
            parent: expect.any(Object),
            balance: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            hasbatches: expect.any(Object),
            accountvalue: expect.any(Object),
            accountlevel: expect.any(Object),
            accountsnumberingsystem: expect.any(Object),
            subparentid: expect.any(Object),
            canedit: expect.any(Object),
            amount: expect.any(Object),
            creditamount: expect.any(Object),
            debitamount: expect.any(Object),
            debitorcredit: expect.any(Object),
            reporttype: expect.any(Object),
          }),
        );
      });

      it('passing IAccounts should create a new form with FormGroup', () => {
        const formGroup = service.createAccountsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            date: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            type: expect.any(Object),
            parent: expect.any(Object),
            balance: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            hasbatches: expect.any(Object),
            accountvalue: expect.any(Object),
            accountlevel: expect.any(Object),
            accountsnumberingsystem: expect.any(Object),
            subparentid: expect.any(Object),
            canedit: expect.any(Object),
            amount: expect.any(Object),
            creditamount: expect.any(Object),
            debitamount: expect.any(Object),
            debitorcredit: expect.any(Object),
            reporttype: expect.any(Object),
          }),
        );
      });
    });

    describe('getAccounts', () => {
      it('should return NewAccounts for default Accounts initial value', () => {
        const formGroup = service.createAccountsFormGroup(sampleWithNewData);

        const accounts = service.getAccounts(formGroup) as any;

        expect(accounts).toMatchObject(sampleWithNewData);
      });

      it('should return NewAccounts for empty Accounts initial value', () => {
        const formGroup = service.createAccountsFormGroup();

        const accounts = service.getAccounts(formGroup) as any;

        expect(accounts).toMatchObject({});
      });

      it('should return IAccounts', () => {
        const formGroup = service.createAccountsFormGroup(sampleWithRequiredData);

        const accounts = service.getAccounts(formGroup) as any;

        expect(accounts).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAccounts should not enable id FormControl', () => {
        const formGroup = service.createAccountsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAccounts should disable id FormControl', () => {
        const formGroup = service.createAccountsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
