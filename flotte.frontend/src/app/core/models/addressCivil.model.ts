export interface IAddress {

  building?: string;
  road?: string;
  residential?: string;
  suburb?: string;
  city_district?: string;
  city?: string;
  county?: string,
  state_district?: string;
  region?: string;
  postcode?: string;
  country?: string;
  country_code?: string;

}

export class Address implements IAddress {
  constructor(
   public building?: string,
   public road?: string,
   public residential?: string,
   public suburb?: string,
   public city_district?: string,
   public city?: string,
   public county?: string,
   public state_district?: string,
   public region?: string,
   public postcode?: string,
   public country?: string,
   public country_code?: string
  ) {}
}
