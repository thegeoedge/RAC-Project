import dayjs from 'dayjs/esm';

export interface IInventorybatches {
  id: number;
  itemid?: number | null;
  code?: string | null;
  txdate?: dayjs.Dayjs | null;
  cost?: number | null;
  price?: number | null;
  costwithoutvat?: number | null;
  pricewithoutvat?: number | null;
  notes?: string | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
  lineid?: number | null;
  manufacturedate?: dayjs.Dayjs | null;
  expiredate?: dayjs.Dayjs | null;
  quantity?: number | null;
  addeddate?: dayjs.Dayjs | null;
  costtotal?: number | null;
  returnprice?: number | null;
}

export type NewInventorybatches = Omit<IInventorybatches, 'id'> & { id: null };
