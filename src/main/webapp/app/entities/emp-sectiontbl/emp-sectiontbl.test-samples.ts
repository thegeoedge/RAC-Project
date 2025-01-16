import dayjs from 'dayjs/esm';

import { IEmpSectiontbl, NewEmpSectiontbl } from './emp-sectiontbl.model';

export const sampleWithRequiredData: IEmpSectiontbl = {
  id: 19593,
};

export const sampleWithPartialData: IEmpSectiontbl = {
  id: 28526,
  empid: 11470,
  sectionid: 24387,
  lmu: 18685,
};

export const sampleWithFullData: IEmpSectiontbl = {
  id: 1940,
  empid: 13410,
  sectionid: 32628,
  lmd: dayjs('2024-10-01T14:09'),
  lmu: 346,
};

export const sampleWithNewData: NewEmpSectiontbl = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
