import dayjs from 'dayjs/esm';

import { IServicesubcategory, NewServicesubcategory } from './servicesubcategory.model';

export const sampleWithRequiredData: IServicesubcategory = {
  id: 23643,
};

export const sampleWithPartialData: IServicesubcategory = {
  id: 30351,
  description: 'ick',
  mainname: 'tan where',
  lmu: 8392,
  lmd: dayjs('2024-08-20T21:07'),
};

export const sampleWithFullData: IServicesubcategory = {
  id: 16972,
  name: 'apropos',
  description: 'praise meanwhile',
  mainid: 17204,
  mainname: 'engorge moan',
  lmu: 26758,
  lmd: dayjs('2024-08-20T09:38'),
  isactive: false,
};

export const sampleWithNewData: NewServicesubcategory = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
