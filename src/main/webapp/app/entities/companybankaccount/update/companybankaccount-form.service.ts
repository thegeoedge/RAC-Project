import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ICompanybankaccount, NewCompanybankaccount } from '../companybankaccount.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICompanybankaccount for edit and NewCompanybankaccountFormGroupInput for create.
 */
type CompanybankaccountFormGroupInput = ICompanybankaccount | PartialWithRequiredKeyOf<NewCompanybankaccount>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ICompanybankaccount | NewCompanybankaccount> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type CompanybankaccountFormRawValue = FormValueOf<ICompanybankaccount>;

type NewCompanybankaccountFormRawValue = FormValueOf<NewCompanybankaccount>;

type CompanybankaccountFormDefaults = Pick<NewCompanybankaccount, 'id' | 'lmd' | 'isactive'>;

type CompanybankaccountFormGroupContent = {
  id: FormControl<CompanybankaccountFormRawValue['id'] | NewCompanybankaccount['id']>;
  companyid: FormControl<CompanybankaccountFormRawValue['companyid']>;
  accountnumber: FormControl<CompanybankaccountFormRawValue['accountnumber']>;
  accountname: FormControl<CompanybankaccountFormRawValue['accountname']>;
  bankname: FormControl<CompanybankaccountFormRawValue['bankname']>;
  bankid: FormControl<CompanybankaccountFormRawValue['bankid']>;
  branchname: FormControl<CompanybankaccountFormRawValue['branchname']>;
  branchid: FormControl<CompanybankaccountFormRawValue['branchid']>;
  amount: FormControl<CompanybankaccountFormRawValue['amount']>;
  accountcode: FormControl<CompanybankaccountFormRawValue['accountcode']>;
  accountid: FormControl<CompanybankaccountFormRawValue['accountid']>;
  lmd: FormControl<CompanybankaccountFormRawValue['lmd']>;
  lmu: FormControl<CompanybankaccountFormRawValue['lmu']>;
  isactive: FormControl<CompanybankaccountFormRawValue['isactive']>;
  accounttypeid: FormControl<CompanybankaccountFormRawValue['accounttypeid']>;
};

export type CompanybankaccountFormGroup = FormGroup<CompanybankaccountFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CompanybankaccountFormService {
  createCompanybankaccountFormGroup(companybankaccount: CompanybankaccountFormGroupInput = { id: null }): CompanybankaccountFormGroup {
    const companybankaccountRawValue = this.convertCompanybankaccountToCompanybankaccountRawValue({
      ...this.getFormDefaults(),
      ...companybankaccount,
    });
    return new FormGroup<CompanybankaccountFormGroupContent>({
      id: new FormControl(
        { value: companybankaccountRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      companyid: new FormControl(companybankaccountRawValue.companyid),
      accountnumber: new FormControl(companybankaccountRawValue.accountnumber),
      accountname: new FormControl(companybankaccountRawValue.accountname),
      bankname: new FormControl(companybankaccountRawValue.bankname),
      bankid: new FormControl(companybankaccountRawValue.bankid),
      branchname: new FormControl(companybankaccountRawValue.branchname),
      branchid: new FormControl(companybankaccountRawValue.branchid),
      amount: new FormControl(companybankaccountRawValue.amount),
      accountcode: new FormControl(companybankaccountRawValue.accountcode),
      accountid: new FormControl(companybankaccountRawValue.accountid),
      lmd: new FormControl(companybankaccountRawValue.lmd),
      lmu: new FormControl(companybankaccountRawValue.lmu),
      isactive: new FormControl(companybankaccountRawValue.isactive),
      accounttypeid: new FormControl(companybankaccountRawValue.accounttypeid),
    });
  }

  getCompanybankaccount(form: CompanybankaccountFormGroup): ICompanybankaccount | NewCompanybankaccount {
    return this.convertCompanybankaccountRawValueToCompanybankaccount(
      form.getRawValue() as CompanybankaccountFormRawValue | NewCompanybankaccountFormRawValue,
    );
  }

  resetForm(form: CompanybankaccountFormGroup, companybankaccount: CompanybankaccountFormGroupInput): void {
    const companybankaccountRawValue = this.convertCompanybankaccountToCompanybankaccountRawValue({
      ...this.getFormDefaults(),
      ...companybankaccount,
    });
    form.reset(
      {
        ...companybankaccountRawValue,
        id: { value: companybankaccountRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CompanybankaccountFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lmd: currentTime,
      isactive: false,
    };
  }

  private convertCompanybankaccountRawValueToCompanybankaccount(
    rawCompanybankaccount: CompanybankaccountFormRawValue | NewCompanybankaccountFormRawValue,
  ): ICompanybankaccount | NewCompanybankaccount {
    return {
      ...rawCompanybankaccount,
      lmd: dayjs(rawCompanybankaccount.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertCompanybankaccountToCompanybankaccountRawValue(
    companybankaccount: ICompanybankaccount | (Partial<NewCompanybankaccount> & CompanybankaccountFormDefaults),
  ): CompanybankaccountFormRawValue | PartialWithRequiredKeyOf<NewCompanybankaccountFormRawValue> {
    return {
      ...companybankaccount,
      lmd: companybankaccount.lmd ? companybankaccount.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
