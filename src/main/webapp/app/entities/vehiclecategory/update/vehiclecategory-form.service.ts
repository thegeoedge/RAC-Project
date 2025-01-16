import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IVehiclecategory, NewVehiclecategory } from '../vehiclecategory.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IVehiclecategory for edit and NewVehiclecategoryFormGroupInput for create.
 */
type VehiclecategoryFormGroupInput = IVehiclecategory | PartialWithRequiredKeyOf<NewVehiclecategory>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IVehiclecategory | NewVehiclecategory> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type VehiclecategoryFormRawValue = FormValueOf<IVehiclecategory>;

type NewVehiclecategoryFormRawValue = FormValueOf<NewVehiclecategory>;

type VehiclecategoryFormDefaults = Pick<NewVehiclecategory, 'id' | 'lmd'>;

type VehiclecategoryFormGroupContent = {
  id: FormControl<VehiclecategoryFormRawValue['id'] | NewVehiclecategory['id']>;
  vehiclecategory: FormControl<VehiclecategoryFormRawValue['vehiclecategory']>;
  vehiclecategorydiscription: FormControl<VehiclecategoryFormRawValue['vehiclecategorydiscription']>;
  lmu: FormControl<VehiclecategoryFormRawValue['lmu']>;
  lmd: FormControl<VehiclecategoryFormRawValue['lmd']>;
};

export type VehiclecategoryFormGroup = FormGroup<VehiclecategoryFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class VehiclecategoryFormService {
  createVehiclecategoryFormGroup(vehiclecategory: VehiclecategoryFormGroupInput = { id: null }): VehiclecategoryFormGroup {
    const vehiclecategoryRawValue = this.convertVehiclecategoryToVehiclecategoryRawValue({
      ...this.getFormDefaults(),
      ...vehiclecategory,
    });
    return new FormGroup<VehiclecategoryFormGroupContent>({
      id: new FormControl(
        { value: vehiclecategoryRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      vehiclecategory: new FormControl(vehiclecategoryRawValue.vehiclecategory),
      vehiclecategorydiscription: new FormControl(vehiclecategoryRawValue.vehiclecategorydiscription),
      lmu: new FormControl(vehiclecategoryRawValue.lmu),
      lmd: new FormControl(vehiclecategoryRawValue.lmd),
    });
  }

  getVehiclecategory(form: VehiclecategoryFormGroup): IVehiclecategory | NewVehiclecategory {
    return this.convertVehiclecategoryRawValueToVehiclecategory(
      form.getRawValue() as VehiclecategoryFormRawValue | NewVehiclecategoryFormRawValue,
    );
  }

  resetForm(form: VehiclecategoryFormGroup, vehiclecategory: VehiclecategoryFormGroupInput): void {
    const vehiclecategoryRawValue = this.convertVehiclecategoryToVehiclecategoryRawValue({ ...this.getFormDefaults(), ...vehiclecategory });
    form.reset(
      {
        ...vehiclecategoryRawValue,
        id: { value: vehiclecategoryRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): VehiclecategoryFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lmd: currentTime,
    };
  }

  private convertVehiclecategoryRawValueToVehiclecategory(
    rawVehiclecategory: VehiclecategoryFormRawValue | NewVehiclecategoryFormRawValue,
  ): IVehiclecategory | NewVehiclecategory {
    return {
      ...rawVehiclecategory,
      lmd: dayjs(rawVehiclecategory.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertVehiclecategoryToVehiclecategoryRawValue(
    vehiclecategory: IVehiclecategory | (Partial<NewVehiclecategory> & VehiclecategoryFormDefaults),
  ): VehiclecategoryFormRawValue | PartialWithRequiredKeyOf<NewVehiclecategoryFormRawValue> {
    return {
      ...vehiclecategory,
      lmd: vehiclecategory.lmd ? vehiclecategory.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
