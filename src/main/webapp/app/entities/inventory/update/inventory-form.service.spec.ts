import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../inventory.test-samples';

import { InventoryFormService } from './inventory-form.service';

describe('Inventory Form Service', () => {
  let service: InventoryFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InventoryFormService);
  });

  describe('Service methods', () => {
    describe('createInventoryFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createInventoryFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            partnumber: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            type: expect.any(Object),
            classification1: expect.any(Object),
            classification2: expect.any(Object),
            classification3: expect.any(Object),
            classification4: expect.any(Object),
            classification5: expect.any(Object),
            unitofmeasurement: expect.any(Object),
            decimalplaces: expect.any(Object),
            isassemblyunit: expect.any(Object),
            assemblyunitof: expect.any(Object),
            reorderlevel: expect.any(Object),
            lastcost: expect.any(Object),
            lastsellingprice: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            availablequantity: expect.any(Object),
            hasbatches: expect.any(Object),
            itemspecfilepath: expect.any(Object),
            itemimagepath: expect.any(Object),
            returnprice: expect.any(Object),
            activeitem: expect.any(Object),
            minstock: expect.any(Object),
            maxstock: expect.any(Object),
            dailyaverage: expect.any(Object),
            bufferlevel: expect.any(Object),
            leadtime: expect.any(Object),
            buffertime: expect.any(Object),
            saftydays: expect.any(Object),
            accountcode: expect.any(Object),
            accountid: expect.any(Object),
            casepackqty: expect.any(Object),
            isregistered: expect.any(Object),
            defaultstocklocationid: expect.any(Object),
            rackno: expect.any(Object),
            barcodeimage: expect.any(Object),
            commissionempid: expect.any(Object),
            checktypeid: expect.any(Object),
            checktype: expect.any(Object),
            reorderqty: expect.any(Object),
            notininvoice: expect.any(Object),
          }),
        );
      });

      it('passing IInventory should create a new form with FormGroup', () => {
        const formGroup = service.createInventoryFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            partnumber: expect.any(Object),
            name: expect.any(Object),
            description: expect.any(Object),
            type: expect.any(Object),
            classification1: expect.any(Object),
            classification2: expect.any(Object),
            classification3: expect.any(Object),
            classification4: expect.any(Object),
            classification5: expect.any(Object),
            unitofmeasurement: expect.any(Object),
            decimalplaces: expect.any(Object),
            isassemblyunit: expect.any(Object),
            assemblyunitof: expect.any(Object),
            reorderlevel: expect.any(Object),
            lastcost: expect.any(Object),
            lastsellingprice: expect.any(Object),
            lmu: expect.any(Object),
            lmd: expect.any(Object),
            availablequantity: expect.any(Object),
            hasbatches: expect.any(Object),
            itemspecfilepath: expect.any(Object),
            itemimagepath: expect.any(Object),
            returnprice: expect.any(Object),
            activeitem: expect.any(Object),
            minstock: expect.any(Object),
            maxstock: expect.any(Object),
            dailyaverage: expect.any(Object),
            bufferlevel: expect.any(Object),
            leadtime: expect.any(Object),
            buffertime: expect.any(Object),
            saftydays: expect.any(Object),
            accountcode: expect.any(Object),
            accountid: expect.any(Object),
            casepackqty: expect.any(Object),
            isregistered: expect.any(Object),
            defaultstocklocationid: expect.any(Object),
            rackno: expect.any(Object),
            barcodeimage: expect.any(Object),
            commissionempid: expect.any(Object),
            checktypeid: expect.any(Object),
            checktype: expect.any(Object),
            reorderqty: expect.any(Object),
            notininvoice: expect.any(Object),
          }),
        );
      });
    });

    describe('getInventory', () => {
      it('should return NewInventory for default Inventory initial value', () => {
        const formGroup = service.createInventoryFormGroup(sampleWithNewData);

        const inventory = service.getInventory(formGroup) as any;

        expect(inventory).toMatchObject(sampleWithNewData);
      });

      it('should return NewInventory for empty Inventory initial value', () => {
        const formGroup = service.createInventoryFormGroup();

        const inventory = service.getInventory(formGroup) as any;

        expect(inventory).toMatchObject({});
      });

      it('should return IInventory', () => {
        const formGroup = service.createInventoryFormGroup(sampleWithRequiredData);

        const inventory = service.getInventory(formGroup) as any;

        expect(inventory).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IInventory should not enable id FormControl', () => {
        const formGroup = service.createInventoryFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewInventory should disable id FormControl', () => {
        const formGroup = service.createInventoryFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
