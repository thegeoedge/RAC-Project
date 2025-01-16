import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ILocationbasedstock, NewLocationbasedstock } from '../locationbasedstock.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ILocationbasedstock for edit and NewLocationbasedstockFormGroupInput for create.
 */
type LocationbasedstockFormGroupInput = ILocationbasedstock | PartialWithRequiredKeyOf<NewLocationbasedstock>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ILocationbasedstock | NewLocationbasedstock> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type LocationbasedstockFormRawValue = FormValueOf<ILocationbasedstock>;

type NewLocationbasedstockFormRawValue = FormValueOf<NewLocationbasedstock>;

type LocationbasedstockFormDefaults = Pick<NewLocationbasedstock, 'id' | 'hasbatches' | 'lmd'>;

type LocationbasedstockFormGroupContent = {
  id: FormControl<LocationbasedstockFormRawValue['id'] | NewLocationbasedstock['id']>;
  itemid: FormControl<LocationbasedstockFormRawValue['itemid']>;
  code: FormControl<LocationbasedstockFormRawValue['code']>;
  name: FormControl<LocationbasedstockFormRawValue['name']>;
  locationid: FormControl<LocationbasedstockFormRawValue['locationid']>;
  locationcode: FormControl<LocationbasedstockFormRawValue['locationcode']>;
  availablequantity: FormControl<LocationbasedstockFormRawValue['availablequantity']>;
  hasbatches: FormControl<LocationbasedstockFormRawValue['hasbatches']>;
  lmu: FormControl<LocationbasedstockFormRawValue['lmu']>;
  lmd: FormControl<LocationbasedstockFormRawValue['lmd']>;
};

export type LocationbasedstockFormGroup = FormGroup<LocationbasedstockFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class LocationbasedstockFormService {
  createLocationbasedstockFormGroup(locationbasedstock: LocationbasedstockFormGroupInput = { id: null }): LocationbasedstockFormGroup {
    const locationbasedstockRawValue = this.convertLocationbasedstockToLocationbasedstockRawValue({
      ...this.getFormDefaults(),
      ...locationbasedstock,
    });
    return new FormGroup<LocationbasedstockFormGroupContent>({
      id: new FormControl(
        { value: locationbasedstockRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      itemid: new FormControl(locationbasedstockRawValue.itemid, {
        validators: [Validators.required],
      }),
      code: new FormControl(locationbasedstockRawValue.code),
      name: new FormControl(locationbasedstockRawValue.name),
      locationid: new FormControl(locationbasedstockRawValue.locationid, {
        validators: [Validators.required],
      }),
      locationcode: new FormControl(locationbasedstockRawValue.locationcode),
      availablequantity: new FormControl(locationbasedstockRawValue.availablequantity),
      hasbatches: new FormControl(locationbasedstockRawValue.hasbatches),
      lmu: new FormControl(locationbasedstockRawValue.lmu),
      lmd: new FormControl(locationbasedstockRawValue.lmd),
    });
  }

  getLocationbasedstock(form: LocationbasedstockFormGroup): ILocationbasedstock | NewLocationbasedstock {
    return this.convertLocationbasedstockRawValueToLocationbasedstock(
      form.getRawValue() as LocationbasedstockFormRawValue | NewLocationbasedstockFormRawValue,
    );
  }

  resetForm(form: LocationbasedstockFormGroup, locationbasedstock: LocationbasedstockFormGroupInput): void {
    const locationbasedstockRawValue = this.convertLocationbasedstockToLocationbasedstockRawValue({
      ...this.getFormDefaults(),
      ...locationbasedstock,
    });
    form.reset(
      {
        ...locationbasedstockRawValue,
        id: { value: locationbasedstockRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): LocationbasedstockFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      hasbatches: false,
      lmd: currentTime,
    };
  }

  private convertLocationbasedstockRawValueToLocationbasedstock(
    rawLocationbasedstock: LocationbasedstockFormRawValue | NewLocationbasedstockFormRawValue,
  ): ILocationbasedstock | NewLocationbasedstock {
    return {
      ...rawLocationbasedstock,
      lmd: dayjs(rawLocationbasedstock.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertLocationbasedstockToLocationbasedstockRawValue(
    locationbasedstock: ILocationbasedstock | (Partial<NewLocationbasedstock> & LocationbasedstockFormDefaults),
  ): LocationbasedstockFormRawValue | PartialWithRequiredKeyOf<NewLocationbasedstockFormRawValue> {
    return {
      ...locationbasedstock,
      lmd: locationbasedstock.lmd ? locationbasedstock.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
