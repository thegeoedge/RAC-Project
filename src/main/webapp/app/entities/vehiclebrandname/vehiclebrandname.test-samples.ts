import dayjs from 'dayjs/esm';

import { IVehiclebrandname, NewVehiclebrandname } from './vehiclebrandname.model';

export const sampleWithRequiredData: IVehiclebrandname = {
  id: 22781,
};

export const sampleWithPartialData: IVehiclebrandname = {
  id: 13260,
  brandname: 'yippee pish',
  lmu: 6109,
  companyid: 15575,
};

export const sampleWithFullData: IVehiclebrandname = {
  id: 18230,
  brandname: 'so',
  description: 'er hm',
  lmu: 26382,
  lmd: dayjs('2024-08-22T17:30'),
  companyid: 6263,
};

export const sampleWithNewData: NewVehiclebrandname = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
