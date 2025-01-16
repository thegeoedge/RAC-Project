import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAutojobsinvoice, NewAutojobsinvoice } from '../autojobsinvoice.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAutojobsinvoice for edit and NewAutojobsinvoiceFormGroupInput for create.
 */
type AutojobsinvoiceFormGroupInput = IAutojobsinvoice | PartialWithRequiredKeyOf<NewAutojobsinvoice>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IAutojobsinvoice | NewAutojobsinvoice> = Omit<T, 'invoicedate' | 'createddate' | 'delieverydate' | 'lmd'> & {
  invoicedate?: string | null;
  createddate?: string | null;
  delieverydate?: string | null;
  lmd?: string | null;
};

type AutojobsinvoiceFormRawValue = FormValueOf<IAutojobsinvoice>;

type NewAutojobsinvoiceFormRawValue = FormValueOf<NewAutojobsinvoice>;

type AutojobsinvoiceFormDefaults = Pick<NewAutojobsinvoice, 'id' | 'invoicedate' | 'createddate' | 'delieverydate' | 'lmd' | 'isactive'>;

type AutojobsinvoiceFormGroupContent = {
  id: FormControl<AutojobsinvoiceFormRawValue['id'] | NewAutojobsinvoice['id']>;
  code: FormControl<AutojobsinvoiceFormRawValue['code']>;
  invoicedate: FormControl<AutojobsinvoiceFormRawValue['invoicedate']>;
  createddate: FormControl<AutojobsinvoiceFormRawValue['createddate']>;
  jobid: FormControl<AutojobsinvoiceFormRawValue['jobid']>;
  quoteid: FormControl<AutojobsinvoiceFormRawValue['quoteid']>;
  orderid: FormControl<AutojobsinvoiceFormRawValue['orderid']>;
  delieverydate: FormControl<AutojobsinvoiceFormRawValue['delieverydate']>;
  autojobsrepid: FormControl<AutojobsinvoiceFormRawValue['autojobsrepid']>;
  autojobsrepname: FormControl<AutojobsinvoiceFormRawValue['autojobsrepname']>;
  delieverfrom: FormControl<AutojobsinvoiceFormRawValue['delieverfrom']>;
  customerid: FormControl<AutojobsinvoiceFormRawValue['customerid']>;
  customername: FormControl<AutojobsinvoiceFormRawValue['customername']>;
  customeraddress: FormControl<AutojobsinvoiceFormRawValue['customeraddress']>;
  deliveryaddress: FormControl<AutojobsinvoiceFormRawValue['deliveryaddress']>;
  subtotal: FormControl<AutojobsinvoiceFormRawValue['subtotal']>;
  totaltax: FormControl<AutojobsinvoiceFormRawValue['totaltax']>;
  totaldiscount: FormControl<AutojobsinvoiceFormRawValue['totaldiscount']>;
  nettotal: FormControl<AutojobsinvoiceFormRawValue['nettotal']>;
  message: FormControl<AutojobsinvoiceFormRawValue['message']>;
  lmu: FormControl<AutojobsinvoiceFormRawValue['lmu']>;
  lmd: FormControl<AutojobsinvoiceFormRawValue['lmd']>;
  paidamount: FormControl<AutojobsinvoiceFormRawValue['paidamount']>;
  amountowing: FormControl<AutojobsinvoiceFormRawValue['amountowing']>;
  isactive: FormControl<AutojobsinvoiceFormRawValue['isactive']>;
  locationid: FormControl<AutojobsinvoiceFormRawValue['locationid']>;
  locationcode: FormControl<AutojobsinvoiceFormRawValue['locationcode']>;
  referencecode: FormControl<AutojobsinvoiceFormRawValue['referencecode']>;
  createdbyid: FormControl<AutojobsinvoiceFormRawValue['createdbyid']>;
  createdbyname: FormControl<AutojobsinvoiceFormRawValue['createdbyname']>;
  autocarecompanyid: FormControl<AutojobsinvoiceFormRawValue['autocarecompanyid']>;
};

export type AutojobsinvoiceFormGroup = FormGroup<AutojobsinvoiceFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AutojobsinvoiceFormService {
  createAutojobsinvoiceFormGroup(autojobsinvoice: AutojobsinvoiceFormGroupInput = { id: null }): AutojobsinvoiceFormGroup {
    const autojobsinvoiceRawValue = this.convertAutojobsinvoiceToAutojobsinvoiceRawValue({
      ...this.getFormDefaults(),
      ...autojobsinvoice,
    });
    return new FormGroup<AutojobsinvoiceFormGroupContent>({
      id: new FormControl(
        { value: autojobsinvoiceRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      code: new FormControl(autojobsinvoiceRawValue.code),
      invoicedate: new FormControl(autojobsinvoiceRawValue.invoicedate),
      createddate: new FormControl(autojobsinvoiceRawValue.createddate),
      jobid: new FormControl(autojobsinvoiceRawValue.jobid),
      quoteid: new FormControl(autojobsinvoiceRawValue.quoteid),
      orderid: new FormControl(autojobsinvoiceRawValue.orderid),
      delieverydate: new FormControl(autojobsinvoiceRawValue.delieverydate),
      autojobsrepid: new FormControl(autojobsinvoiceRawValue.autojobsrepid),
      autojobsrepname: new FormControl(autojobsinvoiceRawValue.autojobsrepname),
      delieverfrom: new FormControl(autojobsinvoiceRawValue.delieverfrom),
      customerid: new FormControl(autojobsinvoiceRawValue.customerid),
      customername: new FormControl(autojobsinvoiceRawValue.customername),
      customeraddress: new FormControl(autojobsinvoiceRawValue.customeraddress),
      deliveryaddress: new FormControl(autojobsinvoiceRawValue.deliveryaddress),
      subtotal: new FormControl(autojobsinvoiceRawValue.subtotal),
      totaltax: new FormControl(autojobsinvoiceRawValue.totaltax),
      totaldiscount: new FormControl(autojobsinvoiceRawValue.totaldiscount),
      nettotal: new FormControl(autojobsinvoiceRawValue.nettotal),
      message: new FormControl(autojobsinvoiceRawValue.message),
      lmu: new FormControl(autojobsinvoiceRawValue.lmu),
      lmd: new FormControl(autojobsinvoiceRawValue.lmd),
      paidamount: new FormControl(autojobsinvoiceRawValue.paidamount),
      amountowing: new FormControl(autojobsinvoiceRawValue.amountowing),
      isactive: new FormControl(autojobsinvoiceRawValue.isactive),
      locationid: new FormControl(autojobsinvoiceRawValue.locationid),
      locationcode: new FormControl(autojobsinvoiceRawValue.locationcode),
      referencecode: new FormControl(autojobsinvoiceRawValue.referencecode),
      createdbyid: new FormControl(autojobsinvoiceRawValue.createdbyid),
      createdbyname: new FormControl(autojobsinvoiceRawValue.createdbyname),
      autocarecompanyid: new FormControl(autojobsinvoiceRawValue.autocarecompanyid),
    });
  }

  getAutojobsinvoice(form: AutojobsinvoiceFormGroup): IAutojobsinvoice | NewAutojobsinvoice {
    return this.convertAutojobsinvoiceRawValueToAutojobsinvoice(
      form.getRawValue() as AutojobsinvoiceFormRawValue | NewAutojobsinvoiceFormRawValue,
    );
  }

  resetForm(form: AutojobsinvoiceFormGroup, autojobsinvoice: AutojobsinvoiceFormGroupInput): void {
    const autojobsinvoiceRawValue = this.convertAutojobsinvoiceToAutojobsinvoiceRawValue({ ...this.getFormDefaults(), ...autojobsinvoice });
    form.reset(
      {
        ...autojobsinvoiceRawValue,
        id: { value: autojobsinvoiceRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AutojobsinvoiceFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      invoicedate: currentTime,
      createddate: currentTime,
      delieverydate: currentTime,
      lmd: currentTime,
      isactive: false,
    };
  }

  private convertAutojobsinvoiceRawValueToAutojobsinvoice(
    rawAutojobsinvoice: AutojobsinvoiceFormRawValue | NewAutojobsinvoiceFormRawValue,
  ): IAutojobsinvoice | NewAutojobsinvoice {
    return {
      ...rawAutojobsinvoice,
      invoicedate: dayjs(rawAutojobsinvoice.invoicedate, DATE_TIME_FORMAT),
      createddate: dayjs(rawAutojobsinvoice.createddate, DATE_TIME_FORMAT),
      delieverydate: dayjs(rawAutojobsinvoice.delieverydate, DATE_TIME_FORMAT),
      lmd: dayjs(rawAutojobsinvoice.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertAutojobsinvoiceToAutojobsinvoiceRawValue(
    autojobsinvoice: IAutojobsinvoice | (Partial<NewAutojobsinvoice> & AutojobsinvoiceFormDefaults),
  ): AutojobsinvoiceFormRawValue | PartialWithRequiredKeyOf<NewAutojobsinvoiceFormRawValue> {
    return {
      ...autojobsinvoice,
      invoicedate: autojobsinvoice.invoicedate ? autojobsinvoice.invoicedate.format(DATE_TIME_FORMAT) : undefined,
      createddate: autojobsinvoice.createddate ? autojobsinvoice.createddate.format(DATE_TIME_FORMAT) : undefined,
      delieverydate: autojobsinvoice.delieverydate ? autojobsinvoice.delieverydate.format(DATE_TIME_FORMAT) : undefined,
      lmd: autojobsinvoice.lmd ? autojobsinvoice.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
