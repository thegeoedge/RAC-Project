import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../customer.test-samples';

import { CustomerFormService } from './customer-form.service';

describe('Customer Form Service', () => {
  let service: CustomerFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomerFormService);
  });

  describe('Service methods', () => {
    describe('createCustomerFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCustomerFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            customertype: expect.any(Object),
            code: expect.any(Object),
            title: expect.any(Object),
            namewithinitials: expect.any(Object),
            fullname: expect.any(Object),
            callingname: expect.any(Object),
            nicno: expect.any(Object),
            nicissueddate: expect.any(Object),
            dateofbirth: expect.any(Object),
            bloodgroup: expect.any(Object),
            gender: expect.any(Object),
            maritalstatus: expect.any(Object),
            marrieddate: expect.any(Object),
            nationality: expect.any(Object),
            territory: expect.any(Object),
            religion: expect.any(Object),
            team: expect.any(Object),
            businessname: expect.any(Object),
            businessregdate: expect.any(Object),
            businessregno: expect.any(Object),
            profilepicturepath: expect.any(Object),
            residencehouseno: expect.any(Object),
            residenceaddress: expect.any(Object),
            residencecity: expect.any(Object),
            residencephone: expect.any(Object),
            businesslocationno: expect.any(Object),
            businessaddress: expect.any(Object),
            businesscity: expect.any(Object),
            businessphone1: expect.any(Object),
            businessphone2: expect.any(Object),
            businessmobile: expect.any(Object),
            businessfax: expect.any(Object),
            businessemail: expect.any(Object),
            businessprovinceid: expect.any(Object),
            businessdistrictid: expect.any(Object),
            contactpersonname: expect.any(Object),
            contactpersonphone: expect.any(Object),
            contactpersonmobile: expect.any(Object),
            contactpersonemail: expect.any(Object),
            rootmappath: expect.any(Object),
            website: expect.any(Object),
            registrationdate: expect.any(Object),
            isactive: expect.any(Object),
            isinternalcustomer: expect.any(Object),
            description: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            maximumdiscount: expect.any(Object),
            creditlimit: expect.any(Object),
            hassecuritydeposit: expect.any(Object),
            securitydepositamount: expect.any(Object),
            paybycash: expect.any(Object),
            cashpaymentbeforeload: expect.any(Object),
            cashlastinvoicebeforeload: expect.any(Object),
            paybycredit: expect.any(Object),
            creditoneweekcheck: expect.any(Object),
            creditpaymentbydays: expect.any(Object),
            haspurchasingdeposit: expect.any(Object),
            hassecuritydepositbond: expect.any(Object),
            hasassestsdeposit: expect.any(Object),
            customerrootmappath: expect.any(Object),
            employername: expect.any(Object),
            employeraddress: expect.any(Object),
            employerphone: expect.any(Object),
            employerdesignation: expect.any(Object),
            previousemployername: expect.any(Object),
            previousemployeraddress: expect.any(Object),
            previousindustry: expect.any(Object),
            previousperiod: expect.any(Object),
            previouspositions: expect.any(Object),
            previousresionforleaving: expect.any(Object),
            hascreaditlimit: expect.any(Object),
            accountid: expect.any(Object),
            accountcode: expect.any(Object),
            isregistered: expect.any(Object),
            vatregnumber: expect.any(Object),
            tinnumber: expect.any(Object),
            lat: expect.any(Object),
            lon: expect.any(Object),
            creditperiod: expect.any(Object),
          }),
        );
      });

      it('passing ICustomer should create a new form with FormGroup', () => {
        const formGroup = service.createCustomerFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            customertype: expect.any(Object),
            code: expect.any(Object),
            title: expect.any(Object),
            namewithinitials: expect.any(Object),
            fullname: expect.any(Object),
            callingname: expect.any(Object),
            nicno: expect.any(Object),
            nicissueddate: expect.any(Object),
            dateofbirth: expect.any(Object),
            bloodgroup: expect.any(Object),
            gender: expect.any(Object),
            maritalstatus: expect.any(Object),
            marrieddate: expect.any(Object),
            nationality: expect.any(Object),
            territory: expect.any(Object),
            religion: expect.any(Object),
            team: expect.any(Object),
            businessname: expect.any(Object),
            businessregdate: expect.any(Object),
            businessregno: expect.any(Object),
            profilepicturepath: expect.any(Object),
            residencehouseno: expect.any(Object),
            residenceaddress: expect.any(Object),
            residencecity: expect.any(Object),
            residencephone: expect.any(Object),
            businesslocationno: expect.any(Object),
            businessaddress: expect.any(Object),
            businesscity: expect.any(Object),
            businessphone1: expect.any(Object),
            businessphone2: expect.any(Object),
            businessmobile: expect.any(Object),
            businessfax: expect.any(Object),
            businessemail: expect.any(Object),
            businessprovinceid: expect.any(Object),
            businessdistrictid: expect.any(Object),
            contactpersonname: expect.any(Object),
            contactpersonphone: expect.any(Object),
            contactpersonmobile: expect.any(Object),
            contactpersonemail: expect.any(Object),
            rootmappath: expect.any(Object),
            website: expect.any(Object),
            registrationdate: expect.any(Object),
            isactive: expect.any(Object),
            isinternalcustomer: expect.any(Object),
            description: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            maximumdiscount: expect.any(Object),
            creditlimit: expect.any(Object),
            hassecuritydeposit: expect.any(Object),
            securitydepositamount: expect.any(Object),
            paybycash: expect.any(Object),
            cashpaymentbeforeload: expect.any(Object),
            cashlastinvoicebeforeload: expect.any(Object),
            paybycredit: expect.any(Object),
            creditoneweekcheck: expect.any(Object),
            creditpaymentbydays: expect.any(Object),
            haspurchasingdeposit: expect.any(Object),
            hassecuritydepositbond: expect.any(Object),
            hasassestsdeposit: expect.any(Object),
            customerrootmappath: expect.any(Object),
            employername: expect.any(Object),
            employeraddress: expect.any(Object),
            employerphone: expect.any(Object),
            employerdesignation: expect.any(Object),
            previousemployername: expect.any(Object),
            previousemployeraddress: expect.any(Object),
            previousindustry: expect.any(Object),
            previousperiod: expect.any(Object),
            previouspositions: expect.any(Object),
            previousresionforleaving: expect.any(Object),
            hascreaditlimit: expect.any(Object),
            accountid: expect.any(Object),
            accountcode: expect.any(Object),
            isregistered: expect.any(Object),
            vatregnumber: expect.any(Object),
            tinnumber: expect.any(Object),
            lat: expect.any(Object),
            lon: expect.any(Object),
            creditperiod: expect.any(Object),
          }),
        );
      });
    });

    describe('getCustomer', () => {
      it('should return NewCustomer for default Customer initial value', () => {
        const formGroup = service.createCustomerFormGroup(sampleWithNewData);

        const customer = service.getCustomer(formGroup) as any;

        expect(customer).toMatchObject(sampleWithNewData);
      });

      it('should return NewCustomer for empty Customer initial value', () => {
        const formGroup = service.createCustomerFormGroup();

        const customer = service.getCustomer(formGroup) as any;

        expect(customer).toMatchObject({});
      });

      it('should return ICustomer', () => {
        const formGroup = service.createCustomerFormGroup(sampleWithRequiredData);

        const customer = service.getCustomer(formGroup) as any;

        expect(customer).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICustomer should not enable id FormControl', () => {
        const formGroup = service.createCustomerFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCustomer should disable id FormControl', () => {
        const formGroup = service.createCustomerFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
