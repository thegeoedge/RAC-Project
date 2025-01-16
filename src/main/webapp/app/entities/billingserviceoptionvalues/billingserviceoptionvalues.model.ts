import dayjs from 'dayjs/esm';

export interface IBillingserviceoptionvalues {
  id: number;
  vehicletypeid?: number | null;
  billingserviceoptionid?: number | null;
  value?: number | null;
  lmd?: dayjs.Dayjs | null;
  lmu?: number | null;
}

export type NewBillingserviceoptionvalues = Omit<IBillingserviceoptionvalues, 'id'> & { id: null };
