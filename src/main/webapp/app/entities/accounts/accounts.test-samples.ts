import dayjs from 'dayjs/esm';

import { IAccounts, NewAccounts } from './accounts.model';

export const sampleWithRequiredData: IAccounts = {
  id: 6640,
};

export const sampleWithPartialData: IAccounts = {
  id: 22827,
  code: 'despite proceed pluralise',
  date: dayjs('2024-10-03T05:42'),
  hasbatches: true,
  canedit: true,
  creditamount: 11324.11,
  debitamount: 20666.71,
  debitorcredit: 'sleepily',
};

export const sampleWithFullData: IAccounts = {
  id: 22096,
  code: 'harmonize although shameless',
  date: dayjs('2024-10-02T20:46'),
  name: 'from',
  description: 'regularly',
  type: 18972,
  parent: 29916,
  balance: 13807.54,
  lmu: 1020,
  lmd: dayjs('2024-10-02T17:22'),
  hasbatches: true,
  accountvalue: 10738.19,
  accountlevel: 21084,
  accountsnumberingsystem: 26738,
  subparentid: 27376,
  canedit: false,
  amount: 8835.59,
  creditamount: 32374.37,
  debitamount: 1407.81,
  debitorcredit: 'whenever canteen',
  reporttype: 17380,
};

export const sampleWithNewData: NewAccounts = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
