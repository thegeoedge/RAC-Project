import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ISalesorder, NewSalesorder } from '../salesorder.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISalesorder for edit and NewSalesorderFormGroupInput for create.
 */
type SalesorderFormGroupInput = ISalesorder | PartialWithRequiredKeyOf<NewSalesorder>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ISalesorder | NewSalesorder> = Omit<T, 'orderdate' | 'createddate' | 'lmd' | 'advancepaymentreturndate'> & {
  orderdate?: string | null;
  createddate?: string | null;
  lmd?: string | null;
  advancepaymentreturndate?: string | null;
};

type SalesorderFormRawValue = FormValueOf<ISalesorder>;

type NewSalesorderFormRawValue = FormValueOf<NewSalesorder>;

type SalesorderFormDefaults = Pick<
  NewSalesorder,
  'id' | 'orderdate' | 'createddate' | 'isinvoiced' | 'lmd' | 'isfixed' | 'isactive' | 'advancepaymentreturndate'
>;

type SalesorderFormGroupContent = {
  id: FormControl<SalesorderFormRawValue['id'] | NewSalesorder['id']>;
  code: FormControl<SalesorderFormRawValue['code']>;
  orderdate: FormControl<SalesorderFormRawValue['orderdate']>;
  createddate: FormControl<SalesorderFormRawValue['createddate']>;
  quoteid: FormControl<SalesorderFormRawValue['quoteid']>;
  salesrepid: FormControl<SalesorderFormRawValue['salesrepid']>;
  salesrepname: FormControl<SalesorderFormRawValue['salesrepname']>;
  delieverfrom: FormControl<SalesorderFormRawValue['delieverfrom']>;
  customerid: FormControl<SalesorderFormRawValue['customerid']>;
  customername: FormControl<SalesorderFormRawValue['customername']>;
  customeraddress: FormControl<SalesorderFormRawValue['customeraddress']>;
  deliveryaddress: FormControl<SalesorderFormRawValue['deliveryaddress']>;
  subtotal: FormControl<SalesorderFormRawValue['subtotal']>;
  totaltax: FormControl<SalesorderFormRawValue['totaltax']>;
  totaldiscount: FormControl<SalesorderFormRawValue['totaldiscount']>;
  nettotal: FormControl<SalesorderFormRawValue['nettotal']>;
  message: FormControl<SalesorderFormRawValue['message']>;
  isinvoiced: FormControl<SalesorderFormRawValue['isinvoiced']>;
  refinvoiceid: FormControl<SalesorderFormRawValue['refinvoiceid']>;
  lmu: FormControl<SalesorderFormRawValue['lmu']>;
  lmd: FormControl<SalesorderFormRawValue['lmd']>;
  isfixed: FormControl<SalesorderFormRawValue['isfixed']>;
  isactive: FormControl<SalesorderFormRawValue['isactive']>;
  advancepayment: FormControl<SalesorderFormRawValue['advancepayment']>;
  advancepaymentreturnamount: FormControl<SalesorderFormRawValue['advancepaymentreturnamount']>;
  advancepaymentreturndate: FormControl<SalesorderFormRawValue['advancepaymentreturndate']>;
  advancepaymentby: FormControl<SalesorderFormRawValue['advancepaymentby']>;
};

export type SalesorderFormGroup = FormGroup<SalesorderFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SalesorderFormService {
  createSalesorderFormGroup(salesorder: SalesorderFormGroupInput = { id: null }): SalesorderFormGroup {
    const salesorderRawValue = this.convertSalesorderToSalesorderRawValue({
      ...this.getFormDefaults(),
      ...salesorder,
    });
    return new FormGroup<SalesorderFormGroupContent>({
      id: new FormControl(
        { value: salesorderRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      code: new FormControl(salesorderRawValue.code),
      orderdate: new FormControl(salesorderRawValue.orderdate),
      createddate: new FormControl(salesorderRawValue.createddate),
      quoteid: new FormControl(salesorderRawValue.quoteid),
      salesrepid: new FormControl(salesorderRawValue.salesrepid),
      salesrepname: new FormControl(salesorderRawValue.salesrepname),
      delieverfrom: new FormControl(salesorderRawValue.delieverfrom),
      customerid: new FormControl(salesorderRawValue.customerid),
      customername: new FormControl(salesorderRawValue.customername),
      customeraddress: new FormControl(salesorderRawValue.customeraddress),
      deliveryaddress: new FormControl(salesorderRawValue.deliveryaddress),
      subtotal: new FormControl(salesorderRawValue.subtotal),
      totaltax: new FormControl(salesorderRawValue.totaltax),
      totaldiscount: new FormControl(salesorderRawValue.totaldiscount),
      nettotal: new FormControl(salesorderRawValue.nettotal),
      message: new FormControl(salesorderRawValue.message),
      isinvoiced: new FormControl(salesorderRawValue.isinvoiced),
      refinvoiceid: new FormControl(salesorderRawValue.refinvoiceid),
      lmu: new FormControl(salesorderRawValue.lmu),
      lmd: new FormControl(salesorderRawValue.lmd),
      isfixed: new FormControl(salesorderRawValue.isfixed),
      isactive: new FormControl(salesorderRawValue.isactive),
      advancepayment: new FormControl(salesorderRawValue.advancepayment),
      advancepaymentreturnamount: new FormControl(salesorderRawValue.advancepaymentreturnamount),
      advancepaymentreturndate: new FormControl(salesorderRawValue.advancepaymentreturndate),
      advancepaymentby: new FormControl(salesorderRawValue.advancepaymentby),
    });
  }

  getSalesorder(form: SalesorderFormGroup): ISalesorder | NewSalesorder {
    return this.convertSalesorderRawValueToSalesorder(form.getRawValue() as SalesorderFormRawValue | NewSalesorderFormRawValue);
  }

  resetForm(form: SalesorderFormGroup, salesorder: SalesorderFormGroupInput): void {
    const salesorderRawValue = this.convertSalesorderToSalesorderRawValue({ ...this.getFormDefaults(), ...salesorder });
    form.reset(
      {
        ...salesorderRawValue,
        id: { value: salesorderRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): SalesorderFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      orderdate: currentTime,
      createddate: currentTime,
      isinvoiced: false,
      lmd: currentTime,
      isfixed: false,
      isactive: false,
      advancepaymentreturndate: currentTime,
    };
  }

  private convertSalesorderRawValueToSalesorder(
    rawSalesorder: SalesorderFormRawValue | NewSalesorderFormRawValue,
  ): ISalesorder | NewSalesorder {
    return {
      ...rawSalesorder,
      orderdate: dayjs(rawSalesorder.orderdate, DATE_TIME_FORMAT),
      createddate: dayjs(rawSalesorder.createddate, DATE_TIME_FORMAT),
      lmd: dayjs(rawSalesorder.lmd, DATE_TIME_FORMAT),
      advancepaymentreturndate: dayjs(rawSalesorder.advancepaymentreturndate, DATE_TIME_FORMAT),
    };
  }

  private convertSalesorderToSalesorderRawValue(
    salesorder: ISalesorder | (Partial<NewSalesorder> & SalesorderFormDefaults),
  ): SalesorderFormRawValue | PartialWithRequiredKeyOf<NewSalesorderFormRawValue> {
    return {
      ...salesorder,
      orderdate: salesorder.orderdate ? salesorder.orderdate.format(DATE_TIME_FORMAT) : undefined,
      createddate: salesorder.createddate ? salesorder.createddate.format(DATE_TIME_FORMAT) : undefined,
      lmd: salesorder.lmd ? salesorder.lmd.format(DATE_TIME_FORMAT) : undefined,
      advancepaymentreturndate: salesorder.advancepaymentreturndate
        ? salesorder.advancepaymentreturndate.format(DATE_TIME_FORMAT)
        : undefined,
    };
  }
}
