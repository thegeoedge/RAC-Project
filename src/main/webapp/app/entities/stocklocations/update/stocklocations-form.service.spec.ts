import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../stocklocations.test-samples';

import { StocklocationsFormService } from './stocklocations-form.service';

describe('Stocklocations Form Service', () => {
  let service: StocklocationsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StocklocationsFormService);
  });

  describe('Service methods', () => {
    describe('createStocklocationsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createStocklocationsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            locationname: expect.any(Object),
            locationcode: expect.any(Object),
            description: expect.any(Object),
          }),
        );
      });

      it('passing IStocklocations should create a new form with FormGroup', () => {
        const formGroup = service.createStocklocationsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            locationname: expect.any(Object),
            locationcode: expect.any(Object),
            description: expect.any(Object),
          }),
        );
      });
    });

    describe('getStocklocations', () => {
      it('should return NewStocklocations for default Stocklocations initial value', () => {
        const formGroup = service.createStocklocationsFormGroup(sampleWithNewData);

        const stocklocations = service.getStocklocations(formGroup) as any;

        expect(stocklocations).toMatchObject(sampleWithNewData);
      });

      it('should return NewStocklocations for empty Stocklocations initial value', () => {
        const formGroup = service.createStocklocationsFormGroup();

        const stocklocations = service.getStocklocations(formGroup) as any;

        expect(stocklocations).toMatchObject({});
      });

      it('should return IStocklocations', () => {
        const formGroup = service.createStocklocationsFormGroup(sampleWithRequiredData);

        const stocklocations = service.getStocklocations(formGroup) as any;

        expect(stocklocations).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IStocklocations should not enable id FormControl', () => {
        const formGroup = service.createStocklocationsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewStocklocations should disable id FormControl', () => {
        const formGroup = service.createStocklocationsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
