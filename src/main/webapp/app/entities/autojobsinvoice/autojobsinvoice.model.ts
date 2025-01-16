import dayjs from 'dayjs/esm';

export interface IAutojobsinvoice {
  id: number;
  code?: string | null;
  invoicedate?: dayjs.Dayjs | null;
  createddate?: dayjs.Dayjs | null;
  jobid?: number | null;
  quoteid?: number | null;
  orderid?: number | null;
  delieverydate?: dayjs.Dayjs | null;
  autojobsrepid?: number | null;
  autojobsrepname?: string | null;
  delieverfrom?: string | null;
  customerid?: number | null;
  customername?: string | null;
  customeraddress?: string | null;
  deliveryaddress?: string | null;
  subtotal?: number | null;
  totaltax?: number | null;
  totaldiscount?: number | null;
  nettotal?: number | null;
  message?: string | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
  paidamount?: number | null;
  amountowing?: number | null;
  isactive?: boolean | null;
  locationid?: number | null;
  locationcode?: string | null;
  referencecode?: string | null;
  createdbyid?: number | null;
  createdbyname?: string | null;
  autocarecompanyid?: number | null;
}

export type NewAutojobsinvoice = Omit<IAutojobsinvoice, 'id'> & { id: null };
