import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IWorkshopvehiclework, NewWorkshopvehiclework } from '../workshopvehiclework.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IWorkshopvehiclework for edit and NewWorkshopvehicleworkFormGroupInput for create.
 */
type WorkshopvehicleworkFormGroupInput = IWorkshopvehiclework | PartialWithRequiredKeyOf<NewWorkshopvehiclework>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IWorkshopvehiclework | NewWorkshopvehiclework> = Omit<T, 'addeddate' | 'calldate' | 'lmd'> & {
  addeddate?: string | null;
  calldate?: string | null;
  lmd?: string | null;
};

type WorkshopvehicleworkFormRawValue = FormValueOf<IWorkshopvehiclework>;

type NewWorkshopvehicleworkFormRawValue = FormValueOf<NewWorkshopvehiclework>;

type WorkshopvehicleworkFormDefaults = Pick<NewWorkshopvehiclework, 'id' | 'addeddate' | 'iscalltocustomer' | 'calldate' | 'lmd'>;

type WorkshopvehicleworkFormGroupContent = {
  id: FormControl<WorkshopvehicleworkFormRawValue['id'] | NewWorkshopvehiclework['id']>;
  jobid: FormControl<WorkshopvehicleworkFormRawValue['jobid']>;
  vehicleid: FormControl<WorkshopvehicleworkFormRawValue['vehicleid']>;
  customerid: FormControl<WorkshopvehicleworkFormRawValue['customerid']>;
  customername: FormControl<WorkshopvehicleworkFormRawValue['customername']>;
  contactno: FormControl<WorkshopvehicleworkFormRawValue['contactno']>;
  vehicleno: FormControl<WorkshopvehicleworkFormRawValue['vehicleno']>;
  vehiclebrand: FormControl<WorkshopvehicleworkFormRawValue['vehiclebrand']>;
  vehiclemodel: FormControl<WorkshopvehicleworkFormRawValue['vehiclemodel']>;
  mileage: FormControl<WorkshopvehicleworkFormRawValue['mileage']>;
  addeddate: FormControl<WorkshopvehicleworkFormRawValue['addeddate']>;
  iscalltocustomer: FormControl<WorkshopvehicleworkFormRawValue['iscalltocustomer']>;
  remarks: FormControl<WorkshopvehicleworkFormRawValue['remarks']>;
  calldate: FormControl<WorkshopvehicleworkFormRawValue['calldate']>;
  lmu: FormControl<WorkshopvehicleworkFormRawValue['lmu']>;
  lmd: FormControl<WorkshopvehicleworkFormRawValue['lmd']>;
};

export type WorkshopvehicleworkFormGroup = FormGroup<WorkshopvehicleworkFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class WorkshopvehicleworkFormService {
  createWorkshopvehicleworkFormGroup(workshopvehiclework: WorkshopvehicleworkFormGroupInput = { id: null }): WorkshopvehicleworkFormGroup {
    const workshopvehicleworkRawValue = this.convertWorkshopvehicleworkToWorkshopvehicleworkRawValue({
      ...this.getFormDefaults(),
      ...workshopvehiclework,
    });
    return new FormGroup<WorkshopvehicleworkFormGroupContent>({
      id: new FormControl(
        { value: workshopvehicleworkRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      jobid: new FormControl(workshopvehicleworkRawValue.jobid),
      vehicleid: new FormControl(workshopvehicleworkRawValue.vehicleid),
      customerid: new FormControl(workshopvehicleworkRawValue.customerid),
      customername: new FormControl(workshopvehicleworkRawValue.customername),
      contactno: new FormControl(workshopvehicleworkRawValue.contactno),
      vehicleno: new FormControl(workshopvehicleworkRawValue.vehicleno),
      vehiclebrand: new FormControl(workshopvehicleworkRawValue.vehiclebrand),
      vehiclemodel: new FormControl(workshopvehicleworkRawValue.vehiclemodel),
      mileage: new FormControl(workshopvehicleworkRawValue.mileage),
      addeddate: new FormControl(workshopvehicleworkRawValue.addeddate),
      iscalltocustomer: new FormControl(workshopvehicleworkRawValue.iscalltocustomer),
      remarks: new FormControl(workshopvehicleworkRawValue.remarks),
      calldate: new FormControl(workshopvehicleworkRawValue.calldate),
      lmu: new FormControl(workshopvehicleworkRawValue.lmu),
      lmd: new FormControl(workshopvehicleworkRawValue.lmd),
    });
  }

  getWorkshopvehiclework(form: WorkshopvehicleworkFormGroup): IWorkshopvehiclework | NewWorkshopvehiclework {
    return this.convertWorkshopvehicleworkRawValueToWorkshopvehiclework(
      form.getRawValue() as WorkshopvehicleworkFormRawValue | NewWorkshopvehicleworkFormRawValue,
    );
  }

  resetForm(form: WorkshopvehicleworkFormGroup, workshopvehiclework: WorkshopvehicleworkFormGroupInput): void {
    const workshopvehicleworkRawValue = this.convertWorkshopvehicleworkToWorkshopvehicleworkRawValue({
      ...this.getFormDefaults(),
      ...workshopvehiclework,
    });
    form.reset(
      {
        ...workshopvehicleworkRawValue,
        id: { value: workshopvehicleworkRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): WorkshopvehicleworkFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      addeddate: currentTime,
      iscalltocustomer: false,
      calldate: currentTime,
      lmd: currentTime,
    };
  }

  private convertWorkshopvehicleworkRawValueToWorkshopvehiclework(
    rawWorkshopvehiclework: WorkshopvehicleworkFormRawValue | NewWorkshopvehicleworkFormRawValue,
  ): IWorkshopvehiclework | NewWorkshopvehiclework {
    return {
      ...rawWorkshopvehiclework,
      addeddate: dayjs(rawWorkshopvehiclework.addeddate, DATE_TIME_FORMAT),
      calldate: dayjs(rawWorkshopvehiclework.calldate, DATE_TIME_FORMAT),
      lmd: dayjs(rawWorkshopvehiclework.lmd, DATE_TIME_FORMAT),
    };
  }

  private convertWorkshopvehicleworkToWorkshopvehicleworkRawValue(
    workshopvehiclework: IWorkshopvehiclework | (Partial<NewWorkshopvehiclework> & WorkshopvehicleworkFormDefaults),
  ): WorkshopvehicleworkFormRawValue | PartialWithRequiredKeyOf<NewWorkshopvehicleworkFormRawValue> {
    return {
      ...workshopvehiclework,
      addeddate: workshopvehiclework.addeddate ? workshopvehiclework.addeddate.format(DATE_TIME_FORMAT) : undefined,
      calldate: workshopvehiclework.calldate ? workshopvehiclework.calldate.format(DATE_TIME_FORMAT) : undefined,
      lmd: workshopvehiclework.lmd ? workshopvehiclework.lmd.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
