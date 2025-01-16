import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../inventorybatches.test-samples';

import { InventorybatchesFormService } from './inventorybatches-form.service';

describe('Inventorybatches Form Service', () => {
  let service: InventorybatchesFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InventorybatchesFormService);
  });

  describe('Service methods', () => {
    describe('createInventorybatchesFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createInventorybatchesFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            itemid: expect.any(Object),
            code: expect.any(Object),
            txdate: expect.any(Object),
            cost: expect.any(Object),
            price: expect.any(Object),
            costwithoutvat: expect.any(Object),
            pricewithoutvat: expect.any(Object),
            notes: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            lineid: expect.any(Object),
            manufacturedate: expect.any(Object),
            expiredate: expect.any(Object),
            quantity: expect.any(Object),
            addeddate: expect.any(Object),
            costtotal: expect.any(Object),
            returnprice: expect.any(Object),
          }),
        );
      });

      it('passing IInventorybatches should create a new form with FormGroup', () => {
        const formGroup = service.createInventorybatchesFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            itemid: expect.any(Object),
            code: expect.any(Object),
            txdate: expect.any(Object),
            cost: expect.any(Object),
            price: expect.any(Object),
            costwithoutvat: expect.any(Object),
            pricewithoutvat: expect.any(Object),
            notes: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            lineid: expect.any(Object),
            manufacturedate: expect.any(Object),
            expiredate: expect.any(Object),
            quantity: expect.any(Object),
            addeddate: expect.any(Object),
            costtotal: expect.any(Object),
            returnprice: expect.any(Object),
          }),
        );
      });
    });

    describe('getInventorybatches', () => {
      it('should return NewInventorybatches for default Inventorybatches initial value', () => {
        const formGroup = service.createInventorybatchesFormGroup(sampleWithNewData);

        const inventorybatches = service.getInventorybatches(formGroup) as any;

        expect(inventorybatches).toMatchObject(sampleWithNewData);
      });

      it('should return NewInventorybatches for empty Inventorybatches initial value', () => {
        const formGroup = service.createInventorybatchesFormGroup();

        const inventorybatches = service.getInventorybatches(formGroup) as any;

        expect(inventorybatches).toMatchObject({});
      });

      it('should return IInventorybatches', () => {
        const formGroup = service.createInventorybatchesFormGroup(sampleWithRequiredData);

        const inventorybatches = service.getInventorybatches(formGroup) as any;

        expect(inventorybatches).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IInventorybatches should not enable id FormControl', () => {
        const formGroup = service.createInventorybatchesFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewInventorybatches should disable id FormControl', () => {
        const formGroup = service.createInventorybatchesFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
