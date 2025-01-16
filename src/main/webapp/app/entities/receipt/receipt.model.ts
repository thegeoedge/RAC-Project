import dayjs from 'dayjs/esm';

export interface IReceipt {
  id: number;
  code?: string | null;
  receiptdate?: dayjs.Dayjs | null;
  customername?: string | null;
  customeraddress?: string | null;
  totalamount?: number | null;
  totalamountinword?: string | null;
  comments?: string | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
  termid?: number | null;
  term?: string | null;
  date?: dayjs.Dayjs | null;
  amount?: number | null;
  checkdate?: dayjs.Dayjs | null;
  checkno?: string | null;
  bank?: string | null;
  customerid?: number | null;
  isactive?: boolean | null;
  deposited?: boolean | null;
  createdby?: number | null;
}

export type NewReceipt = Omit<IReceipt, 'id'> & { id: null };
