import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IBillingserviceoption, NewBillingserviceoption } from '../billingserviceoption.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IBillingserviceoption for edit and NewBillingserviceoptionFormGroupInput for create.
 */
type BillingserviceoptionFormGroupInput = IBillingserviceoption | PartialWithRequiredKeyOf<NewBillingserviceoption>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IBillingserviceoption | NewBillingserviceoption> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type BillingserviceoptionFormRawValue = FormValueOf<IBillingserviceoption>;

type NewBillingserviceoptionFormRawValue = FormValueOf<NewBillingserviceoption>;

type BillingserviceoptionFormDefaults = Pick<NewBillingserviceoption, 'id' | 'isactive' | 'lmd' | 'billtocustomer'>;

type BillingserviceoptionFormGroupContent = {
  id: FormControl<BillingserviceoptionFormRawValue['id'] | NewBillingserviceoption['id']>;
  servicename: FormControl<BillingserviceoptionFormRawValue['servicename']>;
  servicediscription: FormControl<BillingserviceoptionFormRawValue['servicediscription']>;
  isactive: FormControl<BillingserviceoptionFormRawValue['isactive']>;
  lmd: FormControl<BillingserviceoptionFormRawValue['lmd']>;
  lmu: FormControl<BillingserviceoptionFormRawValue['lmu']>;
  orderby: FormControl<BillingserviceoptionFormRawValue['orderby']>;
  billtocustomer: FormControl<BillingserviceoptionFormRawValue['billtocustomer']>;
};

export type BillingserviceoptionFormGroup = FormGroup<BillingserviceoptionFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class BillingserviceoptionFormService {
  createBillingserviceoptionFormGroup(
    billingserviceoption: BillingserviceoptionFormGroupInput = { id: null },
  ): BillingserviceoptionFormGroup {
    const billingserviceoptionRawValue = this.convertBillingserviceoptionToBillingserviceoptionRawValue({
      ...this.getFormDefaults(),
      ...billingserviceoption,
    });
    return new FormGroup<BillingserviceoptionFormGroupContent>({
      id: new FormControl(
        { value: billingserviceoptionRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      servicename: new FormControl(billingserviceoptionRawValue.servicename),
      servicediscription: new FormControl(billingserviceoptionRawValue.servicediscription),
      isactive: new FormControl(billingserviceoptionRawValue.isactive),
      lmd: new FormControl(billingserviceoptionRawValue.lmd),
      lmu: new FormControl(billingserviceoptionRawValue.lmu),
      orderby: new FormControl(billingserviceoptionRawValue.orderby),
      billtocustomer: new FormControl(billingserviceoptionRawValue.billtocustomer),
    });
  }

  getBillingserviceoption(form: BillingserviceoptionFormGroup): IBillingserviceoption | NewBillingserviceoption {
    return this.convertBillingserviceoptionRawValueToBillingserviceoption(
      form.getRawValue() as BillingserviceoptionFormRawValue | NewBillingserviceoptionFormRawValue,
    );
  }

  resetForm(form: BillingserviceoptionFormGroup, billingserviceoption: BillingserviceoptionFormGroupInput): void {
    const billingserviceoptionRawValue = this.convertBillingserviceoptionToBillingserviceoptionRawValue({
      ...this.getFormDefaults(),
      ...billingserviceoption,
    });
    form.reset(
      {
        ...billingserviceoptionRawValue,
        id: { value: billingserviceoptionRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): BillingserviceoptionFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      isactive: false,
      lmd: currentTime,
      billtocustomer: false,
    };
  }

  private convertBillingserviceoptionRawValueToBillingserviceoption(
    rawBillingserviceoption: BillingserviceoptionFormRawValue | NewBillingserviceoptionFormRawValue,
  ): IBillingserviceoption | NewBillingserviceoption {
    return {
      ...rawBillingserviceoption,
      lmd: dayjs(rawBillingserviceoption.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertBillingserviceoptionToBillingserviceoptionRawValue(
    billingserviceoption: IBillingserviceoption | (Partial<NewBillingserviceoption> & BillingserviceoptionFormDefaults),
  ): BillingserviceoptionFormRawValue | PartialWithRequiredKeyOf<NewBillingserviceoptionFormRawValue> {
    return {
      ...billingserviceoption,
      lmd: billingserviceoption.lmd ? billingserviceoption.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
