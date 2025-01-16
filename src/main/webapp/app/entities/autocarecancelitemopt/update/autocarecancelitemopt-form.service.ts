import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAutocarecancelitemopt, NewAutocarecancelitemopt } from '../autocarecancelitemopt.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAutocarecancelitemopt for edit and NewAutocarecancelitemoptFormGroupInput for create.
 */
type AutocarecancelitemoptFormGroupInput = IAutocarecancelitemopt | PartialWithRequiredKeyOf<NewAutocarecancelitemopt>;

type AutocarecancelitemoptFormDefaults = Pick<NewAutocarecancelitemopt, 'id'>;

type AutocarecancelitemoptFormGroupContent = {
  id: FormControl<IAutocarecancelitemopt['id'] | NewAutocarecancelitemopt['id']>;
  canceloption: FormControl<IAutocarecancelitemopt['canceloption']>;
};

export type AutocarecancelitemoptFormGroup = FormGroup<AutocarecancelitemoptFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AutocarecancelitemoptFormService {
  createAutocarecancelitemoptFormGroup(
    autocarecancelitemopt: AutocarecancelitemoptFormGroupInput = { id: null },
  ): AutocarecancelitemoptFormGroup {
    const autocarecancelitemoptRawValue = {
      ...this.getFormDefaults(),
      ...autocarecancelitemopt,
    };
    return new FormGroup<AutocarecancelitemoptFormGroupContent>({
      id: new FormControl(
        { value: autocarecancelitemoptRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      canceloption: new FormControl(autocarecancelitemoptRawValue.canceloption),
    });
  }

  getAutocarecancelitemopt(form: AutocarecancelitemoptFormGroup): IAutocarecancelitemopt | NewAutocarecancelitemopt {
    return form.getRawValue() as IAutocarecancelitemopt | NewAutocarecancelitemopt;
  }

  resetForm(form: AutocarecancelitemoptFormGroup, autocarecancelitemopt: AutocarecancelitemoptFormGroupInput): void {
    const autocarecancelitemoptRawValue = { ...this.getFormDefaults(), ...autocarecancelitemopt };
    form.reset(
      {
        ...autocarecancelitemoptRawValue,
        id: { value: autocarecancelitemoptRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AutocarecancelitemoptFormDefaults {
    return {
      id: null,
    };
  }
}
