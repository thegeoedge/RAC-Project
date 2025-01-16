import dayjs from 'dayjs/esm';

export interface ITaxes {
  id: number;
  code?: string | null;
  description?: string | null;
  effectivefrom?: dayjs.Dayjs | null;
  effectiveto?: dayjs.Dayjs | null;
  percentage?: number | null;
  fixedamount?: number | null;
  ismanual?: boolean | null;
  isactive?: boolean | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
}

export type NewTaxes = Omit<ITaxes, 'id'> & { id: null };
