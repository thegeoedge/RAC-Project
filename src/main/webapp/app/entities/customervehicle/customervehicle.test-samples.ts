import dayjs from 'dayjs/esm';

import { ICustomervehicle, NewCustomervehicle } from './customervehicle.model';

export const sampleWithRequiredData: ICustomervehicle = {
  id: 26328,
};

export const sampleWithPartialData: ICustomervehicle = {
  id: 21068,
  customerid: 20502,
  categoryid: 32222,
  typeid: 1922,
  typename: 'towards striped sink',
  remarks: 'pish jeer',
  engNo: 'circa tensely so',
  nextservicedate: dayjs('2024-08-19'),
  lmd: dayjs('2024-08-20T00:56'),
};

export const sampleWithFullData: ICustomervehicle = {
  id: 18285,
  customerid: 13289,
  vehiclenumber: 'before govern trafficker',
  categoryid: 17252,
  categoryname: 'monster',
  typeid: 27659,
  typename: 'trained how worriedly',
  makeid: 30159,
  makename: 'ack anenst',
  model: 'begonia',
  yom: 'pish',
  customercode: 'filly',
  remarks: 'from',
  servicecount: 29708,
  engNo: 'a gadzooks',
  chaNo: 'till',
  milage: 'always',
  lastservicedate: dayjs('2024-08-19'),
  nextservicedate: dayjs('2024-08-19'),
  lmu: 20937,
  lmd: dayjs('2024-08-20T02:33'),
  nextgearoilmilage: 'eyelash clean supplant',
  nextmilage: 'angrily',
  serviceperiod: 7600,
};

export const sampleWithNewData: NewCustomervehicle = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
