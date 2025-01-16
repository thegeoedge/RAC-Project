import dayjs from 'dayjs/esm';

export interface IServicesubcategory {
  id: number;
  name?: string | null;
  description?: string | null;
  mainid?: number | null;
  mainname?: string | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
  isactive?: boolean | null;
}

export type NewServicesubcategory = Omit<IServicesubcategory, 'id'> & { id: null };
