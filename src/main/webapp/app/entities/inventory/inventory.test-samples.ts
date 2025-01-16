import dayjs from 'dayjs/esm';

import { IInventory, NewInventory } from './inventory.model';

export const sampleWithRequiredData: IInventory = {
  id: 23284,
};

export const sampleWithPartialData: IInventory = {
  id: 7856,
  type: 22769,
  classification1: 'hmph whoa wall',
  classification3: 'honestly greatly below',
  classification4: 'emboss offensively vainly',
  unitofmeasurement: 'oof as anticipate',
  isassemblyunit: true,
  reorderlevel: 4045.55,
  hasbatches: false,
  itemimagepath: 'junior doubtfully',
  bufferlevel: 2378.86,
  leadtime: 8503.44,
  saftydays: 19093.83,
  accountcode: 'luxurious',
  accountid: 6855,
  casepackqty: 1748,
  isregistered: false,
  barcodeimage: '../fake-data/blob/hipster.png',
  barcodeimageContentType: 'unknown',
  checktypeid: 13590,
  notininvoice: true,
};

export const sampleWithFullData: IInventory = {
  id: 23502,
  code: 'where',
  partnumber: 'nor den',
  name: 'fill dimly',
  description: 'frustrate',
  type: 17457,
  classification1: 'each why pricey',
  classification2: 'duplexer',
  classification3: 'anchored woozy',
  classification4: 'silently',
  classification5: 'unwritten if including',
  unitofmeasurement: 'the',
  decimalplaces: 7395,
  isassemblyunit: false,
  assemblyunitof: 3586,
  reorderlevel: 11044.28,
  lastcost: 29084.39,
  lastsellingprice: 12133.65,
  lmu: 1079,
  lmd: dayjs('2024-08-20T03:41'),
  availablequantity: 31269.46,
  hasbatches: true,
  itemspecfilepath: 'whoa instead',
  itemimagepath: 'lop instead',
  returnprice: 31776.39,
  activeitem: false,
  minstock: 25200.14,
  maxstock: 7177.72,
  dailyaverage: 1031.05,
  bufferlevel: 8403.53,
  leadtime: 15754.82,
  buffertime: 2964.75,
  saftydays: 29182.47,
  accountcode: 'provided',
  accountid: 19183,
  casepackqty: 11320,
  isregistered: false,
  defaultstocklocationid: 25324,
  rackno: 'consequently harm',
  barcodeimage: '../fake-data/blob/hipster.png',
  barcodeimageContentType: 'unknown',
  commissionempid: 5702,
  checktypeid: 20592,
  checktype: 'above',
  reorderqty: 25212.13,
  notininvoice: true,
};

export const sampleWithNewData: NewInventory = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
