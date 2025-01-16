export interface IPaymentterm {
  id: number;
  paymenttype?: string | null;
  forvoucher?: boolean | null;
}

export type NewPaymentterm = Omit<IPaymentterm, 'id'> & { id: null };
