import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IWorkshopworklist, NewWorkshopworklist } from '../workshopworklist.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IWorkshopworklist for edit and NewWorkshopworklistFormGroupInput for create.
 */
type WorkshopworklistFormGroupInput = IWorkshopworklist | PartialWithRequiredKeyOf<NewWorkshopworklist>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IWorkshopworklist | NewWorkshopworklist> = Omit<T, 'lmd'> & {
  lmd?: string | null;
};

type WorkshopworklistFormRawValue = FormValueOf<IWorkshopworklist>;

type NewWorkshopworklistFormRawValue = FormValueOf<NewWorkshopworklist>;

type WorkshopworklistFormDefaults = Pick<NewWorkshopworklist, 'id' | 'isactive' | 'lmd'>;

type WorkshopworklistFormGroupContent = {
  id: FormControl<WorkshopworklistFormRawValue['id'] | NewWorkshopworklist['id']>;
  workshopwork: FormControl<WorkshopworklistFormRawValue['workshopwork']>;
  workshopworkdescription: FormControl<WorkshopworklistFormRawValue['workshopworkdescription']>;
  isactive: FormControl<WorkshopworklistFormRawValue['isactive']>;
  lmd: FormControl<WorkshopworklistFormRawValue['lmd']>;
  lmu: FormControl<WorkshopworklistFormRawValue['lmu']>;
};

export type WorkshopworklistFormGroup = FormGroup<WorkshopworklistFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class WorkshopworklistFormService {
  createWorkshopworklistFormGroup(workshopworklist: WorkshopworklistFormGroupInput = { id: null }): WorkshopworklistFormGroup {
    const workshopworklistRawValue = this.convertWorkshopworklistToWorkshopworklistRawValue({
      ...this.getFormDefaults(),
      ...workshopworklist,
    });
    return new FormGroup<WorkshopworklistFormGroupContent>({
      id: new FormControl(
        { value: workshopworklistRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      workshopwork: new FormControl(workshopworklistRawValue.workshopwork),
      workshopworkdescription: new FormControl(workshopworklistRawValue.workshopworkdescription),
      isactive: new FormControl(workshopworklistRawValue.isactive),
      lmd: new FormControl(workshopworklistRawValue.lmd),
      lmu: new FormControl(workshopworklistRawValue.lmu),
    });
  }

  getWorkshopworklist(form: WorkshopworklistFormGroup): IWorkshopworklist | NewWorkshopworklist {
    return this.convertWorkshopworklistRawValueToWorkshopworklist(
      form.getRawValue() as WorkshopworklistFormRawValue | NewWorkshopworklistFormRawValue,
    );
  }

  resetForm(form: WorkshopworklistFormGroup, workshopworklist: WorkshopworklistFormGroupInput): void {
    const workshopworklistRawValue = this.convertWorkshopworklistToWorkshopworklistRawValue({
      ...this.getFormDefaults(),
      ...workshopworklist,
    });
    form.reset(
      {
        ...workshopworklistRawValue,
        id: { value: workshopworklistRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): WorkshopworklistFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      isactive: false,
      lmd: currentTime,
    };
  }

  private convertWorkshopworklistRawValueToWorkshopworklist(
    rawWorkshopworklist: WorkshopworklistFormRawValue | NewWorkshopworklistFormRawValue,
  ): IWorkshopworklist | NewWorkshopworklist {
    return {
      ...rawWorkshopworklist,
      lmd: dayjs(rawWorkshopworklist.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertWorkshopworklistToWorkshopworklistRawValue(
    workshopworklist: IWorkshopworklist | (Partial<NewWorkshopworklist> & WorkshopworklistFormDefaults),
  ): WorkshopworklistFormRawValue | PartialWithRequiredKeyOf<NewWorkshopworklistFormRawValue> {
    return {
      ...workshopworklist,
      lmd: workshopworklist.lmd ? workshopworklist.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
