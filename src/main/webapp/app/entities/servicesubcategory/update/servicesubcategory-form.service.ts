import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IServicesubcategory, NewServicesubcategory } from '../servicesubcategory.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IServicesubcategory for edit and NewServicesubcategoryFormGroupInput for create.
 */
type ServicesubcategoryFormGroupInput = IServicesubcategory | PartialWithRequiredKeyOf<NewServicesubcategory>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IServicesubcategory | NewServicesubcategory> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type ServicesubcategoryFormRawValue = FormValueOf<IServicesubcategory>;

type NewServicesubcategoryFormRawValue = FormValueOf<NewServicesubcategory>;

type ServicesubcategoryFormDefaults = Pick<NewServicesubcategory, 'id' | 'lmd' | 'isactive'>;

type ServicesubcategoryFormGroupContent = {
  id: FormControl<ServicesubcategoryFormRawValue['id'] | NewServicesubcategory['id']>;
  name: FormControl<ServicesubcategoryFormRawValue['name']>;
  description: FormControl<ServicesubcategoryFormRawValue['description']>;
  mainid: FormControl<ServicesubcategoryFormRawValue['mainid']>;
  mainname: FormControl<ServicesubcategoryFormRawValue['mainname']>;
  lmu: FormControl<ServicesubcategoryFormRawValue['lmu']>;
  lmd: FormControl<ServicesubcategoryFormRawValue['lmd']>;
  isactive: FormControl<ServicesubcategoryFormRawValue['isactive']>;
};

export type ServicesubcategoryFormGroup = FormGroup<ServicesubcategoryFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ServicesubcategoryFormService {
  createServicesubcategoryFormGroup(servicesubcategory: ServicesubcategoryFormGroupInput = { id: null }): ServicesubcategoryFormGroup {
    const servicesubcategoryRawValue = this.convertServicesubcategoryToServicesubcategoryRawValue({
      ...this.getFormDefaults(),
      ...servicesubcategory,
    });
    return new FormGroup<ServicesubcategoryFormGroupContent>({
      id: new FormControl(
        { value: servicesubcategoryRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      name: new FormControl(servicesubcategoryRawValue.name),
      description: new FormControl(servicesubcategoryRawValue.description),
      mainid: new FormControl(servicesubcategoryRawValue.mainid),
      mainname: new FormControl(servicesubcategoryRawValue.mainname),
      lmu: new FormControl(servicesubcategoryRawValue.lmu),
      lmd: new FormControl(servicesubcategoryRawValue.lmd),
      isactive: new FormControl(servicesubcategoryRawValue.isactive),
    });
  }

  getServicesubcategory(form: ServicesubcategoryFormGroup): IServicesubcategory | NewServicesubcategory {
    return this.convertServicesubcategoryRawValueToServicesubcategory(
      form.getRawValue() as ServicesubcategoryFormRawValue | NewServicesubcategoryFormRawValue,
    );
  }

  resetForm(form: ServicesubcategoryFormGroup, servicesubcategory: ServicesubcategoryFormGroupInput): void {
    const servicesubcategoryRawValue = this.convertServicesubcategoryToServicesubcategoryRawValue({
      ...this.getFormDefaults(),
      ...servicesubcategory,
    });
    form.reset(
      {
        ...servicesubcategoryRawValue,
        id: { value: servicesubcategoryRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ServicesubcategoryFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lmd: currentTime,
      isactive: false,
    };
  }

  private convertServicesubcategoryRawValueToServicesubcategory(
    rawServicesubcategory: ServicesubcategoryFormRawValue | NewServicesubcategoryFormRawValue,
  ): IServicesubcategory | NewServicesubcategory {
    return {
      ...rawServicesubcategory,
      lmd: dayjs(rawServicesubcategory.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertServicesubcategoryToServicesubcategoryRawValue(
    servicesubcategory: IServicesubcategory | (Partial<NewServicesubcategory> & ServicesubcategoryFormDefaults),
  ): ServicesubcategoryFormRawValue | PartialWithRequiredKeyOf<NewServicesubcategoryFormRawValue> {
    return {
      ...servicesubcategory,
      lmd: servicesubcategory.lmd ? servicesubcategory.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
