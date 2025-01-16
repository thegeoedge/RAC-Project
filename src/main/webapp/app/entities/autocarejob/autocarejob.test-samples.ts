import dayjs from 'dayjs/esm';

import { IAutocarejob, NewAutocarejob } from './autocarejob.model';

export const sampleWithRequiredData: IAutocarejob = {
  id: 23787,
};

export const sampleWithPartialData: IAutocarejob = {
  id: 28180,
  jobnumber: 375,
  nextmillage: 13345.33,
  vehicletypeid: 19314,
  lmd: dayjs('2024-08-19T10:48'),
  specialrquirments: 'via unethically',
  remarks: 'terribly',
  lastserviceinstructions: 'cost',
  isjobclose: false,
  feedbackstatusid: 11966,
  customername: 'dispense',
  customertel: 'inheritance till mole',
  customerid: 2462,
  jobdate: dayjs('2024-08-19T21:35'),
  iscompanyservice: false,
  freeservicenumber: 'badly sane oof',
  nextgearoilmilage: 'painfully while cart',
  isjobinvoiced: false,
  frontimage: 'hence',
  dashboardimage: 'phew',
};

export const sampleWithFullData: IAutocarejob = {
  id: 5943,
  jobnumber: 19635,
  vehicleid: 10024,
  vehiclenumber: 'fooey haunt',
  millage: 11140.57,
  nextmillage: 860.79,
  nextservicedate: dayjs('2024-08-20'),
  vehicletypeid: 17256,
  jobtypeid: 7080,
  jobtypename: 'duh',
  jobopenby: 8546,
  jobopentime: dayjs('2024-08-19T09:04'),
  lmu: 18104,
  lmd: dayjs('2024-08-19T09:37'),
  specialrquirments: 'than',
  specialinstructions: 'because given over',
  remarks: 'catacomb aw',
  nextserviceinstructions: 'downgrade',
  lastserviceinstructions: 'painfully',
  isadvisorchecked: false,
  isempallocated: true,
  jobclosetime: dayjs('2024-08-19T06:56'),
  isjobclose: false,
  isfeedback: false,
  feedbackstatusid: 11770,
  customername: 'incidentally',
  customertel: 'rhubarb phew mosquito',
  customerid: 13020,
  advisorfinalcheck: true,
  jobdate: dayjs('2024-08-19T20:42'),
  iscompanyservice: false,
  freeservicenumber: 'aha hastily supposing',
  companyid: 21932,
  updatetocustomer: true,
  nextgearoilmilage: 'demolish',
  isjobinvoiced: true,
  iswaiting: true,
  iscustomercomment: false,
  imagefolder: 'pro',
  frontimage: 'telephone',
  leftimage: 'whose repel',
  rightimage: 'rally',
  backimage: 'adorable clear',
  dashboardimage: 'riot cuff cloudy',
};

export const sampleWithNewData: NewAutocarejob = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
