import dayjs from 'dayjs/esm';

export interface IVehiclebrandmodel {
  id: number;
  brandid?: number | null;
  brandname?: string | null;
  model?: string | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
}

export type NewVehiclebrandmodel = Omit<IVehiclebrandmodel, 'id'> & { id: null };
