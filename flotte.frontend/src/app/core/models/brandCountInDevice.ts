
export interface IBrandCountInDevice {
  _id?: string;
  count?: number;

}

export class BrandCountInDevice implements IBrandCountInDevice {
  constructor(public _id?: string, public count?: number) {}
}
export interface SearchResult {
  tables: BrandCountInDevice[];
  total: number;
}


