import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ICustomervehicle, NewCustomervehicle } from '../customervehicle.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICustomervehicle for edit and NewCustomervehicleFormGroupInput for create.
 */
type CustomervehicleFormGroupInput = ICustomervehicle | PartialWithRequiredKeyOf<NewCustomervehicle>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ICustomervehicle | NewCustomervehicle> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type CustomervehicleFormRawValue = FormValueOf<ICustomervehicle>;

type NewCustomervehicleFormRawValue = FormValueOf<NewCustomervehicle>;

type CustomervehicleFormDefaults = Pick<NewCustomervehicle, 'id' | 'lmd'>;

type CustomervehicleFormGroupContent = {
  id: FormControl<CustomervehicleFormRawValue['id'] | NewCustomervehicle['id']>;
  customerid: FormControl<CustomervehicleFormRawValue['customerid']>;
  vehiclenumber: FormControl<CustomervehicleFormRawValue['vehiclenumber']>;
  categoryid: FormControl<CustomervehicleFormRawValue['categoryid']>;
  categoryname: FormControl<CustomervehicleFormRawValue['categoryname']>;
  typeid: FormControl<CustomervehicleFormRawValue['typeid']>;
  typename: FormControl<CustomervehicleFormRawValue['typename']>;
  makeid: FormControl<CustomervehicleFormRawValue['makeid']>;
  makename: FormControl<CustomervehicleFormRawValue['makename']>;
  model: FormControl<CustomervehicleFormRawValue['model']>;
  yom: FormControl<CustomervehicleFormRawValue['yom']>;
  customercode: FormControl<CustomervehicleFormRawValue['customercode']>;
  remarks: FormControl<CustomervehicleFormRawValue['remarks']>;
  servicecount: FormControl<CustomervehicleFormRawValue['servicecount']>;
  engNo: FormControl<CustomervehicleFormRawValue['engNo']>;
  chaNo: FormControl<CustomervehicleFormRawValue['chaNo']>;
  milage: FormControl<CustomervehicleFormRawValue['milage']>;
  lastservicedate: FormControl<CustomervehicleFormRawValue['lastservicedate']>;
  nextservicedate: FormControl<CustomervehicleFormRawValue['nextservicedate']>;
  lmu: FormControl<CustomervehicleFormRawValue['lmu']>;
  lmd: FormControl<CustomervehicleFormRawValue['lmd']>;
  nextgearoilmilage: FormControl<CustomervehicleFormRawValue['nextgearoilmilage']>;
  nextmilage: FormControl<CustomervehicleFormRawValue['nextmilage']>;
  serviceperiod: FormControl<CustomervehicleFormRawValue['serviceperiod']>;
};

export type CustomervehicleFormGroup = FormGroup<CustomervehicleFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CustomervehicleFormService {
  createCustomervehicleFormGroup(customervehicle: CustomervehicleFormGroupInput = { id: null }): CustomervehicleFormGroup {
    const customervehicleRawValue = this.convertCustomervehicleToCustomervehicleRawValue({
      ...this.getFormDefaults(),
      ...customervehicle,
    });
    return new FormGroup<CustomervehicleFormGroupContent>({
      id: new FormControl(
        { value: customervehicleRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      customerid: new FormControl(customervehicleRawValue.customerid),
      vehiclenumber: new FormControl(customervehicleRawValue.vehiclenumber),
      categoryid: new FormControl(customervehicleRawValue.categoryid),
      categoryname: new FormControl(customervehicleRawValue.categoryname),
      typeid: new FormControl(customervehicleRawValue.typeid),
      typename: new FormControl(customervehicleRawValue.typename),
      makeid: new FormControl(customervehicleRawValue.makeid),
      makename: new FormControl(customervehicleRawValue.makename),
      model: new FormControl(customervehicleRawValue.model),
      yom: new FormControl(customervehicleRawValue.yom),
      customercode: new FormControl(customervehicleRawValue.customercode),
      remarks: new FormControl(customervehicleRawValue.remarks),
      servicecount: new FormControl(customervehicleRawValue.servicecount),
      engNo: new FormControl(customervehicleRawValue.engNo),
      chaNo: new FormControl(customervehicleRawValue.chaNo),
      milage: new FormControl(customervehicleRawValue.milage),
      lastservicedate: new FormControl(customervehicleRawValue.lastservicedate),
      nextservicedate: new FormControl(customervehicleRawValue.nextservicedate),
      lmu: new FormControl(customervehicleRawValue.lmu),
      lmd: new FormControl(customervehicleRawValue.lmd),
      nextgearoilmilage: new FormControl(customervehicleRawValue.nextgearoilmilage),
      nextmilage: new FormControl(customervehicleRawValue.nextmilage),
      serviceperiod: new FormControl(customervehicleRawValue.serviceperiod),
    });
  }

  getCustomervehicle(form: CustomervehicleFormGroup): ICustomervehicle | NewCustomervehicle {
    return this.convertCustomervehicleRawValueToCustomervehicle(
      form.getRawValue() as CustomervehicleFormRawValue | NewCustomervehicleFormRawValue,
    );
  }

  resetForm(form: CustomervehicleFormGroup, customervehicle: CustomervehicleFormGroupInput): void {
    const customervehicleRawValue = this.convertCustomervehicleToCustomervehicleRawValue({ ...this.getFormDefaults(), ...customervehicle });
    form.reset(
      {
        ...customervehicleRawValue,
        id: { value: customervehicleRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CustomervehicleFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lmd: currentTime,
    };
  }

  private convertCustomervehicleRawValueToCustomervehicle(
    rawCustomervehicle: CustomervehicleFormRawValue | NewCustomervehicleFormRawValue,
  ): ICustomervehicle | NewCustomervehicle {
    return {
      ...rawCustomervehicle,
      lmd: dayjs(rawCustomervehicle.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertCustomervehicleToCustomervehicleRawValue(
    customervehicle: ICustomervehicle | (Partial<NewCustomervehicle> & CustomervehicleFormDefaults),
  ): CustomervehicleFormRawValue | PartialWithRequiredKeyOf<NewCustomervehicleFormRawValue> {
    return {
      ...customervehicle,
      lmd: customervehicle.lmd ? customervehicle.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
