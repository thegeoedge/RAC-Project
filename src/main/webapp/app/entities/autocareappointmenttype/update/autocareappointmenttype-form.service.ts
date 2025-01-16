import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAutocareappointmenttype, NewAutocareappointmenttype } from '../autocareappointmenttype.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAutocareappointmenttype for edit and NewAutocareappointmenttypeFormGroupInput for create.
 */
type AutocareappointmenttypeFormGroupInput = IAutocareappointmenttype | PartialWithRequiredKeyOf<NewAutocareappointmenttype>;

type AutocareappointmenttypeFormDefaults = Pick<NewAutocareappointmenttype, 'id'>;

type AutocareappointmenttypeFormGroupContent = {
  id: FormControl<IAutocareappointmenttype['id'] | NewAutocareappointmenttype['id']>;
  typename: FormControl<IAutocareappointmenttype['typename']>;
};

export type AutocareappointmenttypeFormGroup = FormGroup<AutocareappointmenttypeFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AutocareappointmenttypeFormService {
  createAutocareappointmenttypeFormGroup(
    autocareappointmenttype: AutocareappointmenttypeFormGroupInput = { id: null },
  ): AutocareappointmenttypeFormGroup {
    const autocareappointmenttypeRawValue = {
      ...this.getFormDefaults(),
      ...autocareappointmenttype,
    };
    return new FormGroup<AutocareappointmenttypeFormGroupContent>({
      id: new FormControl(
        { value: autocareappointmenttypeRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      typename: new FormControl(autocareappointmenttypeRawValue.typename),
    });
  }

  getAutocareappointmenttype(form: AutocareappointmenttypeFormGroup): IAutocareappointmenttype | NewAutocareappointmenttype {
    return form.getRawValue() as IAutocareappointmenttype | NewAutocareappointmenttype;
  }

  resetForm(form: AutocareappointmenttypeFormGroup, autocareappointmenttype: AutocareappointmenttypeFormGroupInput): void {
    const autocareappointmenttypeRawValue = { ...this.getFormDefaults(), ...autocareappointmenttype };
    form.reset(
      {
        ...autocareappointmenttypeRawValue,
        id: { value: autocareappointmenttypeRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AutocareappointmenttypeFormDefaults {
    return {
      id: null,
    };
  }
}
