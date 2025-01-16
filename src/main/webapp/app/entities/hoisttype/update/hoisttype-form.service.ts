import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IHoisttype, NewHoisttype } from '../hoisttype.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IHoisttype for edit and NewHoisttypeFormGroupInput for create.
 */
type HoisttypeFormGroupInput = IHoisttype | PartialWithRequiredKeyOf<NewHoisttype>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IHoisttype | NewHoisttype> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type HoisttypeFormRawValue = FormValueOf<IHoisttype>;

type NewHoisttypeFormRawValue = FormValueOf<NewHoisttype>;

type HoisttypeFormDefaults = Pick<NewHoisttype, 'id' | 'lmd'>;

type HoisttypeFormGroupContent = {
  id: FormControl<HoisttypeFormRawValue['id'] | NewHoisttype['id']>;
  hoisttype: FormControl<HoisttypeFormRawValue['hoisttype']>;
  lmu: FormControl<HoisttypeFormRawValue['lmu']>;
  lmd: FormControl<HoisttypeFormRawValue['lmd']>;
};

export type HoisttypeFormGroup = FormGroup<HoisttypeFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class HoisttypeFormService {
  createHoisttypeFormGroup(hoisttype: HoisttypeFormGroupInput = { id: null }): HoisttypeFormGroup {
    const hoisttypeRawValue = this.convertHoisttypeToHoisttypeRawValue({
      ...this.getFormDefaults(),
      ...hoisttype,
    });
    return new FormGroup<HoisttypeFormGroupContent>({
      id: new FormControl(
        { value: hoisttypeRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      hoisttype: new FormControl(hoisttypeRawValue.hoisttype),
      lmu: new FormControl(hoisttypeRawValue.lmu),
      lmd: new FormControl(hoisttypeRawValue.lmd),
    });
  }

  getHoisttype(form: HoisttypeFormGroup): IHoisttype | NewHoisttype {
    return this.convertHoisttypeRawValueToHoisttype(form.getRawValue() as HoisttypeFormRawValue | NewHoisttypeFormRawValue);
  }

  resetForm(form: HoisttypeFormGroup, hoisttype: HoisttypeFormGroupInput): void {
    const hoisttypeRawValue = this.convertHoisttypeToHoisttypeRawValue({ ...this.getFormDefaults(), ...hoisttype });
    form.reset(
      {
        ...hoisttypeRawValue,
        id: { value: hoisttypeRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): HoisttypeFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lmd: currentTime,
    };
  }

  private convertHoisttypeRawValueToHoisttype(rawHoisttype: HoisttypeFormRawValue | NewHoisttypeFormRawValue): IHoisttype | NewHoisttype {
    return {
      ...rawHoisttype,
      lmd: dayjs(rawHoisttype.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertHoisttypeToHoisttypeRawValue(
    hoisttype: IHoisttype | (Partial<NewHoisttype> & HoisttypeFormDefaults),
  ): HoisttypeFormRawValue | PartialWithRequiredKeyOf<NewHoisttypeFormRawValue> {
    return {
      ...hoisttype,
      lmd: hoisttype.lmd ? hoisttype.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
