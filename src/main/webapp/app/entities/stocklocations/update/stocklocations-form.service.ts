import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IStocklocations, NewStocklocations } from '../stocklocations.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IStocklocations for edit and NewStocklocationsFormGroupInput for create.
 */
type StocklocationsFormGroupInput = IStocklocations | PartialWithRequiredKeyOf<NewStocklocations>;

type StocklocationsFormDefaults = Pick<NewStocklocations, 'id'>;

type StocklocationsFormGroupContent = {
  id: FormControl<IStocklocations['id'] | NewStocklocations['id']>;
  locationname: FormControl<IStocklocations['locationname']>;
  locationcode: FormControl<IStocklocations['locationcode']>;
  description: FormControl<IStocklocations['description']>;
};

export type StocklocationsFormGroup = FormGroup<StocklocationsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class StocklocationsFormService {
  createStocklocationsFormGroup(stocklocations: StocklocationsFormGroupInput = { id: null }): StocklocationsFormGroup {
    const stocklocationsRawValue = {
      ...this.getFormDefaults(),
      ...stocklocations,
    };
    return new FormGroup<StocklocationsFormGroupContent>({
      id: new FormControl(
        { value: stocklocationsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      locationname: new FormControl(stocklocationsRawValue.locationname),
      locationcode: new FormControl(stocklocationsRawValue.locationcode),
      description: new FormControl(stocklocationsRawValue.description),
    });
  }

  getStocklocations(form: StocklocationsFormGroup): IStocklocations | NewStocklocations {
    return form.getRawValue() as IStocklocations | NewStocklocations;
  }

  resetForm(form: StocklocationsFormGroup, stocklocations: StocklocationsFormGroupInput): void {
    const stocklocationsRawValue = { ...this.getFormDefaults(), ...stocklocations };
    form.reset(
      {
        ...stocklocationsRawValue,
        id: { value: stocklocationsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): StocklocationsFormDefaults {
    return {
      id: null,
    };
  }
}
