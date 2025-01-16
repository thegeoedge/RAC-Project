import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IEmpJobcommission, NewEmpJobcommission } from '../emp-jobcommission.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IEmpJobcommission for edit and NewEmpJobcommissionFormGroupInput for create.
 */
type EmpJobcommissionFormGroupInput = IEmpJobcommission | PartialWithRequiredKeyOf<NewEmpJobcommission>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IEmpJobcommission | NewEmpJobcommission> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type EmpJobcommissionFormRawValue = FormValueOf<IEmpJobcommission>;

type NewEmpJobcommissionFormRawValue = FormValueOf<NewEmpJobcommission>;

type EmpJobcommissionFormDefaults = Pick<NewEmpJobcommission, 'id' | 'lmd'>;

type EmpJobcommissionFormGroupContent = {
  id: FormControl<EmpJobcommissionFormRawValue['id'] | NewEmpJobcommission['id']>;
  vehcatid: FormControl<EmpJobcommissionFormRawValue['vehcatid']>;
  autojobcatid: FormControl<EmpJobcommissionFormRawValue['autojobcatid']>;
  firstcom: FormControl<EmpJobcommissionFormRawValue['firstcom']>;
  secondcom: FormControl<EmpJobcommissionFormRawValue['secondcom']>;
  thirdcom: FormControl<EmpJobcommissionFormRawValue['thirdcom']>;
  lmd: FormControl<EmpJobcommissionFormRawValue['lmd']>;
  lmu: FormControl<EmpJobcommissionFormRawValue['lmu']>;
};

export type EmpJobcommissionFormGroup = FormGroup<EmpJobcommissionFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class EmpJobcommissionFormService {
  createEmpJobcommissionFormGroup(empJobcommission: EmpJobcommissionFormGroupInput = { id: null }): EmpJobcommissionFormGroup {
    const empJobcommissionRawValue = this.convertEmpJobcommissionToEmpJobcommissionRawValue({
      ...this.getFormDefaults(),
      ...empJobcommission,
    });
    return new FormGroup<EmpJobcommissionFormGroupContent>({
      id: new FormControl(
        { value: empJobcommissionRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      vehcatid: new FormControl(empJobcommissionRawValue.vehcatid, {
        validators: [Validators.required],
      }),
      autojobcatid: new FormControl(empJobcommissionRawValue.autojobcatid, {
        validators: [Validators.required],
      }),
      firstcom: new FormControl(empJobcommissionRawValue.firstcom),
      secondcom: new FormControl(empJobcommissionRawValue.secondcom),
      thirdcom: new FormControl(empJobcommissionRawValue.thirdcom),
      lmd: new FormControl(empJobcommissionRawValue.lmd),
      lmu: new FormControl(empJobcommissionRawValue.lmu),
    });
  }

  getEmpJobcommission(form: EmpJobcommissionFormGroup): IEmpJobcommission | NewEmpJobcommission {
    return this.convertEmpJobcommissionRawValueToEmpJobcommission(
      form.getRawValue() as EmpJobcommissionFormRawValue | NewEmpJobcommissionFormRawValue,
    );
  }

  resetForm(form: EmpJobcommissionFormGroup, empJobcommission: EmpJobcommissionFormGroupInput): void {
    const empJobcommissionRawValue = this.convertEmpJobcommissionToEmpJobcommissionRawValue({
      ...this.getFormDefaults(),
      ...empJobcommission,
    });
    form.reset(
      {
        ...empJobcommissionRawValue,
        id: { value: empJobcommissionRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): EmpJobcommissionFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lmd: currentTime,
    };
  }

  private convertEmpJobcommissionRawValueToEmpJobcommission(
    rawEmpJobcommission: EmpJobcommissionFormRawValue | NewEmpJobcommissionFormRawValue,
  ): IEmpJobcommission | NewEmpJobcommission {
    return {
      ...rawEmpJobcommission,
      lmd: dayjs(rawEmpJobcommission.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertEmpJobcommissionToEmpJobcommissionRawValue(
    empJobcommission: IEmpJobcommission | (Partial<NewEmpJobcommission> & EmpJobcommissionFormDefaults),
  ): EmpJobcommissionFormRawValue | PartialWithRequiredKeyOf<NewEmpJobcommissionFormRawValue> {
    return {
      ...empJobcommission,
      lmd: empJobcommission.lmd ? empJobcommission.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
