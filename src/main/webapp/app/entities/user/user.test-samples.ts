import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 3225,
  login: 'NC.pcg',
};

export const sampleWithPartialData: IUser = {
  id: 31762,
  login: '719',
};

export const sampleWithFullData: IUser = {
  id: 14397,
  login: 'a-oJ',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
