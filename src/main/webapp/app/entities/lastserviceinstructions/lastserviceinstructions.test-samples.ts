import dayjs from 'dayjs/esm';

import { ILastserviceinstructions, NewLastserviceinstructions } from './lastserviceinstructions.model';

export const sampleWithRequiredData: ILastserviceinstructions = {
  id: 9885,
};

export const sampleWithPartialData: ILastserviceinstructions = {
  id: 30733,
  isactive: false,
  lmd: dayjs('2024-10-03T12:11'),
  ignore: false,
  vehicleno: 'authorized allow',
};

export const sampleWithFullData: ILastserviceinstructions = {
  id: 13747,
  jobid: 25204,
  instruction: 'tram maximise',
  isactive: false,
  lmu: 14106,
  lmd: dayjs('2024-10-04T06:34'),
  ignore: false,
  vehicleid: 31367,
  vehicleno: 'yet interestingly',
};

export const sampleWithNewData: NewLastserviceinstructions = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
