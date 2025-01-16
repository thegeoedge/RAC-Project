import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../salesorder.test-samples';

import { SalesorderFormService } from './salesorder-form.service';

describe('Salesorder Form Service', () => {
  let service: SalesorderFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SalesorderFormService);
  });

  describe('Service methods', () => {
    describe('createSalesorderFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSalesorderFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            orderdate: expect.any(Object),
            createddate: expect.any(Object),
            quoteid: expect.any(Object),
            salesrepid: expect.any(Object),
            salesrepname: expect.any(Object),
            delieverfrom: expect.any(Object),
            customerid: expect.any(Object),
            customername: expect.any(Object),
            customeraddress: expect.any(Object),
            deliveryaddress: expect.any(Object),
            subtotal: expect.any(Object),
            totaltax: expect.any(Object),
            totaldiscount: expect.any(Object),
            nettotal: expect.any(Object),
            message: expect.any(Object),
            isinvoiced: expect.any(Object),
            refinvoiceid: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            isfixed: expect.any(Object),
            isactive: expect.any(Object),
            advancepayment: expect.any(Object),
            advancepaymentreturnamount: expect.any(Object),
            advancepaymentreturndate: expect.any(Object),
            advancepaymentby: expect.any(Object),
          }),
        );
      });

      it('passing ISalesorder should create a new form with FormGroup', () => {
        const formGroup = service.createSalesorderFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            orderdate: expect.any(Object),
            createddate: expect.any(Object),
            quoteid: expect.any(Object),
            salesrepid: expect.any(Object),
            salesrepname: expect.any(Object),
            delieverfrom: expect.any(Object),
            customerid: expect.any(Object),
            customername: expect.any(Object),
            customeraddress: expect.any(Object),
            deliveryaddress: expect.any(Object),
            subtotal: expect.any(Object),
            totaltax: expect.any(Object),
            totaldiscount: expect.any(Object),
            nettotal: expect.any(Object),
            message: expect.any(Object),
            isinvoiced: expect.any(Object),
            refinvoiceid: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            isfixed: expect.any(Object),
            isactive: expect.any(Object),
            advancepayment: expect.any(Object),
            advancepaymentreturnamount: expect.any(Object),
            advancepaymentreturndate: expect.any(Object),
            advancepaymentby: expect.any(Object),
          }),
        );
      });
    });

    describe('getSalesorder', () => {
      it('should return NewSalesorder for default Salesorder initial value', () => {
        const formGroup = service.createSalesorderFormGroup(sampleWithNewData);

        const salesorder = service.getSalesorder(formGroup) as any;

        expect(salesorder).toMatchObject(sampleWithNewData);
      });

      it('should return NewSalesorder for empty Salesorder initial value', () => {
        const formGroup = service.createSalesorderFormGroup();

        const salesorder = service.getSalesorder(formGroup) as any;

        expect(salesorder).toMatchObject({});
      });

      it('should return ISalesorder', () => {
        const formGroup = service.createSalesorderFormGroup(sampleWithRequiredData);

        const salesorder = service.getSalesorder(formGroup) as any;

        expect(salesorder).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISalesorder should not enable id FormControl', () => {
        const formGroup = service.createSalesorderFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSalesorder should disable id FormControl', () => {
        const formGroup = service.createSalesorderFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
