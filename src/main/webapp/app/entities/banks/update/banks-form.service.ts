import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IBanks, NewBanks } from '../banks.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IBanks for edit and NewBanksFormGroupInput for create.
 */
type BanksFormGroupInput = IBanks | PartialWithRequiredKeyOf<NewBanks>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IBanks | NewBanks> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type BanksFormRawValue = FormValueOf<IBanks>;

type NewBanksFormRawValue = FormValueOf<NewBanks>;

type BanksFormDefaults = Pick<NewBanks, 'id' | 'lmd'>;

type BanksFormGroupContent = {
  id: FormControl<BanksFormRawValue['id'] | NewBanks['id']>;
  code: FormControl<BanksFormRawValue['code']>;
  name: FormControl<BanksFormRawValue['name']>;
  description: FormControl<BanksFormRawValue['description']>;
  lmu: FormControl<BanksFormRawValue['lmu']>;
  lmd: FormControl<BanksFormRawValue['lmd']>;
};

export type BanksFormGroup = FormGroup<BanksFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class BanksFormService {
  createBanksFormGroup(banks: BanksFormGroupInput = { id: null }): BanksFormGroup {
    const banksRawValue = this.convertBanksToBanksRawValue({
      ...this.getFormDefaults(),
      ...banks,
    });
    return new FormGroup<BanksFormGroupContent>({
      id: new FormControl(
        { value: banksRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      code: new FormControl(banksRawValue.code),
      name: new FormControl(banksRawValue.name),
      description: new FormControl(banksRawValue.description),
      lmu: new FormControl(banksRawValue.lmu),
      lmd: new FormControl(banksRawValue.lmd),
    });
  }

  getBanks(form: BanksFormGroup): IBanks | NewBanks {
    return this.convertBanksRawValueToBanks(form.getRawValue() as BanksFormRawValue | NewBanksFormRawValue);
  }

  resetForm(form: BanksFormGroup, banks: BanksFormGroupInput): void {
    const banksRawValue = this.convertBanksToBanksRawValue({ ...this.getFormDefaults(), ...banks });
    form.reset(
      {
        ...banksRawValue,
        id: { value: banksRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): BanksFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lmd: currentTime,
    };
  }

  private convertBanksRawValueToBanks(rawBanks: BanksFormRawValue | NewBanksFormRawValue): IBanks | NewBanks {
    return {
      ...rawBanks,
      lmd: dayjs(rawBanks.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertBanksToBanksRawValue(
    banks: IBanks | (Partial<NewBanks> & BanksFormDefaults),
  ): BanksFormRawValue | PartialWithRequiredKeyOf<NewBanksFormRawValue> {
    return {
      ...banks,
      lmd: banks.lmd ? banks.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
