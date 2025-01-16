import { IBankbranch, NewBankbranch } from './bankbranch.model';

export const sampleWithRequiredData: IBankbranch = {
  id: 10548,
};

export const sampleWithPartialData: IBankbranch = {
  id: 995,
  bankcode: 'quizzically where',
  branchcode: 'towards',
  branchname: 'respond a garbage',
};

export const sampleWithFullData: IBankbranch = {
  id: 5608,
  bankcode: 'beneath',
  branchcode: 'knottily mechanize',
  branchname: 'foolishly torn',
};

export const sampleWithNewData: NewBankbranch = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
