import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ITaxes, NewTaxes } from '../taxes.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITaxes for edit and NewTaxesFormGroupInput for create.
 */
type TaxesFormGroupInput = ITaxes | PartialWithRequiredKeyOf<NewTaxes>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ITaxes | NewTaxes> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type TaxesFormRawValue = FormValueOf<ITaxes>;

type NewTaxesFormRawValue = FormValueOf<NewTaxes>;

type TaxesFormDefaults = Pick<NewTaxes, 'id' | 'ismanual' | 'isactive' | 'lmd'>;

type TaxesFormGroupContent = {
  id: FormControl<TaxesFormRawValue['id'] | NewTaxes['id']>;
  code: FormControl<TaxesFormRawValue['code']>;
  description: FormControl<TaxesFormRawValue['description']>;
  effectivefrom: FormControl<TaxesFormRawValue['effectivefrom']>;
  effectiveto: FormControl<TaxesFormRawValue['effectiveto']>;
  percentage: FormControl<TaxesFormRawValue['percentage']>;
  fixedamount: FormControl<TaxesFormRawValue['fixedamount']>;
  ismanual: FormControl<TaxesFormRawValue['ismanual']>;
  isactive: FormControl<TaxesFormRawValue['isactive']>;
  lmu: FormControl<TaxesFormRawValue['lmu']>;
  lmd: FormControl<TaxesFormRawValue['lmd']>;
};

export type TaxesFormGroup = FormGroup<TaxesFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TaxesFormService {
  createTaxesFormGroup(taxes: TaxesFormGroupInput = { id: null }): TaxesFormGroup {
    const taxesRawValue = this.convertTaxesToTaxesRawValue({
      ...this.getFormDefaults(),
      ...taxes,
    });
    return new FormGroup<TaxesFormGroupContent>({
      id: new FormControl(
        { value: taxesRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      code: new FormControl(taxesRawValue.code),
      description: new FormControl(taxesRawValue.description),
      effectivefrom: new FormControl(taxesRawValue.effectivefrom),
      effectiveto: new FormControl(taxesRawValue.effectiveto),
      percentage: new FormControl(taxesRawValue.percentage),
      fixedamount: new FormControl(taxesRawValue.fixedamount),
      ismanual: new FormControl(taxesRawValue.ismanual),
      isactive: new FormControl(taxesRawValue.isactive),
      lmu: new FormControl(taxesRawValue.lmu),
      lmd: new FormControl(taxesRawValue.lmd),
    });
  }

  getTaxes(form: TaxesFormGroup): ITaxes | NewTaxes {
    return this.convertTaxesRawValueToTaxes(form.getRawValue() as TaxesFormRawValue | NewTaxesFormRawValue);
  }

  resetForm(form: TaxesFormGroup, taxes: TaxesFormGroupInput): void {
    const taxesRawValue = this.convertTaxesToTaxesRawValue({ ...this.getFormDefaults(), ...taxes });
    form.reset(
      {
        ...taxesRawValue,
        id: { value: taxesRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): TaxesFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      ismanual: false,
      isactive: false,
      lmd: currentTime,
    };
  }

  private convertTaxesRawValueToTaxes(rawTaxes: TaxesFormRawValue | NewTaxesFormRawValue): ITaxes | NewTaxes {
    return {
      ...rawTaxes,
      lmd: dayjs(rawTaxes.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertTaxesToTaxesRawValue(
    taxes: ITaxes | (Partial<NewTaxes> & TaxesFormDefaults),
  ): TaxesFormRawValue | PartialWithRequiredKeyOf<NewTaxesFormRawValue> {
    return {
      ...taxes,
      lmd: taxes.lmd ? taxes.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
