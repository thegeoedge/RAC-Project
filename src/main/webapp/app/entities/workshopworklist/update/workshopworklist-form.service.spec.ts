import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../workshopworklist.test-samples';

import { WorkshopworklistFormService } from './workshopworklist-form.service';

describe('Workshopworklist Form Service', () => {
  let service: WorkshopworklistFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkshopworklistFormService);
  });

  describe('Service methods', () => {
    describe('createWorkshopworklistFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createWorkshopworklistFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            workshopwork: expect.any(Object),
            workshopworkdescription: expect.any(Object),
            isactive: expect.any(Object),
            lmd: expect.any(Object),
            lmu: expect.any(Object),
          }),
        );
      });

      it('passing IWorkshopworklist should create a new form with FormGroup', () => {
        const formGroup = service.createWorkshopworklistFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            workshopwork: expect.any(Object),
            workshopworkdescription: expect.any(Object),
            isactive: expect.any(Object),
            lmd: expect.any(Object),
            lmu: expect.any(Object),
          }),
        );
      });
    });

    describe('getWorkshopworklist', () => {
      it('should return NewWorkshopworklist for default Workshopworklist initial value', () => {
        const formGroup = service.createWorkshopworklistFormGroup(sampleWithNewData);

        const workshopworklist = service.getWorkshopworklist(formGroup) as any;

        expect(workshopworklist).toMatchObject(sampleWithNewData);
      });

      it('should return NewWorkshopworklist for empty Workshopworklist initial value', () => {
        const formGroup = service.createWorkshopworklistFormGroup();

        const workshopworklist = service.getWorkshopworklist(formGroup) as any;

        expect(workshopworklist).toMatchObject({});
      });

      it('should return IWorkshopworklist', () => {
        const formGroup = service.createWorkshopworklistFormGroup(sampleWithRequiredData);

        const workshopworklist = service.getWorkshopworklist(formGroup) as any;

        expect(workshopworklist).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IWorkshopworklist should not enable id FormControl', () => {
        const formGroup = service.createWorkshopworklistFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewWorkshopworklist should disable id FormControl', () => {
        const formGroup = service.createWorkshopworklistFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
