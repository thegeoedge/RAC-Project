import dayjs from 'dayjs/esm';

import { IAutocareappointment, NewAutocareappointment } from './autocareappointment.model';

export const sampleWithRequiredData: IAutocareappointment = {
  id: 11837,
};

export const sampleWithPartialData: IAutocareappointment = {
  id: 72,
  appointmenttype: 21477,
  addeddate: dayjs('2024-08-19T18:31'),
  conformdate: dayjs('2024-08-19T14:52'),
  lmu: 31570,
  customername: 'whether',
  iscancel: true,
  customermobileid: 18205,
  advancepayment: 5138.09,
};

export const sampleWithFullData: IAutocareappointment = {
  id: 14325,
  appointmenttype: 27509,
  appointmentdate: dayjs('2024-08-19T14:37'),
  addeddate: dayjs('2024-08-20T01:56'),
  conformdate: dayjs('2024-08-20T04:48'),
  appointmentnumber: 4919,
  vehiclenumber: 'modulo',
  appointmenttime: dayjs('2024-08-19T08:49'),
  isconformed: true,
  conformedby: 23527,
  lmd: dayjs('2024-08-19T12:04'),
  lmu: 12015,
  customerid: 16595,
  contactnumber: 'awkwardly by',
  customername: 'mom',
  issued: false,
  hoistid: 23145,
  isarrived: true,
  iscancel: true,
  isnoanswer: true,
  missedappointmentcall: 'sit',
  customermobileid: 12269,
  customermobilevehicleid: 19068,
  vehicleid: 3699,
  ismobileappointment: true,
  advancepayment: 9085.6,
  jobid: 20359,
};

export const sampleWithNewData: NewAutocareappointment = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
