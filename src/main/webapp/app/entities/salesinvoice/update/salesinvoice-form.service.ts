import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ISalesinvoice, NewSalesinvoice } from '../salesinvoice.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISalesinvoice for edit and NewSalesinvoiceFormGroupInput for create.
 */
type SalesinvoiceFormGroupInput = ISalesinvoice | PartialWithRequiredKeyOf<NewSalesinvoice>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ISalesinvoice | NewSalesinvoice> = Omit<
  T,
  'invoicedate' | 'createddate' | 'delieverydate' | 'lmd' | 'invcanceldate'
> & {
  invoicedate?: string | null;
  createddate?: string | null;
  delieverydate?: string | null;
  lmd?: string | null;
  invcanceldate?: string | null;
};

type SalesinvoiceFormRawValue = FormValueOf<ISalesinvoice>;

type NewSalesinvoiceFormRawValue = FormValueOf<NewSalesinvoice>;

type SalesinvoiceFormDefaults = Pick<
  NewSalesinvoice,
  | 'id'
  | 'invoicedate'
  | 'createddate'
  | 'delieverydate'
  | 'lmd'
  | 'isactive'
  | 'hasdummybill'
  | 'isserviceinvoice'
  | 'iscompanyinvoice'
  | 'invcanceldate'
  | 'isvatinvoice'
>;

type SalesinvoiceFormGroupContent = {
  id: FormControl<SalesinvoiceFormRawValue['id'] | NewSalesinvoice['id']>;
  code: FormControl<SalesinvoiceFormRawValue['code']>;
  invoicedate: FormControl<SalesinvoiceFormRawValue['invoicedate']>;
  createddate: FormControl<SalesinvoiceFormRawValue['createddate']>;
  quoteid: FormControl<SalesinvoiceFormRawValue['quoteid']>;
  orderid: FormControl<SalesinvoiceFormRawValue['orderid']>;
  delieverydate: FormControl<SalesinvoiceFormRawValue['delieverydate']>;
  salesrepid: FormControl<SalesinvoiceFormRawValue['salesrepid']>;
  salesrepname: FormControl<SalesinvoiceFormRawValue['salesrepname']>;
  delieverfrom: FormControl<SalesinvoiceFormRawValue['delieverfrom']>;
  customerid: FormControl<SalesinvoiceFormRawValue['customerid']>;
  customername: FormControl<SalesinvoiceFormRawValue['customername']>;
  customeraddress: FormControl<SalesinvoiceFormRawValue['customeraddress']>;
  deliveryaddress: FormControl<SalesinvoiceFormRawValue['deliveryaddress']>;
  subtotal: FormControl<SalesinvoiceFormRawValue['subtotal']>;
  totaltax: FormControl<SalesinvoiceFormRawValue['totaltax']>;
  totaldiscount: FormControl<SalesinvoiceFormRawValue['totaldiscount']>;
  nettotal: FormControl<SalesinvoiceFormRawValue['nettotal']>;
  message: FormControl<SalesinvoiceFormRawValue['message']>;
  lmu: FormControl<SalesinvoiceFormRawValue['lmu']>;
  lmd: FormControl<SalesinvoiceFormRawValue['lmd']>;
  paidamount: FormControl<SalesinvoiceFormRawValue['paidamount']>;
  amountowing: FormControl<SalesinvoiceFormRawValue['amountowing']>;
  isactive: FormControl<SalesinvoiceFormRawValue['isactive']>;
  locationid: FormControl<SalesinvoiceFormRawValue['locationid']>;
  locationcode: FormControl<SalesinvoiceFormRawValue['locationcode']>;
  referencecode: FormControl<SalesinvoiceFormRawValue['referencecode']>;
  createdbyid: FormControl<SalesinvoiceFormRawValue['createdbyid']>;
  createdbyname: FormControl<SalesinvoiceFormRawValue['createdbyname']>;
  autocarecharges: FormControl<SalesinvoiceFormRawValue['autocarecharges']>;
  autocarejobid: FormControl<SalesinvoiceFormRawValue['autocarejobid']>;
  vehicleno: FormControl<SalesinvoiceFormRawValue['vehicleno']>;
  nextmeter: FormControl<SalesinvoiceFormRawValue['nextmeter']>;
  currentmeter: FormControl<SalesinvoiceFormRawValue['currentmeter']>;
  remarks: FormControl<SalesinvoiceFormRawValue['remarks']>;
  hasdummybill: FormControl<SalesinvoiceFormRawValue['hasdummybill']>;
  dummybillid: FormControl<SalesinvoiceFormRawValue['dummybillid']>;
  dummybillamount: FormControl<SalesinvoiceFormRawValue['dummybillamount']>;
  dummycommision: FormControl<SalesinvoiceFormRawValue['dummycommision']>;
  isserviceinvoice: FormControl<SalesinvoiceFormRawValue['isserviceinvoice']>;
  nbtamount: FormControl<SalesinvoiceFormRawValue['nbtamount']>;
  vatamount: FormControl<SalesinvoiceFormRawValue['vatamount']>;
  autocarecompanyid: FormControl<SalesinvoiceFormRawValue['autocarecompanyid']>;
  iscompanyinvoice: FormControl<SalesinvoiceFormRawValue['iscompanyinvoice']>;
  invcanceldate: FormControl<SalesinvoiceFormRawValue['invcanceldate']>;
  invcancelby: FormControl<SalesinvoiceFormRawValue['invcancelby']>;
  isvatinvoice: FormControl<SalesinvoiceFormRawValue['isvatinvoice']>;
  paymenttype: FormControl<SalesinvoiceFormRawValue['paymenttype']>;
  pendingamount: FormControl<SalesinvoiceFormRawValue['pendingamount']>;
  advancepayment: FormControl<SalesinvoiceFormRawValue['advancepayment']>;
  discountcode: FormControl<SalesinvoiceFormRawValue['discountcode']>;
};

export type SalesinvoiceFormGroup = FormGroup<SalesinvoiceFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SalesinvoiceFormService {
  createSalesinvoiceFormGroup(salesinvoice: SalesinvoiceFormGroupInput = { id: null }): SalesinvoiceFormGroup {
    const salesinvoiceRawValue = this.convertSalesinvoiceToSalesinvoiceRawValue({
      ...this.getFormDefaults(),
      ...salesinvoice,
    });
    return new FormGroup<SalesinvoiceFormGroupContent>({
      id: new FormControl(
        { value: salesinvoiceRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      code: new FormControl(salesinvoiceRawValue.code),
      invoicedate: new FormControl(salesinvoiceRawValue.invoicedate),
      createddate: new FormControl(salesinvoiceRawValue.createddate),
      quoteid: new FormControl(salesinvoiceRawValue.quoteid),
      orderid: new FormControl(salesinvoiceRawValue.orderid),
      delieverydate: new FormControl(salesinvoiceRawValue.delieverydate),
      salesrepid: new FormControl(salesinvoiceRawValue.salesrepid),
      salesrepname: new FormControl(salesinvoiceRawValue.salesrepname),
      delieverfrom: new FormControl(salesinvoiceRawValue.delieverfrom),
      customerid: new FormControl(salesinvoiceRawValue.customerid),
      customername: new FormControl(salesinvoiceRawValue.customername),
      customeraddress: new FormControl(salesinvoiceRawValue.customeraddress),
      deliveryaddress: new FormControl(salesinvoiceRawValue.deliveryaddress),
      subtotal: new FormControl(salesinvoiceRawValue.subtotal),
      totaltax: new FormControl(salesinvoiceRawValue.totaltax),
      totaldiscount: new FormControl(salesinvoiceRawValue.totaldiscount),
      nettotal: new FormControl(salesinvoiceRawValue.nettotal),
      message: new FormControl(salesinvoiceRawValue.message),
      lmu: new FormControl(salesinvoiceRawValue.lmu),
      lmd: new FormControl(salesinvoiceRawValue.lmd),
      paidamount: new FormControl(salesinvoiceRawValue.paidamount),
      amountowing: new FormControl(salesinvoiceRawValue.amountowing),
      isactive: new FormControl(salesinvoiceRawValue.isactive),
      locationid: new FormControl(salesinvoiceRawValue.locationid),
      locationcode: new FormControl(salesinvoiceRawValue.locationcode),
      referencecode: new FormControl(salesinvoiceRawValue.referencecode),
      createdbyid: new FormControl(salesinvoiceRawValue.createdbyid),
      createdbyname: new FormControl(salesinvoiceRawValue.createdbyname),
      autocarecharges: new FormControl(salesinvoiceRawValue.autocarecharges),
      autocarejobid: new FormControl(salesinvoiceRawValue.autocarejobid),
      vehicleno: new FormControl(salesinvoiceRawValue.vehicleno),
      nextmeter: new FormControl(salesinvoiceRawValue.nextmeter),
      currentmeter: new FormControl(salesinvoiceRawValue.currentmeter),
      remarks: new FormControl(salesinvoiceRawValue.remarks),
      hasdummybill: new FormControl(salesinvoiceRawValue.hasdummybill),
      dummybillid: new FormControl(salesinvoiceRawValue.dummybillid),
      dummybillamount: new FormControl(salesinvoiceRawValue.dummybillamount),
      dummycommision: new FormControl(salesinvoiceRawValue.dummycommision),
      isserviceinvoice: new FormControl(salesinvoiceRawValue.isserviceinvoice),
      nbtamount: new FormControl(salesinvoiceRawValue.nbtamount),
      vatamount: new FormControl(salesinvoiceRawValue.vatamount),
      autocarecompanyid: new FormControl(salesinvoiceRawValue.autocarecompanyid),
      iscompanyinvoice: new FormControl(salesinvoiceRawValue.iscompanyinvoice),
      invcanceldate: new FormControl(salesinvoiceRawValue.invcanceldate),
      invcancelby: new FormControl(salesinvoiceRawValue.invcancelby),
      isvatinvoice: new FormControl(salesinvoiceRawValue.isvatinvoice),
      paymenttype: new FormControl(salesinvoiceRawValue.paymenttype),
      pendingamount: new FormControl(salesinvoiceRawValue.pendingamount),
      advancepayment: new FormControl(salesinvoiceRawValue.advancepayment),
      discountcode: new FormControl(salesinvoiceRawValue.discountcode),
    });
  }

  getSalesinvoice(form: SalesinvoiceFormGroup): ISalesinvoice | NewSalesinvoice {
    return this.convertSalesinvoiceRawValueToSalesinvoice(form.getRawValue() as SalesinvoiceFormRawValue | NewSalesinvoiceFormRawValue);
  }

  resetForm(form: SalesinvoiceFormGroup, salesinvoice: SalesinvoiceFormGroupInput): void {
    const salesinvoiceRawValue = this.convertSalesinvoiceToSalesinvoiceRawValue({ ...this.getFormDefaults(), ...salesinvoice });
    form.reset(
      {
        ...salesinvoiceRawValue,
        id: { value: salesinvoiceRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): SalesinvoiceFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      invoicedate: currentTime,
      createddate: currentTime,
      delieverydate: currentTime,
      lmd: currentTime,
      isactive: false,
      hasdummybill: false,
      isserviceinvoice: false,
      iscompanyinvoice: false,
      invcanceldate: currentTime,
      isvatinvoice: false,
    };
  }

  private convertSalesinvoiceRawValueToSalesinvoice(
    rawSalesinvoice: SalesinvoiceFormRawValue | NewSalesinvoiceFormRawValue,
  ): ISalesinvoice | NewSalesinvoice {
    return {
      ...rawSalesinvoice,
      invoicedate: dayjs(rawSalesinvoice.invoicedate, DATE_TIME_FORMAT),
      createddate: dayjs(rawSalesinvoice.createddate, DATE_TIME_FORMAT),
      delieverydate: dayjs(rawSalesinvoice.delieverydate, DATE_TIME_FORMAT),
      lmd: dayjs(rawSalesinvoice.lmd, DATE_TIME_FORMAT),
      invcanceldate: dayjs(rawSalesinvoice.invcanceldate, DATE_TIME_FORMAT),
    };
  }

  private convertSalesinvoiceToSalesinvoiceRawValue(
    salesinvoice: ISalesinvoice | (Partial<NewSalesinvoice> & SalesinvoiceFormDefaults),
  ): SalesinvoiceFormRawValue | PartialWithRequiredKeyOf<NewSalesinvoiceFormRawValue> {
    return {
      ...salesinvoice,
      invoicedate: salesinvoice.invoicedate ? salesinvoice.invoicedate.format(DATE_TIME_FORMAT) : undefined,
      createddate: salesinvoice.createddate ? salesinvoice.createddate.format(DATE_TIME_FORMAT) : undefined,
      delieverydate: salesinvoice.delieverydate ? salesinvoice.delieverydate.format(DATE_TIME_FORMAT) : undefined,
      lmd: salesinvoice.lmd ? salesinvoice.lmd.format(DATE_TIME_FORMAT) : undefined,
      invcanceldate: salesinvoice.invcanceldate ? salesinvoice.invcanceldate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
