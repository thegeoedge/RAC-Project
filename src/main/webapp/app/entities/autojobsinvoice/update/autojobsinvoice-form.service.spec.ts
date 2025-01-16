import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../autojobsinvoice.test-samples';

import { AutojobsinvoiceFormService } from './autojobsinvoice-form.service';

describe('Autojobsinvoice Form Service', () => {
  let service: AutojobsinvoiceFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutojobsinvoiceFormService);
  });

  describe('Service methods', () => {
    describe('createAutojobsinvoiceFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAutojobsinvoiceFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            invoicedate: expect.any(Object),
            createddate: expect.any(Object),
            jobid: expect.any(Object),
            quoteid: expect.any(Object),
            orderid: expect.any(Object),
            delieverydate: expect.any(Object),
            autojobsrepid: expect.any(Object),
            autojobsrepname: expect.any(Object),
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
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            paidamount: expect.any(Object),
            amountowing: expect.any(Object),
            isactive: expect.any(Object),
            locationid: expect.any(Object),
            locationcode: expect.any(Object),
            referencecode: expect.any(Object),
            createdbyid: expect.any(Object),
            createdbyname: expect.any(Object),
            autocarecompanyid: expect.any(Object),
          }),
        );
      });

      it('passing IAutojobsinvoice should create a new form with FormGroup', () => {
        const formGroup = service.createAutojobsinvoiceFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            invoicedate: expect.any(Object),
            createddate: expect.any(Object),
            jobid: expect.any(Object),
            quoteid: expect.any(Object),
            orderid: expect.any(Object),
            delieverydate: expect.any(Object),
            autojobsrepid: expect.any(Object),
            autojobsrepname: expect.any(Object),
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
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            paidamount: expect.any(Object),
            amountowing: expect.any(Object),
            isactive: expect.any(Object),
            locationid: expect.any(Object),
            locationcode: expect.any(Object),
            referencecode: expect.any(Object),
            createdbyid: expect.any(Object),
            createdbyname: expect.any(Object),
            autocarecompanyid: expect.any(Object),
          }),
        );
      });
    });

    describe('getAutojobsinvoice', () => {
      it('should return NewAutojobsinvoice for default Autojobsinvoice initial value', () => {
        const formGroup = service.createAutojobsinvoiceFormGroup(sampleWithNewData);

        const autojobsinvoice = service.getAutojobsinvoice(formGroup) as any;

        expect(autojobsinvoice).toMatchObject(sampleWithNewData);
      });

      it('should return NewAutojobsinvoice for empty Autojobsinvoice initial value', () => {
        const formGroup = service.createAutojobsinvoiceFormGroup();

        const autojobsinvoice = service.getAutojobsinvoice(formGroup) as any;

        expect(autojobsinvoice).toMatchObject({});
      });

      it('should return IAutojobsinvoice', () => {
        const formGroup = service.createAutojobsinvoiceFormGroup(sampleWithRequiredData);

        const autojobsinvoice = service.getAutojobsinvoice(formGroup) as any;

        expect(autojobsinvoice).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAutojobsinvoice should not enable id FormControl', () => {
        const formGroup = service.createAutojobsinvoiceFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAutojobsinvoice should disable id FormControl', () => {
        const formGroup = service.createAutojobsinvoiceFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
