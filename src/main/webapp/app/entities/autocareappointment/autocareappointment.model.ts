import dayjs from 'dayjs/esm';

export interface IAutocareappointment {
  id: number;
  appointmenttype?: number | null;
  appointmentdate?: dayjs.Dayjs | null;
  addeddate?: dayjs.Dayjs | null;
  conformdate?: dayjs.Dayjs | null;
  appointmentnumber?: number | null;
  vehiclenumber?: string | null;
  appointmenttime?: dayjs.Dayjs | null;
  isconformed?: boolean | null;
  conformedby?: number | null;
  lmd?: dayjs.Dayjs | null;
  lmu?: number | null;
  customerid?: number | null;
  contactnumber?: string | null;
  customername?: string | null;
  issued?: boolean | null;
  hoistid?: number | null;
  isarrived?: boolean | null;
  iscancel?: boolean | null;
  isnoanswer?: boolean | null;
  missedappointmentcall?: string | null;
  customermobileid?: number | null;
  customermobilevehicleid?: number | null;
  vehicleid?: number | null;
  ismobileappointment?: boolean | null;
  advancepayment?: number | null;
  jobid?: number | null;
}

export type NewAutocareappointment = Omit<IAutocareappointment, 'id'> & { id: null };
