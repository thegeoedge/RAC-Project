import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IServicecategory, NewServicecategory } from '../servicecategory.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IServicecategory for edit and NewServicecategoryFormGroupInput for create.
 */
type ServicecategoryFormGroupInput = IServicecategory | PartialWithRequiredKeyOf<NewServicecategory>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IServicecategory | NewServicecategory> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type ServicecategoryFormRawValue = FormValueOf<IServicecategory>;

type NewServicecategoryFormRawValue = FormValueOf<NewServicecategory>;

type ServicecategoryFormDefaults = Pick<NewServicecategory, 'id' | 'lmd' | 'showsecurity' | 'isactive'>;

type ServicecategoryFormGroupContent = {
  id: FormControl<ServicecategoryFormRawValue['id'] | NewServicecategory['id']>;
  name: FormControl<ServicecategoryFormRawValue['name']>;
  description: FormControl<ServicecategoryFormRawValue['description']>;
  lmu: FormControl<ServicecategoryFormRawValue['lmu']>;
  lmd: FormControl<ServicecategoryFormRawValue['lmd']>;
  showsecurity: FormControl<ServicecategoryFormRawValue['showsecurity']>;
  sortorder: FormControl<ServicecategoryFormRawValue['sortorder']>;
  isactive: FormControl<ServicecategoryFormRawValue['isactive']>;
};

export type ServicecategoryFormGroup = FormGroup<ServicecategoryFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ServicecategoryFormService {
  createServicecategoryFormGroup(servicecategory: ServicecategoryFormGroupInput = { id: null }): ServicecategoryFormGroup {
    const servicecategoryRawValue = this.convertServicecategoryToServicecategoryRawValue({
      ...this.getFormDefaults(),
      ...servicecategory,
    });
    return new FormGroup<ServicecategoryFormGroupContent>({
      id: new FormControl(
        { value: servicecategoryRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      name: new FormControl(servicecategoryRawValue.name),
      description: new FormControl(servicecategoryRawValue.description),
      lmu: new FormControl(servicecategoryRawValue.lmu),
      lmd: new FormControl(servicecategoryRawValue.lmd),
      showsecurity: new FormControl(servicecategoryRawValue.showsecurity),
      sortorder: new FormControl(servicecategoryRawValue.sortorder),
      isactive: new FormControl(servicecategoryRawValue.isactive),
    });
  }

  getServicecategory(form: ServicecategoryFormGroup): IServicecategory | NewServicecategory {
    return this.convertServicecategoryRawValueToServicecategory(
      form.getRawValue() as ServicecategoryFormRawValue | NewServicecategoryFormRawValue,
    );
  }

  resetForm(form: ServicecategoryFormGroup, servicecategory: ServicecategoryFormGroupInput): void {
    const servicecategoryRawValue = this.convertServicecategoryToServicecategoryRawValue({ ...this.getFormDefaults(), ...servicecategory });
    form.reset(
      {
        ...servicecategoryRawValue,
        id: { value: servicecategoryRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ServicecategoryFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lmd: currentTime,
      showsecurity: false,
      isactive: false,
    };
  }

  private convertServicecategoryRawValueToServicecategory(
    rawServicecategory: ServicecategoryFormRawValue | NewServicecategoryFormRawValue,
  ): IServicecategory | NewServicecategory {
    return {
      ...rawServicecategory,
      lmd: dayjs(rawServicecategory.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertServicecategoryToServicecategoryRawValue(
    servicecategory: IServicecategory | (Partial<NewServicecategory> & ServicecategoryFormDefaults),
  ): ServicecategoryFormRawValue | PartialWithRequiredKeyOf<NewServicecategoryFormRawValue> {
    return {
      ...servicecategory,
      lmd: servicecategory.lmd ? servicecategory.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
