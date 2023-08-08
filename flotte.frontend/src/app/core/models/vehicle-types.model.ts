import {Vehicle} from './vehicle.model';


export class VehicleTypes {
  id?: null;
  vehiculeType?: string | null;
  customField?: string | null;
  vehicles?: Vehicle[] | null;


  constructor() {
    this.id = null;
    this.vehiculeType = '';
    this.customField = '';
    this.vehicles = null;
  }
}


export function getVehicleTypesIdentifier(vehiculeTypes: VehicleTypes): null | undefined {
  return vehiculeTypes.id;
}

export interface SearchResult {
  tables: VehicleTypes[];
  total: number;
}
