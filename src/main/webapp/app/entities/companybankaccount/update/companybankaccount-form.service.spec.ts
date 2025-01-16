import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../companybankaccount.test-samples';

import { CompanybankaccountFormService } from './companybankaccount-form.service';

describe('Companybankaccount Form Service', () => {
  let service: CompanybankaccountFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CompanybankaccountFormService);
  });

  describe('Service methods', () => {
    describe('createCompanybankaccountFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCompanybankaccountFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            companyid: expect.any(Object),
            accountnumber: expect.any(Object),
            accountname: expect.any(Object),
            bankname: expect.any(Object),
            bankid: expect.any(Object),
            branchname: expect.any(Object),
            branchid: expect.any(Object),
            amount: expect.any(Object),
            accountcode: expect.any(Object),
            accountid: expect.any(Object),
            lmd: expect.any(Object),
            lmu: expect.any(Object),
            isactive: expect.any(Object),
            accounttypeid: expect.any(Object),
          }),
        );
      });

      it('passing ICompanybankaccount should create a new form with FormGroup', () => {
        const formGroup = service.createCompanybankaccountFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            companyid: expect.any(Object),
            accountnumber: expect.any(Object),
            accountname: expect.any(Object),
            bankname: expect.any(Object),
            bankid: expect.any(Object),
            branchname: expect.any(Object),
            branchid: expect.any(Object),
            amount: expect.any(Object),
            accountcode: expect.any(Object),
            accountid: expect.any(Object),
            lmd: expect.any(Object),
            lmu: expect.any(Object),
            isactive: expect.any(Object),
            accounttypeid: expect.any(Object),
          }),
        );
      });
    });

    describe('getCompanybankaccount', () => {
      it('should return NewCompanybankaccount for default Companybankaccount initial value', () => {
        const formGroup = service.createCompanybankaccountFormGroup(sampleWithNewData);

        const companybankaccount = service.getCompanybankaccount(formGroup) as any;

        expect(companybankaccount).toMatchObject(sampleWithNewData);
      });

      it('should return NewCompanybankaccount for empty Companybankaccount initial value', () => {
        const formGroup = service.createCompanybankaccountFormGroup();

        const companybankaccount = service.getCompanybankaccount(formGroup) as any;

        expect(companybankaccount).toMatchObject({});
      });

      it('should return ICompanybankaccount', () => {
        const formGroup = service.createCompanybankaccountFormGroup(sampleWithRequiredData);

        const companybankaccount = service.getCompanybankaccount(formGroup) as any;

        expect(companybankaccount).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICompanybankaccount should not enable id FormControl', () => {
        const formGroup = service.createCompanybankaccountFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCompanybankaccount should disable id FormControl', () => {
        const formGroup = service.createCompanybankaccountFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
