import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IVehiclebrandmodel, NewVehiclebrandmodel } from '../vehiclebrandmodel.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IVehiclebrandmodel for edit and NewVehiclebrandmodelFormGroupInput for create.
 */
type VehiclebrandmodelFormGroupInput = IVehiclebrandmodel | PartialWithRequiredKeyOf<NewVehiclebrandmodel>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IVehiclebrandmodel | NewVehiclebrandmodel> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type VehiclebrandmodelFormRawValue = FormValueOf<IVehiclebrandmodel>;

type NewVehiclebrandmodelFormRawValue = FormValueOf<NewVehiclebrandmodel>;

type VehiclebrandmodelFormDefaults = Pick<NewVehiclebrandmodel, 'id' | 'lmd'>;

type VehiclebrandmodelFormGroupContent = {
  id: FormControl<VehiclebrandmodelFormRawValue['id'] | NewVehiclebrandmodel['id']>;
  brandid: FormControl<VehiclebrandmodelFormRawValue['brandid']>;
  brandname: FormControl<VehiclebrandmodelFormRawValue['brandname']>;
  model: FormControl<VehiclebrandmodelFormRawValue['model']>;
  lmu: FormControl<VehiclebrandmodelFormRawValue['lmu']>;
  lmd: FormControl<VehiclebrandmodelFormRawValue['lmd']>;
};

export type VehiclebrandmodelFormGroup = FormGroup<VehiclebrandmodelFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class VehiclebrandmodelFormService {
  createVehiclebrandmodelFormGroup(vehiclebrandmodel: VehiclebrandmodelFormGroupInput = { id: null }): VehiclebrandmodelFormGroup {
    const vehiclebrandmodelRawValue = this.convertVehiclebrandmodelToVehiclebrandmodelRawValue({
      ...this.getFormDefaults(),
      ...vehiclebrandmodel,
    });
    return new FormGroup<VehiclebrandmodelFormGroupContent>({
      id: new FormControl(
        { value: vehiclebrandmodelRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      brandid: new FormControl(vehiclebrandmodelRawValue.brandid),
      brandname: new FormControl(vehiclebrandmodelRawValue.brandname),
      model: new FormControl(vehiclebrandmodelRawValue.model),
      lmu: new FormControl(vehiclebrandmodelRawValue.lmu),
      lmd: new FormControl(vehiclebrandmodelRawValue.lmd),
    });
  }

  getVehiclebrandmodel(form: VehiclebrandmodelFormGroup): IVehiclebrandmodel | NewVehiclebrandmodel {
    return this.convertVehiclebrandmodelRawValueToVehiclebrandmodel(
      form.getRawValue() as VehiclebrandmodelFormRawValue | NewVehiclebrandmodelFormRawValue,
    );
  }

  resetForm(form: VehiclebrandmodelFormGroup, vehiclebrandmodel: VehiclebrandmodelFormGroupInput): void {
    const vehiclebrandmodelRawValue = this.convertVehiclebrandmodelToVehiclebrandmodelRawValue({
      ...this.getFormDefaults(),
      ...vehiclebrandmodel,
    });
    form.reset(
      {
        ...vehiclebrandmodelRawValue,
        id: { value: vehiclebrandmodelRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): VehiclebrandmodelFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lmd: currentTime,
    };
  }

  private convertVehiclebrandmodelRawValueToVehiclebrandmodel(
    rawVehiclebrandmodel: VehiclebrandmodelFormRawValue | NewVehiclebrandmodelFormRawValue,
  ): IVehiclebrandmodel | NewVehiclebrandmodel {
    return {
      ...rawVehiclebrandmodel,
      lmd: dayjs(rawVehiclebrandmodel.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertVehiclebrandmodelToVehiclebrandmodelRawValue(
    vehiclebrandmodel: IVehiclebrandmodel | (Partial<NewVehiclebrandmodel> & VehiclebrandmodelFormDefaults),
  ): VehiclebrandmodelFormRawValue | PartialWithRequiredKeyOf<NewVehiclebrandmodelFormRawValue> {
    return {
      ...vehiclebrandmodel,
      lmd: vehiclebrandmodel.lmd ? vehiclebrandmodel.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
