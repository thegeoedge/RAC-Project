import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../workshopvehiclework.test-samples';

import { WorkshopvehicleworkFormService } from './workshopvehiclework-form.service';

describe('Workshopvehiclework Form Service', () => {
  let service: WorkshopvehicleworkFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkshopvehicleworkFormService);
  });

  describe('Service methods', () => {
    describe('createWorkshopvehicleworkFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createWorkshopvehicleworkFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            jobid: expect.any(Object),
            vehicleid: expect.any(Object),
            customerid: expect.any(Object),
            customername: expect.any(Object),
            contactno: expect.any(Object),
            vehicleno: expect.any(Object),
            vehiclebrand: expect.any(Object),
            vehiclemodel: expect.any(Object),
            mileage: expect.any(Object),
            addeddate: expect.any(Object),
            iscalltocustomer: expect.any(Object),
            remarks: expect.any(Object),
            calldate: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
          }),
        );
      });

      it('passing IWorkshopvehiclework should create a new form with FormGroup', () => {
        const formGroup = service.createWorkshopvehicleworkFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            jobid: expect.any(Object),
            vehicleid: expect.any(Object),
            customerid: expect.any(Object),
            customername: expect.any(Object),
            contactno: expect.any(Object),
            vehicleno: expect.any(Object),
            vehiclebrand: expect.any(Object),
            vehiclemodel: expect.any(Object),
            mileage: expect.any(Object),
            addeddate: expect.any(Object),
            iscalltocustomer: expect.any(Object),
            remarks: expect.any(Object),
            calldate: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
          }),
        );
      });
    });

    describe('getWorkshopvehiclework', () => {
      it('should return NewWorkshopvehiclework for default Workshopvehiclework initial value', () => {
        const formGroup = service.createWorkshopvehicleworkFormGroup(sampleWithNewData);

        const workshopvehiclework = service.getWorkshopvehiclework(formGroup) as any;

        expect(workshopvehiclework).toMatchObject(sampleWithNewData);
      });

      it('should return NewWorkshopvehiclework for empty Workshopvehiclework initial value', () => {
        const formGroup = service.createWorkshopvehicleworkFormGroup();

        const workshopvehiclework = service.getWorkshopvehiclework(formGroup) as any;

        expect(workshopvehiclework).toMatchObject({});
      });

      it('should return IWorkshopvehiclework', () => {
        const formGroup = service.createWorkshopvehicleworkFormGroup(sampleWithRequiredData);

        const workshopvehiclework = service.getWorkshopvehiclework(formGroup) as any;

        expect(workshopvehiclework).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IWorkshopvehiclework should not enable id FormControl', () => {
        const formGroup = service.createWorkshopvehicleworkFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewWorkshopvehiclework should disable id FormControl', () => {
        const formGroup = service.createWorkshopvehicleworkFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
