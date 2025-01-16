import { IPaymentterm, NewPaymentterm } from './paymentterm.model';

export const sampleWithRequiredData: IPaymentterm = {
  id: 29970,
};

export const sampleWithPartialData: IPaymentterm = {
  id: 8619,
  paymenttype: 'instead',
};

export const sampleWithFullData: IPaymentterm = {
  id: 5715,
  paymenttype: 'brr',
  forvoucher: true,
};

export const sampleWithNewData: NewPaymentterm = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
