import dayjs from 'dayjs/esm';

import { IAutocarecompany, NewAutocarecompany } from './autocarecompany.model';

export const sampleWithRequiredData: IAutocarecompany = {
  id: 20594,
};

export const sampleWithPartialData: IAutocarecompany = {
  id: 13903,
  name: 'twist',
  bodypaint: 'fortunate',
  generalphone: 'too mallard',
  email: 'Bennett84@yahoo.com',
  lmd: dayjs('2024-10-04T01:09'),
  vatregnumber: 'reappoint until',
};

export const sampleWithFullData: IAutocarecompany = {
  id: 20087,
  name: 'digitalize',
  address: 'tattered',
  servicephone: 'envious bumpy consequently',
  sparepartphone: 'till',
  bodypaint: 'immediate',
  generalphone: 'blaring pound',
  fax: 'excepting',
  email: 'Ericka_Sporer61@yahoo.com',
  description: 'geez predate',
  lmu: 28406,
  lmd: dayjs('2024-10-03T21:56'),
  vatregnumber: 'ace adventurously',
  tinnumber: 'improbable smoothly',
  accountcode: 'versus',
  accountid: 27934,
};

export const sampleWithNewData: NewAutocarecompany = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
