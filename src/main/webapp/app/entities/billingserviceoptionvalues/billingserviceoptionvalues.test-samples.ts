import dayjs from 'dayjs/esm';

import { IBillingserviceoptionvalues, NewBillingserviceoptionvalues } from './billingserviceoptionvalues.model';

export const sampleWithRequiredData: IBillingserviceoptionvalues = {
  id: 13898,
};

export const sampleWithPartialData: IBillingserviceoptionvalues = {
  id: 15584,
  billingserviceoptionid: 21102,
  value: 4957.55,
  lmd: dayjs('2024-10-04T03:56'),
};

export const sampleWithFullData: IBillingserviceoptionvalues = {
  id: 17173,
  vehicletypeid: 9466,
  billingserviceoptionid: 27102,
  value: 7211.5,
  lmd: dayjs('2024-10-03T21:13'),
  lmu: 10770,
};

export const sampleWithNewData: NewBillingserviceoptionvalues = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
