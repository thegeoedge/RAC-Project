import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../taxes.test-samples';

import { TaxesFormService } from './taxes-form.service';

describe('Taxes Form Service', () => {
  let service: TaxesFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TaxesFormService);
  });

  describe('Service methods', () => {
    describe('createTaxesFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTaxesFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            description: expect.any(Object),
            effectivefrom: expect.any(Object),
            effectiveto: expect.any(Object),
            percentage: expect.any(Object),
            fixedamount: expect.any(Object),
            ismanual: expect.any(Object),
            isactive: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
          }),
        );
      });

      it('passing ITaxes should create a new form with FormGroup', () => {
        const formGroup = service.createTaxesFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            description: expect.any(Object),
            effectivefrom: expect.any(Object),
            effectiveto: expect.any(Object),
            percentage: expect.any(Object),
            fixedamount: expect.any(Object),
            ismanual: expect.any(Object),
            isactive: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
          }),
        );
      });
    });

    describe('getTaxes', () => {
      it('should return NewTaxes for default Taxes initial value', () => {
        const formGroup = service.createTaxesFormGroup(sampleWithNewData);

        const taxes = service.getTaxes(formGroup) as any;

        expect(taxes).toMatchObject(sampleWithNewData);
      });

      it('should return NewTaxes for empty Taxes initial value', () => {
        const formGroup = service.createTaxesFormGroup();

        const taxes = service.getTaxes(formGroup) as any;

        expect(taxes).toMatchObject({});
      });

      it('should return ITaxes', () => {
        const formGroup = service.createTaxesFormGroup(sampleWithRequiredData);

        const taxes = service.getTaxes(formGroup) as any;

        expect(taxes).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITaxes should not enable id FormControl', () => {
        const formGroup = service.createTaxesFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTaxes should disable id FormControl', () => {
        const formGroup = service.createTaxesFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
