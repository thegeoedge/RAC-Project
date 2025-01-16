import dayjs from 'dayjs/esm';

import { IServicecategory, NewServicecategory } from './servicecategory.model';

export const sampleWithRequiredData: IServicecategory = {
  id: 3752,
};

export const sampleWithPartialData: IServicecategory = {
  id: 18616,
  name: 'geez lest',
  description: 'rash action',
  lmu: 5235,
  sortorder: 8427,
  isactive: false,
};

export const sampleWithFullData: IServicecategory = {
  id: 217,
  name: 'gadzooks',
  description: 'wherever sans',
  lmu: 28504,
  lmd: dayjs('2024-08-19T07:10'),
  showsecurity: false,
  sortorder: 14013,
  isactive: false,
};

export const sampleWithNewData: NewServicecategory = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
