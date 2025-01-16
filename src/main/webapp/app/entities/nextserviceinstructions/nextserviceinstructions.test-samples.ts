import dayjs from 'dayjs/esm';

import { INextserviceinstructions, NewNextserviceinstructions } from './nextserviceinstructions.model';

export const sampleWithRequiredData: INextserviceinstructions = {
  id: 25714,
};

export const sampleWithPartialData: INextserviceinstructions = {
  id: 30882,
  isactive: true,
  lmu: 32109,
  lmd: dayjs('2024-10-03T13:25'),
};

export const sampleWithFullData: INextserviceinstructions = {
  id: 14675,
  jobid: 8750,
  instruction: 'pledge',
  isactive: false,
  lmu: 26761,
  lmd: dayjs('2024-10-03T12:39'),
  ignore: true,
  vehicleid: 12857,
  vehicleno: 'cultivator absent which',
};

export const sampleWithNewData: NewNextserviceinstructions = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
