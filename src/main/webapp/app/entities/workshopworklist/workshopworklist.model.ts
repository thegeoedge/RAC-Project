import dayjs from 'dayjs/esm';

export interface IWorkshopworklist {
  id: number;
  workshopwork?: string | null;
  workshopworkdescription?: string | null;
  isactive?: boolean | null;
  lmd?: dayjs.Dayjs | null;
  lmu?: number | null;
}

export type NewWorkshopworklist = Omit<IWorkshopworklist, 'id'> & { id: null };
