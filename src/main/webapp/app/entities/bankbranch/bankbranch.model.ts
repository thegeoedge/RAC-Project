export interface IBankbranch {
  id: number;
  bankcode?: string | null;
  branchcode?: string | null;
  branchname?: string | null;
}

export type NewBankbranch = Omit<IBankbranch, 'id'> & { id: null };
