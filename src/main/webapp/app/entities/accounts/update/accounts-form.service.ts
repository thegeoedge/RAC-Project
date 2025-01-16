import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAccounts, NewAccounts } from '../accounts.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAccounts for edit and NewAccountsFormGroupInput for create.
 */
type AccountsFormGroupInput = IAccounts | PartialWithRequiredKeyOf<NewAccounts>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IAccounts | NewAccounts> = Omit<T, 'date' | 'lmd'> & {
  date?: string | null;
  lmd?: string | null;
};

type AccountsFormRawValue = FormValueOf<IAccounts>;

type NewAccountsFormRawValue = FormValueOf<NewAccounts>;

type AccountsFormDefaults = Pick<NewAccounts, 'id' | 'date' | 'lmd' | 'hasbatches' | 'canedit'>;

type AccountsFormGroupContent = {
  id: FormControl<AccountsFormRawValue['id'] | NewAccounts['id']>;
  code: FormControl<AccountsFormRawValue['code']>;
  date: FormControl<AccountsFormRawValue['date']>;
  name: FormControl<AccountsFormRawValue['name']>;
  description: FormControl<AccountsFormRawValue['description']>;
  type: FormControl<AccountsFormRawValue['type']>;
  parent: FormControl<AccountsFormRawValue['parent']>;
  balance: FormControl<AccountsFormRawValue['balance']>;
  lmu: FormControl<AccountsFormRawValue['lmu']>;
  lmd: FormControl<AccountsFormRawValue['lmd']>;
  hasbatches: FormControl<AccountsFormRawValue['hasbatches']>;
  accountvalue: FormControl<AccountsFormRawValue['accountvalue']>;
  accountlevel: FormControl<AccountsFormRawValue['accountlevel']>;
  accountsnumberingsystem: FormControl<AccountsFormRawValue['accountsnumberingsystem']>;
  subparentid: FormControl<AccountsFormRawValue['subparentid']>;
  canedit: FormControl<AccountsFormRawValue['canedit']>;
  amount: FormControl<AccountsFormRawValue['amount']>;
  creditamount: FormControl<AccountsFormRawValue['creditamount']>;
  debitamount: FormControl<AccountsFormRawValue['debitamount']>;
  debitorcredit: FormControl<AccountsFormRawValue['debitorcredit']>;
  reporttype: FormControl<AccountsFormRawValue['reporttype']>;
};

export type AccountsFormGroup = FormGroup<AccountsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AccountsFormService {
  createAccountsFormGroup(accounts: AccountsFormGroupInput = { id: null }): AccountsFormGroup {
    const accountsRawValue = this.convertAccountsToAccountsRawValue({
      ...this.getFormDefaults(),
      ...accounts,
    });
    return new FormGroup<AccountsFormGroupContent>({
      id: new FormControl(
        { value: accountsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      code: new FormControl(accountsRawValue.code),
      date: new FormControl(accountsRawValue.date),
      name: new FormControl(accountsRawValue.name),
      description: new FormControl(accountsRawValue.description),
      type: new FormControl(accountsRawValue.type),
      parent: new FormControl(accountsRawValue.parent),
      balance: new FormControl(accountsRawValue.balance),
      lmu: new FormControl(accountsRawValue.lmu),
      lmd: new FormControl(accountsRawValue.lmd),
      hasbatches: new FormControl(accountsRawValue.hasbatches),
      accountvalue: new FormControl(accountsRawValue.accountvalue),
      accountlevel: new FormControl(accountsRawValue.accountlevel),
      accountsnumberingsystem: new FormControl(accountsRawValue.accountsnumberingsystem),
      subparentid: new FormControl(accountsRawValue.subparentid),
      canedit: new FormControl(accountsRawValue.canedit),
      amount: new FormControl(accountsRawValue.amount),
      creditamount: new FormControl(accountsRawValue.creditamount),
      debitamount: new FormControl(accountsRawValue.debitamount),
      debitorcredit: new FormControl(accountsRawValue.debitorcredit),
      reporttype: new FormControl(accountsRawValue.reporttype),
    });
  }

  getAccounts(form: AccountsFormGroup): IAccounts | NewAccounts {
    return this.convertAccountsRawValueToAccounts(form.getRawValue() as AccountsFormRawValue | NewAccountsFormRawValue);
  }

  resetForm(form: AccountsFormGroup, accounts: AccountsFormGroupInput): void {
    const accountsRawValue = this.convertAccountsToAccountsRawValue({ ...this.getFormDefaults(), ...accounts });
    form.reset(
      {
        ...accountsRawValue,
        id: { value: accountsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AccountsFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      date: currentTime,
      lmd: currentTime,
      hasbatches: false,
      canedit: false,
    };
  }

  private convertAccountsRawValueToAccounts(rawAccounts: AccountsFormRawValue | NewAccountsFormRawValue): IAccounts | NewAccounts {
    return {
      ...rawAccounts,
      date: dayjs(rawAccounts.date, DATE_TIME_FORMAT),
      lmd: dayjs(rawAccounts.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertAccountsToAccountsRawValue(
    accounts: IAccounts | (Partial<NewAccounts> & AccountsFormDefaults),
  ): AccountsFormRawValue | PartialWithRequiredKeyOf<NewAccountsFormRawValue> {
    return {
      ...accounts,
      date: accounts.date ? accounts.date.format(DATE_TIME_FORMAT) : undefined,
      lmd: accounts.lmd ? accounts.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
