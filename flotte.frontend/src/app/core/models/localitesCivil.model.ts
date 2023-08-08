import {Address} from './addressCivil.model';

export interface ILocalitesCivil {
  place_id: string;
  licence: string;
    osm_type: string;
  osm_id: string;
  lat: string;
  lon: string;
  display_name:string;
    address :Address;
  boundingbox:string[];
}

export class LocalitesCivil implements ILocalitesCivil {
  constructor(
    public place_id: string,
  public licence: string,
  public osm_type: string,
  public osm_id: string,
  public lat: string,
  public lon: string,
  public display_name:string,
  public address :Address,
  public boundingbox:string[],
  ) {}
}
