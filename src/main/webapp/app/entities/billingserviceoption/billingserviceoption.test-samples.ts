import dayjs from 'dayjs/esm';

import { IBillingserviceoption, NewBillingserviceoption } from './billingserviceoption.model';

export const sampleWithRequiredData: IBillingserviceoption = {
  id: 14081,
};

export const sampleWithPartialData: IBillingserviceoption = {
  id: 860,
  servicename: 'into',
  lmd: dayjs('2024-10-03T20:57'),
  lmu: 26306,
  orderby: 8865,
};

export const sampleWithFullData: IBillingserviceoption = {
  id: 2539,
  servicename: 'bah stockpile',
  servicediscription: 'carrier viciously wholly',
  isactive: false,
  lmd: dayjs('2024-10-04T07:47'),
  lmu: 16202,
  orderby: 27406,
  billtocustomer: false,
};

export const sampleWithNewData: NewBillingserviceoption = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
