import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { INextserviceinstructions, NewNextserviceinstructions } from '../nextserviceinstructions.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts INextserviceinstructions for edit and NewNextserviceinstructionsFormGroupInput for create.
 */
type NextserviceinstructionsFormGroupInput = INextserviceinstructions | PartialWithRequiredKeyOf<NewNextserviceinstructions>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends INextserviceinstructions | NewNextserviceinstructions> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type NextserviceinstructionsFormRawValue = FormValueOf<INextserviceinstructions>;

type NewNextserviceinstructionsFormRawValue = FormValueOf<NewNextserviceinstructions>;

type NextserviceinstructionsFormDefaults = Pick<NewNextserviceinstructions, 'id' | 'isactive' | 'lmd' | 'ignore'>;

type NextserviceinstructionsFormGroupContent = {
  id: FormControl<NextserviceinstructionsFormRawValue['id'] | NewNextserviceinstructions['id']>;
  jobid: FormControl<NextserviceinstructionsFormRawValue['jobid']>;
  instruction: FormControl<NextserviceinstructionsFormRawValue['instruction']>;
  isactive: FormControl<NextserviceinstructionsFormRawValue['isactive']>;
  lmu: FormControl<NextserviceinstructionsFormRawValue['lmu']>;
  lmd: FormControl<NextserviceinstructionsFormRawValue['lmd']>;
  ignore: FormControl<NextserviceinstructionsFormRawValue['ignore']>;
  vehicleid: FormControl<NextserviceinstructionsFormRawValue['vehicleid']>;
  vehicleno: FormControl<NextserviceinstructionsFormRawValue['vehicleno']>;
};

export type NextserviceinstructionsFormGroup = FormGroup<NextserviceinstructionsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class NextserviceinstructionsFormService {
  createNextserviceinstructionsFormGroup(
    nextserviceinstructions: NextserviceinstructionsFormGroupInput = { id: null },
  ): NextserviceinstructionsFormGroup {
    const nextserviceinstructionsRawValue = this.convertNextserviceinstructionsToNextserviceinstructionsRawValue({
      ...this.getFormDefaults(),
      ...nextserviceinstructions,
    });
    return new FormGroup<NextserviceinstructionsFormGroupContent>({
      id: new FormControl(
        { value: nextserviceinstructionsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      jobid: new FormControl(nextserviceinstructionsRawValue.jobid),
      instruction: new FormControl(nextserviceinstructionsRawValue.instruction),
      isactive: new FormControl(nextserviceinstructionsRawValue.isactive),
      lmu: new FormControl(nextserviceinstructionsRawValue.lmu),
      lmd: new FormControl(nextserviceinstructionsRawValue.lmd),
      ignore: new FormControl(nextserviceinstructionsRawValue.ignore),
      vehicleid: new FormControl(nextserviceinstructionsRawValue.vehicleid),
      vehicleno: new FormControl(nextserviceinstructionsRawValue.vehicleno),
    });
  }

  getNextserviceinstructions(form: NextserviceinstructionsFormGroup): INextserviceinstructions | NewNextserviceinstructions {
    return this.convertNextserviceinstructionsRawValueToNextserviceinstructions(
      form.getRawValue() as NextserviceinstructionsFormRawValue | NewNextserviceinstructionsFormRawValue,
    );
  }

  resetForm(form: NextserviceinstructionsFormGroup, nextserviceinstructions: NextserviceinstructionsFormGroupInput): void {
    const nextserviceinstructionsRawValue = this.convertNextserviceinstructionsToNextserviceinstructionsRawValue({
      ...this.getFormDefaults(),
      ...nextserviceinstructions,
    });
    form.reset(
      {
        ...nextserviceinstructionsRawValue,
        id: { value: nextserviceinstructionsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): NextserviceinstructionsFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      isactive: false,
      lmd: currentTime,
      ignore: false,
    };
  }

  private convertNextserviceinstructionsRawValueToNextserviceinstructions(
    rawNextserviceinstructions: NextserviceinstructionsFormRawValue | NewNextserviceinstructionsFormRawValue,
  ): INextserviceinstructions | NewNextserviceinstructions {
    return {
      ...rawNextserviceinstructions,
      lmd: dayjs(rawNextserviceinstructions.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertNextserviceinstructionsToNextserviceinstructionsRawValue(
    nextserviceinstructions: INextserviceinstructions | (Partial<NewNextserviceinstructions> & NextserviceinstructionsFormDefaults),
  ): NextserviceinstructionsFormRawValue | PartialWithRequiredKeyOf<NewNextserviceinstructionsFormRawValue> {
    return {
      ...nextserviceinstructions,
      lmd: nextserviceinstructions.lmd ? nextserviceinstructions.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
