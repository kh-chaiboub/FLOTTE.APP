import {Vehicle} from './vehicle.model';


export class FuelTypes {
  id?: null;
  fuelType?: string | null;
  vehicles?: Vehicle[] | null;


  constructor() {
    this.id = null;
    this.fuelType = '';
    this.vehicles = null;
  }
}

export function getFuelTypesIdentifier(fuelTypes: FuelTypes): null | undefined {
  return fuelTypes.id;
}


export interface SearchResult {
  tables: FuelTypes[];
  total: number;
}
