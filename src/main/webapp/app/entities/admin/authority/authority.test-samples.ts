import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: '37ca2e60-b36b-4ff9-8c9a-99d05f779f1e',
};

export const sampleWithPartialData: IAuthority = {
  name: '59171e45-d8b7-4c11-8ae8-80fdf4391307',
};

export const sampleWithFullData: IAuthority = {
  name: '69c1c2c1-862d-4667-b66a-82a778ba1b4d',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
