import dayjs from 'dayjs/esm';

export interface IAutocarejob {
  id: number;
  jobnumber?: number | null;
  vehicleid?: number | null;
  vehiclenumber?: string | null;
  millage?: number | null;
  nextmillage?: number | null;
  nextservicedate?: dayjs.Dayjs | null;
  vehicletypeid?: number | null;
  jobtypeid?: number | null;
  jobtypename?: string | null;
  jobopenby?: number | null;
  jobopentime?: dayjs.Dayjs | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
  specialrquirments?: string | null;
  specialinstructions?: string | null;
  remarks?: string | null;
  nextserviceinstructions?: string | null;
  lastserviceinstructions?: string | null;
  isadvisorchecked?: boolean | null;
  isempallocated?: boolean | null;
  jobclosetime?: dayjs.Dayjs | null;
  isjobclose?: boolean | null;
  isfeedback?: boolean | null;
  feedbackstatusid?: number | null;
  customername?: string | null;
  customertel?: string | null;
  customerid?: number | null;
  advisorfinalcheck?: boolean | null;
  jobdate?: dayjs.Dayjs | null;
  iscompanyservice?: boolean | null;
  freeservicenumber?: string | null;
  companyid?: number | null;
  updatetocustomer?: boolean | null;
  nextgearoilmilage?: string | null;
  isjobinvoiced?: boolean | null;
  iswaiting?: boolean | null;
  iscustomercomment?: boolean | null;
  imagefolder?: string | null;
  frontimage?: string | null;
  leftimage?: string | null;
  rightimage?: string | null;
  backimage?: string | null;
  dashboardimage?: string | null;
}

export type NewAutocarejob = Omit<IAutocarejob, 'id'> & { id: null };
