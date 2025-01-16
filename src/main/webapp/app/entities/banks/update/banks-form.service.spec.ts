import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../banks.test-samples';

import { BanksFormService } from './banks-form.service';

describe('Banks Form Service', () => {
  let service: BanksFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BanksFormService);
  });

  describe('Service methods', () => {
    describe('createBanksFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createBanksFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
          }),
        );
      });

      it('passing IBanks should create a new form with FormGroup', () => {
        const formGroup = service.createBanksFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
          }),
        );
      });
    });

    describe('getBanks', () => {
      it('should return NewBanks for default Banks initial value', () => {
        const formGroup = service.createBanksFormGroup(sampleWithNewData);

        const banks = service.getBanks(formGroup) as any;

        expect(banks).toMatchObject(sampleWithNewData);
      });

      it('should return NewBanks for empty Banks initial value', () => {
        const formGroup = service.createBanksFormGroup();

        const banks = service.getBanks(formGroup) as any;

        expect(banks).toMatchObject({});
      });

      it('should return IBanks', () => {
        const formGroup = service.createBanksFormGroup(sampleWithRequiredData);

        const banks = service.getBanks(formGroup) as any;

        expect(banks).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IBanks should not enable id FormControl', () => {
        const formGroup = service.createBanksFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewBanks should disable id FormControl', () => {
        const formGroup = service.createBanksFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
