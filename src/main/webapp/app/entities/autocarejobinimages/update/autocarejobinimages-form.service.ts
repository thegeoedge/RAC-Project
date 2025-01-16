import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAutocarejobinimages, NewAutocarejobinimages } from '../autocarejobinimages.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAutocarejobinimages for edit and NewAutocarejobinimagesFormGroupInput for create.
 */
type AutocarejobinimagesFormGroupInput = IAutocarejobinimages | PartialWithRequiredKeyOf<NewAutocarejobinimages>;

type AutocarejobinimagesFormDefaults = Pick<NewAutocarejobinimages, 'id'>;

type AutocarejobinimagesFormGroupContent = {
  id: FormControl<IAutocarejobinimages['id'] | NewAutocarejobinimages['id']>;
  jobid: FormControl<IAutocarejobinimages['jobid']>;
  imagefolder: FormControl<IAutocarejobinimages['imagefolder']>;
  imagename: FormControl<IAutocarejobinimages['imagename']>;
};

export type AutocarejobinimagesFormGroup = FormGroup<AutocarejobinimagesFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AutocarejobinimagesFormService {
  createAutocarejobinimagesFormGroup(autocarejobinimages: AutocarejobinimagesFormGroupInput = { id: null }): AutocarejobinimagesFormGroup {
    const autocarejobinimagesRawValue = {
      ...this.getFormDefaults(),
      ...autocarejobinimages,
    };
    return new FormGroup<AutocarejobinimagesFormGroupContent>({
      id: new FormControl(
        { value: autocarejobinimagesRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      jobid: new FormControl(autocarejobinimagesRawValue.jobid),
      imagefolder: new FormControl(autocarejobinimagesRawValue.imagefolder),
      imagename: new FormControl(autocarejobinimagesRawValue.imagename),
    });
  }

  getAutocarejobinimages(form: AutocarejobinimagesFormGroup): IAutocarejobinimages | NewAutocarejobinimages {
    return form.getRawValue() as IAutocarejobinimages | NewAutocarejobinimages;
  }

  resetForm(form: AutocarejobinimagesFormGroup, autocarejobinimages: AutocarejobinimagesFormGroupInput): void {
    const autocarejobinimagesRawValue = { ...this.getFormDefaults(), ...autocarejobinimages };
    form.reset(
      {
        ...autocarejobinimagesRawValue,
        id: { value: autocarejobinimagesRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AutocarejobinimagesFormDefaults {
    return {
      id: null,
    };
  }
}
