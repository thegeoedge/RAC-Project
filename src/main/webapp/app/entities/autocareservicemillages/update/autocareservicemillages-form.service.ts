import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAutocareservicemillages, NewAutocareservicemillages } from '../autocareservicemillages.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAutocareservicemillages for edit and NewAutocareservicemillagesFormGroupInput for create.
 */
type AutocareservicemillagesFormGroupInput = IAutocareservicemillages | PartialWithRequiredKeyOf<NewAutocareservicemillages>;

type AutocareservicemillagesFormDefaults = Pick<NewAutocareservicemillages, 'id'>;

type AutocareservicemillagesFormGroupContent = {
  id: FormControl<IAutocareservicemillages['id'] | NewAutocareservicemillages['id']>;
  millage: FormControl<IAutocareservicemillages['millage']>;
};

export type AutocareservicemillagesFormGroup = FormGroup<AutocareservicemillagesFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AutocareservicemillagesFormService {
  createAutocareservicemillagesFormGroup(
    autocareservicemillages: AutocareservicemillagesFormGroupInput = { id: null },
  ): AutocareservicemillagesFormGroup {
    const autocareservicemillagesRawValue = {
      ...this.getFormDefaults(),
      ...autocareservicemillages,
    };
    return new FormGroup<AutocareservicemillagesFormGroupContent>({
      id: new FormControl(
        { value: autocareservicemillagesRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      millage: new FormControl(autocareservicemillagesRawValue.millage),
    });
  }

  getAutocareservicemillages(form: AutocareservicemillagesFormGroup): IAutocareservicemillages | NewAutocareservicemillages {
    return form.getRawValue() as IAutocareservicemillages | NewAutocareservicemillages;
  }

  resetForm(form: AutocareservicemillagesFormGroup, autocareservicemillages: AutocareservicemillagesFormGroupInput): void {
    const autocareservicemillagesRawValue = { ...this.getFormDefaults(), ...autocareservicemillages };
    form.reset(
      {
        ...autocareservicemillagesRawValue,
        id: { value: autocareservicemillagesRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AutocareservicemillagesFormDefaults {
    return {
      id: null,
    };
  }
}
