import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../vehiclebrandname.test-samples';

import { VehiclebrandnameFormService } from './vehiclebrandname-form.service';

describe('Vehiclebrandname Form Service', () => {
  let service: VehiclebrandnameFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VehiclebrandnameFormService);
  });

  describe('Service methods', () => {
    describe('createVehiclebrandnameFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createVehiclebrandnameFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            brandname: expect.any(Object),
            description: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            companyid: expect.any(Object),
          }),
        );
      });

      it('passing IVehiclebrandname should create a new form with FormGroup', () => {
        const formGroup = service.createVehiclebrandnameFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            brandname: expect.any(Object),
            description: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            companyid: expect.any(Object),
          }),
        );
      });
    });

    describe('getVehiclebrandname', () => {
      it('should return NewVehiclebrandname for default Vehiclebrandname initial value', () => {
        const formGroup = service.createVehiclebrandnameFormGroup(sampleWithNewData);

        const vehiclebrandname = service.getVehiclebrandname(formGroup) as any;

        expect(vehiclebrandname).toMatchObject(sampleWithNewData);
      });

      it('should return NewVehiclebrandname for empty Vehiclebrandname initial value', () => {
        const formGroup = service.createVehiclebrandnameFormGroup();

        const vehiclebrandname = service.getVehiclebrandname(formGroup) as any;

        expect(vehiclebrandname).toMatchObject({});
      });

      it('should return IVehiclebrandname', () => {
        const formGroup = service.createVehiclebrandnameFormGroup(sampleWithRequiredData);

        const vehiclebrandname = service.getVehiclebrandname(formGroup) as any;

        expect(vehiclebrandname).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IVehiclebrandname should not enable id FormControl', () => {
        const formGroup = service.createVehiclebrandnameFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewVehiclebrandname should disable id FormControl', () => {
        const formGroup = service.createVehiclebrandnameFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
