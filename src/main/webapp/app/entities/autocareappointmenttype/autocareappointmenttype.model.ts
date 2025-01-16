export interface IAutocareappointmenttype {
  id: number;
  typename?: string | null;
}

export type NewAutocareappointmenttype = Omit<IAutocareappointmenttype, 'id'> & { id: null };
