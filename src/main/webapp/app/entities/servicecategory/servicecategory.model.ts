import dayjs from 'dayjs/esm';

export interface IServicecategory {
  id: number;
  name?: string | null;
  description?: string | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
  showsecurity?: boolean | null;
  sortorder?: number | null;
  isactive?: boolean | null;
}

export type NewServicecategory = Omit<IServicecategory, 'id'> & { id: null };
