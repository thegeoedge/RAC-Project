import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IVehiclebrandname, NewVehiclebrandname } from '../vehiclebrandname.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IVehiclebrandname for edit and NewVehiclebrandnameFormGroupInput for create.
 */
type VehiclebrandnameFormGroupInput = IVehiclebrandname | PartialWithRequiredKeyOf<NewVehiclebrandname>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IVehiclebrandname | NewVehiclebrandname> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type VehiclebrandnameFormRawValue = FormValueOf<IVehiclebrandname>;

type NewVehiclebrandnameFormRawValue = FormValueOf<NewVehiclebrandname>;

type VehiclebrandnameFormDefaults = Pick<NewVehiclebrandname, 'id' | 'lmd'>;

type VehiclebrandnameFormGroupContent = {
  id: FormControl<VehiclebrandnameFormRawValue['id'] | NewVehiclebrandname['id']>;
  brandname: FormControl<VehiclebrandnameFormRawValue['brandname']>;
  description: FormControl<VehiclebrandnameFormRawValue['description']>;
  lmu: FormControl<VehiclebrandnameFormRawValue['lmu']>;
  lmd: FormControl<VehiclebrandnameFormRawValue['lmd']>;
  companyid: FormControl<VehiclebrandnameFormRawValue['companyid']>;
};

export type VehiclebrandnameFormGroup = FormGroup<VehiclebrandnameFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class VehiclebrandnameFormService {
  createVehiclebrandnameFormGroup(vehiclebrandname: VehiclebrandnameFormGroupInput = { id: null }): VehiclebrandnameFormGroup {
    const vehiclebrandnameRawValue = this.convertVehiclebrandnameToVehiclebrandnameRawValue({
      ...this.getFormDefaults(),
      ...vehiclebrandname,
    });
    return new FormGroup<VehiclebrandnameFormGroupContent>({
      id: new FormControl(
        { value: vehiclebrandnameRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      brandname: new FormControl(vehiclebrandnameRawValue.brandname),
      description: new FormControl(vehiclebrandnameRawValue.description),
      lmu: new FormControl(vehiclebrandnameRawValue.lmu),
      lmd: new FormControl(vehiclebrandnameRawValue.lmd),
      companyid: new FormControl(vehiclebrandnameRawValue.companyid),
    });
  }

  getVehiclebrandname(form: VehiclebrandnameFormGroup): IVehiclebrandname | NewVehiclebrandname {
    return this.convertVehiclebrandnameRawValueToVehiclebrandname(
      form.getRawValue() as VehiclebrandnameFormRawValue | NewVehiclebrandnameFormRawValue,
    );
  }

  resetForm(form: VehiclebrandnameFormGroup, vehiclebrandname: VehiclebrandnameFormGroupInput): void {
    const vehiclebrandnameRawValue = this.convertVehiclebrandnameToVehiclebrandnameRawValue({
      ...this.getFormDefaults(),
      ...vehiclebrandname,
    });
    form.reset(
      {
        ...vehiclebrandnameRawValue,
        id: { value: vehiclebrandnameRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): VehiclebrandnameFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lmd: currentTime,
    };
  }

  private convertVehiclebrandnameRawValueToVehiclebrandname(
    rawVehiclebrandname: VehiclebrandnameFormRawValue | NewVehiclebrandnameFormRawValue,
  ): IVehiclebrandname | NewVehiclebrandname {
    return {
      ...rawVehiclebrandname,
      lmd: dayjs(rawVehiclebrandname.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertVehiclebrandnameToVehiclebrandnameRawValue(
    vehiclebrandname: IVehiclebrandname | (Partial<NewVehiclebrandname> & VehiclebrandnameFormDefaults),
  ): VehiclebrandnameFormRawValue | PartialWithRequiredKeyOf<NewVehiclebrandnameFormRawValue> {
    return {
      ...vehiclebrandname,
      lmd: vehiclebrandname.lmd ? vehiclebrandname.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
