import dayjs from 'dayjs/esm';

export interface INextserviceinstructions {
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

export type NewNextserviceinstructions = Omit<INextserviceinstructions, 'id'> & { id: null };
