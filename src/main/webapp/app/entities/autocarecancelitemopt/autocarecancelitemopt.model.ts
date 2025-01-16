export interface IAutocarecancelitemopt {
  id: number;
  canceloption?: string | null;
}

export type NewAutocarecancelitemopt = Omit<IAutocarecancelitemopt, 'id'> & { id: null };
