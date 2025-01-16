import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAutocareappointment, NewAutocareappointment } from '../autocareappointment.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAutocareappointment for edit and NewAutocareappointmentFormGroupInput for create.
 */
type AutocareappointmentFormGroupInput = IAutocareappointment | PartialWithRequiredKeyOf<NewAutocareappointment>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IAutocareappointment | NewAutocareappointment> = Omit<
  T,
  'appointmentdate' | 'addeddate' | 'conformdate' | 'appointmenttime' | 'lmd'
> & {
  appointmentdate?: string | null;
  addeddate?: string | null;
  conformdate?: string | null;
  appointmenttime?: string | null;
  lmd?: string | null;
};

type AutocareappointmentFormRawValue = FormValueOf<IAutocareappointment>;

type NewAutocareappointmentFormRawValue = FormValueOf<NewAutocareappointment>;

type AutocareappointmentFormDefaults = Pick<
  NewAutocareappointment,
  | 'id'
  | 'appointmentdate'
  | 'addeddate'
  | 'conformdate'
  | 'appointmenttime'
  | 'isconformed'
  | 'lmd'
  | 'issued'
  | 'isarrived'
  | 'iscancel'
  | 'isnoanswer'
  | 'ismobileappointment'
>;

type AutocareappointmentFormGroupContent = {
  id: FormControl<AutocareappointmentFormRawValue['id'] | NewAutocareappointment['id']>;
  appointmenttype: FormControl<AutocareappointmentFormRawValue['appointmenttype']>;
  appointmentdate: FormControl<AutocareappointmentFormRawValue['appointmentdate']>;
  addeddate: FormControl<AutocareappointmentFormRawValue['addeddate']>;
  conformdate: FormControl<AutocareappointmentFormRawValue['conformdate']>;
  appointmentnumber: FormControl<AutocareappointmentFormRawValue['appointmentnumber']>;
  vehiclenumber: FormControl<AutocareappointmentFormRawValue['vehiclenumber']>;
  appointmenttime: FormControl<AutocareappointmentFormRawValue['appointmenttime']>;
  isconformed: FormControl<AutocareappointmentFormRawValue['isconformed']>;
  conformedby: FormControl<AutocareappointmentFormRawValue['conformedby']>;
  lmd: FormControl<AutocareappointmentFormRawValue['lmd']>;
  lmu: FormControl<AutocareappointmentFormRawValue['lmu']>;
  customerid: FormControl<AutocareappointmentFormRawValue['customerid']>;
  contactnumber: FormControl<AutocareappointmentFormRawValue['contactnumber']>;
  customername: FormControl<AutocareappointmentFormRawValue['customername']>;
  issued: FormControl<AutocareappointmentFormRawValue['issued']>;
  hoistid: FormControl<AutocareappointmentFormRawValue['hoistid']>;
  isarrived: FormControl<AutocareappointmentFormRawValue['isarrived']>;
  iscancel: FormControl<AutocareappointmentFormRawValue['iscancel']>;
  isnoanswer: FormControl<AutocareappointmentFormRawValue['isnoanswer']>;
  missedappointmentcall: FormControl<AutocareappointmentFormRawValue['missedappointmentcall']>;
  customermobileid: FormControl<AutocareappointmentFormRawValue['customermobileid']>;
  customermobilevehicleid: FormControl<AutocareappointmentFormRawValue['customermobilevehicleid']>;
  vehicleid: FormControl<AutocareappointmentFormRawValue['vehicleid']>;
  ismobileappointment: FormControl<AutocareappointmentFormRawValue['ismobileappointment']>;
  advancepayment: FormControl<AutocareappointmentFormRawValue['advancepayment']>;
  jobid: FormControl<AutocareappointmentFormRawValue['jobid']>;
};

export type AutocareappointmentFormGroup = FormGroup<AutocareappointmentFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AutocareappointmentFormService {
  createAutocareappointmentFormGroup(autocareappointment: AutocareappointmentFormGroupInput = { id: null }): AutocareappointmentFormGroup {
    const autocareappointmentRawValue = this.convertAutocareappointmentToAutocareappointmentRawValue({
      ...this.getFormDefaults(),
      ...autocareappointment,
    });
    return new FormGroup<AutocareappointmentFormGroupContent>({
      id: new FormControl(
        { value: autocareappointmentRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      appointmenttype: new FormControl(autocareappointmentRawValue.appointmenttype),
      appointmentdate: new FormControl(autocareappointmentRawValue.appointmentdate),
      addeddate: new FormControl(autocareappointmentRawValue.addeddate),
      conformdate: new FormControl(autocareappointmentRawValue.conformdate),
      appointmentnumber: new FormControl(autocareappointmentRawValue.appointmentnumber),
      vehiclenumber: new FormControl(autocareappointmentRawValue.vehiclenumber),
      appointmenttime: new FormControl(autocareappointmentRawValue.appointmenttime),
      isconformed: new FormControl(autocareappointmentRawValue.isconformed),
      conformedby: new FormControl(autocareappointmentRawValue.conformedby),
      lmd: new FormControl(autocareappointmentRawValue.lmd),
      lmu: new FormControl(autocareappointmentRawValue.lmu),
      customerid: new FormControl(autocareappointmentRawValue.customerid),
      contactnumber: new FormControl(autocareappointmentRawValue.contactnumber),
      customername: new FormControl(autocareappointmentRawValue.customername),
      issued: new FormControl(autocareappointmentRawValue.issued),
      hoistid: new FormControl(autocareappointmentRawValue.hoistid),
      isarrived: new FormControl(autocareappointmentRawValue.isarrived),
      iscancel: new FormControl(autocareappointmentRawValue.iscancel),
      isnoanswer: new FormControl(autocareappointmentRawValue.isnoanswer),
      missedappointmentcall: new FormControl(autocareappointmentRawValue.missedappointmentcall),
      customermobileid: new FormControl(autocareappointmentRawValue.customermobileid),
      customermobilevehicleid: new FormControl(autocareappointmentRawValue.customermobilevehicleid),
      vehicleid: new FormControl(autocareappointmentRawValue.vehicleid),
      ismobileappointment: new FormControl(autocareappointmentRawValue.ismobileappointment),
      advancepayment: new FormControl(autocareappointmentRawValue.advancepayment),
      jobid: new FormControl(autocareappointmentRawValue.jobid),
    });
  }

  getAutocareappointment(form: AutocareappointmentFormGroup): IAutocareappointment | NewAutocareappointment {
    return this.convertAutocareappointmentRawValueToAutocareappointment(
      form.getRawValue() as AutocareappointmentFormRawValue | NewAutocareappointmentFormRawValue,
    );
  }

  resetForm(form: AutocareappointmentFormGroup, autocareappointment: AutocareappointmentFormGroupInput): void {
    const autocareappointmentRawValue = this.convertAutocareappointmentToAutocareappointmentRawValue({
      ...this.getFormDefaults(),
      ...autocareappointment,
    });
    form.reset(
      {
        ...autocareappointmentRawValue,
        id: { value: autocareappointmentRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AutocareappointmentFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      // appointmentdate: currentTime,
      addeddate: currentTime,
      conformdate: currentTime,
      // appointmenttime: currentTime,
      isconformed: false,
      lmd: currentTime,
      issued: false,
      isarrived: false,
      iscancel: false,
      isnoanswer: false,
      ismobileappointment: false,
    };
  }

  private convertAutocareappointmentRawValueToAutocareappointment(
    rawAutocareappointment: AutocareappointmentFormRawValue | NewAutocareappointmentFormRawValue,
  ): IAutocareappointment | NewAutocareappointment {
    return {
      ...rawAutocareappointment,
      appointmentdate: dayjs(rawAutocareappointment.appointmentdate, DATE_TIME_FORMAT),
      addeddate: dayjs(rawAutocareappointment.addeddate, DATE_TIME_FORMAT),
      conformdate: dayjs(rawAutocareappointment.conformdate, DATE_TIME_FORMAT),
      appointmenttime: dayjs(rawAutocareappointment.appointmenttime, DATE_TIME_FORMAT),
      lmd: dayjs(rawAutocareappointment.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertAutocareappointmentToAutocareappointmentRawValue(
    autocareappointment: IAutocareappointment | (Partial<NewAutocareappointment> & AutocareappointmentFormDefaults),
  ): AutocareappointmentFormRawValue | PartialWithRequiredKeyOf<NewAutocareappointmentFormRawValue> {
    return {
      ...autocareappointment,
      appointmentdate: autocareappointment.appointmentdate ? autocareappointment.appointmentdate.format(DATE_TIME_FORMAT) : undefined,
      addeddate: autocareappointment.addeddate ? autocareappointment.addeddate.format(DATE_TIME_FORMAT) : undefined,
      conformdate: autocareappointment.conformdate ? autocareappointment.conformdate.format(DATE_TIME_FORMAT) : undefined,
      appointmenttime: autocareappointment.appointmenttime ? autocareappointment.appointmenttime.format(DATE_TIME_FORMAT) : undefined,
      lmd: autocareappointment.lmd ? autocareappointment.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
