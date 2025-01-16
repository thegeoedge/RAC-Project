import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../autocarecompany.test-samples';

import { AutocarecompanyFormService } from './autocarecompany-form.service';

describe('Autocarecompany Form Service', () => {
  let service: AutocarecompanyFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutocarecompanyFormService);
  });

  describe('Service methods', () => {
    describe('createAutocarecompanyFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAutocarecompanyFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            address: expect.any(Object),
            servicephone: expect.any(Object),
            sparepartphone: expect.any(Object),
            bodypaint: expect.any(Object),
            generalphone: expect.any(Object),
            fax: expect.any(Object),
            email: expect.any(Object),
            description: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            vatregnumber: expect.any(Object),
            tinnumber: expect.any(Object),
            accountcode: expect.any(Object),
            accountid: expect.any(Object),
          }),
        );
      });

      it('passing IAutocarecompany should create a new form with FormGroup', () => {
        const formGroup = service.createAutocarecompanyFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            address: expect.any(Object),
            servicephone: expect.any(Object),
            sparepartphone: expect.any(Object),
            bodypaint: expect.any(Object),
            generalphone: expect.any(Object),
            fax: expect.any(Object),
            email: expect.any(Object),
            description: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            vatregnumber: expect.any(Object),
            tinnumber: expect.any(Object),
            accountcode: expect.any(Object),
            accountid: expect.any(Object),
          }),
        );
      });
    });

    describe('getAutocarecompany', () => {
      it('should return NewAutocarecompany for default Autocarecompany initial value', () => {
        const formGroup = service.createAutocarecompanyFormGroup(sampleWithNewData);

        const autocarecompany = service.getAutocarecompany(formGroup) as any;

        expect(autocarecompany).toMatchObject(sampleWithNewData);
      });

      it('should return NewAutocarecompany for empty Autocarecompany initial value', () => {
        const formGroup = service.createAutocarecompanyFormGroup();

        const autocarecompany = service.getAutocarecompany(formGroup) as any;

        expect(autocarecompany).toMatchObject({});
      });

      it('should return IAutocarecompany', () => {
        const formGroup = service.createAutocarecompanyFormGroup(sampleWithRequiredData);

        const autocarecompany = service.getAutocarecompany(formGroup) as any;

        expect(autocarecompany).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAutocarecompany should not enable id FormControl', () => {
        const formGroup = service.createAutocarecompanyFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAutocarecompany should disable id FormControl', () => {
        const formGroup = service.createAutocarecompanyFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
