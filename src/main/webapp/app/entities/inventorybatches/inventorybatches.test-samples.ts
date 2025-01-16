import dayjs from 'dayjs/esm';

import { IInventorybatches, NewInventorybatches } from './inventorybatches.model';

export const sampleWithRequiredData: IInventorybatches = {
  id: 2896,
};

export const sampleWithPartialData: IInventorybatches = {
  id: 15444,
  itemid: 8502,
  txdate: dayjs('2024-08-22T09:12'),
  price: 7919.39,
  costwithoutvat: 32540.63,
  lmu: 17618,
  lmd: dayjs('2024-08-22T15:23'),
  manufacturedate: dayjs('2024-08-22T20:55'),
  expiredate: dayjs('2024-08-23T04:41'),
  addeddate: dayjs('2024-08-22T20:20'),
  costtotal: 2195.15,
  returnprice: 31193.85,
};

export const sampleWithFullData: IInventorybatches = {
  id: 21832,
  itemid: 7498,
  code: 'though manager whoa',
  txdate: dayjs('2024-08-22T15:41'),
  cost: 3089.58,
  price: 28723.57,
  costwithoutvat: 27778.28,
  pricewithoutvat: 28241.16,
  notes: 'ew',
  lmu: 13305,
  lmd: dayjs('2024-08-22T19:10'),
  lineid: 14847,
  manufacturedate: dayjs('2024-08-23T07:32'),
  expiredate: dayjs('2024-08-22T15:52'),
  quantity: 21736.98,
  addeddate: dayjs('2024-08-22T21:56'),
  costtotal: 3737.11,
  returnprice: 32132.24,
};

export const sampleWithNewData: NewInventorybatches = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
