import dayjs from 'dayjs/esm';

import { IAutocarehoist, NewAutocarehoist } from './autocarehoist.model';

export const sampleWithRequiredData: IAutocarehoist = {
  id: 4305,
};

export const sampleWithPartialData: IAutocarehoist = {
  id: 8271,
  hoisttypeid: 27452,
  lmu: 13425,
  lmd: dayjs('2024-10-20T18:25'),
};

export const sampleWithFullData: IAutocarehoist = {
  id: 1113,
  hoistname: 'though phooey justly',
  hoistcode: 'gleeful',
  hoisttypeid: 31926,
  hoisttypename: 'hard-to-find',
  lmu: 27888,
  lmd: dayjs('2024-10-20T23:24'),
};

export const sampleWithNewData: NewAutocarehoist = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
