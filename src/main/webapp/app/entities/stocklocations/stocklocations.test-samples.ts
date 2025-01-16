import { IStocklocations, NewStocklocations } from './stocklocations.model';

export const sampleWithRequiredData: IStocklocations = {
  id: 7185,
};

export const sampleWithPartialData: IStocklocations = {
  id: 31045,
  locationname: 'pish',
  locationcode: 'whether riposte across',
};

export const sampleWithFullData: IStocklocations = {
  id: 14483,
  locationname: 'phooey',
  locationcode: 'fortunately',
  description: 'phew',
};

export const sampleWithNewData: NewStocklocations = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
