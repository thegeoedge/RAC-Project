import { IAutocarecancelitemopt, NewAutocarecancelitemopt } from './autocarecancelitemopt.model';

export const sampleWithRequiredData: IAutocarecancelitemopt = {
  id: 1994,
};

export const sampleWithPartialData: IAutocarecancelitemopt = {
  id: 20127,
};

export const sampleWithFullData: IAutocarecancelitemopt = {
  id: 15845,
  canceloption: 'leg quirkily',
};

export const sampleWithNewData: NewAutocarecancelitemopt = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
