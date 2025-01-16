import dayjs from 'dayjs/esm';

import { IBanks, NewBanks } from './banks.model';

export const sampleWithRequiredData: IBanks = {
  id: 280,
};

export const sampleWithPartialData: IBanks = {
  id: 15191,
  code: 'aw divide',
  name: 'untrue',
  lmd: dayjs('2024-10-02T16:39'),
};

export const sampleWithFullData: IBanks = {
  id: 29803,
  code: 'beside',
  name: 'scream after',
  description: 'lobotomise yuck',
  lmu: 14922,
  lmd: dayjs('2024-10-02T08:29'),
};

export const sampleWithNewData: NewBanks = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
