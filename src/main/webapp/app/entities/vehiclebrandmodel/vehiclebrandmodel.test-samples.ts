import dayjs from 'dayjs/esm';

import { IVehiclebrandmodel, NewVehiclebrandmodel } from './vehiclebrandmodel.model';

export const sampleWithRequiredData: IVehiclebrandmodel = {
  id: 8666,
};

export const sampleWithPartialData: IVehiclebrandmodel = {
  id: 6514,
  brandid: 22088,
};

export const sampleWithFullData: IVehiclebrandmodel = {
  id: 13762,
  brandid: 9388,
  brandname: 'phooey wannabe structure',
  model: 'aboard uh-huh obedience',
  lmu: 32527,
  lmd: dayjs('2024-08-22T16:53'),
};

export const sampleWithNewData: NewVehiclebrandmodel = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
