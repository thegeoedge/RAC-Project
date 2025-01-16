export interface IAutocareservicemillages {
  id: number;
  millage?: number | null;
}

export type NewAutocareservicemillages = Omit<IAutocareservicemillages, 'id'> & { id: null };
