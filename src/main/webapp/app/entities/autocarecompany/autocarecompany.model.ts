import dayjs from 'dayjs/esm';

export interface IAutocarecompany {
  id: number;
  name?: string | null;
  address?: string | null;
  servicephone?: string | null;
  sparepartphone?: string | null;
  bodypaint?: string | null;
  generalphone?: string | null;
  fax?: string | null;
  email?: string | null;
  description?: string | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
  vatregnumber?: string | null;
  tinnumber?: string | null;
  accountcode?: string | null;
  accountid?: number | null;
}

export type NewAutocarecompany = Omit<IAutocarecompany, 'id'> & { id: null };
