import { IAutocareappointmenttype, NewAutocareappointmenttype } from './autocareappointmenttype.model';

export const sampleWithRequiredData: IAutocareappointmenttype = {
  id: 16238,
};

export const sampleWithPartialData: IAutocareappointmenttype = {
  id: 11027,
  typename: 'for ugh against',
};

export const sampleWithFullData: IAutocareappointmenttype = {
  id: 15725,
  typename: 'flaunt handmaiden',
};

export const sampleWithNewData: NewAutocareappointmenttype = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
