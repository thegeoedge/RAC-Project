import dayjs from 'dayjs/esm';

import { IHoisttype, NewHoisttype } from './hoisttype.model';

export const sampleWithRequiredData: IHoisttype = {
  id: 14363,
};

export const sampleWithPartialData: IHoisttype = {
  id: 3300,
  hoisttype: 'abnormality devour',
  lmu: 3134,
};

export const sampleWithFullData: IHoisttype = {
  id: 3979,
  hoisttype: 'why true',
  lmu: 4099,
  lmd: dayjs('2024-10-20T17:38'),
};

export const sampleWithNewData: NewHoisttype = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
