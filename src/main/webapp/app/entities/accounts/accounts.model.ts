import dayjs from 'dayjs/esm';

export interface IAccounts {
  id: number;
  code?: string | null;
  date?: dayjs.Dayjs | null;
  name?: string | null;
  description?: string | null;
  type?: number | null;
  parent?: number | null;
  balance?: number | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
  hasbatches?: boolean | null;
  accountvalue?: number | null;
  accountlevel?: number | null;
  accountsnumberingsystem?: number | null;
  subparentid?: number | null;
  canedit?: boolean | null;
  amount?: number | null;
  creditamount?: number | null;
  debitamount?: number | null;
  debitorcredit?: string | null;
  reporttype?: number | null;
}

export type NewAccounts = Omit<IAccounts, 'id'> & { id: null };
