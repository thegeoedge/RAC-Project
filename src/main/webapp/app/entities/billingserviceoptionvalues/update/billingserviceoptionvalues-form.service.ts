import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IBillingserviceoptionvalues, NewBillingserviceoptionvalues } from '../billingserviceoptionvalues.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IBillingserviceoptionvalues for edit and NewBillingserviceoptionvaluesFormGroupInput for create.
 */
type BillingserviceoptionvaluesFormGroupInput = IBillingserviceoptionvalues | PartialWithRequiredKeyOf<NewBillingserviceoptionvalues>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IBillingserviceoptionvalues | NewBillingserviceoptionvalues> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type BillingserviceoptionvaluesFormRawValue = FormValueOf<IBillingserviceoptionvalues>;

type NewBillingserviceoptionvaluesFormRawValue = FormValueOf<NewBillingserviceoptionvalues>;

type BillingserviceoptionvaluesFormDefaults = Pick<NewBillingserviceoptionvalues, 'id' | 'lmd'>;

type BillingserviceoptionvaluesFormGroupContent = {
  id: FormControl<BillingserviceoptionvaluesFormRawValue['id'] | NewBillingserviceoptionvalues['id']>;
  vehicletypeid: FormControl<BillingserviceoptionvaluesFormRawValue['vehicletypeid']>;
  billingserviceoptionid: FormControl<BillingserviceoptionvaluesFormRawValue['billingserviceoptionid']>;
  value: FormControl<BillingserviceoptionvaluesFormRawValue['value']>;
  lmd: FormControl<BillingserviceoptionvaluesFormRawValue['lmd']>;
  lmu: FormControl<BillingserviceoptionvaluesFormRawValue['lmu']>;
};

export type BillingserviceoptionvaluesFormGroup = FormGroup<BillingserviceoptionvaluesFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class BillingserviceoptionvaluesFormService {
  createBillingserviceoptionvaluesFormGroup(
    billingserviceoptionvalues: BillingserviceoptionvaluesFormGroupInput = { id: null },
  ): BillingserviceoptionvaluesFormGroup {
    const billingserviceoptionvaluesRawValue = this.convertBillingserviceoptionvaluesToBillingserviceoptionvaluesRawValue({
      ...this.getFormDefaults(),
      ...billingserviceoptionvalues,
    });
    return new FormGroup<BillingserviceoptionvaluesFormGroupContent>({
      id: new FormControl(
        { value: billingserviceoptionvaluesRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      vehicletypeid: new FormControl(billingserviceoptionvaluesRawValue.vehicletypeid),
      billingserviceoptionid: new FormControl(billingserviceoptionvaluesRawValue.billingserviceoptionid),
      value: new FormControl(billingserviceoptionvaluesRawValue.value),
      lmd: new FormControl(billingserviceoptionvaluesRawValue.lmd),
      lmu: new FormControl(billingserviceoptionvaluesRawValue.lmu),
    });
  }

  getBillingserviceoptionvalues(form: BillingserviceoptionvaluesFormGroup): IBillingserviceoptionvalues | NewBillingserviceoptionvalues {
    return this.convertBillingserviceoptionvaluesRawValueToBillingserviceoptionvalues(
      form.getRawValue() as BillingserviceoptionvaluesFormRawValue | NewBillingserviceoptionvaluesFormRawValue,
    );
  }

  resetForm(form: BillingserviceoptionvaluesFormGroup, billingserviceoptionvalues: BillingserviceoptionvaluesFormGroupInput): void {
    const billingserviceoptionvaluesRawValue = this.convertBillingserviceoptionvaluesToBillingserviceoptionvaluesRawValue({
      ...this.getFormDefaults(),
      ...billingserviceoptionvalues,
    });
    form.reset(
      {
        ...billingserviceoptionvaluesRawValue,
        id: { value: billingserviceoptionvaluesRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): BillingserviceoptionvaluesFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lmd: currentTime,
    };
  }

  private convertBillingserviceoptionvaluesRawValueToBillingserviceoptionvalues(
    rawBillingserviceoptionvalues: BillingserviceoptionvaluesFormRawValue | NewBillingserviceoptionvaluesFormRawValue,
  ): IBillingserviceoptionvalues | NewBillingserviceoptionvalues {
    return {
      ...rawBillingserviceoptionvalues,
      lmd: dayjs(rawBillingserviceoptionvalues.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertBillingserviceoptionvaluesToBillingserviceoptionvaluesRawValue(
    billingserviceoptionvalues:
      | IBillingserviceoptionvalues
      | (Partial<NewBillingserviceoptionvalues> & BillingserviceoptionvaluesFormDefaults),
  ): BillingserviceoptionvaluesFormRawValue | PartialWithRequiredKeyOf<NewBillingserviceoptionvaluesFormRawValue> {
    return {
      ...billingserviceoptionvalues,
      lmd: billingserviceoptionvalues.lmd ? billingserviceoptionvalues.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
