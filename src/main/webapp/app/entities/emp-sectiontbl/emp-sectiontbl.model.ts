import dayjs from 'dayjs/esm';

export interface IEmpSectiontbl {
  id: number;
  empid?: number | null;
  sectionid?: number | null;
  lmd?: dayjs.Dayjs | null;
  lmu?: number | null;
}

export type NewEmpSectiontbl = Omit<IEmpSectiontbl, 'id'> & { id: null };
