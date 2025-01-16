import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAutojobempallocation, NewAutojobempallocation } from '../autojobempallocation.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAutojobempallocation for edit and NewAutojobempallocationFormGroupInput for create.
 */
type AutojobempallocationFormGroupInput = IAutojobempallocation | PartialWithRequiredKeyOf<NewAutojobempallocation>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IAutojobempallocation | NewAutojobempallocation> = Omit<
  T,
  'allocatetime' | 'lmd' | 'jobdate' | 'starttime' | 'endtime'
> & {
  allocatetime?: string | null;
  lmd?: string | null;
  jobdate?: string | null;
  starttime?: string | null;
  endtime?: string | null;
};

type AutojobempallocationFormRawValue = FormValueOf<IAutojobempallocation>;

type NewAutojobempallocationFormRawValue = FormValueOf<NewAutojobempallocation>;

type AutojobempallocationFormDefaults = Pick<
  NewAutojobempallocation,
  'id' | 'allocatetime' | 'lmd' | 'jobdate' | 'iscommissionpaid' | 'starttime' | 'endtime'
>;

type AutojobempallocationFormGroupContent = {
  id: FormControl<AutojobempallocationFormRawValue['id'] | NewAutojobempallocation['id']>;
  jobid: FormControl<AutojobempallocationFormRawValue['jobid']>;
  categoryid: FormControl<AutojobempallocationFormRawValue['categoryid']>;
  empposition: FormControl<AutojobempallocationFormRawValue['empposition']>;
  empid: FormControl<AutojobempallocationFormRawValue['empid']>;
  empname: FormControl<AutojobempallocationFormRawValue['empname']>;
  allocatetime: FormControl<AutojobempallocationFormRawValue['allocatetime']>;
  lmu: FormControl<AutojobempallocationFormRawValue['lmu']>;
  lmd: FormControl<AutojobempallocationFormRawValue['lmd']>;
  jobdate: FormControl<AutojobempallocationFormRawValue['jobdate']>;
  commission: FormControl<AutojobempallocationFormRawValue['commission']>;
  iscommissionpaid: FormControl<AutojobempallocationFormRawValue['iscommissionpaid']>;
  starttime: FormControl<AutojobempallocationFormRawValue['starttime']>;
  endtime: FormControl<AutojobempallocationFormRawValue['endtime']>;
};

export type AutojobempallocationFormGroup = FormGroup<AutojobempallocationFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AutojobempallocationFormService {
  createAutojobempallocationFormGroup(
    autojobempallocation: AutojobempallocationFormGroupInput = { id: null },
  ): AutojobempallocationFormGroup {
    const autojobempallocationRawValue = this.convertAutojobempallocationToAutojobempallocationRawValue({
      ...this.getFormDefaults(),
      ...autojobempallocation,
    });
    return new FormGroup<AutojobempallocationFormGroupContent>({
      id: new FormControl(
        { value: autojobempallocationRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      jobid: new FormControl(autojobempallocationRawValue.jobid),
      categoryid: new FormControl(autojobempallocationRawValue.categoryid),
      empposition: new FormControl(autojobempallocationRawValue.empposition),
      empid: new FormControl(autojobempallocationRawValue.empid),
      empname: new FormControl(autojobempallocationRawValue.empname),
      allocatetime: new FormControl(autojobempallocationRawValue.allocatetime),
      lmu: new FormControl(autojobempallocationRawValue.lmu),
      lmd: new FormControl(autojobempallocationRawValue.lmd),
      jobdate: new FormControl(autojobempallocationRawValue.jobdate),
      commission: new FormControl(autojobempallocationRawValue.commission),
      iscommissionpaid: new FormControl(autojobempallocationRawValue.iscommissionpaid),
      starttime: new FormControl(autojobempallocationRawValue.starttime),
      endtime: new FormControl(autojobempallocationRawValue.endtime),
    });
  }

  getAutojobempallocation(form: AutojobempallocationFormGroup): IAutojobempallocation | NewAutojobempallocation {
    return this.convertAutojobempallocationRawValueToAutojobempallocation(
      form.getRawValue() as AutojobempallocationFormRawValue | NewAutojobempallocationFormRawValue,
    );
  }

  resetForm(form: AutojobempallocationFormGroup, autojobempallocation: AutojobempallocationFormGroupInput): void {
    const autojobempallocationRawValue = this.convertAutojobempallocationToAutojobempallocationRawValue({
      ...this.getFormDefaults(),
      ...autojobempallocation,
    });
    form.reset(
      {
        ...autojobempallocationRawValue,
        id: { value: autojobempallocationRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AutojobempallocationFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      allocatetime: currentTime,
      lmd: currentTime,
      jobdate: currentTime,
      iscommissionpaid: false,
      starttime: currentTime,
      endtime: currentTime,
    };
  }

  private convertAutojobempallocationRawValueToAutojobempallocation(
    rawAutojobempallocation: AutojobempallocationFormRawValue | NewAutojobempallocationFormRawValue,
  ): IAutojobempallocation | NewAutojobempallocation {
    return {
      ...rawAutojobempallocation,
      allocatetime: dayjs(rawAutojobempallocation.allocatetime, DATE_TIME_FORMAT),
      lmd: dayjs(rawAutojobempallocation.lmd, DATE_TIME_FORMAT),
      jobdate: dayjs(rawAutojobempallocation.jobdate, DATE_TIME_FORMAT),
      starttime: dayjs(rawAutojobempallocation.starttime, DATE_TIME_FORMAT),
      endtime: dayjs(rawAutojobempallocation.endtime, DATE_TIME_FORMAT),
    };
  }

  private convertAutojobempallocationToAutojobempallocationRawValue(
    autojobempallocation: IAutojobempallocation | (Partial<NewAutojobempallocation> & AutojobempallocationFormDefaults),
  ): AutojobempallocationFormRawValue | PartialWithRequiredKeyOf<NewAutojobempallocationFormRawValue> {
    return {
      ...autojobempallocation,
      allocatetime: autojobempallocation.allocatetime ? autojobempallocation.allocatetime.format(DATE_TIME_FORMAT) : undefined,
      lmd: autojobempallocation.lmd ? autojobempallocation.lmd.format(DATE_TIME_FORMAT) : undefined,
      jobdate: autojobempallocation.jobdate ? autojobempallocation.jobdate.format(DATE_TIME_FORMAT) : undefined,
      starttime: autojobempallocation.starttime ? autojobempallocation.starttime.format(DATE_TIME_FORMAT) : undefined,
      endtime: autojobempallocation.endtime ? autojobempallocation.endtime.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
