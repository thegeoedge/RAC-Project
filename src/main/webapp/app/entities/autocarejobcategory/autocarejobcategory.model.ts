import dayjs from 'dayjs/esm';

export interface IAutocarejobcategory {
  id: number;
  code?: string | null;
  name?: string | null;
  description?: string | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
  displayorder?: number | null;
}

export type NewAutocarejobcategory = Omit<IAutocarejobcategory, 'id'> & { id: null };
