import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ICustomer, NewCustomer } from '../customer.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICustomer for edit and NewCustomerFormGroupInput for create.
 */
type CustomerFormGroupInput = ICustomer | PartialWithRequiredKeyOf<NewCustomer>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ICustomer | NewCustomer> = Omit<T, 'registrationdate' | 'lmd'> & {
  registrationdate?: string | null;
  lmd?: string | null;
};

type CustomerFormRawValue = FormValueOf<ICustomer>;

type NewCustomerFormRawValue = FormValueOf<NewCustomer>;

type CustomerFormDefaults = Pick<
  NewCustomer,
  | 'id'
  | 'registrationdate'
  | 'lmd'
  | 'hassecuritydeposit'
  | 'paybycash'
  | 'cashpaymentbeforeload'
  | 'cashlastinvoicebeforeload'
  | 'paybycredit'
  | 'creditoneweekcheck'
  | 'haspurchasingdeposit'
  | 'hassecuritydepositbond'
  | 'hasassestsdeposit'
  | 'hascreaditlimit'
  | 'isregistered'
>;

type CustomerFormGroupContent = {
  id: FormControl<CustomerFormRawValue['id'] | NewCustomer['id']>;
  customertype: FormControl<CustomerFormRawValue['customertype']>;
  code: FormControl<CustomerFormRawValue['code']>;
  title: FormControl<CustomerFormRawValue['title']>;
  namewithinitials: FormControl<CustomerFormRawValue['namewithinitials']>;
  fullname: FormControl<CustomerFormRawValue['fullname']>;
  callingname: FormControl<CustomerFormRawValue['callingname']>;
  nicno: FormControl<CustomerFormRawValue['nicno']>;
  nicissueddate: FormControl<CustomerFormRawValue['nicissueddate']>;
  dateofbirth: FormControl<CustomerFormRawValue['dateofbirth']>;
  bloodgroup: FormControl<CustomerFormRawValue['bloodgroup']>;
  gender: FormControl<CustomerFormRawValue['gender']>;
  maritalstatus: FormControl<CustomerFormRawValue['maritalstatus']>;
  marrieddate: FormControl<CustomerFormRawValue['marrieddate']>;
  nationality: FormControl<CustomerFormRawValue['nationality']>;
  territory: FormControl<CustomerFormRawValue['territory']>;
  religion: FormControl<CustomerFormRawValue['religion']>;
  team: FormControl<CustomerFormRawValue['team']>;
  businessname: FormControl<CustomerFormRawValue['businessname']>;
  businessregdate: FormControl<CustomerFormRawValue['businessregdate']>;
  businessregno: FormControl<CustomerFormRawValue['businessregno']>;
  profilepicturepath: FormControl<CustomerFormRawValue['profilepicturepath']>;
  residencehouseno: FormControl<CustomerFormRawValue['residencehouseno']>;
  residenceaddress: FormControl<CustomerFormRawValue['residenceaddress']>;
  residencecity: FormControl<CustomerFormRawValue['residencecity']>;
  residencephone: FormControl<CustomerFormRawValue['residencephone']>;
  businesslocationno: FormControl<CustomerFormRawValue['businesslocationno']>;
  businessaddress: FormControl<CustomerFormRawValue['businessaddress']>;
  businesscity: FormControl<CustomerFormRawValue['businesscity']>;
  businessphone1: FormControl<CustomerFormRawValue['businessphone1']>;
  businessphone2: FormControl<CustomerFormRawValue['businessphone2']>;
  businessmobile: FormControl<CustomerFormRawValue['businessmobile']>;
  businessfax: FormControl<CustomerFormRawValue['businessfax']>;
  businessemail: FormControl<CustomerFormRawValue['businessemail']>;
  businessprovinceid: FormControl<CustomerFormRawValue['businessprovinceid']>;
  businessdistrictid: FormControl<CustomerFormRawValue['businessdistrictid']>;
  contactpersonname: FormControl<CustomerFormRawValue['contactpersonname']>;
  contactpersonphone: FormControl<CustomerFormRawValue['contactpersonphone']>;
  contactpersonmobile: FormControl<CustomerFormRawValue['contactpersonmobile']>;
  contactpersonemail: FormControl<CustomerFormRawValue['contactpersonemail']>;
  rootmappath: FormControl<CustomerFormRawValue['rootmappath']>;
  website: FormControl<CustomerFormRawValue['website']>;
  registrationdate: FormControl<CustomerFormRawValue['registrationdate']>;
  isactive: FormControl<CustomerFormRawValue['isactive']>;
  isinternalcustomer: FormControl<CustomerFormRawValue['isinternalcustomer']>;
  description: FormControl<CustomerFormRawValue['description']>;
  lmu: FormControl<CustomerFormRawValue['lmu']>;
  lmd: FormControl<CustomerFormRawValue['lmd']>;
  maximumdiscount: FormControl<CustomerFormRawValue['maximumdiscount']>;
  creditlimit: FormControl<CustomerFormRawValue['creditlimit']>;
  hassecuritydeposit: FormControl<CustomerFormRawValue['hassecuritydeposit']>;
  securitydepositamount: FormControl<CustomerFormRawValue['securitydepositamount']>;
  paybycash: FormControl<CustomerFormRawValue['paybycash']>;
  cashpaymentbeforeload: FormControl<CustomerFormRawValue['cashpaymentbeforeload']>;
  cashlastinvoicebeforeload: FormControl<CustomerFormRawValue['cashlastinvoicebeforeload']>;
  paybycredit: FormControl<CustomerFormRawValue['paybycredit']>;
  creditoneweekcheck: FormControl<CustomerFormRawValue['creditoneweekcheck']>;
  creditpaymentbydays: FormControl<CustomerFormRawValue['creditpaymentbydays']>;
  haspurchasingdeposit: FormControl<CustomerFormRawValue['haspurchasingdeposit']>;
  hassecuritydepositbond: FormControl<CustomerFormRawValue['hassecuritydepositbond']>;
  hasassestsdeposit: FormControl<CustomerFormRawValue['hasassestsdeposit']>;
  customerrootmappath: FormControl<CustomerFormRawValue['customerrootmappath']>;
  employername: FormControl<CustomerFormRawValue['employername']>;
  employeraddress: FormControl<CustomerFormRawValue['employeraddress']>;
  employerphone: FormControl<CustomerFormRawValue['employerphone']>;
  employerdesignation: FormControl<CustomerFormRawValue['employerdesignation']>;
  previousemployername: FormControl<CustomerFormRawValue['previousemployername']>;
  previousemployeraddress: FormControl<CustomerFormRawValue['previousemployeraddress']>;
  previousindustry: FormControl<CustomerFormRawValue['previousindustry']>;
  previousperiod: FormControl<CustomerFormRawValue['previousperiod']>;
  previouspositions: FormControl<CustomerFormRawValue['previouspositions']>;
  previousresionforleaving: FormControl<CustomerFormRawValue['previousresionforleaving']>;
  hascreaditlimit: FormControl<CustomerFormRawValue['hascreaditlimit']>;
  accountid: FormControl<CustomerFormRawValue['accountid']>;
  accountcode: FormControl<CustomerFormRawValue['accountcode']>;
  isregistered: FormControl<CustomerFormRawValue['isregistered']>;
  vatregnumber: FormControl<CustomerFormRawValue['vatregnumber']>;
  tinnumber: FormControl<CustomerFormRawValue['tinnumber']>;
  lat: FormControl<CustomerFormRawValue['lat']>;
  lon: FormControl<CustomerFormRawValue['lon']>;
  creditperiod: FormControl<CustomerFormRawValue['creditperiod']>;
};

export type CustomerFormGroup = FormGroup<CustomerFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CustomerFormService {
  createCustomerFormGroup(customer: CustomerFormGroupInput = { id: null }): CustomerFormGroup {
    const customerRawValue = this.convertCustomerToCustomerRawValue({
      ...this.getFormDefaults(),
      ...customer,
    });
    return new FormGroup<CustomerFormGroupContent>({
      id: new FormControl(
        { value: customerRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      customertype: new FormControl(customerRawValue.customertype),
      code: new FormControl(customerRawValue.code),
      title: new FormControl(customerRawValue.title),
      namewithinitials: new FormControl(customerRawValue.namewithinitials),
      fullname: new FormControl(customerRawValue.fullname),
      callingname: new FormControl(customerRawValue.callingname),
      nicno: new FormControl(customerRawValue.nicno),
      nicissueddate: new FormControl(customerRawValue.nicissueddate),
      dateofbirth: new FormControl(customerRawValue.dateofbirth),
      bloodgroup: new FormControl(customerRawValue.bloodgroup),
      gender: new FormControl(customerRawValue.gender),
      maritalstatus: new FormControl(customerRawValue.maritalstatus),
      marrieddate: new FormControl(customerRawValue.marrieddate),
      nationality: new FormControl(customerRawValue.nationality),
      territory: new FormControl(customerRawValue.territory),
      religion: new FormControl(customerRawValue.religion),
      team: new FormControl(customerRawValue.team),
      businessname: new FormControl(customerRawValue.businessname),
      businessregdate: new FormControl(customerRawValue.businessregdate),
      businessregno: new FormControl(customerRawValue.businessregno),
      profilepicturepath: new FormControl(customerRawValue.profilepicturepath),
      residencehouseno: new FormControl(customerRawValue.residencehouseno),
      residenceaddress: new FormControl(customerRawValue.residenceaddress),
      residencecity: new FormControl(customerRawValue.residencecity),
      residencephone: new FormControl(customerRawValue.residencephone),
      businesslocationno: new FormControl(customerRawValue.businesslocationno),
      businessaddress: new FormControl(customerRawValue.businessaddress),
      businesscity: new FormControl(customerRawValue.businesscity),
      businessphone1: new FormControl(customerRawValue.businessphone1),
      businessphone2: new FormControl(customerRawValue.businessphone2),
      businessmobile: new FormControl(customerRawValue.businessmobile),
      businessfax: new FormControl(customerRawValue.businessfax),
      businessemail: new FormControl(customerRawValue.businessemail),
      businessprovinceid: new FormControl(customerRawValue.businessprovinceid),
      businessdistrictid: new FormControl(customerRawValue.businessdistrictid),
      contactpersonname: new FormControl(customerRawValue.contactpersonname),
      contactpersonphone: new FormControl(customerRawValue.contactpersonphone),
      contactpersonmobile: new FormControl(customerRawValue.contactpersonmobile),
      contactpersonemail: new FormControl(customerRawValue.contactpersonemail),
      rootmappath: new FormControl(customerRawValue.rootmappath),
      website: new FormControl(customerRawValue.website),
      registrationdate: new FormControl(customerRawValue.registrationdate),
      isactive: new FormControl(customerRawValue.isactive),
      isinternalcustomer: new FormControl(customerRawValue.isinternalcustomer),
      description: new FormControl(customerRawValue.description),
      lmu: new FormControl(customerRawValue.lmu),
      lmd: new FormControl(customerRawValue.lmd),
      maximumdiscount: new FormControl(customerRawValue.maximumdiscount),
      creditlimit: new FormControl(customerRawValue.creditlimit),
      hassecuritydeposit: new FormControl(customerRawValue.hassecuritydeposit),
      securitydepositamount: new FormControl(customerRawValue.securitydepositamount),
      paybycash: new FormControl(customerRawValue.paybycash),
      cashpaymentbeforeload: new FormControl(customerRawValue.cashpaymentbeforeload),
      cashlastinvoicebeforeload: new FormControl(customerRawValue.cashlastinvoicebeforeload),
      paybycredit: new FormControl(customerRawValue.paybycredit),
      creditoneweekcheck: new FormControl(customerRawValue.creditoneweekcheck),
      creditpaymentbydays: new FormControl(customerRawValue.creditpaymentbydays),
      haspurchasingdeposit: new FormControl(customerRawValue.haspurchasingdeposit),
      hassecuritydepositbond: new FormControl(customerRawValue.hassecuritydepositbond),
      hasassestsdeposit: new FormControl(customerRawValue.hasassestsdeposit),
      customerrootmappath: new FormControl(customerRawValue.customerrootmappath),
      employername: new FormControl(customerRawValue.employername),
      employeraddress: new FormControl(customerRawValue.employeraddress),
      employerphone: new FormControl(customerRawValue.employerphone),
      employerdesignation: new FormControl(customerRawValue.employerdesignation),
      previousemployername: new FormControl(customerRawValue.previousemployername),
      previousemployeraddress: new FormControl(customerRawValue.previousemployeraddress),
      previousindustry: new FormControl(customerRawValue.previousindustry),
      previousperiod: new FormControl(customerRawValue.previousperiod),
      previouspositions: new FormControl(customerRawValue.previouspositions),
      previousresionforleaving: new FormControl(customerRawValue.previousresionforleaving),
      hascreaditlimit: new FormControl(customerRawValue.hascreaditlimit),
      accountid: new FormControl(customerRawValue.accountid),
      accountcode: new FormControl(customerRawValue.accountcode),
      isregistered: new FormControl(customerRawValue.isregistered),
      vatregnumber: new FormControl(customerRawValue.vatregnumber),
      tinnumber: new FormControl(customerRawValue.tinnumber),
      lat: new FormControl(customerRawValue.lat),
      lon: new FormControl(customerRawValue.lon),
      creditperiod: new FormControl(customerRawValue.creditperiod),
    });
  }

  getCustomer(form: CustomerFormGroup): ICustomer | NewCustomer {
    return this.convertCustomerRawValueToCustomer(form.getRawValue() as CustomerFormRawValue | NewCustomerFormRawValue);
  }

  resetForm(form: CustomerFormGroup, customer: CustomerFormGroupInput): void {
    const customerRawValue = this.convertCustomerToCustomerRawValue({ ...this.getFormDefaults(), ...customer });
    form.reset(
      {
        ...customerRawValue,
        id: { value: customerRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CustomerFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      registrationdate: currentTime,
      lmd: currentTime,
      hassecuritydeposit: false,
      paybycash: false,
      cashpaymentbeforeload: false,
      cashlastinvoicebeforeload: false,
      paybycredit: false,
      creditoneweekcheck: false,
      haspurchasingdeposit: false,
      hassecuritydepositbond: false,
      hasassestsdeposit: false,
      hascreaditlimit: false,
      isregistered: false,
    };
  }

  private convertCustomerRawValueToCustomer(rawCustomer: CustomerFormRawValue | NewCustomerFormRawValue): ICustomer | NewCustomer {
    return {
      ...rawCustomer,
      registrationdate: dayjs(rawCustomer.registrationdate, DATE_TIME_FORMAT),
      lmd: dayjs(rawCustomer.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertCustomerToCustomerRawValue(
    customer: ICustomer | (Partial<NewCustomer> & CustomerFormDefaults),
  ): CustomerFormRawValue | PartialWithRequiredKeyOf<NewCustomerFormRawValue> {
    return {
      ...customer,
      registrationdate: customer.registrationdate ? customer.registrationdate.format(DATE_TIME_FORMAT) : undefined,
      lmd: customer.lmd ? customer.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
