import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../vehiclebrandmodel.test-samples';

import { VehiclebrandmodelFormService } from './vehiclebrandmodel-form.service';

describe('Vehiclebrandmodel Form Service', () => {
  let service: VehiclebrandmodelFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VehiclebrandmodelFormService);
  });

  describe('Service methods', () => {
    describe('createVehiclebrandmodelFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createVehiclebrandmodelFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            brandid: expect.any(Object),
            brandname: expect.any(Object),
            model: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
          }),
        );
      });

      it('passing IVehiclebrandmodel should create a new form with FormGroup', () => {
        const formGroup = service.createVehiclebrandmodelFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            brandid: expect.any(Object),
            brandname: expect.any(Object),
            model: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
          }),
        );
      });
    });

    describe('getVehiclebrandmodel', () => {
      it('should return NewVehiclebrandmodel for default Vehiclebrandmodel initial value', () => {
        const formGroup = service.createVehiclebrandmodelFormGroup(sampleWithNewData);

        const vehiclebrandmodel = service.getVehiclebrandmodel(formGroup) as any;

        expect(vehiclebrandmodel).toMatchObject(sampleWithNewData);
      });

      it('should return NewVehiclebrandmodel for empty Vehiclebrandmodel initial value', () => {
        const formGroup = service.createVehiclebrandmodelFormGroup();

        const vehiclebrandmodel = service.getVehiclebrandmodel(formGroup) as any;

        expect(vehiclebrandmodel).toMatchObject({});
      });

      it('should return IVehiclebrandmodel', () => {
        const formGroup = service.createVehiclebrandmodelFormGroup(sampleWithRequiredData);

        const vehiclebrandmodel = service.getVehiclebrandmodel(formGroup) as any;

        expect(vehiclebrandmodel).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IVehiclebrandmodel should not enable id FormControl', () => {
        const formGroup = service.createVehiclebrandmodelFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewVehiclebrandmodel should disable id FormControl', () => {
        const formGroup = service.createVehiclebrandmodelFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
