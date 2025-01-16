import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAutocarejob, NewAutocarejob } from '../autocarejob.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAutocarejob for edit and NewAutocarejobFormGroupInput for create.
 */
type AutocarejobFormGroupInput = IAutocarejob | PartialWithRequiredKeyOf<NewAutocarejob>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IAutocarejob | NewAutocarejob> = Omit<T, 'jobopentime' | 'lmd' | 'jobclosetime' | 'jobdate'> & {
  jobopentime?: string | null;
  lmd?: string | null;
  jobclosetime?: string | null;
  jobdate?: string | null;
};

type AutocarejobFormRawValue = FormValueOf<IAutocarejob>;

type NewAutocarejobFormRawValue = FormValueOf<NewAutocarejob>;

type AutocarejobFormDefaults = Pick<
  NewAutocarejob,
  | 'id'
  | 'jobopentime'
  | 'lmd'
  | 'isadvisorchecked'
  | 'isempallocated'
  | 'jobclosetime'
  | 'isjobclose'
  | 'isfeedback'
  | 'advisorfinalcheck'
  | 'jobdate'
  | 'iscompanyservice'
  | 'updatetocustomer'
  | 'isjobinvoiced'
  | 'iswaiting'
  | 'iscustomercomment'
>;

type AutocarejobFormGroupContent = {
  id: FormControl<AutocarejobFormRawValue['id'] | NewAutocarejob['id']>;
  jobnumber: FormControl<AutocarejobFormRawValue['jobnumber']>;
  vehicleid: FormControl<AutocarejobFormRawValue['vehicleid']>;
  vehiclenumber: FormControl<AutocarejobFormRawValue['vehiclenumber']>;
  millage: FormControl<AutocarejobFormRawValue['millage']>;
  nextmillage: FormControl<AutocarejobFormRawValue['nextmillage']>;
  nextservicedate: FormControl<AutocarejobFormRawValue['nextservicedate']>;
  vehicletypeid: FormControl<AutocarejobFormRawValue['vehicletypeid']>;
  jobtypeid: FormControl<AutocarejobFormRawValue['jobtypeid']>;
  jobtypename: FormControl<AutocarejobFormRawValue['jobtypename']>;
  jobopenby: FormControl<AutocarejobFormRawValue['jobopenby']>;
  jobopentime: FormControl<AutocarejobFormRawValue['jobopentime']>;
  lmu: FormControl<AutocarejobFormRawValue['lmu']>;
  lmd: FormControl<AutocarejobFormRawValue['lmd']>;
  specialrquirments: FormControl<AutocarejobFormRawValue['specialrquirments']>;
  specialinstructions: FormControl<AutocarejobFormRawValue['specialinstructions']>;
  remarks: FormControl<AutocarejobFormRawValue['remarks']>;
  nextserviceinstructions: FormControl<AutocarejobFormRawValue['nextserviceinstructions']>;
  lastserviceinstructions: FormControl<AutocarejobFormRawValue['lastserviceinstructions']>;
  isadvisorchecked: FormControl<AutocarejobFormRawValue['isadvisorchecked']>;
  isempallocated: FormControl<AutocarejobFormRawValue['isempallocated']>;
  jobclosetime: FormControl<AutocarejobFormRawValue['jobclosetime']>;
  isjobclose: FormControl<AutocarejobFormRawValue['isjobclose']>;
  isfeedback: FormControl<AutocarejobFormRawValue['isfeedback']>;
  feedbackstatusid: FormControl<AutocarejobFormRawValue['feedbackstatusid']>;
  customername: FormControl<AutocarejobFormRawValue['customername']>;
  customertel: FormControl<AutocarejobFormRawValue['customertel']>;
  customerid: FormControl<AutocarejobFormRawValue['customerid']>;
  advisorfinalcheck: FormControl<AutocarejobFormRawValue['advisorfinalcheck']>;
  jobdate: FormControl<AutocarejobFormRawValue['jobdate']>;
  iscompanyservice: FormControl<AutocarejobFormRawValue['iscompanyservice']>;
  freeservicenumber: FormControl<AutocarejobFormRawValue['freeservicenumber']>;
  companyid: FormControl<AutocarejobFormRawValue['companyid']>;
  updatetocustomer: FormControl<AutocarejobFormRawValue['updatetocustomer']>;
  nextgearoilmilage: FormControl<AutocarejobFormRawValue['nextgearoilmilage']>;
  isjobinvoiced: FormControl<AutocarejobFormRawValue['isjobinvoiced']>;
  iswaiting: FormControl<AutocarejobFormRawValue['iswaiting']>;
  iscustomercomment: FormControl<AutocarejobFormRawValue['iscustomercomment']>;
  imagefolder: FormControl<AutocarejobFormRawValue['imagefolder']>;
  frontimage: FormControl<AutocarejobFormRawValue['frontimage']>;
  leftimage: FormControl<AutocarejobFormRawValue['leftimage']>;
  rightimage: FormControl<AutocarejobFormRawValue['rightimage']>;
  backimage: FormControl<AutocarejobFormRawValue['backimage']>;
  dashboardimage: FormControl<AutocarejobFormRawValue['dashboardimage']>;
};

export type AutocarejobFormGroup = FormGroup<AutocarejobFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AutocarejobFormService {
  createAutocarejobFormGroup(autocarejob: AutocarejobFormGroupInput = { id: null }): AutocarejobFormGroup {
    const autocarejobRawValue = this.convertAutocarejobToAutocarejobRawValue({
      ...this.getFormDefaults(),
      ...autocarejob,
    });
    return new FormGroup<AutocarejobFormGroupContent>({
      id: new FormControl(
        { value: autocarejobRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      jobnumber: new FormControl(autocarejobRawValue.jobnumber),
      vehicleid: new FormControl(autocarejobRawValue.vehicleid),
      vehiclenumber: new FormControl(autocarejobRawValue.vehiclenumber),
      millage: new FormControl(autocarejobRawValue.millage),
      nextmillage: new FormControl(autocarejobRawValue.nextmillage),
      nextservicedate: new FormControl(autocarejobRawValue.nextservicedate),
      vehicletypeid: new FormControl(autocarejobRawValue.vehicletypeid),
      jobtypeid: new FormControl(autocarejobRawValue.jobtypeid),
      jobtypename: new FormControl(autocarejobRawValue.jobtypename),
      jobopenby: new FormControl(autocarejobRawValue.jobopenby),
      jobopentime: new FormControl(autocarejobRawValue.jobopentime),
      lmu: new FormControl(autocarejobRawValue.lmu),
      lmd: new FormControl(autocarejobRawValue.lmd),
      specialrquirments: new FormControl(autocarejobRawValue.specialrquirments),
      specialinstructions: new FormControl(autocarejobRawValue.specialinstructions),
      remarks: new FormControl(autocarejobRawValue.remarks),
      nextserviceinstructions: new FormControl(autocarejobRawValue.nextserviceinstructions),
      lastserviceinstructions: new FormControl(autocarejobRawValue.lastserviceinstructions),
      isadvisorchecked: new FormControl(autocarejobRawValue.isadvisorchecked),
      isempallocated: new FormControl(autocarejobRawValue.isempallocated),
      jobclosetime: new FormControl(autocarejobRawValue.jobclosetime),
      isjobclose: new FormControl(autocarejobRawValue.isjobclose),
      isfeedback: new FormControl(autocarejobRawValue.isfeedback),
      feedbackstatusid: new FormControl(autocarejobRawValue.feedbackstatusid),
      customername: new FormControl(autocarejobRawValue.customername),
      customertel: new FormControl(autocarejobRawValue.customertel),
      customerid: new FormControl(autocarejobRawValue.customerid),
      advisorfinalcheck: new FormControl(autocarejobRawValue.advisorfinalcheck),
      jobdate: new FormControl(autocarejobRawValue.jobdate),
      iscompanyservice: new FormControl(autocarejobRawValue.iscompanyservice),
      freeservicenumber: new FormControl(autocarejobRawValue.freeservicenumber),
      companyid: new FormControl(autocarejobRawValue.companyid),
      updatetocustomer: new FormControl(autocarejobRawValue.updatetocustomer),
      nextgearoilmilage: new FormControl(autocarejobRawValue.nextgearoilmilage),
      isjobinvoiced: new FormControl(autocarejobRawValue.isjobinvoiced),
      iswaiting: new FormControl(autocarejobRawValue.iswaiting),
      iscustomercomment: new FormControl(autocarejobRawValue.iscustomercomment),
      imagefolder: new FormControl(autocarejobRawValue.imagefolder),
      frontimage: new FormControl(autocarejobRawValue.frontimage),
      leftimage: new FormControl(autocarejobRawValue.leftimage),
      rightimage: new FormControl(autocarejobRawValue.rightimage),
      backimage: new FormControl(autocarejobRawValue.backimage),
      dashboardimage: new FormControl(autocarejobRawValue.dashboardimage),
    });
  }

  getAutocarejob(form: AutocarejobFormGroup): IAutocarejob | NewAutocarejob {
    return this.convertAutocarejobRawValueToAutocarejob(form.getRawValue() as AutocarejobFormRawValue | NewAutocarejobFormRawValue);
  }

  resetForm(form: AutocarejobFormGroup, autocarejob: AutocarejobFormGroupInput): void {
    const autocarejobRawValue = this.convertAutocarejobToAutocarejobRawValue({ ...this.getFormDefaults(), ...autocarejob });
    form.reset(
      {
        ...autocarejobRawValue,
        id: { value: autocarejobRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AutocarejobFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      jobopentime: currentTime,
      lmd: currentTime,
      isadvisorchecked: false,
      isempallocated: false,
      jobclosetime: currentTime,
      isjobclose: false,
      isfeedback: false,
      advisorfinalcheck: false,
      jobdate: currentTime,
      iscompanyservice: false,
      updatetocustomer: false,
      isjobinvoiced: false,
      iswaiting: false,
      iscustomercomment: false,
    };
  }

  private convertAutocarejobRawValueToAutocarejob(
    rawAutocarejob: AutocarejobFormRawValue | NewAutocarejobFormRawValue,
  ): IAutocarejob | NewAutocarejob {
    return {
      ...rawAutocarejob,
      jobopentime: dayjs(rawAutocarejob.jobopentime, DATE_TIME_FORMAT),
      lmd: dayjs(rawAutocarejob.lmd, DATE_TIME_FORMAT),
      jobclosetime: dayjs(rawAutocarejob.jobclosetime, DATE_TIME_FORMAT),
      jobdate: dayjs(rawAutocarejob.jobdate, DATE_TIME_FORMAT),
    };
  }

  private convertAutocarejobToAutocarejobRawValue(
    autocarejob: IAutocarejob | (Partial<NewAutocarejob> & AutocarejobFormDefaults),
  ): AutocarejobFormRawValue | PartialWithRequiredKeyOf<NewAutocarejobFormRawValue> {
    return {
      ...autocarejob,
      jobopentime: autocarejob.jobopentime ? autocarejob.jobopentime.format(DATE_TIME_FORMAT) : undefined,
      lmd: autocarejob.lmd ? autocarejob.lmd.format(DATE_TIME_FORMAT) : undefined,
      jobclosetime: autocarejob.jobclosetime ? autocarejob.jobclosetime.format(DATE_TIME_FORMAT) : undefined,
      jobdate: autocarejob.jobdate ? autocarejob.jobdate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
