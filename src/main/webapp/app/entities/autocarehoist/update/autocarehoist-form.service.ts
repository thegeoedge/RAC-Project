import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAutocarehoist, NewAutocarehoist } from '../autocarehoist.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAutocarehoist for edit and NewAutocarehoistFormGroupInput for create.
 */
type AutocarehoistFormGroupInput = IAutocarehoist | PartialWithRequiredKeyOf<NewAutocarehoist>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IAutocarehoist | NewAutocarehoist> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type AutocarehoistFormRawValue = FormValueOf<IAutocarehoist>;

type NewAutocarehoistFormRawValue = FormValueOf<NewAutocarehoist>;

type AutocarehoistFormDefaults = Pick<NewAutocarehoist, 'id' | 'lmd'>;

type AutocarehoistFormGroupContent = {
  id: FormControl<AutocarehoistFormRawValue['id'] | NewAutocarehoist['id']>;
  hoistname: FormControl<AutocarehoistFormRawValue['hoistname']>;
  hoistcode: FormControl<AutocarehoistFormRawValue['hoistcode']>;
  hoisttypeid: FormControl<AutocarehoistFormRawValue['hoisttypeid']>;
  hoisttypename: FormControl<AutocarehoistFormRawValue['hoisttypename']>;
  lmu: FormControl<AutocarehoistFormRawValue['lmu']>;
  lmd: FormControl<AutocarehoistFormRawValue['lmd']>;
};

export type AutocarehoistFormGroup = FormGroup<AutocarehoistFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AutocarehoistFormService {
  createAutocarehoistFormGroup(autocarehoist: AutocarehoistFormGroupInput = { id: null }): AutocarehoistFormGroup {
    const autocarehoistRawValue = this.convertAutocarehoistToAutocarehoistRawValue({
      ...this.getFormDefaults(),
      ...autocarehoist,
    });
    return new FormGroup<AutocarehoistFormGroupContent>({
      id: new FormControl(
        { value: autocarehoistRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      hoistname: new FormControl(autocarehoistRawValue.hoistname),
      hoistcode: new FormControl(autocarehoistRawValue.hoistcode),
      hoisttypeid: new FormControl(autocarehoistRawValue.hoisttypeid),
      hoisttypename: new FormControl(autocarehoistRawValue.hoisttypename),
      lmu: new FormControl(autocarehoistRawValue.lmu),
      lmd: new FormControl(autocarehoistRawValue.lmd),
    });
  }

  getAutocarehoist(form: AutocarehoistFormGroup): IAutocarehoist | NewAutocarehoist {
    return this.convertAutocarehoistRawValueToAutocarehoist(form.getRawValue() as AutocarehoistFormRawValue | NewAutocarehoistFormRawValue);
  }

  resetForm(form: AutocarehoistFormGroup, autocarehoist: AutocarehoistFormGroupInput): void {
    const autocarehoistRawValue = this.convertAutocarehoistToAutocarehoistRawValue({ ...this.getFormDefaults(), ...autocarehoist });
    form.reset(
      {
        ...autocarehoistRawValue,
        id: { value: autocarehoistRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AutocarehoistFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lmd: currentTime,
    };
  }

  private convertAutocarehoistRawValueToAutocarehoist(
    rawAutocarehoist: AutocarehoistFormRawValue | NewAutocarehoistFormRawValue,
  ): IAutocarehoist | NewAutocarehoist {
    return {
      ...rawAutocarehoist,
      lmd: dayjs(rawAutocarehoist.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertAutocarehoistToAutocarehoistRawValue(
    autocarehoist: IAutocarehoist | (Partial<NewAutocarehoist> & AutocarehoistFormDefaults),
  ): AutocarehoistFormRawValue | PartialWithRequiredKeyOf<NewAutocarehoistFormRawValue> {
    return {
      ...autocarehoist,
      lmd: autocarehoist.lmd ? autocarehoist.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
