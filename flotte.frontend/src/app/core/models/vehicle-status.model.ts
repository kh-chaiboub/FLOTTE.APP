
import {Vehicle} from './vehicle.model';


export class VehicleStatus {
  id?: string;
  vehicleStatus?: string | null;
  vehicles?: Vehicle[] | null;


  constructor(id?: string, vehicleStatus?: string | null, vehicles?: Vehicle[] | null) {
    this.id = id;
    this.vehicleStatus = vehicleStatus;
    this.vehicles = vehicles;
  }
}

export function getVehicleStatusIdentifier(vehicleStatus: VehicleStatus): string | undefined {
  return vehicleStatus.id;
}

export interface SearchResult {
  tables: VehicleStatus[];
  total: number;
}


