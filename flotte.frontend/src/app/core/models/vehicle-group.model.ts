import { Vehicle} from './vehicle.model';


export class VehicleGroup {
  id?: number;
  groupName?: string | null;
  groupDescription?: string | null;
  vehicles?: Vehicle[] | null;

  constructor() {
    this.id = null;
    this.groupName = '';
    this.groupDescription = '';
    this.vehicles = null;
  }
}

export function getVehicleGroupIdentifier(vehicleGroup: VehicleGroup): number | undefined {
  return vehicleGroup.id;
}

export interface SearchResult {
  tables: VehicleGroup[];
  total: number;
}
