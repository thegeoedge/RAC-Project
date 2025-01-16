import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ICommonserviceoption, NewCommonserviceoption } from '../commonserviceoption.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICommonserviceoption for edit and NewCommonserviceoptionFormGroupInput for create.
 */
type CommonserviceoptionFormGroupInput = ICommonserviceoption | PartialWithRequiredKeyOf<NewCommonserviceoption>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ICommonserviceoption | NewCommonserviceoption> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type CommonserviceoptionFormRawValue = FormValueOf<ICommonserviceoption>;

type NewCommonserviceoptionFormRawValue = FormValueOf<NewCommonserviceoption>;

type CommonserviceoptionFormDefaults = Pick<NewCommonserviceoption, 'id' | 'isactive' | 'lmd'>;

type CommonserviceoptionFormGroupContent = {
  id: FormControl<CommonserviceoptionFormRawValue['id'] | NewCommonserviceoption['id']>;
  mainid: FormControl<CommonserviceoptionFormRawValue['mainid']>;
  code: FormControl<CommonserviceoptionFormRawValue['code']>;
  name: FormControl<CommonserviceoptionFormRawValue['name']>;
  description: FormControl<CommonserviceoptionFormRawValue['description']>;
  value: FormControl<CommonserviceoptionFormRawValue['value']>;
  isactive: FormControl<CommonserviceoptionFormRawValue['isactive']>;
  lmd: FormControl<CommonserviceoptionFormRawValue['lmd']>;
  lmu: FormControl<CommonserviceoptionFormRawValue['lmu']>;
};

export type CommonserviceoptionFormGroup = FormGroup<CommonserviceoptionFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CommonserviceoptionFormService {
  createCommonserviceoptionFormGroup(commonserviceoption: CommonserviceoptionFormGroupInput = { id: null }): CommonserviceoptionFormGroup {
    const commonserviceoptionRawValue = this.convertCommonserviceoptionToCommonserviceoptionRawValue({
      ...this.getFormDefaults(),
      ...commonserviceoption,
    });
    return new FormGroup<CommonserviceoptionFormGroupContent>({
      id: new FormControl(
        { value: commonserviceoptionRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      mainid: new FormControl(commonserviceoptionRawValue.mainid),
      code: new FormControl(commonserviceoptionRawValue.code),
      name: new FormControl(commonserviceoptionRawValue.name),
      description: new FormControl(commonserviceoptionRawValue.description),
      value: new FormControl(commonserviceoptionRawValue.value),
      isactive: new FormControl(commonserviceoptionRawValue.isactive),
      lmd: new FormControl(commonserviceoptionRawValue.lmd),
      lmu: new FormControl(commonserviceoptionRawValue.lmu),
    });
  }

  getCommonserviceoption(form: CommonserviceoptionFormGroup): ICommonserviceoption | NewCommonserviceoption {
    return this.convertCommonserviceoptionRawValueToCommonserviceoption(
      form.getRawValue() as CommonserviceoptionFormRawValue | NewCommonserviceoptionFormRawValue,
    );
  }

  resetForm(form: CommonserviceoptionFormGroup, commonserviceoption: CommonserviceoptionFormGroupInput): void {
    const commonserviceoptionRawValue = this.convertCommonserviceoptionToCommonserviceoptionRawValue({
      ...this.getFormDefaults(),
      ...commonserviceoption,
    });
    form.reset(
      {
        ...commonserviceoptionRawValue,
        id: { value: commonserviceoptionRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CommonserviceoptionFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      isactive: false,
      lmd: currentTime,
    };
  }

  private convertCommonserviceoptionRawValueToCommonserviceoption(
    rawCommonserviceoption: CommonserviceoptionFormRawValue | NewCommonserviceoptionFormRawValue,
  ): ICommonserviceoption | NewCommonserviceoption {
    return {
      ...rawCommonserviceoption,
      lmd: dayjs(rawCommonserviceoption.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertCommonserviceoptionToCommonserviceoptionRawValue(
    commonserviceoption: ICommonserviceoption | (Partial<NewCommonserviceoption> & CommonserviceoptionFormDefaults),
  ): CommonserviceoptionFormRawValue | PartialWithRequiredKeyOf<NewCommonserviceoptionFormRawValue> {
    return {
      ...commonserviceoption,
      lmd: commonserviceoption.lmd ? commonserviceoption.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
