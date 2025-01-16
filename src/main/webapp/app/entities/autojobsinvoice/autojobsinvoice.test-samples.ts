import dayjs from 'dayjs/esm';

import { IAutojobsinvoice, NewAutojobsinvoice } from './autojobsinvoice.model';

export const sampleWithRequiredData: IAutojobsinvoice = {
  id: 31679,
};

export const sampleWithPartialData: IAutojobsinvoice = {
  id: 12529,
  createddate: dayjs('2024-10-03T14:08'),
  quoteid: 32300,
  autojobsrepid: 17701,
  delieverfrom: 'now',
  customeraddress: 'tomorrow',
  subtotal: 30978.76,
  totaltax: 29767.29,
  totaldiscount: 18027.5,
  nettotal: 773.42,
  amountowing: 28796.27,
  isactive: true,
  locationcode: 'some',
  referencecode: 'chronicle',
  createdbyid: 23673,
};

export const sampleWithFullData: IAutojobsinvoice = {
  id: 1259,
  code: 'since idle disdain',
  invoicedate: dayjs('2024-10-03T14:20'),
  createddate: dayjs('2024-10-04T01:41'),
  jobid: 7913,
  quoteid: 17320,
  orderid: 12530,
  delieverydate: dayjs('2024-10-03T17:48'),
  autojobsrepid: 10572,
  autojobsrepname: 'heavy',
  delieverfrom: 'fresco hmph',
  customerid: 9708,
  customername: 'lionise so',
  customeraddress: 'customise',
  deliveryaddress: 'but',
  subtotal: 20898.06,
  totaltax: 28303.83,
  totaldiscount: 21435.63,
  nettotal: 8199.26,
  message: 'suspiciously',
  lmu: 1783,
  lmd: dayjs('2024-10-03T23:57'),
  paidamount: 1592.03,
  amountowing: 13779.7,
  isactive: false,
  locationid: 14372,
  locationcode: 'likewise knight',
  referencecode: 'unto',
  createdbyid: 8594,
  createdbyname: 'youthfully',
  autocarecompanyid: 8760,
};

export const sampleWithNewData: NewAutojobsinvoice = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
