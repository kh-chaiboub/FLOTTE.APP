
import {VehicleBrands} from './vehicle-brands.model';
import {Vehicle} from './vehicle.model';

export class VehicleModel {
  id?: string;
  modelName?: string | null;
  defautImage?: string | null;
  mouve_D_Image?: string | null;
  mouve_G_Image?: string | null;
  // tslint:disable-next-line:variable-name
  stop_D_Image?: string | null;
  // tslint:disable-next-line:variable-name
  stop_G_Image?: string | null;
  // tslint:disable-next-line:variable-name
  alert_Mission_Image?: string | null;
  vehicleBrands?: VehicleBrands | null;



  // tslint:disable-next-line:variable-name
  constructor(id?: string, modelName?: string | null, defautImage?: string | null, mouve_D_Image?: string | null, mouve_G_Image?: string | null, stop_D_Image?: string | null, stop_G_Image?: string | null, alert_Mission_Image?: string | null, vehicleBrands?: VehicleBrands | null) {
    this.id = id;
    this.modelName = modelName;
    this.vehicleBrands = vehicleBrands;
    this.defautImage = defautImage;
    this.mouve_D_Image = mouve_D_Image;
    this.mouve_G_Image = mouve_G_Image;
    this.stop_D_Image = stop_D_Image;
    this.stop_G_Image = stop_G_Image;
    this.alert_Mission_Image = alert_Mission_Image;
    // @ts-ignore
    this.vehicleBrands = VehicleBrands;

  }
}



export function getVehicleModelIdentifier(vehicleModel: VehicleModel): string | undefined {
  return vehicleModel.id;
}

export interface SearchResult {

  total: number;
}

