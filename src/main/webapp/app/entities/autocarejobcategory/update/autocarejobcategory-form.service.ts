import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAutocarejobcategory, NewAutocarejobcategory } from '../autocarejobcategory.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAutocarejobcategory for edit and NewAutocarejobcategoryFormGroupInput for create.
 */
type AutocarejobcategoryFormGroupInput = IAutocarejobcategory | PartialWithRequiredKeyOf<NewAutocarejobcategory>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IAutocarejobcategory | NewAutocarejobcategory> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type AutocarejobcategoryFormRawValue = FormValueOf<IAutocarejobcategory>;

type NewAutocarejobcategoryFormRawValue = FormValueOf<NewAutocarejobcategory>;

type AutocarejobcategoryFormDefaults = Pick<NewAutocarejobcategory, 'id' | 'lmd'>;

type AutocarejobcategoryFormGroupContent = {
  id: FormControl<AutocarejobcategoryFormRawValue['id'] | NewAutocarejobcategory['id']>;
  code: FormControl<AutocarejobcategoryFormRawValue['code']>;
  name: FormControl<AutocarejobcategoryFormRawValue['name']>;
  description: FormControl<AutocarejobcategoryFormRawValue['description']>;
  lmu: FormControl<AutocarejobcategoryFormRawValue['lmu']>;
  lmd: FormControl<AutocarejobcategoryFormRawValue['lmd']>;
  displayorder: FormControl<AutocarejobcategoryFormRawValue['displayorder']>;
};

export type AutocarejobcategoryFormGroup = FormGroup<AutocarejobcategoryFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AutocarejobcategoryFormService {
  createAutocarejobcategoryFormGroup(autocarejobcategory: AutocarejobcategoryFormGroupInput = { id: null }): AutocarejobcategoryFormGroup {
    const autocarejobcategoryRawValue = this.convertAutocarejobcategoryToAutocarejobcategoryRawValue({
      ...this.getFormDefaults(),
      ...autocarejobcategory,
    });
    return new FormGroup<AutocarejobcategoryFormGroupContent>({
      id: new FormControl(
        { value: autocarejobcategoryRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      code: new FormControl(autocarejobcategoryRawValue.code),
      name: new FormControl(autocarejobcategoryRawValue.name),
      description: new FormControl(autocarejobcategoryRawValue.description),
      lmu: new FormControl(autocarejobcategoryRawValue.lmu),
      lmd: new FormControl(autocarejobcategoryRawValue.lmd),
      displayorder: new FormControl(autocarejobcategoryRawValue.displayorder),
    });
  }

  getAutocarejobcategory(form: AutocarejobcategoryFormGroup): IAutocarejobcategory | NewAutocarejobcategory {
    return this.convertAutocarejobcategoryRawValueToAutocarejobcategory(
      form.getRawValue() as AutocarejobcategoryFormRawValue | NewAutocarejobcategoryFormRawValue,
    );
  }

  resetForm(form: AutocarejobcategoryFormGroup, autocarejobcategory: AutocarejobcategoryFormGroupInput): void {
    const autocarejobcategoryRawValue = this.convertAutocarejobcategoryToAutocarejobcategoryRawValue({
      ...this.getFormDefaults(),
      ...autocarejobcategory,
    });
    form.reset(
      {
        ...autocarejobcategoryRawValue,
        id: { value: autocarejobcategoryRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AutocarejobcategoryFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lmd: currentTime,
    };
  }

  private convertAutocarejobcategoryRawValueToAutocarejobcategory(
    rawAutocarejobcategory: AutocarejobcategoryFormRawValue | NewAutocarejobcategoryFormRawValue,
  ): IAutocarejobcategory | NewAutocarejobcategory {
    return {
      ...rawAutocarejobcategory,
      lmd: dayjs(rawAutocarejobcategory.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertAutocarejobcategoryToAutocarejobcategoryRawValue(
    autocarejobcategory: IAutocarejobcategory | (Partial<NewAutocarejobcategory> & AutocarejobcategoryFormDefaults),
  ): AutocarejobcategoryFormRawValue | PartialWithRequiredKeyOf<NewAutocarejobcategoryFormRawValue> {
    return {
      ...autocarejobcategory,
      lmd: autocarejobcategory.lmd ? autocarejobcategory.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
