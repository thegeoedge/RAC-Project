import dayjs from 'dayjs/esm';

import { ISalesorder, NewSalesorder } from './salesorder.model';

export const sampleWithRequiredData: ISalesorder = {
  id: 2482,
};

export const sampleWithPartialData: ISalesorder = {
  id: 21886,
  orderdate: dayjs('2024-10-02T09:32'),
  quoteid: 29978,
  salesrepname: 'nor',
  customername: 'corrupt than ouch',
  customeraddress: 'gel blaring',
  deliveryaddress: 'so',
  totaldiscount: 12600.21,
  nettotal: 14336.69,
  message: 'eek pish',
  refinvoiceid: 13706,
  lmd: dayjs('2024-10-02T21:41'),
  isfixed: true,
  advancepaymentreturndate: dayjs('2024-10-02T20:32'),
  advancepaymentby: 12368,
};

export const sampleWithFullData: ISalesorder = {
  id: 8383,
  code: 'of known',
  orderdate: dayjs('2024-10-03T00:31'),
  createddate: dayjs('2024-10-02T09:45'),
  quoteid: 15896,
  salesrepid: 29286,
  salesrepname: 'alive beside',
  delieverfrom: 'guard',
  customerid: 32600,
  customername: 'afore',
  customeraddress: 'finally',
  deliveryaddress: 'stoke string',
  subtotal: 3144.67,
  totaltax: 27014.71,
  totaldiscount: 16294.78,
  nettotal: 21950.35,
  message: 'beneath kindheartedly',
  isinvoiced: false,
  refinvoiceid: 19668,
  lmu: 21249,
  lmd: dayjs('2024-10-02T22:15'),
  isfixed: true,
  isactive: false,
  advancepayment: 12468.79,
  advancepaymentreturnamount: 19058.03,
  advancepaymentreturndate: dayjs('2024-10-02T17:34'),
  advancepaymentby: 3488,
};

export const sampleWithNewData: NewSalesorder = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
