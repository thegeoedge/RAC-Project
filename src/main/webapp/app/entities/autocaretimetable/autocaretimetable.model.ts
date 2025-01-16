import dayjs from 'dayjs/esm';

export interface IAutocaretimetable {
  id: number;
  hoisttypeid?: number | null;
  hoisttypename?: string | null;
  hoisttime?: dayjs.Dayjs | null;
}

export type NewAutocaretimetable = Omit<IAutocaretimetable, 'id'> & { id: null };
