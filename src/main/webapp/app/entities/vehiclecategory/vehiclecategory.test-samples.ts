import dayjs from 'dayjs/esm';

import { IVehiclecategory, NewVehiclecategory } from './vehiclecategory.model';

export const sampleWithRequiredData: IVehiclecategory = {
  id: 3149,
};

export const sampleWithPartialData: IVehiclecategory = {
  id: 19986,
  vehiclecategory: 'commonly',
  vehiclecategorydiscription: 'buff angrily phooey',
};

export const sampleWithFullData: IVehiclecategory = {
  id: 74,
  vehiclecategory: 'wide blah messy',
  vehiclecategorydiscription: 'aw amongst',
  lmu: 11031,
  lmd: dayjs('2024-08-19T11:00'),
};

export const sampleWithNewData: NewVehiclecategory = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
