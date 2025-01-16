import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../vehicletype.test-samples';

import { VehicletypeFormService } from './vehicletype-form.service';

describe('Vehicletype Form Service', () => {
  let service: VehicletypeFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VehicletypeFormService);
  });

  describe('Service methods', () => {
    describe('createVehicletypeFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createVehicletypeFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            vehicletype: expect.any(Object),
            vehicletypediscription: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            catid: expect.any(Object),
            catname: expect.any(Object),
          }),
        );
      });

      it('passing IVehicletype should create a new form with FormGroup', () => {
        const formGroup = service.createVehicletypeFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            vehicletype: expect.any(Object),
            vehicletypediscription: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            catid: expect.any(Object),
            catname: expect.any(Object),
          }),
        );
      });
    });

    describe('getVehicletype', () => {
      it('should return NewVehicletype for default Vehicletype initial value', () => {
        const formGroup = service.createVehicletypeFormGroup(sampleWithNewData);

        const vehicletype = service.getVehicletype(formGroup) as any;

        expect(vehicletype).toMatchObject(sampleWithNewData);
      });

      it('should return NewVehicletype for empty Vehicletype initial value', () => {
        const formGroup = service.createVehicletypeFormGroup();

        const vehicletype = service.getVehicletype(formGroup) as any;

        expect(vehicletype).toMatchObject({});
      });

      it('should return IVehicletype', () => {
        const formGroup = service.createVehicletypeFormGroup(sampleWithRequiredData);

        const vehicletype = service.getVehicletype(formGroup) as any;

        expect(vehicletype).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IVehicletype should not enable id FormControl', () => {
        const formGroup = service.createVehicletypeFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewVehicletype should disable id FormControl', () => {
        const formGroup = service.createVehicletypeFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
