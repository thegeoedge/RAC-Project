import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../paymentterm.test-samples';

import { PaymenttermFormService } from './paymentterm-form.service';

describe('Paymentterm Form Service', () => {
  let service: PaymenttermFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PaymenttermFormService);
  });

  describe('Service methods', () => {
    describe('createPaymenttermFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createPaymenttermFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            paymenttype: expect.any(Object),
            forvoucher: expect.any(Object),
          }),
        );
      });

      it('passing IPaymentterm should create a new form with FormGroup', () => {
        const formGroup = service.createPaymenttermFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            paymenttype: expect.any(Object),
            forvoucher: expect.any(Object),
          }),
        );
      });
    });

    describe('getPaymentterm', () => {
      it('should return NewPaymentterm for default Paymentterm initial value', () => {
        const formGroup = service.createPaymenttermFormGroup(sampleWithNewData);

        const paymentterm = service.getPaymentterm(formGroup) as any;

        expect(paymentterm).toMatchObject(sampleWithNewData);
      });

      it('should return NewPaymentterm for empty Paymentterm initial value', () => {
        const formGroup = service.createPaymenttermFormGroup();

        const paymentterm = service.getPaymentterm(formGroup) as any;

        expect(paymentterm).toMatchObject({});
      });

      it('should return IPaymentterm', () => {
        const formGroup = service.createPaymenttermFormGroup(sampleWithRequiredData);

        const paymentterm = service.getPaymentterm(formGroup) as any;

        expect(paymentterm).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IPaymentterm should not enable id FormControl', () => {
        const formGroup = service.createPaymenttermFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewPaymentterm should disable id FormControl', () => {
        const formGroup = service.createPaymenttermFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
