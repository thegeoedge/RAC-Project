import dayjs from 'dayjs/esm';

export interface ICommonserviceoption {
  id: number;
  mainid?: number | null;
  code?: string | null;
  name?: string | null;
  description?: string | null;
  value?: number | null;
  isactive?: boolean | null;
  lmd?: dayjs.Dayjs | null;
  lmu?: number | null;
}

export type NewCommonserviceoption = Omit<ICommonserviceoption, 'id'> & { id: null };
