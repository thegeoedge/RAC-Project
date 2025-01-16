import dayjs from 'dayjs/esm';

import { IWorkshopvehiclework, NewWorkshopvehiclework } from './workshopvehiclework.model';

export const sampleWithRequiredData: IWorkshopvehiclework = {
  id: 6106,
};

export const sampleWithPartialData: IWorkshopvehiclework = {
  id: 22167,
  jobid: 26741,
  customerid: 4882,
  customername: 'hence',
  contactno: 'forenenst',
  vehicleno: 'organic interpolate',
  vehiclebrand: 'pootle',
  vehiclemodel: 'daily',
  addeddate: dayjs('2024-08-22T11:52'),
  remarks: 'if sedately even',
  calldate: dayjs('2024-08-22T14:27'),
  lmu: 934,
  lmd: dayjs('2024-08-23T00:21'),
};

export const sampleWithFullData: IWorkshopvehiclework = {
  id: 7509,
  jobid: 6430,
  vehicleid: 9423,
  customerid: 21331,
  customername: 'power trivial',
  contactno: 'likewise upon',
  vehicleno: 'juicy',
  vehiclebrand: 'wherever hmph',
  vehiclemodel: 'uncommon half-sister silently',
  mileage: 'ice boastfully',
  addeddate: dayjs('2024-08-23T09:58'),
  iscalltocustomer: true,
  remarks: 'as recognition amused',
  calldate: dayjs('2024-08-23T08:06'),
  lmu: 15597,
  lmd: dayjs('2024-08-23T00:35'),
};

export const sampleWithNewData: NewWorkshopvehiclework = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
