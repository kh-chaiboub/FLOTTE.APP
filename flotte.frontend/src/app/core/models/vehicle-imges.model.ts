import {Vehicle} from './vehicle.model';

export class VehicleImges {
  id?: number;
  vehicleImgeContentType?: string | null;
  vehicleImge?: string | null;
  vehicle?: Vehicle | null;


  constructor(id: number, vehicleImgeContentType: string | null, vehicleImge: string | null, vehicle: Vehicle | null) {
    this.id = id;
    this.vehicleImgeContentType = vehicleImgeContentType;
    this.vehicleImge = vehicleImge;
    this.vehicle = vehicle;
  }
}

export function getVehicleImgesIdentifier(vehicleImges: VehicleImges): number | undefined {
  return vehicleImges.id;
}

export interface SearchResult {
  tables: VehicleImges[];
  total: number;
}
