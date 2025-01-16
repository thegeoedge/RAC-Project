import dayjs from 'dayjs/esm';

export interface IHoisttype {
  id: number;
  hoisttype?: string | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
}

export type NewHoisttype = Omit<IHoisttype, 'id'> & { id: null };
