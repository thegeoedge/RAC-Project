import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../autocareappointment.test-samples';

import { AutocareappointmentFormService } from './autocareappointment-form.service';

describe('Autocareappointment Form Service', () => {
  let service: AutocareappointmentFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutocareappointmentFormService);
  });

  describe('Service methods', () => {
    describe('createAutocareappointmentFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAutocareappointmentFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            appointmenttype: expect.any(Object),
            appointmentdate: expect.any(Object),
            addeddate: expect.any(Object),
            conformdate: expect.any(Object),
            appointmentnumber: expect.any(Object),
            vehiclenumber: expect.any(Object),
            appointmenttime: expect.any(Object),
            isconformed: expect.any(Object),
            conformedby: expect.any(Object),
            lmd: expect.any(Object),
            lmu: expect.any(Object),
            customerid: expect.any(Object),
            contactnumber: expect.any(Object),
            customername: expect.any(Object),
            issued: expect.any(Object),
            hoistid: expect.any(Object),
            isarrived: expect.any(Object),
            iscancel: expect.any(Object),
            isnoanswer: expect.any(Object),
            missedappointmentcall: expect.any(Object),
            customermobileid: expect.any(Object),
            customermobilevehicleid: expect.any(Object),
            vehicleid: expect.any(Object),
            ismobileappointment: expect.any(Object),
            advancepayment: expect.any(Object),
            jobid: expect.any(Object),
          }),
        );
      });

      it('passing IAutocareappointment should create a new form with FormGroup', () => {
        const formGroup = service.createAutocareappointmentFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            appointmenttype: expect.any(Object),
            appointmentdate: expect.any(Object),
            addeddate: expect.any(Object),
            conformdate: expect.any(Object),
            appointmentnumber: expect.any(Object),
            vehiclenumber: expect.any(Object),
            appointmenttime: expect.any(Object),
            isconformed: expect.any(Object),
            conformedby: expect.any(Object),
            lmd: expect.any(Object),
            lmu: expect.any(Object),
            customerid: expect.any(Object),
            contactnumber: expect.any(Object),
            customername: expect.any(Object),
            issued: expect.any(Object),
            hoistid: expect.any(Object),
            isarrived: expect.any(Object),
            iscancel: expect.any(Object),
            isnoanswer: expect.any(Object),
            missedappointmentcall: expect.any(Object),
            customermobileid: expect.any(Object),
            customermobilevehicleid: expect.any(Object),
            vehicleid: expect.any(Object),
            ismobileappointment: expect.any(Object),
            advancepayment: expect.any(Object),
            jobid: expect.any(Object),
          }),
        );
      });
    });

    describe('getAutocareappointment', () => {
      it('should return NewAutocareappointment for default Autocareappointment initial value', () => {
        const formGroup = service.createAutocareappointmentFormGroup(sampleWithNewData);

        const autocareappointment = service.getAutocareappointment(formGroup) as any;

        expect(autocareappointment).toMatchObject(sampleWithNewData);
      });

      it('should return NewAutocareappointment for empty Autocareappointment initial value', () => {
        const formGroup = service.createAutocareappointmentFormGroup();

        const autocareappointment = service.getAutocareappointment(formGroup) as any;

        expect(autocareappointment).toMatchObject({});
      });

      it('should return IAutocareappointment', () => {
        const formGroup = service.createAutocareappointmentFormGroup(sampleWithRequiredData);

        const autocareappointment = service.getAutocareappointment(formGroup) as any;

        expect(autocareappointment).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAutocareappointment should not enable id FormControl', () => {
        const formGroup = service.createAutocareappointmentFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAutocareappointment should disable id FormControl', () => {
        const formGroup = service.createAutocareappointmentFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
