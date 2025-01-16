import dayjs from 'dayjs/esm';

export interface IAutocarehoist {
  id: number;
  hoistname?: string | null;
  hoistcode?: string | null;
  hoisttypeid?: number | null;
  hoisttypename?: string | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
}

export type NewAutocarehoist = Omit<IAutocarehoist, 'id'> & { id: null };
