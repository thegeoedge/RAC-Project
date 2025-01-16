import dayjs from 'dayjs/esm';

export interface IWorkshopvehiclework {
  id: number;
  jobid?: number | null;
  vehicleid?: number | null;
  customerid?: number | null;
  customername?: string | null;
  contactno?: string | null;
  vehicleno?: string | null;
  vehiclebrand?: string | null;
  vehiclemodel?: string | null;
  mileage?: string | null;
  addeddate?: dayjs.Dayjs | null;
  iscalltocustomer?: boolean | null;
  remarks?: string | null;
  calldate?: dayjs.Dayjs | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
}

export type NewWorkshopvehiclework = Omit<IWorkshopvehiclework, 'id'> & { id: null };
