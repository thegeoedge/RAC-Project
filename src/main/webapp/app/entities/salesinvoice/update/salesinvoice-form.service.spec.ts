import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../salesinvoice.test-samples';

import { SalesinvoiceFormService } from './salesinvoice-form.service';

describe('Salesinvoice Form Service', () => {
  let service: SalesinvoiceFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SalesinvoiceFormService);
  });

  describe('Service methods', () => {
    describe('createSalesinvoiceFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSalesinvoiceFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            invoicedate: expect.any(Object),
            createddate: expect.any(Object),
            quoteid: expect.any(Object),
            orderid: expect.any(Object),
            delieverydate: expect.any(Object),
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
            autocarecharges: expect.any(Object),
            autocarejobid: expect.any(Object),
            vehicleno: expect.any(Object),
            nextmeter: expect.any(Object),
            currentmeter: expect.any(Object),
            remarks: expect.any(Object),
            hasdummybill: expect.any(Object),
            dummybillid: expect.any(Object),
            dummybillamount: expect.any(Object),
            dummycommision: expect.any(Object),
            isserviceinvoice: expect.any(Object),
            nbtamount: expect.any(Object),
            vatamount: expect.any(Object),
            autocarecompanyid: expect.any(Object),
            iscompanyinvoice: expect.any(Object),
            invcanceldate: expect.any(Object),
            invcancelby: expect.any(Object),
            isvatinvoice: expect.any(Object),
            paymenttype: expect.any(Object),
            pendingamount: expect.any(Object),
            advancepayment: expect.any(Object),
            discountcode: expect.any(Object),
          }),
        );
      });

      it('passing ISalesinvoice should create a new form with FormGroup', () => {
        const formGroup = service.createSalesinvoiceFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            invoicedate: expect.any(Object),
            createddate: expect.any(Object),
            quoteid: expect.any(Object),
            orderid: expect.any(Object),
            delieverydate: expect.any(Object),
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
            autocarecharges: expect.any(Object),
            autocarejobid: expect.any(Object),
            vehicleno: expect.any(Object),
            nextmeter: expect.any(Object),
            currentmeter: expect.any(Object),
            remarks: expect.any(Object),
            hasdummybill: expect.any(Object),
            dummybillid: expect.any(Object),
            dummybillamount: expect.any(Object),
            dummycommision: expect.any(Object),
            isserviceinvoice: expect.any(Object),
            nbtamount: expect.any(Object),
            vatamount: expect.any(Object),
            autocarecompanyid: expect.any(Object),
            iscompanyinvoice: expect.any(Object),
            invcanceldate: expect.any(Object),
            invcancelby: expect.any(Object),
            isvatinvoice: expect.any(Object),
            paymenttype: expect.any(Object),
            pendingamount: expect.any(Object),
            advancepayment: expect.any(Object),
            discountcode: expect.any(Object),
          }),
        );
      });
    });

    describe('getSalesinvoice', () => {
      it('should return NewSalesinvoice for default Salesinvoice initial value', () => {
        const formGroup = service.createSalesinvoiceFormGroup(sampleWithNewData);

        const salesinvoice = service.getSalesinvoice(formGroup) as any;

        expect(salesinvoice).toMatchObject(sampleWithNewData);
      });

      it('should return NewSalesinvoice for empty Salesinvoice initial value', () => {
        const formGroup = service.createSalesinvoiceFormGroup();

        const salesinvoice = service.getSalesinvoice(formGroup) as any;

        expect(salesinvoice).toMatchObject({});
      });

      it('should return ISalesinvoice', () => {
        const formGroup = service.createSalesinvoiceFormGroup(sampleWithRequiredData);

        const salesinvoice = service.getSalesinvoice(formGroup) as any;

        expect(salesinvoice).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISalesinvoice should not enable id FormControl', () => {
        const formGroup = service.createSalesinvoiceFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSalesinvoice should disable id FormControl', () => {
        const formGroup = service.createSalesinvoiceFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
