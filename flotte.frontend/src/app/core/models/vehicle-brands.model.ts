
import {VehicleTypes} from './vehicle-types.model';


export class VehicleBrands {
  id?: null;
  vehicleBrand?: string | null;
  vehiculeType?: VehicleTypes | null;


  constructor() {
    this.id = null;
    this.vehicleBrand = '';
    this.vehiculeType = null;
  }
}



export function getVehicleBrandsIdentifier(vehicleBrands: VehicleBrands): null | undefined {
  return vehicleBrands.id;
}
export interface SearchResult {
  tables: VehicleBrands[];
  total: number;
}
