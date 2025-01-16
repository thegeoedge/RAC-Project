import dayjs from 'dayjs/esm';

import { IEmpJobcommission, NewEmpJobcommission } from './emp-jobcommission.model';

export const sampleWithRequiredData: IEmpJobcommission = {
  id: 16932,
  vehcatid: 13060,
  autojobcatid: 32277,
};

export const sampleWithPartialData: IEmpJobcommission = {
  id: 25502,
  vehcatid: 14548,
  autojobcatid: 23872,
  secondcom: 14243.16,
  thirdcom: 1777.1,
  lmd: dayjs('2024-10-02T16:04'),
};

export const sampleWithFullData: IEmpJobcommission = {
  id: 17303,
  vehcatid: 19513,
  autojobcatid: 7819,
  firstcom: 13000.81,
  secondcom: 23806.59,
  thirdcom: 1064.41,
  lmd: dayjs('2024-10-02T19:11'),
  lmu: 7292,
};

export const sampleWithNewData: NewEmpJobcommission = {
  vehcatid: 27512,
  autojobcatid: 4021,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
