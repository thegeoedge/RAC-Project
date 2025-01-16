import dayjs from 'dayjs/esm';

export interface IAutojobempallocation {
  id: number;
  jobid?: number | null;
  categoryid?: number | null;
  empposition?: number | null;
  empid?: number | null;
  empname?: string | null;
  allocatetime?: dayjs.Dayjs | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
  jobdate?: dayjs.Dayjs | null;
  commission?: number | null;
  iscommissionpaid?: boolean | null;
  starttime?: dayjs.Dayjs | null;
  endtime?: dayjs.Dayjs | null;
}

export type NewAutojobempallocation = Omit<IAutojobempallocation, 'id'> & { id: null };
