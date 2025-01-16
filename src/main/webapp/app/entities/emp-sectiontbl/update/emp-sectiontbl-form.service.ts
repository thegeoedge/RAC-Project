import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IEmpSectiontbl, NewEmpSectiontbl } from '../emp-sectiontbl.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IEmpSectiontbl for edit and NewEmpSectiontblFormGroupInput for create.
 */
type EmpSectiontblFormGroupInput = IEmpSectiontbl | PartialWithRequiredKeyOf<NewEmpSectiontbl>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IEmpSectiontbl | NewEmpSectiontbl> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type EmpSectiontblFormRawValue = FormValueOf<IEmpSectiontbl>;

type NewEmpSectiontblFormRawValue = FormValueOf<NewEmpSectiontbl>;

type EmpSectiontblFormDefaults = Pick<NewEmpSectiontbl, 'id' | 'lmd'>;

type EmpSectiontblFormGroupContent = {
  id: FormControl<EmpSectiontblFormRawValue['id'] | NewEmpSectiontbl['id']>;
  empid: FormControl<EmpSectiontblFormRawValue['empid']>;
  sectionid: FormControl<EmpSectiontblFormRawValue['sectionid']>;
  lmd: FormControl<EmpSectiontblFormRawValue['lmd']>;
  lmu: FormControl<EmpSectiontblFormRawValue['lmu']>;
};

export type EmpSectiontblFormGroup = FormGroup<EmpSectiontblFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class EmpSectiontblFormService {
  createEmpSectiontblFormGroup(empSectiontbl: EmpSectiontblFormGroupInput = { id: null }): EmpSectiontblFormGroup {
    const empSectiontblRawValue = this.convertEmpSectiontblToEmpSectiontblRawValue({
      ...this.getFormDefaults(),
      ...empSectiontbl,
    });
    return new FormGroup<EmpSectiontblFormGroupContent>({
      id: new FormControl(
        { value: empSectiontblRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      empid: new FormControl(empSectiontblRawValue.empid),
      sectionid: new FormControl(empSectiontblRawValue.sectionid),
      lmd: new FormControl(empSectiontblRawValue.lmd),
      lmu: new FormControl(empSectiontblRawValue.lmu),
    });
  }

  getEmpSectiontbl(form: EmpSectiontblFormGroup): IEmpSectiontbl | NewEmpSectiontbl {
    return this.convertEmpSectiontblRawValueToEmpSectiontbl(form.getRawValue() as EmpSectiontblFormRawValue | NewEmpSectiontblFormRawValue);
  }

  resetForm(form: EmpSectiontblFormGroup, empSectiontbl: EmpSectiontblFormGroupInput): void {
    const empSectiontblRawValue = this.convertEmpSectiontblToEmpSectiontblRawValue({ ...this.getFormDefaults(), ...empSectiontbl });
    form.reset(
      {
        ...empSectiontblRawValue,
        id: { value: empSectiontblRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): EmpSectiontblFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lmd: currentTime,
    };
  }

  private convertEmpSectiontblRawValueToEmpSectiontbl(
    rawEmpSectiontbl: EmpSectiontblFormRawValue | NewEmpSectiontblFormRawValue,
  ): IEmpSectiontbl | NewEmpSectiontbl {
    return {
      ...rawEmpSectiontbl,
      lmd: dayjs(rawEmpSectiontbl.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertEmpSectiontblToEmpSectiontblRawValue(
    empSectiontbl: IEmpSectiontbl | (Partial<NewEmpSectiontbl> & EmpSectiontblFormDefaults),
  ): EmpSectiontblFormRawValue | PartialWithRequiredKeyOf<NewEmpSectiontblFormRawValue> {
    return {
      ...empSectiontbl,
      lmd: empSectiontbl.lmd ? empSectiontbl.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
