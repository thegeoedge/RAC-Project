import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../autocaretimetable.test-samples';

import { AutocaretimetableFormService } from './autocaretimetable-form.service';

describe('Autocaretimetable Form Service', () => {
  let service: AutocaretimetableFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutocaretimetableFormService);
  });

  describe('Service methods', () => {
    describe('createAutocaretimetableFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAutocaretimetableFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            hoisttypeid: expect.any(Object),
            hoisttypename: expect.any(Object),
            hoisttime: expect.any(Object),
          }),
        );
      });

      it('passing IAutocaretimetable should create a new form with FormGroup', () => {
        const formGroup = service.createAutocaretimetableFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            hoisttypeid: expect.any(Object),
            hoisttypename: expect.any(Object),
            hoisttime: expect.any(Object),
          }),
        );
      });
    });

    describe('getAutocaretimetable', () => {
      it('should return NewAutocaretimetable for default Autocaretimetable initial value', () => {
        const formGroup = service.createAutocaretimetableFormGroup(sampleWithNewData);

        const autocaretimetable = service.getAutocaretimetable(formGroup) as any;

        expect(autocaretimetable).toMatchObject(sampleWithNewData);
      });

      it('should return NewAutocaretimetable for empty Autocaretimetable initial value', () => {
        const formGroup = service.createAutocaretimetableFormGroup();

        const autocaretimetable = service.getAutocaretimetable(formGroup) as any;

        expect(autocaretimetable).toMatchObject({});
      });

      it('should return IAutocaretimetable', () => {
        const formGroup = service.createAutocaretimetableFormGroup(sampleWithRequiredData);

        const autocaretimetable = service.getAutocaretimetable(formGroup) as any;

        expect(autocaretimetable).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAutocaretimetable should not enable id FormControl', () => {
        const formGroup = service.createAutocaretimetableFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAutocaretimetable should disable id FormControl', () => {
        const formGroup = service.createAutocaretimetableFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
