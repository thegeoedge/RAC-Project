import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../locationbasedstock.test-samples';

import { LocationbasedstockFormService } from './locationbasedstock-form.service';

describe('Locationbasedstock Form Service', () => {
  let service: LocationbasedstockFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LocationbasedstockFormService);
  });

  describe('Service methods', () => {
    describe('createLocationbasedstockFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createLocationbasedstockFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            itemid: expect.any(Object),
            code: expect.any(Object),
            name: expect.any(Object),
            locationid: expect.any(Object),
            locationcode: expect.any(Object),
            availablequantity: expect.any(Object),
            hasbatches: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
          }),
        );
      });

      it('passing ILocationbasedstock should create a new form with FormGroup', () => {
        const formGroup = service.createLocationbasedstockFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            itemid: expect.any(Object),
            code: expect.any(Object),
            name: expect.any(Object),
            locationid: expect.any(Object),
            locationcode: expect.any(Object),
            availablequantity: expect.any(Object),
            hasbatches: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
          }),
        );
      });
    });

    describe('getLocationbasedstock', () => {
      it('should return NewLocationbasedstock for default Locationbasedstock initial value', () => {
        const formGroup = service.createLocationbasedstockFormGroup(sampleWithNewData);

        const locationbasedstock = service.getLocationbasedstock(formGroup) as any;

        expect(locationbasedstock).toMatchObject(sampleWithNewData);
      });

      it('should return NewLocationbasedstock for empty Locationbasedstock initial value', () => {
        const formGroup = service.createLocationbasedstockFormGroup();

        const locationbasedstock = service.getLocationbasedstock(formGroup) as any;

        expect(locationbasedstock).toMatchObject({});
      });

      it('should return ILocationbasedstock', () => {
        const formGroup = service.createLocationbasedstockFormGroup(sampleWithRequiredData);

        const locationbasedstock = service.getLocationbasedstock(formGroup) as any;

        expect(locationbasedstock).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ILocationbasedstock should not enable id FormControl', () => {
        const formGroup = service.createLocationbasedstockFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewLocationbasedstock should disable id FormControl', () => {
        const formGroup = service.createLocationbasedstockFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
