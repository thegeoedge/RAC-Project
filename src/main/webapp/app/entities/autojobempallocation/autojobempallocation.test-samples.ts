import dayjs from 'dayjs/esm';

import { IAutojobempallocation, NewAutojobempallocation } from './autojobempallocation.model';

export const sampleWithRequiredData: IAutojobempallocation = {
  id: 19220,
};

export const sampleWithPartialData: IAutojobempallocation = {
  id: 2582,
  jobid: 8301,
  categoryid: 14570,
  empposition: 29698,
  allocatetime: dayjs('2024-08-25T05:18'),
  lmu: 3407,
  jobdate: dayjs('2024-08-25T06:19'),
  commission: 30288.89,
  iscommissionpaid: true,
  endtime: dayjs('2024-08-25T09:11'),
};

export const sampleWithFullData: IAutojobempallocation = {
  id: 9229,
  jobid: 27721,
  categoryid: 19125,
  empposition: 31997,
  empid: 5030,
  empname: 'oddly hungrily',
  allocatetime: dayjs('2024-08-25T08:01'),
  lmu: 14379,
  lmd: dayjs('2024-08-25T05:37'),
  jobdate: dayjs('2024-08-25T13:51'),
  commission: 25471.47,
  iscommissionpaid: true,
  starttime: dayjs('2024-08-25T11:19'),
  endtime: dayjs('2024-08-25T22:45'),
};

export const sampleWithNewData: NewAutojobempallocation = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
