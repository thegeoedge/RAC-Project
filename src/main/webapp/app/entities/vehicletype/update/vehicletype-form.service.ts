import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IVehicletype, NewVehicletype } from '../vehicletype.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IVehicletype for edit and NewVehicletypeFormGroupInput for create.
 */
type VehicletypeFormGroupInput = IVehicletype | PartialWithRequiredKeyOf<NewVehicletype>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IVehicletype | NewVehicletype> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type VehicletypeFormRawValue = FormValueOf<IVehicletype>;

type NewVehicletypeFormRawValue = FormValueOf<NewVehicletype>;

type VehicletypeFormDefaults = Pick<NewVehicletype, 'id' | 'lmd'>;

type VehicletypeFormGroupContent = {
  id: FormControl<VehicletypeFormRawValue['id'] | NewVehicletype['id']>;
  vehicletype: FormControl<VehicletypeFormRawValue['vehicletype']>;
  vehicletypediscription: FormControl<VehicletypeFormRawValue['vehicletypediscription']>;
  lmu: FormControl<VehicletypeFormRawValue['lmu']>;
  lmd: FormControl<VehicletypeFormRawValue['lmd']>;
  catid: FormControl<VehicletypeFormRawValue['catid']>;
  catname: FormControl<VehicletypeFormRawValue['catname']>;
};

export type VehicletypeFormGroup = FormGroup<VehicletypeFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class VehicletypeFormService {
  createVehicletypeFormGroup(vehicletype: VehicletypeFormGroupInput = { id: null }): VehicletypeFormGroup {
    const vehicletypeRawValue = this.convertVehicletypeToVehicletypeRawValue({
      ...this.getFormDefaults(),
      ...vehicletype,
    });
    return new FormGroup<VehicletypeFormGroupContent>({
      id: new FormControl(
        { value: vehicletypeRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      vehicletype: new FormControl(vehicletypeRawValue.vehicletype),
      vehicletypediscription: new FormControl(vehicletypeRawValue.vehicletypediscription),
      lmu: new FormControl(vehicletypeRawValue.lmu),
      lmd: new FormControl(vehicletypeRawValue.lmd),
      catid: new FormControl(vehicletypeRawValue.catid),
      catname: new FormControl(vehicletypeRawValue.catname),
    });
  }

  getVehicletype(form: VehicletypeFormGroup): IVehicletype | NewVehicletype {
    return this.convertVehicletypeRawValueToVehicletype(form.getRawValue() as VehicletypeFormRawValue | NewVehicletypeFormRawValue);
  }

  resetForm(form: VehicletypeFormGroup, vehicletype: VehicletypeFormGroupInput): void {
    const vehicletypeRawValue = this.convertVehicletypeToVehicletypeRawValue({ ...this.getFormDefaults(), ...vehicletype });
    form.reset(
      {
        ...vehicletypeRawValue,
        id: { value: vehicletypeRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): VehicletypeFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lmd: currentTime,
    };
  }

  private convertVehicletypeRawValueToVehicletype(
    rawVehicletype: VehicletypeFormRawValue | NewVehicletypeFormRawValue,
  ): IVehicletype | NewVehicletype {
    return {
      ...rawVehicletype,
      lmd: dayjs(rawVehicletype.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertVehicletypeToVehicletypeRawValue(
    vehicletype: IVehicletype | (Partial<NewVehicletype> & VehicletypeFormDefaults),
  ): VehicletypeFormRawValue | PartialWithRequiredKeyOf<NewVehicletypeFormRawValue> {
    return {
      ...vehicletype,
      lmd: vehicletype.lmd ? vehicletype.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
