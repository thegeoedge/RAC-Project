import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAutocaretimetable, NewAutocaretimetable } from '../autocaretimetable.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAutocaretimetable for edit and NewAutocaretimetableFormGroupInput for create.
 */
type AutocaretimetableFormGroupInput = IAutocaretimetable | PartialWithRequiredKeyOf<NewAutocaretimetable>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IAutocaretimetable | NewAutocaretimetable> = Omit<T, 'hoisttime'> & {
  hoisttime?: string | null;
};

type AutocaretimetableFormRawValue = FormValueOf<IAutocaretimetable>;

type NewAutocaretimetableFormRawValue = FormValueOf<NewAutocaretimetable>;

type AutocaretimetableFormDefaults = Pick<NewAutocaretimetable, 'id' | 'hoisttime'>;

type AutocaretimetableFormGroupContent = {
  id: FormControl<AutocaretimetableFormRawValue['id'] | NewAutocaretimetable['id']>;
  hoisttypeid: FormControl<AutocaretimetableFormRawValue['hoisttypeid']>;
  hoisttypename: FormControl<AutocaretimetableFormRawValue['hoisttypename']>;
  hoisttime: FormControl<AutocaretimetableFormRawValue['hoisttime']>;
};

export type AutocaretimetableFormGroup = FormGroup<AutocaretimetableFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AutocaretimetableFormService {
  createAutocaretimetableFormGroup(autocaretimetable: AutocaretimetableFormGroupInput = { id: null }): AutocaretimetableFormGroup {
    const autocaretimetableRawValue = this.convertAutocaretimetableToAutocaretimetableRawValue({
      ...this.getFormDefaults(),
      ...autocaretimetable,
    });
    return new FormGroup<AutocaretimetableFormGroupContent>({
      id: new FormControl(
        { value: autocaretimetableRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      hoisttypeid: new FormControl(autocaretimetableRawValue.hoisttypeid),
      hoisttypename: new FormControl(autocaretimetableRawValue.hoisttypename),
      hoisttime: new FormControl(autocaretimetableRawValue.hoisttime),
    });
  }

  getAutocaretimetable(form: AutocaretimetableFormGroup): IAutocaretimetable | NewAutocaretimetable {
    return this.convertAutocaretimetableRawValueToAutocaretimetable(
      form.getRawValue() as AutocaretimetableFormRawValue | NewAutocaretimetableFormRawValue,
    );
  }

  resetForm(form: AutocaretimetableFormGroup, autocaretimetable: AutocaretimetableFormGroupInput): void {
    const autocaretimetableRawValue = this.convertAutocaretimetableToAutocaretimetableRawValue({
      ...this.getFormDefaults(),
      ...autocaretimetable,
    });
    form.reset(
      {
        ...autocaretimetableRawValue,
        id: { value: autocaretimetableRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AutocaretimetableFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      hoisttime: currentTime,
    };
  }

  private convertAutocaretimetableRawValueToAutocaretimetable(
    rawAutocaretimetable: AutocaretimetableFormRawValue | NewAutocaretimetableFormRawValue,
  ): IAutocaretimetable | NewAutocaretimetable {
    return {
      ...rawAutocaretimetable,
      hoisttime: dayjs(rawAutocaretimetable.hoisttime, DATE_TIME_FORMAT),
    };
  }

  private convertAutocaretimetableToAutocaretimetableRawValue(
    autocaretimetable: IAutocaretimetable | (Partial<NewAutocaretimetable> & AutocaretimetableFormDefaults),
  ): AutocaretimetableFormRawValue | PartialWithRequiredKeyOf<NewAutocaretimetableFormRawValue> {
    return {
      ...autocaretimetable,
      hoisttime: autocaretimetable.hoisttime ? autocaretimetable.hoisttime.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
