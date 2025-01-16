import { IAutocareservicemillages, NewAutocareservicemillages } from './autocareservicemillages.model';

export const sampleWithRequiredData: IAutocareservicemillages = {
  id: 25563,
};

export const sampleWithPartialData: IAutocareservicemillages = {
  id: 2377,
  millage: 29185,
};

export const sampleWithFullData: IAutocareservicemillages = {
  id: 10366,
  millage: 9381,
};

export const sampleWithNewData: NewAutocareservicemillages = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
