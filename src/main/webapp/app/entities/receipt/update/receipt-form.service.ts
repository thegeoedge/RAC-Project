import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IReceipt, NewReceipt } from '../receipt.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IReceipt for edit and NewReceiptFormGroupInput for create.
 */
type ReceiptFormGroupInput = IReceipt | PartialWithRequiredKeyOf<NewReceipt>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IReceipt | NewReceipt> = Omit<T, 'receiptdate' | 'lmd' | 'date' | 'checkdate'> & {
  receiptdate?: string | null;
  lmd?: string | null;
  date?: string | null;
  checkdate?: string | null;
};

type ReceiptFormRawValue = FormValueOf<IReceipt>;

type NewReceiptFormRawValue = FormValueOf<NewReceipt>;

type ReceiptFormDefaults = Pick<NewReceipt, 'id' | 'receiptdate' | 'lmd' | 'date' | 'checkdate' | 'isactive' | 'deposited'>;

type ReceiptFormGroupContent = {
  id: FormControl<ReceiptFormRawValue['id'] | NewReceipt['id']>;
  code: FormControl<ReceiptFormRawValue['code']>;
  receiptdate: FormControl<ReceiptFormRawValue['receiptdate']>;
  customername: FormControl<ReceiptFormRawValue['customername']>;
  customeraddress: FormControl<ReceiptFormRawValue['customeraddress']>;
  totalamount: FormControl<ReceiptFormRawValue['totalamount']>;
  totalamountinword: FormControl<ReceiptFormRawValue['totalamountinword']>;
  comments: FormControl<ReceiptFormRawValue['comments']>;
  lmu: FormControl<ReceiptFormRawValue['lmu']>;
  lmd: FormControl<ReceiptFormRawValue['lmd']>;
  termid: FormControl<ReceiptFormRawValue['termid']>;
  term: FormControl<ReceiptFormRawValue['term']>;
  date: FormControl<ReceiptFormRawValue['date']>;
  amount: FormControl<ReceiptFormRawValue['amount']>;
  checkdate: FormControl<ReceiptFormRawValue['checkdate']>;
  checkno: FormControl<ReceiptFormRawValue['checkno']>;
  bank: FormControl<ReceiptFormRawValue['bank']>;
  customerid: FormControl<ReceiptFormRawValue['customerid']>;
  isactive: FormControl<ReceiptFormRawValue['isactive']>;
  deposited: FormControl<ReceiptFormRawValue['deposited']>;
  createdby: FormControl<ReceiptFormRawValue['createdby']>;
};

export type ReceiptFormGroup = FormGroup<ReceiptFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ReceiptFormService {
  createReceiptFormGroup(receipt: ReceiptFormGroupInput = { id: null }): ReceiptFormGroup {
    const receiptRawValue = this.convertReceiptToReceiptRawValue({
      ...this.getFormDefaults(),
      ...receipt,
    });
    return new FormGroup<ReceiptFormGroupContent>({
      id: new FormControl(
        { value: receiptRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      code: new FormControl(receiptRawValue.code),
      receiptdate: new FormControl(receiptRawValue.receiptdate),
      customername: new FormControl(receiptRawValue.customername),
      customeraddress: new FormControl(receiptRawValue.customeraddress),
      totalamount: new FormControl(receiptRawValue.totalamount),
      totalamountinword: new FormControl(receiptRawValue.totalamountinword),
      comments: new FormControl(receiptRawValue.comments),
      lmu: new FormControl(receiptRawValue.lmu),
      lmd: new FormControl(receiptRawValue.lmd),
      termid: new FormControl(receiptRawValue.termid),
      term: new FormControl(receiptRawValue.term),
      date: new FormControl(receiptRawValue.date),
      amount: new FormControl(receiptRawValue.amount),
      checkdate: new FormControl(receiptRawValue.checkdate),
      checkno: new FormControl(receiptRawValue.checkno),
      bank: new FormControl(receiptRawValue.bank),
      customerid: new FormControl(receiptRawValue.customerid),
      isactive: new FormControl(receiptRawValue.isactive),
      deposited: new FormControl(receiptRawValue.deposited),
      createdby: new FormControl(receiptRawValue.createdby),
    });
  }

  getReceipt(form: ReceiptFormGroup): IReceipt | NewReceipt {
    return this.convertReceiptRawValueToReceipt(form.getRawValue() as ReceiptFormRawValue | NewReceiptFormRawValue);
  }

  resetForm(form: ReceiptFormGroup, receipt: ReceiptFormGroupInput): void {
    const receiptRawValue = this.convertReceiptToReceiptRawValue({ ...this.getFormDefaults(), ...receipt });
    form.reset(
      {
        ...receiptRawValue,
        id: { value: receiptRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ReceiptFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      receiptdate: currentTime,
      lmd: currentTime,
      date: currentTime,
      checkdate: currentTime,
      isactive: false,
      deposited: false,
    };
  }

  private convertReceiptRawValueToReceipt(rawReceipt: ReceiptFormRawValue | NewReceiptFormRawValue): IReceipt | NewReceipt {
    return {
      ...rawReceipt,
      receiptdate: dayjs(rawReceipt.receiptdate, DATE_TIME_FORMAT),
      lmd: dayjs(rawReceipt.lmd, DATE_TIME_FORMAT),
      date: dayjs(rawReceipt.date, DATE_TIME_FORMAT),
      checkdate: dayjs(rawReceipt.checkdate, DATE_TIME_FORMAT),
    };
  }

  private convertReceiptToReceiptRawValue(
    receipt: IReceipt | (Partial<NewReceipt> & ReceiptFormDefaults),
  ): ReceiptFormRawValue | PartialWithRequiredKeyOf<NewReceiptFormRawValue> {
    return {
      ...receipt,
      receiptdate: receipt.receiptdate ? receipt.receiptdate.format(DATE_TIME_FORMAT) : undefined,
      lmd: receipt.lmd ? receipt.lmd.format(DATE_TIME_FORMAT) : undefined,
      date: receipt.date ? receipt.date.format(DATE_TIME_FORMAT) : undefined,
      checkdate: receipt.checkdate ? receipt.checkdate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
