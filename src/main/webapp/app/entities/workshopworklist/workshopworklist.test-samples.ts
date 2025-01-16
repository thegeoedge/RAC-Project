import dayjs from 'dayjs/esm';

import { IWorkshopworklist, NewWorkshopworklist } from './workshopworklist.model';

export const sampleWithRequiredData: IWorkshopworklist = {
  id: 2335,
};

export const sampleWithPartialData: IWorkshopworklist = {
  id: 25522,
  workshopwork: 'loyalty always once',
  workshopworkdescription: 'nocturnal consequently',
};

export const sampleWithFullData: IWorkshopworklist = {
  id: 28727,
  workshopwork: 'meh',
  workshopworkdescription: 'happy silent durian',
  isactive: true,
  lmd: dayjs('2024-08-22T23:34'),
  lmu: 1947,
};

export const sampleWithNewData: NewWorkshopworklist = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
