import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../vehiclecategory.test-samples';

import { VehiclecategoryFormService } from './vehiclecategory-form.service';

describe('Vehiclecategory Form Service', () => {
  let service: VehiclecategoryFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VehiclecategoryFormService);
  });

  describe('Service methods', () => {
    describe('createVehiclecategoryFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createVehiclecategoryFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            vehiclecategory: expect.any(Object),
            vehiclecategorydiscription: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
          }),
        );
      });

      it('passing IVehiclecategory should create a new form with FormGroup', () => {
        const formGroup = service.createVehiclecategoryFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            vehiclecategory: expect.any(Object),
            vehiclecategorydiscription: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
          }),
        );
      });
    });

    describe('getVehiclecategory', () => {
      it('should return NewVehiclecategory for default Vehiclecategory initial value', () => {
        const formGroup = service.createVehiclecategoryFormGroup(sampleWithNewData);

        const vehiclecategory = service.getVehiclecategory(formGroup) as any;

        expect(vehiclecategory).toMatchObject(sampleWithNewData);
      });

      it('should return NewVehiclecategory for empty Vehiclecategory initial value', () => {
        const formGroup = service.createVehiclecategoryFormGroup();

        const vehiclecategory = service.getVehiclecategory(formGroup) as any;

        expect(vehiclecategory).toMatchObject({});
      });

      it('should return IVehiclecategory', () => {
        const formGroup = service.createVehiclecategoryFormGroup(sampleWithRequiredData);

        const vehiclecategory = service.getVehiclecategory(formGroup) as any;

        expect(vehiclecategory).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IVehiclecategory should not enable id FormControl', () => {
        const formGroup = service.createVehiclecategoryFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewVehiclecategory should disable id FormControl', () => {
        const formGroup = service.createVehiclecategoryFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
