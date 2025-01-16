import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IBankbranch, NewBankbranch } from '../bankbranch.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IBankbranch for edit and NewBankbranchFormGroupInput for create.
 */
type BankbranchFormGroupInput = IBankbranch | PartialWithRequiredKeyOf<NewBankbranch>;

type BankbranchFormDefaults = Pick<NewBankbranch, 'id'>;

type BankbranchFormGroupContent = {
  id: FormControl<IBankbranch['id'] | NewBankbranch['id']>;
  bankcode: FormControl<IBankbranch['bankcode']>;
  branchcode: FormControl<IBankbranch['branchcode']>;
  branchname: FormControl<IBankbranch['branchname']>;
};

export type BankbranchFormGroup = FormGroup<BankbranchFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class BankbranchFormService {
  createBankbranchFormGroup(bankbranch: BankbranchFormGroupInput = { id: null }): BankbranchFormGroup {
    const bankbranchRawValue = {
      ...this.getFormDefaults(),
      ...bankbranch,
    };
    return new FormGroup<BankbranchFormGroupContent>({
      id: new FormControl(
        { value: bankbranchRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      bankcode: new FormControl(bankbranchRawValue.bankcode),
      branchcode: new FormControl(bankbranchRawValue.branchcode),
      branchname: new FormControl(bankbranchRawValue.branchname),
    });
  }

  getBankbranch(form: BankbranchFormGroup): IBankbranch | NewBankbranch {
    return form.getRawValue() as IBankbranch | NewBankbranch;
  }

  resetForm(form: BankbranchFormGroup, bankbranch: BankbranchFormGroupInput): void {
    const bankbranchRawValue = { ...this.getFormDefaults(), ...bankbranch };
    form.reset(
      {
        ...bankbranchRawValue,
        id: { value: bankbranchRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): BankbranchFormDefaults {
    return {
      id: null,
    };
  }
}
