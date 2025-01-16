import { IAutocarejobinimages, NewAutocarejobinimages } from './autocarejobinimages.model';

export const sampleWithRequiredData: IAutocarejobinimages = {
  id: 21942,
};

export const sampleWithPartialData: IAutocarejobinimages = {
  id: 6647,
  imagefolder: 'what or carelessly',
  imagename: 'aha flamboyant',
};

export const sampleWithFullData: IAutocarejobinimages = {
  id: 23606,
  jobid: 475,
  imagefolder: 'chalet threat welcome',
  imagename: 'following overcompensate miniature',
};

export const sampleWithNewData: NewAutocarejobinimages = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
