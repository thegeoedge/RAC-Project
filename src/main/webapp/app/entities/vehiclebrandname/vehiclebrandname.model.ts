import dayjs from 'dayjs/esm';

export interface IVehiclebrandname {
  id: number;
  brandname?: string | null;
  description?: string | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
  companyid?: number | null;
}

export type NewVehiclebrandname = Omit<IVehiclebrandname, 'id'> & { id: null };
