import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ILastserviceinstructions, NewLastserviceinstructions } from '../lastserviceinstructions.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ILastserviceinstructions for edit and NewLastserviceinstructionsFormGroupInput for create.
 */
type LastserviceinstructionsFormGroupInput = ILastserviceinstructions | PartialWithRequiredKeyOf<NewLastserviceinstructions>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ILastserviceinstructions | NewLastserviceinstructions> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type LastserviceinstructionsFormRawValue = FormValueOf<ILastserviceinstructions>;

type NewLastserviceinstructionsFormRawValue = FormValueOf<NewLastserviceinstructions>;

type LastserviceinstructionsFormDefaults = Pick<NewLastserviceinstructions, 'id' | 'isactive' | 'lmd' | 'ignore'>;

type LastserviceinstructionsFormGroupContent = {
  id: FormControl<LastserviceinstructionsFormRawValue['id'] | NewLastserviceinstructions['id']>;
  jobid: FormControl<LastserviceinstructionsFormRawValue['jobid']>;
  instruction: FormControl<LastserviceinstructionsFormRawValue['instruction']>;
  isactive: FormControl<LastserviceinstructionsFormRawValue['isactive']>;
  lmu: FormControl<LastserviceinstructionsFormRawValue['lmu']>;
  lmd: FormControl<LastserviceinstructionsFormRawValue['lmd']>;
  ignore: FormControl<LastserviceinstructionsFormRawValue['ignore']>;
  vehicleid: FormControl<LastserviceinstructionsFormRawValue['vehicleid']>;
  vehicleno: FormControl<LastserviceinstructionsFormRawValue['vehicleno']>;
};

export type LastserviceinstructionsFormGroup = FormGroup<LastserviceinstructionsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class LastserviceinstructionsFormService {
  createLastserviceinstructionsFormGroup(
    lastserviceinstructions: LastserviceinstructionsFormGroupInput = { id: null },
  ): LastserviceinstructionsFormGroup {
    const lastserviceinstructionsRawValue = this.convertLastserviceinstructionsToLastserviceinstructionsRawValue({
      ...this.getFormDefaults(),
      ...lastserviceinstructions,
    });
    return new FormGroup<LastserviceinstructionsFormGroupContent>({
      id: new FormControl(
        { value: lastserviceinstructionsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      jobid: new FormControl(lastserviceinstructionsRawValue.jobid),
      instruction: new FormControl(lastserviceinstructionsRawValue.instruction),
      isactive: new FormControl(lastserviceinstructionsRawValue.isactive),
      lmu: new FormControl(lastserviceinstructionsRawValue.lmu),
      lmd: new FormControl(lastserviceinstructionsRawValue.lmd),
      ignore: new FormControl(lastserviceinstructionsRawValue.ignore),
      vehicleid: new FormControl(lastserviceinstructionsRawValue.vehicleid),
      vehicleno: new FormControl(lastserviceinstructionsRawValue.vehicleno),
    });
  }

  getLastserviceinstructions(form: LastserviceinstructionsFormGroup): ILastserviceinstructions | NewLastserviceinstructions {
    return this.convertLastserviceinstructionsRawValueToLastserviceinstructions(
      form.getRawValue() as LastserviceinstructionsFormRawValue | NewLastserviceinstructionsFormRawValue,
    );
  }

  resetForm(form: LastserviceinstructionsFormGroup, lastserviceinstructions: LastserviceinstructionsFormGroupInput): void {
    const lastserviceinstructionsRawValue = this.convertLastserviceinstructionsToLastserviceinstructionsRawValue({
      ...this.getFormDefaults(),
      ...lastserviceinstructions,
    });
    form.reset(
      {
        ...lastserviceinstructionsRawValue,
        id: { value: lastserviceinstructionsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): LastserviceinstructionsFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      isactive: false,
      lmd: currentTime,
      ignore: false,
    };
  }

  private convertLastserviceinstructionsRawValueToLastserviceinstructions(
    rawLastserviceinstructions: LastserviceinstructionsFormRawValue | NewLastserviceinstructionsFormRawValue,
  ): ILastserviceinstructions | NewLastserviceinstructions {
    return {
      ...rawLastserviceinstructions,
      lmd: dayjs(rawLastserviceinstructions.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertLastserviceinstructionsToLastserviceinstructionsRawValue(
    lastserviceinstructions: ILastserviceinstructions | (Partial<NewLastserviceinstructions> & LastserviceinstructionsFormDefaults),
  ): LastserviceinstructionsFormRawValue | PartialWithRequiredKeyOf<NewLastserviceinstructionsFormRawValue> {
    return {
      ...lastserviceinstructions,
      lmd: lastserviceinstructions.lmd ? lastserviceinstructions.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
