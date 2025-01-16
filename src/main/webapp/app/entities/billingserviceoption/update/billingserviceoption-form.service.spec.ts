import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../billingserviceoption.test-samples';

import { BillingserviceoptionFormService } from './billingserviceoption-form.service';

describe('Billingserviceoption Form Service', () => {
  let service: BillingserviceoptionFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BillingserviceoptionFormService);
  });

  describe('Service methods', () => {
    describe('createBillingserviceoptionFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createBillingserviceoptionFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            servicename: expect.any(Object),
            servicediscription: expect.any(Object),
            isactive: expect.any(Object),
            lmd: expect.any(Object),
            lmu: expect.any(Object),
            orderby: expect.any(Object),
            billtocustomer: expect.any(Object),
          }),
        );
      });

      it('passing IBillingserviceoption should create a new form with FormGroup', () => {
        const formGroup = service.createBillingserviceoptionFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            servicename: expect.any(Object),
            servicediscription: expect.any(Object),
            isactive: expect.any(Object),
            lmd: expect.any(Object),
            lmu: expect.any(Object),
            orderby: expect.any(Object),
            billtocustomer: expect.any(Object),
          }),
        );
      });
    });

    describe('getBillingserviceoption', () => {
      it('should return NewBillingserviceoption for default Billingserviceoption initial value', () => {
        const formGroup = service.createBillingserviceoptionFormGroup(sampleWithNewData);

        const billingserviceoption = service.getBillingserviceoption(formGroup) as any;

        expect(billingserviceoption).toMatchObject(sampleWithNewData);
      });

      it('should return NewBillingserviceoption for empty Billingserviceoption initial value', () => {
        const formGroup = service.createBillingserviceoptionFormGroup();

        const billingserviceoption = service.getBillingserviceoption(formGroup) as any;

        expect(billingserviceoption).toMatchObject({});
      });

      it('should return IBillingserviceoption', () => {
        const formGroup = service.createBillingserviceoptionFormGroup(sampleWithRequiredData);

        const billingserviceoption = service.getBillingserviceoption(formGroup) as any;

        expect(billingserviceoption).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IBillingserviceoption should not enable id FormControl', () => {
        const formGroup = service.createBillingserviceoptionFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewBillingserviceoption should disable id FormControl', () => {
        const formGroup = service.createBillingserviceoptionFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
