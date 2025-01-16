import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../servicecategory.test-samples';

import { ServicecategoryFormService } from './servicecategory-form.service';

describe('Servicecategory Form Service', () => {
  let service: ServicecategoryFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicecategoryFormService);
  });

  describe('Service methods', () => {
    describe('createServicecategoryFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createServicecategoryFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            showsecurity: expect.any(Object),
            sortorder: expect.any(Object),
            isactive: expect.any(Object),
          }),
        );
      });

      it('passing IServicecategory should create a new form with FormGroup', () => {
        const formGroup = service.createServicecategoryFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            showsecurity: expect.any(Object),
            sortorder: expect.any(Object),
            isactive: expect.any(Object),
          }),
        );
      });
    });

    describe('getServicecategory', () => {
      it('should return NewServicecategory for default Servicecategory initial value', () => {
        const formGroup = service.createServicecategoryFormGroup(sampleWithNewData);

        const servicecategory = service.getServicecategory(formGroup) as any;

        expect(servicecategory).toMatchObject(sampleWithNewData);
      });

      it('should return NewServicecategory for empty Servicecategory initial value', () => {
        const formGroup = service.createServicecategoryFormGroup();

        const servicecategory = service.getServicecategory(formGroup) as any;

        expect(servicecategory).toMatchObject({});
      });

      it('should return IServicecategory', () => {
        const formGroup = service.createServicecategoryFormGroup(sampleWithRequiredData);

        const servicecategory = service.getServicecategory(formGroup) as any;

        expect(servicecategory).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IServicecategory should not enable id FormControl', () => {
        const formGroup = service.createServicecategoryFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewServicecategory should disable id FormControl', () => {
        const formGroup = service.createServicecategoryFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
