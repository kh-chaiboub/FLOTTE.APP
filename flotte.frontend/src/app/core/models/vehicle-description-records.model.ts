import {Vehicle} from './vehicle.model';

export class VehicleDescriptionRecords {
  id?: number;
  vehicleDescription?: string | null;
  vehicle?: Vehicle | null;


  constructor(id: number, vehicleDescription: string | null, vehicle: Vehicle | null) {
    this.id = id;
    this.vehicleDescription = vehicleDescription;
    this.vehicle = vehicle;
  }
}


export function getVehicleDescriptionRecordsIdentifier(vehicleDescriptionRecords: VehicleDescriptionRecords): number | undefined {
  return vehicleDescriptionRecords.id;
}

export interface SearchResult {
  tables: VehicleDescriptionRecords[];
  total: number;
}
