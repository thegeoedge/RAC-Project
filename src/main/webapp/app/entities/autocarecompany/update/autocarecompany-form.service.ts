import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAutocarecompany, NewAutocarecompany } from '../autocarecompany.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAutocarecompany for edit and NewAutocarecompanyFormGroupInput for create.
 */
type AutocarecompanyFormGroupInput = IAutocarecompany | PartialWithRequiredKeyOf<NewAutocarecompany>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IAutocarecompany | NewAutocarecompany> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type AutocarecompanyFormRawValue = FormValueOf<IAutocarecompany>;

type NewAutocarecompanyFormRawValue = FormValueOf<NewAutocarecompany>;

type AutocarecompanyFormDefaults = Pick<NewAutocarecompany, 'id' | 'lmd'>;

type AutocarecompanyFormGroupContent = {
  id: FormControl<AutocarecompanyFormRawValue['id'] | NewAutocarecompany['id']>;
  name: FormControl<AutocarecompanyFormRawValue['name']>;
  address: FormControl<AutocarecompanyFormRawValue['address']>;
  servicephone: FormControl<AutocarecompanyFormRawValue['servicephone']>;
  sparepartphone: FormControl<AutocarecompanyFormRawValue['sparepartphone']>;
  bodypaint: FormControl<AutocarecompanyFormRawValue['bodypaint']>;
  generalphone: FormControl<AutocarecompanyFormRawValue['generalphone']>;
  fax: FormControl<AutocarecompanyFormRawValue['fax']>;
  email: FormControl<AutocarecompanyFormRawValue['email']>;
  description: FormControl<AutocarecompanyFormRawValue['description']>;
  lmu: FormControl<AutocarecompanyFormRawValue['lmu']>;
  lmd: FormControl<AutocarecompanyFormRawValue['lmd']>;
  vatregnumber: FormControl<AutocarecompanyFormRawValue['vatregnumber']>;
  tinnumber: FormControl<AutocarecompanyFormRawValue['tinnumber']>;
  accountcode: FormControl<AutocarecompanyFormRawValue['accountcode']>;
  accountid: FormControl<AutocarecompanyFormRawValue['accountid']>;
};

export type AutocarecompanyFormGroup = FormGroup<AutocarecompanyFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AutocarecompanyFormService {
  createAutocarecompanyFormGroup(autocarecompany: AutocarecompanyFormGroupInput = { id: null }): AutocarecompanyFormGroup {
    const autocarecompanyRawValue = this.convertAutocarecompanyToAutocarecompanyRawValue({
      ...this.getFormDefaults(),
      ...autocarecompany,
    });
    return new FormGroup<AutocarecompanyFormGroupContent>({
      id: new FormControl(
        { value: autocarecompanyRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      name: new FormControl(autocarecompanyRawValue.name),
      address: new FormControl(autocarecompanyRawValue.address),
      servicephone: new FormControl(autocarecompanyRawValue.servicephone),
      sparepartphone: new FormControl(autocarecompanyRawValue.sparepartphone),
      bodypaint: new FormControl(autocarecompanyRawValue.bodypaint),
      generalphone: new FormControl(autocarecompanyRawValue.generalphone),
      fax: new FormControl(autocarecompanyRawValue.fax),
      email: new FormControl(autocarecompanyRawValue.email),
      description: new FormControl(autocarecompanyRawValue.description),
      lmu: new FormControl(autocarecompanyRawValue.lmu),
      lmd: new FormControl(autocarecompanyRawValue.lmd),
      vatregnumber: new FormControl(autocarecompanyRawValue.vatregnumber),
      tinnumber: new FormControl(autocarecompanyRawValue.tinnumber),
      accountcode: new FormControl(autocarecompanyRawValue.accountcode),
      accountid: new FormControl(autocarecompanyRawValue.accountid),
    });
  }

  getAutocarecompany(form: AutocarecompanyFormGroup): IAutocarecompany | NewAutocarecompany {
    return this.convertAutocarecompanyRawValueToAutocarecompany(
      form.getRawValue() as AutocarecompanyFormRawValue | NewAutocarecompanyFormRawValue,
    );
  }

  resetForm(form: AutocarecompanyFormGroup, autocarecompany: AutocarecompanyFormGroupInput): void {
    const autocarecompanyRawValue = this.convertAutocarecompanyToAutocarecompanyRawValue({ ...this.getFormDefaults(), ...autocarecompany });
    form.reset(
      {
        ...autocarecompanyRawValue,
        id: { value: autocarecompanyRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): AutocarecompanyFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lmd: currentTime,
    };
  }

  private convertAutocarecompanyRawValueToAutocarecompany(
    rawAutocarecompany: AutocarecompanyFormRawValue | NewAutocarecompanyFormRawValue,
  ): IAutocarecompany | NewAutocarecompany {
    return {
      ...rawAutocarecompany,
      lmd: dayjs(rawAutocarecompany.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertAutocarecompanyToAutocarecompanyRawValue(
    autocarecompany: IAutocarecompany | (Partial<NewAutocarecompany> & AutocarecompanyFormDefaults),
  ): AutocarecompanyFormRawValue | PartialWithRequiredKeyOf<NewAutocarecompanyFormRawValue> {
    return {
      ...autocarecompany,
      lmd: autocarecompany.lmd ? autocarecompany.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
