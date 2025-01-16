import dayjs from 'dayjs/esm';

import { ICompanybankaccount, NewCompanybankaccount } from './companybankaccount.model';

export const sampleWithRequiredData: ICompanybankaccount = {
  id: 30277,
};

export const sampleWithPartialData: ICompanybankaccount = {
  id: 22841,
  companyid: 9754,
  accountnumber: 'aching',
  accountname: 'worn',
  accountcode: 'earplug below',
  lmd: dayjs('2024-10-02T16:33'),
  lmu: 'between propose cloudy',
  isactive: false,
  accounttypeid: 13006,
};

export const sampleWithFullData: ICompanybankaccount = {
  id: 18922,
  companyid: 5590,
  accountnumber: 'before hm',
  accountname: 'ouch batch supposing',
  bankname: 'buff hedgehog stable',
  bankid: 10128,
  branchname: 'former or ironclad',
  branchid: 6258,
  amount: 28847.52,
  accountcode: 'submissive',
  accountid: 5772,
  lmd: dayjs('2024-10-03T03:16'),
  lmu: 'tinker',
  isactive: true,
  accounttypeid: 26533,
};

export const sampleWithNewData: NewCompanybankaccount = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
