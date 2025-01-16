import dayjs from 'dayjs/esm';

import { ILocationbasedstock, NewLocationbasedstock } from './locationbasedstock.model';

export const sampleWithRequiredData: ILocationbasedstock = {
  id: 26248,
  itemid: 6908,
  locationid: 6579,
};

export const sampleWithPartialData: ILocationbasedstock = {
  id: 15749,
  itemid: 12010,
  locationid: 25231,
  availablequantity: 21482.35,
  lmu: 21468,
};

export const sampleWithFullData: ILocationbasedstock = {
  id: 22110,
  itemid: 17579,
  code: 'physically aside',
  name: 'overdo although drinking',
  locationid: 25795,
  locationcode: 'toward inasmuch worth',
  availablequantity: 24447.03,
  hasbatches: false,
  lmu: 25819,
  lmd: dayjs('2024-10-04T01:01'),
};

export const sampleWithNewData: NewLocationbasedstock = {
  itemid: 22041,
  locationid: 1522,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
