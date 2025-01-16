import dayjs from 'dayjs/esm';

export interface IVehiclecategory {
  id: number;
  vehiclecategory?: string | null;
  vehiclecategorydiscription?: string | null;
  lmu?: number | null;
  lmd?: dayjs.Dayjs | null;
}

export type NewVehiclecategory = Omit<IVehiclecategory, 'id'> & { id: null };
