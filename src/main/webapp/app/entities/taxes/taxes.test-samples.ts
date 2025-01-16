import dayjs from 'dayjs/esm';

import { ITaxes, NewTaxes } from './taxes.model';

export const sampleWithRequiredData: ITaxes = {
  id: 19949,
};

export const sampleWithPartialData: ITaxes = {
  id: 23564,
  effectiveto: dayjs('2024-10-02'),
  fixedamount: 26348.79,
  ismanual: true,
  isactive: false,
  lmu: 2135,
  lmd: dayjs('2024-10-02T15:38'),
};

export const sampleWithFullData: ITaxes = {
  id: 7794,
  code: 'instead than',
  description: 'unbearably',
  effectivefrom: dayjs('2024-10-02'),
  effectiveto: dayjs('2024-10-02'),
  percentage: 21470.96,
  fixedamount: 17358.09,
  ismanual: false,
  isactive: false,
  lmu: 20679,
  lmd: dayjs('2024-10-03T04:18'),
};

export const sampleWithNewData: NewTaxes = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
