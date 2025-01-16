import dayjs from 'dayjs/esm';

export interface IBanks {
  id: number;
  code?: string | null;
  name?: string | null;
  description?: string | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
}

export type NewBanks = Omit<IBanks, 'id'> & { id: null };
