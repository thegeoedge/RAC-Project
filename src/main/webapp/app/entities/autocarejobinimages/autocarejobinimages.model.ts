export interface IAutocarejobinimages {
  id: number;
  jobid?: number | null;
  imagefolder?: string | null;
  imagename?: string | null;
}

export type NewAutocarejobinimages = Omit<IAutocarejobinimages, 'id'> & { id: null };
