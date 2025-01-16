import dayjs from 'dayjs/esm';

export interface IVehicletype {
  id: number;
  vehicletype?: string | null;
  vehicletypediscription?: string | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
  catid?: number | null;
  catname?: string | null;
}

export type NewVehicletype = Omit<IVehicletype, 'id'> & { id: null };
