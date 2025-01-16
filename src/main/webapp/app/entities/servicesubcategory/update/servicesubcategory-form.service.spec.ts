import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../servicesubcategory.test-samples';

import { ServicesubcategoryFormService } from './servicesubcategory-form.service';

describe('Servicesubcategory Form Service', () => {
  let service: ServicesubcategoryFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicesubcategoryFormService);
  });

  describe('Service methods', () => {
    describe('createServicesubcategoryFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createServicesubcategoryFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            mainid: expect.any(Object),
            mainname: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            isactive: expect.any(Object),
          }),
        );
      });

      it('passing IServicesubcategory should create a new form with FormGroup', () => {
        const formGroup = service.createServicesubcategoryFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            mainid: expect.any(Object),
            mainname: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            isactive: expect.any(Object),
          }),
        );
      });
    });

    describe('getServicesubcategory', () => {
      it('should return NewServicesubcategory for default Servicesubcategory initial value', () => {
        const formGroup = service.createServicesubcategoryFormGroup(sampleWithNewData);

        const servicesubcategory = service.getServicesubcategory(formGroup) as any;

        expect(servicesubcategory).toMatchObject(sampleWithNewData);
      });

      it('should return NewServicesubcategory for empty Servicesubcategory initial value', () => {
        const formGroup = service.createServicesubcategoryFormGroup();

        const servicesubcategory = service.getServicesubcategory(formGroup) as any;

        expect(servicesubcategory).toMatchObject({});
      });

      it('should return IServicesubcategory', () => {
        const formGroup = service.createServicesubcategoryFormGroup(sampleWithRequiredData);

        const servicesubcategory = service.getServicesubcategory(formGroup) as any;

        expect(servicesubcategory).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IServicesubcategory should not enable id FormControl', () => {
        const formGroup = service.createServicesubcategoryFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewServicesubcategory should disable id FormControl', () => {
        const formGroup = service.createServicesubcategoryFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
