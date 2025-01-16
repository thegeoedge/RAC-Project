import dayjs from 'dayjs/esm';

export interface ICompanybankaccount {
  id: number;
  companyid?: number | null;
  accountnumber?: string | null;
  accountname?: string | null;
  bankname?: string | null;
  bankid?: number | null;
  branchname?: string | null;
  branchid?: number | null;
  amount?: number | null;
  accountcode?: string | null;
  accountid?: number | null;
  lmd?: dayjs.Dayjs | null;
  lmu?: string | null;
  isactive?: boolean | null;
  accounttypeid?: number | null;
}

export type NewCompanybankaccount = Omit<ICompanybankaccount, 'id'> & { id: null };
