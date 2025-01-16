import dayjs from 'dayjs/esm';

export interface ILastserviceinstructions {
  id: number;
  jobid?: number | null;
  instruction?: string | null;
  isactive?: boolean | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
  ignore?: boolean | null;
  vehicleid?: number | null;
  vehicleno?: string | null;
}

export type NewLastserviceinstructions = Omit<ILastserviceinstructions, 'id'> & { id: null };
