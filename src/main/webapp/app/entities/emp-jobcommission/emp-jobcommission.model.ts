import dayjs from 'dayjs/esm';

export interface IEmpJobcommission {
  id: number;
  vehcatid?: number | null;
  autojobcatid?: number | null;
  firstcom?: number | null;
  secondcom?: number | null;
  thirdcom?: number | null;
  lmd?: dayjs.Dayjs | null;
  lmu?: number | null;
}

export type NewEmpJobcommission = Omit<IEmpJobcommission, 'id'> & { id: null };
