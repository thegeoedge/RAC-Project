import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../billingserviceoptionvalues.test-samples';

import { BillingserviceoptionvaluesFormService } from './billingserviceoptionvalues-form.service';

describe('Billingserviceoptionvalues Form Service', () => {
  let service: BillingserviceoptionvaluesFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BillingserviceoptionvaluesFormService);
  });

  describe('Service methods', () => {
    describe('createBillingserviceoptionvaluesFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createBillingserviceoptionvaluesFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            vehicletypeid: expect.any(Object),
            billingserviceoptionid: expect.any(Object),
            value: expect.any(Object),
            lmd: expect.any(Object),
            lmu: expect.any(Object),
          }),
        );
      });

      it('passing IBillingserviceoptionvalues should create a new form with FormGroup', () => {
        const formGroup = service.createBillingserviceoptionvaluesFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            vehicletypeid: expect.any(Object),
            billingserviceoptionid: expect.any(Object),
            value: expect.any(Object),
            lmd: expect.any(Object),
            lmu: expect.any(Object),
          }),
        );
      });
    });

    describe('getBillingserviceoptionvalues', () => {
      it('should return NewBillingserviceoptionvalues for default Billingserviceoptionvalues initial value', () => {
        const formGroup = service.createBillingserviceoptionvaluesFormGroup(sampleWithNewData);

        const billingserviceoptionvalues = service.getBillingserviceoptionvalues(formGroup) as any;

        expect(billingserviceoptionvalues).toMatchObject(sampleWithNewData);
      });

      it('should return NewBillingserviceoptionvalues for empty Billingserviceoptionvalues initial value', () => {
        const formGroup = service.createBillingserviceoptionvaluesFormGroup();

        const billingserviceoptionvalues = service.getBillingserviceoptionvalues(formGroup) as any;

        expect(billingserviceoptionvalues).toMatchObject({});
      });

      it('should return IBillingserviceoptionvalues', () => {
        const formGroup = service.createBillingserviceoptionvaluesFormGroup(sampleWithRequiredData);

        const billingserviceoptionvalues = service.getBillingserviceoptionvalues(formGroup) as any;

        expect(billingserviceoptionvalues).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IBillingserviceoptionvalues should not enable id FormControl', () => {
        const formGroup = service.createBillingserviceoptionvaluesFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewBillingserviceoptionvalues should disable id FormControl', () => {
        const formGroup = service.createBillingserviceoptionvaluesFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
