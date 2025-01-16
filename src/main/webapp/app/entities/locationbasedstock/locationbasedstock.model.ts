import dayjs from 'dayjs/esm';

export interface ILocationbasedstock {
  id: number;
  itemid?: number | null;
  code?: string | null;
  name?: string | null;
  locationid?: number | null;
  locationcode?: string | null;
  availablequantity?: number | null;
  hasbatches?: boolean | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
}

export type NewLocationbasedstock = Omit<ILocationbasedstock, 'id'> & { id: null };
