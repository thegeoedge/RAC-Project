import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IInventory, NewInventory } from '../inventory.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IInventory for edit and NewInventoryFormGroupInput for create.
 */
type InventoryFormGroupInput = IInventory | PartialWithRequiredKeyOf<NewInventory>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IInventory | NewInventory> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type InventoryFormRawValue = FormValueOf<IInventory>;

type NewInventoryFormRawValue = FormValueOf<NewInventory>;

type InventoryFormDefaults = Pick<
  NewInventory,
  'id' | 'isassemblyunit' | 'lmd' | 'hasbatches' | 'activeitem' | 'isregistered' | 'notininvoice'
>;

type InventoryFormGroupContent = {
  id: FormControl<InventoryFormRawValue['id'] | NewInventory['id']>;
  code: FormControl<InventoryFormRawValue['code']>;
  partnumber: FormControl<InventoryFormRawValue['partnumber']>;
  name: FormControl<InventoryFormRawValue['name']>;
  description: FormControl<InventoryFormRawValue['description']>;
  type: FormControl<InventoryFormRawValue['type']>;
  classification1: FormControl<InventoryFormRawValue['classification1']>;
  classification2: FormControl<InventoryFormRawValue['classification2']>;
  classification3: FormControl<InventoryFormRawValue['classification3']>;
  classification4: FormControl<InventoryFormRawValue['classification4']>;
  classification5: FormControl<InventoryFormRawValue['classification5']>;
  unitofmeasurement: FormControl<InventoryFormRawValue['unitofmeasurement']>;
  decimalplaces: FormControl<InventoryFormRawValue['decimalplaces']>;
  isassemblyunit: FormControl<InventoryFormRawValue['isassemblyunit']>;
  assemblyunitof: FormControl<InventoryFormRawValue['assemblyunitof']>;
  reorderlevel: FormControl<InventoryFormRawValue['reorderlevel']>;
  lastcost: FormControl<InventoryFormRawValue['lastcost']>;
  lastsellingprice: FormControl<InventoryFormRawValue['lastsellingprice']>;
  lmu: FormControl<InventoryFormRawValue['lmu']>;
  lmd: FormControl<InventoryFormRawValue['lmd']>;
  availablequantity: FormControl<InventoryFormRawValue['availablequantity']>;
  hasbatches: FormControl<InventoryFormRawValue['hasbatches']>;
  itemspecfilepath: FormControl<InventoryFormRawValue['itemspecfilepath']>;
  itemimagepath: FormControl<InventoryFormRawValue['itemimagepath']>;
  returnprice: FormControl<InventoryFormRawValue['returnprice']>;
  activeitem: FormControl<InventoryFormRawValue['activeitem']>;
  minstock: FormControl<InventoryFormRawValue['minstock']>;
  maxstock: FormControl<InventoryFormRawValue['maxstock']>;
  dailyaverage: FormControl<InventoryFormRawValue['dailyaverage']>;
  bufferlevel: FormControl<InventoryFormRawValue['bufferlevel']>;
  leadtime: FormControl<InventoryFormRawValue['leadtime']>;
  buffertime: FormControl<InventoryFormRawValue['buffertime']>;
  saftydays: FormControl<InventoryFormRawValue['saftydays']>;
  accountcode: FormControl<InventoryFormRawValue['accountcode']>;
  accountid: FormControl<InventoryFormRawValue['accountid']>;
  casepackqty: FormControl<InventoryFormRawValue['casepackqty']>;
  isregistered: FormControl<InventoryFormRawValue['isregistered']>;
  defaultstocklocationid: FormControl<InventoryFormRawValue['defaultstocklocationid']>;
  rackno: FormControl<InventoryFormRawValue['rackno']>;
  barcodeimage: FormControl<InventoryFormRawValue['barcodeimage']>;
  barcodeimageContentType: FormControl<InventoryFormRawValue['barcodeimageContentType']>;
  commissionempid: FormControl<InventoryFormRawValue['commissionempid']>;
  checktypeid: FormControl<InventoryFormRawValue['checktypeid']>;
  checktype: FormControl<InventoryFormRawValue['checktype']>;
  reorderqty: FormControl<InventoryFormRawValue['reorderqty']>;
  notininvoice: FormControl<InventoryFormRawValue['notininvoice']>;
};

export type InventoryFormGroup = FormGroup<InventoryFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class InventoryFormService {
  createInventoryFormGroup(inventory: InventoryFormGroupInput = { id: null }): InventoryFormGroup {
    const inventoryRawValue = this.convertInventoryToInventoryRawValue({
      ...this.getFormDefaults(),
      ...inventory,
    });
    return new FormGroup<InventoryFormGroupContent>({
      id: new FormControl(
        { value: inventoryRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      code: new FormControl(inventoryRawValue.code),
      partnumber: new FormControl(inventoryRawValue.partnumber),
      name: new FormControl(inventoryRawValue.name),
      description: new FormControl(inventoryRawValue.description),
      type: new FormControl(inventoryRawValue.type),
      classification1: new FormControl(inventoryRawValue.classification1),
      classification2: new FormControl(inventoryRawValue.classification2),
      classification3: new FormControl(inventoryRawValue.classification3),
      classification4: new FormControl(inventoryRawValue.classification4),
      classification5: new FormControl(inventoryRawValue.classification5),
      unitofmeasurement: new FormControl(inventoryRawValue.unitofmeasurement),
      decimalplaces: new FormControl(inventoryRawValue.decimalplaces),
      isassemblyunit: new FormControl(inventoryRawValue.isassemblyunit),
      assemblyunitof: new FormControl(inventoryRawValue.assemblyunitof),
      reorderlevel: new FormControl(inventoryRawValue.reorderlevel),
      lastcost: new FormControl(inventoryRawValue.lastcost),
      lastsellingprice: new FormControl(inventoryRawValue.lastsellingprice),
      lmu: new FormControl(inventoryRawValue.lmu),
      lmd: new FormControl(inventoryRawValue.lmd),
      availablequantity: new FormControl(inventoryRawValue.availablequantity),
      hasbatches: new FormControl(inventoryRawValue.hasbatches),
      itemspecfilepath: new FormControl(inventoryRawValue.itemspecfilepath),
      itemimagepath: new FormControl(inventoryRawValue.itemimagepath),
      returnprice: new FormControl(inventoryRawValue.returnprice),
      activeitem: new FormControl(inventoryRawValue.activeitem),
      minstock: new FormControl(inventoryRawValue.minstock),
      maxstock: new FormControl(inventoryRawValue.maxstock),
      dailyaverage: new FormControl(inventoryRawValue.dailyaverage),
      bufferlevel: new FormControl(inventoryRawValue.bufferlevel),
      leadtime: new FormControl(inventoryRawValue.leadtime),
      buffertime: new FormControl(inventoryRawValue.buffertime),
      saftydays: new FormControl(inventoryRawValue.saftydays),
      accountcode: new FormControl(inventoryRawValue.accountcode),
      accountid: new FormControl(inventoryRawValue.accountid),
      casepackqty: new FormControl(inventoryRawValue.casepackqty),
      isregistered: new FormControl(inventoryRawValue.isregistered),
      defaultstocklocationid: new FormControl(inventoryRawValue.defaultstocklocationid),
      rackno: new FormControl(inventoryRawValue.rackno),
      barcodeimage: new FormControl(inventoryRawValue.barcodeimage),
      barcodeimageContentType: new FormControl(inventoryRawValue.barcodeimageContentType),
      commissionempid: new FormControl(inventoryRawValue.commissionempid),
      checktypeid: new FormControl(inventoryRawValue.checktypeid),
      checktype: new FormControl(inventoryRawValue.checktype),
      reorderqty: new FormControl(inventoryRawValue.reorderqty),
      notininvoice: new FormControl(inventoryRawValue.notininvoice),
    });
  }

  getInventory(form: InventoryFormGroup): IInventory | NewInventory {
    return this.convertInventoryRawValueToInventory(form.getRawValue() as InventoryFormRawValue | NewInventoryFormRawValue);
  }

  resetForm(form: InventoryFormGroup, inventory: InventoryFormGroupInput): void {
    const inventoryRawValue = this.convertInventoryToInventoryRawValue({ ...this.getFormDefaults(), ...inventory });
    form.reset(
      {
        ...inventoryRawValue,
        id: { value: inventoryRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): InventoryFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      isassemblyunit: false,
      lmd: currentTime,
      hasbatches: false,
      activeitem: false,
      isregistered: false,
      notininvoice: false,
    };
  }

  private convertInventoryRawValueToInventory(rawInventory: InventoryFormRawValue | NewInventoryFormRawValue): IInventory | NewInventory {
    return {
      ...rawInventory,
      lmd: dayjs(rawInventory.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertInventoryToInventoryRawValue(
    inventory: IInventory | (Partial<NewInventory> & InventoryFormDefaults),
  ): InventoryFormRawValue | PartialWithRequiredKeyOf<NewInventoryFormRawValue> {
    return {
      ...inventory,
      lmd: inventory.lmd ? inventory.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
