export interface IStocklocations {
  id: number;
  locationname?: string | null;
  locationcode?: string | null;
  description?: string | null;
}

export type NewStocklocations = Omit<IStocklocations, 'id'> & { id: null };
