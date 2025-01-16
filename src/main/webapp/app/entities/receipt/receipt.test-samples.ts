import dayjs from 'dayjs/esm';

import { IReceipt, NewReceipt } from './receipt.model';

export const sampleWithRequiredData: IReceipt = {
  id: 6805,
};

export const sampleWithPartialData: IReceipt = {
  id: 3960,
  customername: 'for soprano',
  customeraddress: 'considering',
  totalamount: 19631.63,
  totalamountinword: 'orchestrate perform infamous',
  comments: 'into',
  lmu: 11080,
  term: 'why',
  date: dayjs('2024-10-03T00:14'),
  checkdate: dayjs('2024-10-03T01:15'),
  checkno: 'tom-tom till',
  customerid: 17110,
  deposited: true,
};

export const sampleWithFullData: IReceipt = {
  id: 13215,
  code: 'trigonometry trailpatrol cytokine',
  receiptdate: dayjs('2024-10-02T17:13'),
  customername: 'mixed',
  customeraddress: 'slit lover',
  totalamount: 29942.95,
  totalamountinword: 'righteously',
  comments: 'stabilise stockpile which',
  lmu: 32109,
  lmd: dayjs('2024-10-02T14:28'),
  termid: 14172,
  term: 'solicit whether but',
  date: dayjs('2024-10-03T01:05'),
  amount: 11538.31,
  checkdate: dayjs('2024-10-03T08:05'),
  checkno: 'deprecate',
  bank: 'overhead',
  customerid: 24786,
  isactive: false,
  deposited: true,
  createdby: 25607,
};

export const sampleWithNewData: NewReceipt = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
