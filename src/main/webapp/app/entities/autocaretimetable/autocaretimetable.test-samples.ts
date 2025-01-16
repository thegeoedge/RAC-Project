import dayjs from 'dayjs/esm';

import { IAutocaretimetable, NewAutocaretimetable } from './autocaretimetable.model';

export const sampleWithRequiredData: IAutocaretimetable = {
  id: 24486,
};

export const sampleWithPartialData: IAutocaretimetable = {
  id: 4630,
  hoisttypeid: 15910,
  hoisttypename: 'any aha pish',
  hoisttime: dayjs('2024-10-28T20:06'),
};

export const sampleWithFullData: IAutocaretimetable = {
  id: 18741,
  hoisttypeid: 13520,
  hoisttypename: 'broadly icy that',
  hoisttime: dayjs('2024-10-29T04:13'),
};

export const sampleWithNewData: NewAutocaretimetable = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
