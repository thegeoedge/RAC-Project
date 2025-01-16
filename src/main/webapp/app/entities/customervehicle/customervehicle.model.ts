import dayjs from 'dayjs/esm';

export interface ICustomervehicle {
  id: number;
  customerid?: number | null;
  vehiclenumber?: string | null;
  categoryid?: number | null;
  categoryname?: string | null;
  typeid?: number | null;
  typename?: string | null;
  makeid?: number | null;
  makename?: string | null;
  model?: string | null;
  yom?: string | null;
  customercode?: string | null;
  remarks?: string | null;
  servicecount?: number | null;
  engNo?: string | null;
  chaNo?: string | null;
  milage?: string | null;
  lastservicedate?: dayjs.Dayjs | null;
  nextservicedate?: dayjs.Dayjs | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
  nextgearoilmilage?: string | null;
  nextmilage?: string | null;
  serviceperiod?: number | null;
}

export type NewCustomervehicle = Omit<ICustomervehicle, 'id'> & { id: null };
