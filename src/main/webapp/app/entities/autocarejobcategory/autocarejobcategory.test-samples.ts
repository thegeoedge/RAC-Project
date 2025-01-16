import dayjs from 'dayjs/esm';

import { IAutocarejobcategory, NewAutocarejobcategory } from './autocarejobcategory.model';

export const sampleWithRequiredData: IAutocarejobcategory = {
  id: 4141,
};

export const sampleWithPartialData: IAutocarejobcategory = {
  id: 4856,
  code: 'crunch fulfill',
  displayorder: 10741,
};

export const sampleWithFullData: IAutocarejobcategory = {
  id: 2741,
  code: 'valuable never',
  name: 'meh whether',
  description: 'woof extremely about',
  lmu: 12870,
  lmd: dayjs('2024-08-25T16:10'),
  displayorder: 6345,
};

export const sampleWithNewData: NewAutocarejobcategory = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
