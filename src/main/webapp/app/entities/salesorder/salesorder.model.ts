import dayjs from 'dayjs/esm';

export interface ISalesorder {
  id: number;
  code?: string | null;
  orderdate?: dayjs.Dayjs | null;
  createddate?: dayjs.Dayjs | null;
  quoteid?: number | null;
  salesrepid?: number | null;
  salesrepname?: string | null;
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
  isinvoiced?: boolean | null;
  refinvoiceid?: number | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
  isfixed?: boolean | null;
  isactive?: boolean | null;
  advancepayment?: number | null;
  advancepaymentreturnamount?: number | null;
  advancepaymentreturndate?: dayjs.Dayjs | null;
  advancepaymentby?: number | null;
}

export type NewSalesorder = Omit<ISalesorder, 'id'> & { id: null };
