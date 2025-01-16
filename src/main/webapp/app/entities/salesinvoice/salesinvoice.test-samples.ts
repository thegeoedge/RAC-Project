import dayjs from 'dayjs/esm';

import { ISalesinvoice, NewSalesinvoice } from './salesinvoice.model';

export const sampleWithRequiredData: ISalesinvoice = {
  id: 11864,
};

export const sampleWithPartialData: ISalesinvoice = {
  id: 22817,
  delieverydate: dayjs('2024-10-02T18:31'),
  salesrepid: 25016,
  salesrepname: 'gratefully adapt',
  delieverfrom: 'amidst',
  customerid: 15560,
  customername: 'scarcely',
  customeraddress: 'opposite meaningfully annually',
  subtotal: 11468.59,
  totaltax: 27855.14,
  totaldiscount: 18603.86,
  nettotal: 31304.14,
  lmd: dayjs('2024-10-02T08:18'),
  isactive: true,
  createdbyid: 11554,
  autocarejobid: 30853,
  nextmeter: 'abstain',
  currentmeter: 'complicated scrabble supposing',
  remarks: 'gee mild',
  dummybillid: 6405,
  isserviceinvoice: true,
  iscompanyinvoice: true,
  isvatinvoice: true,
  advancepayment: 21243.38,
};

export const sampleWithFullData: ISalesinvoice = {
  id: 7456,
  code: 'pleasant',
  invoicedate: dayjs('2024-10-02T23:07'),
  createddate: dayjs('2024-10-03T02:44'),
  quoteid: 8588,
  orderid: 11604,
  delieverydate: dayjs('2024-10-02T20:07'),
  salesrepid: 23229,
  salesrepname: 'valiantly',
  delieverfrom: 'aw',
  customerid: 10415,
  customername: 'until yoga',
  customeraddress: 'compensation',
  deliveryaddress: 'aha boo',
  subtotal: 4058.6,
  totaltax: 2990.99,
  totaldiscount: 21131.2,
  nettotal: 6809.59,
  message: 'flaky yet',
  lmu: 23182,
  lmd: dayjs('2024-10-02T16:50'),
  paidamount: 11273.4,
  amountowing: 13364.25,
  isactive: true,
  locationid: 7516,
  locationcode: 'phooey luxurious whoever',
  referencecode: 'objective softly essential',
  createdbyid: 22868,
  createdbyname: 'geez bud',
  autocarecharges: 18498.24,
  autocarejobid: 22955,
  vehicleno: 'loosely barring',
  nextmeter: 'roughen',
  currentmeter: 'judge judgementally',
  remarks: 'lest tune',
  hasdummybill: false,
  dummybillid: 2907,
  dummybillamount: 20532.49,
  dummycommision: 24279.75,
  isserviceinvoice: false,
  nbtamount: 5731.04,
  vatamount: 26478.15,
  autocarecompanyid: 13989,
  iscompanyinvoice: false,
  invcanceldate: dayjs('2024-10-02T07:55'),
  invcancelby: 13165,
  isvatinvoice: false,
  paymenttype: 'befit',
  pendingamount: 29649.26,
  advancepayment: 14819.86,
  discountcode: 'excepting',
};

export const sampleWithNewData: NewSalesinvoice = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
