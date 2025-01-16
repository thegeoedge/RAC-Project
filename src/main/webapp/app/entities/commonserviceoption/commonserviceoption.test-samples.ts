import dayjs from 'dayjs/esm';

import { ICommonserviceoption, NewCommonserviceoption } from './commonserviceoption.model';

export const sampleWithRequiredData: ICommonserviceoption = {
  id: 7619,
};

export const sampleWithPartialData: ICommonserviceoption = {
  id: 22875,
  mainid: 15197,
  name: 'concerning alongside',
  description: 'masquerade',
  value: 19390.85,
  lmd: dayjs('2024-10-04T00:14'),
  lmu: 31277,
};

export const sampleWithFullData: ICommonserviceoption = {
  id: 9902,
  mainid: 20692,
  code: 'elementary bitter than',
  name: 'phooey',
  description: 'instead ah',
  value: 24818.17,
  isactive: true,
  lmd: dayjs('2024-10-03T13:19'),
  lmu: 29658,
};

export const sampleWithNewData: NewCommonserviceoption = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
