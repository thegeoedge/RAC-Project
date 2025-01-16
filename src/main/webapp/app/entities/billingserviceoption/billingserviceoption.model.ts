import dayjs from 'dayjs/esm';

export interface IBillingserviceoption {
  id: number;
  servicename?: string | null;
  servicediscription?: string | null;
  isactive?: boolean | null;
  lmd?: dayjs.Dayjs | null;
  lmu?: number | null;
  orderby?: number | null;
  billtocustomer?: boolean | null;
}

export type NewBillingserviceoption = Omit<IBillingserviceoption, 'id'> & { id: null };
