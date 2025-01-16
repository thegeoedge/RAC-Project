import dayjs from 'dayjs/esm';

import { IVehicletype, NewVehicletype } from './vehicletype.model';

export const sampleWithRequiredData: IVehicletype = {
  id: 13529,
};

export const sampleWithPartialData: IVehicletype = {
  id: 15993,
  vehicletypediscription: 'hm',
  lmd: dayjs('2024-08-19T17:09'),
  catid: 7023,
};

export const sampleWithFullData: IVehicletype = {
  id: 2482,
  vehicletype: 'gloomy famously',
  vehicletypediscription: 'trash hammer',
  lmu: 18875,
  lmd: dayjs('2024-08-20T05:41'),
  catid: 25962,
  catname: 'untried',
};

export const sampleWithNewData: NewVehicletype = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
