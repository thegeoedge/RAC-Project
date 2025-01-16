import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../customervehicle.test-samples';

import { CustomervehicleFormService } from './customervehicle-form.service';

describe('Customervehicle Form Service', () => {
  let service: CustomervehicleFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomervehicleFormService);
  });

  describe('Service methods', () => {
    describe('createCustomervehicleFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCustomervehicleFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            customerid: expect.any(Object),
            vehiclenumber: expect.any(Object),
            categoryid: expect.any(Object),
            categoryname: expect.any(Object),
            typeid: expect.any(Object),
            typename: expect.any(Object),
            makeid: expect.any(Object),
            makename: expect.any(Object),
            model: expect.any(Object),
            yom: expect.any(Object),
            customercode: expect.any(Object),
            remarks: expect.any(Object),
            servicecount: expect.any(Object),
            engNo: expect.any(Object),
            chaNo: expect.any(Object),
            milage: expect.any(Object),
            lastservicedate: expect.any(Object),
            nextservicedate: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            nextgearoilmilage: expect.any(Object),
            nextmilage: expect.any(Object),
            serviceperiod: expect.any(Object),
          }),
        );
      });

      it('passing ICustomervehicle should create a new form with FormGroup', () => {
        const formGroup = service.createCustomervehicleFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            customerid: expect.any(Object),
            vehiclenumber: expect.any(Object),
            categoryid: expect.any(Object),
            categoryname: expect.any(Object),
            typeid: expect.any(Object),
            typename: expect.any(Object),
            makeid: expect.any(Object),
            makename: expect.any(Object),
            model: expect.any(Object),
            yom: expect.any(Object),
            customercode: expect.any(Object),
            remarks: expect.any(Object),
            servicecount: expect.any(Object),
            engNo: expect.any(Object),
            chaNo: expect.any(Object),
            milage: expect.any(Object),
            lastservicedate: expect.any(Object),
            nextservicedate: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            nextgearoilmilage: expect.any(Object),
            nextmilage: expect.any(Object),
            serviceperiod: expect.any(Object),
          }),
        );
      });
    });

    describe('getCustomervehicle', () => {
      it('should return NewCustomervehicle for default Customervehicle initial value', () => {
        const formGroup = service.createCustomervehicleFormGroup(sampleWithNewData);

        const customervehicle = service.getCustomervehicle(formGroup) as any;

        expect(customervehicle).toMatchObject(sampleWithNewData);
      });

      it('should return NewCustomervehicle for empty Customervehicle initial value', () => {
        const formGroup = service.createCustomervehicleFormGroup();

        const customervehicle = service.getCustomervehicle(formGroup) as any;

        expect(customervehicle).toMatchObject({});
      });

      it('should return ICustomervehicle', () => {
        const formGroup = service.createCustomervehicleFormGroup(sampleWithRequiredData);

        const customervehicle = service.getCustomervehicle(formGroup) as any;

        expect(customervehicle).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICustomervehicle should not enable id FormControl', () => {
        const formGroup = service.createCustomervehicleFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCustomervehicle should disable id FormControl', () => {
        const formGroup = service.createCustomervehicleFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
