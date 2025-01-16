import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IInventorybatches, NewInventorybatches } from '../inventorybatches.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IInventorybatches for edit and NewInventorybatchesFormGroupInput for create.
 */
type InventorybatchesFormGroupInput = IInventorybatches | PartialWithRequiredKeyOf<NewInventorybatches>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IInventorybatches | NewInventorybatches> = Omit<
  T,
  'txdate' | 'lmd' | 'manufacturedate' | 'expiredate' | 'addeddate'
> & {
  txdate?: string | null;
  lmd?: string | null;
  manufacturedate?: string | null;
  expiredate?: string | null;
  addeddate?: string | null;
};

type InventorybatchesFormRawValue = FormValueOf<IInventorybatches>;

type NewInventorybatchesFormRawValue = FormValueOf<NewInventorybatches>;

type InventorybatchesFormDefaults = Pick<NewInventorybatches, 'id' | 'txdate' | 'lmd' | 'manufacturedate' | 'expiredate' | 'addeddate'>;

type InventorybatchesFormGroupContent = {
  id: FormControl<InventorybatchesFormRawValue['id'] | NewInventorybatches['id']>;
  itemid: FormControl<InventorybatchesFormRawValue['itemid']>;
  code: FormControl<InventorybatchesFormRawValue['code']>;
  txdate: FormControl<InventorybatchesFormRawValue['txdate']>;
  cost: FormControl<InventorybatchesFormRawValue['cost']>;
  price: FormControl<InventorybatchesFormRawValue['price']>;
  costwithoutvat: FormControl<InventorybatchesFormRawValue['costwithoutvat']>;
  pricewithoutvat: FormControl<InventorybatchesFormRawValue['pricewithoutvat']>;
  notes: FormControl<InventorybatchesFormRawValue['notes']>;
  lmu: FormControl<InventorybatchesFormRawValue['lmu']>;
  lmd: FormControl<InventorybatchesFormRawValue['lmd']>;
  lineid: FormControl<InventorybatchesFormRawValue['lineid']>;
  manufacturedate: FormControl<InventorybatchesFormRawValue['manufacturedate']>;
  expiredate: FormControl<InventorybatchesFormRawValue['expiredate']>;
  quantity: FormControl<InventorybatchesFormRawValue['quantity']>;
  addeddate: FormControl<InventorybatchesFormRawValue['addeddate']>;
  costtotal: FormControl<InventorybatchesFormRawValue['costtotal']>;
  returnprice: FormControl<InventorybatchesFormRawValue['returnprice']>;
};

export type InventorybatchesFormGroup = FormGroup<InventorybatchesFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class InventorybatchesFormService {
  createInventorybatchesFormGroup(inventorybatches: InventorybatchesFormGroupInput = { id: null }): InventorybatchesFormGroup {
    const inventorybatchesRawValue = this.convertInventorybatchesToInventorybatchesRawValue({
      ...this.getFormDefaults(),
      ...inventorybatches,
    });
    return new FormGroup<InventorybatchesFormGroupContent>({
      id: new FormControl(
        { value: inventorybatchesRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      itemid: new FormControl(inventorybatchesRawValue.itemid),
      code: new FormControl(inventorybatchesRawValue.code),
      txdate: new FormControl(inventorybatchesRawValue.txdate),
      cost: new FormControl(inventorybatchesRawValue.cost),
      price: new FormControl(inventorybatchesRawValue.price),
      costwithoutvat: new FormControl(inventorybatchesRawValue.costwithoutvat),
      pricewithoutvat: new FormControl(inventorybatchesRawValue.pricewithoutvat),
      notes: new FormControl(inventorybatchesRawValue.notes),
      lmu: new FormControl(inventorybatchesRawValue.lmu),
      lmd: new FormControl(inventorybatchesRawValue.lmd),
      lineid: new FormControl(inventorybatchesRawValue.lineid),
      manufacturedate: new FormControl(inventorybatchesRawValue.manufacturedate),
      expiredate: new FormControl(inventorybatchesRawValue.expiredate),
      quantity: new FormControl(inventorybatchesRawValue.quantity),
      addeddate: new FormControl(inventorybatchesRawValue.addeddate),
      costtotal: new FormControl(inventorybatchesRawValue.costtotal),
      returnprice: new FormControl(inventorybatchesRawValue.returnprice),
    });
  }

  getInventorybatches(form: InventorybatchesFormGroup): IInventorybatches | NewInventorybatches {
    return this.convertInventorybatchesRawValueToInventorybatches(
      form.getRawValue() as InventorybatchesFormRawValue | NewInventorybatchesFormRawValue,
    );
  }

  resetForm(form: InventorybatchesFormGroup, inventorybatches: InventorybatchesFormGroupInput): void {
    const inventorybatchesRawValue = this.convertInventorybatchesToInventorybatchesRawValue({
      ...this.getFormDefaults(),
      ...inventorybatches,
    });
    form.reset(
      {
        ...inventorybatchesRawValue,
        id: { value: inventorybatchesRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): InventorybatchesFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      txdate: currentTime,
      lmd: currentTime,
      manufacturedate: currentTime,
      expiredate: currentTime,
      addeddate: currentTime,
    };
  }

  private convertInventorybatchesRawValueToInventorybatches(
    rawInventorybatches: InventorybatchesFormRawValue | NewInventorybatchesFormRawValue,
  ): IInventorybatches | NewInventorybatches {
    return {
      ...rawInventorybatches,
      txdate: dayjs(rawInventorybatches.txdate, DATE_TIME_FORMAT),
      lmd: dayjs(rawInventorybatches.lmd, DATE_TIME_FORMAT),
      manufacturedate: dayjs(rawInventorybatches.manufacturedate, DATE_TIME_FORMAT),
      expiredate: dayjs(rawInventorybatches.expiredate, DATE_TIME_FORMAT),
      addeddate: dayjs(rawInventorybatches.addeddate, DATE_TIME_FORMAT),
    };
  }

  private convertInventorybatchesToInventorybatchesRawValue(
    inventorybatches: IInventorybatches | (Partial<NewInventorybatches> & InventorybatchesFormDefaults),
  ): InventorybatchesFormRawValue | PartialWithRequiredKeyOf<NewInventorybatchesFormRawValue> {
    return {
      ...inventorybatches,
      txdate: inventorybatches.txdate ? inventorybatches.txdate.format(DATE_TIME_FORMAT) : undefined,
      lmd: inventorybatches.lmd ? inventorybatches.lmd.format(DATE_TIME_FORMAT) : undefined,
      manufacturedate: inventorybatches.manufacturedate ? inventorybatches.manufacturedate.format(DATE_TIME_FORMAT) : undefined,
      expiredate: inventorybatches.expiredate ? inventorybatches.expiredate.format(DATE_TIME_FORMAT) : undefined,
      addeddate: inventorybatches.addeddate ? inventorybatches.addeddate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
