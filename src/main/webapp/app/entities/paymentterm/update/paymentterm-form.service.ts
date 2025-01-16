import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IPaymentterm, NewPaymentterm } from '../paymentterm.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPaymentterm for edit and NewPaymenttermFormGroupInput for create.
 */
type PaymenttermFormGroupInput = IPaymentterm | PartialWithRequiredKeyOf<NewPaymentterm>;

type PaymenttermFormDefaults = Pick<NewPaymentterm, 'id' | 'forvoucher'>;

type PaymenttermFormGroupContent = {
  id: FormControl<IPaymentterm['id'] | NewPaymentterm['id']>;
  paymenttype: FormControl<IPaymentterm['paymenttype']>;
  forvoucher: FormControl<IPaymentterm['forvoucher']>;
};

export type PaymenttermFormGroup = FormGroup<PaymenttermFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PaymenttermFormService {
  createPaymenttermFormGroup(paymentterm: PaymenttermFormGroupInput = { id: null }): PaymenttermFormGroup {
    const paymenttermRawValue = {
      ...this.getFormDefaults(),
      ...paymentterm,
    };
    return new FormGroup<PaymenttermFormGroupContent>({
      id: new FormControl(
        { value: paymenttermRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      paymenttype: new FormControl(paymenttermRawValue.paymenttype),
      forvoucher: new FormControl(paymenttermRawValue.forvoucher),
    });
  }

  getPaymentterm(form: PaymenttermFormGroup): IPaymentterm | NewPaymentterm {
    return form.getRawValue() as IPaymentterm | NewPaymentterm;
  }

  resetForm(form: PaymenttermFormGroup, paymentterm: PaymenttermFormGroupInput): void {
    const paymenttermRawValue = { ...this.getFormDefaults(), ...paymentterm };
    form.reset(
      {
        ...paymenttermRawValue,
        id: { value: paymenttermRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): PaymenttermFormDefaults {
    return {
      id: null,
      forvoucher: false,
    };
  }
}
