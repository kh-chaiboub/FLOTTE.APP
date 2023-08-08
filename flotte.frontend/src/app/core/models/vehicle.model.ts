import {Driver} from './driver.model';
import {VehicleModel} from './vehicle-model.model';
import {VehicleTypes} from './vehicle-types.model';
import {Device} from './device.model';
import {LastPosition} from './last-position.model';
import {RefOrgan} from './ref-organ.model';
import {VehicleBrands} from './vehicle-brands.model';
import {VehicleStatus} from './vehicle-status.model';
import {FuelTypes} from './fuel-types.model';
import {Moment} from 'moment';


export interface IVehicule {
  id?: string;
  registrationNumber?: string;
  device?: Device;
  vehiculeDesc?: string;
  vehicleType?: VehicleTypes;
  vehicleBrand?: VehicleBrands;
  vehicleModel?: VehicleModel;
  fuelType?: FuelTypes;
  vehiculeKmEstime?: number;
  vehiculeMaxVitesse?: number;
  driver?: Driver;
  etatVehicule?: VehicleStatus;
  vehiculeKmDate?: Moment;
  frequence?: string;
  indicatif?: string;
  lastPosition?: LastPosition;
  refOrgan ?: RefOrgan;
  refOrganID?: string;
  prefOrgan?: string;
  iStracking?:boolean;

  // deviceID?: string | null;
  // available?: boolean | null;
  // refOrganID?: string | null;


}

export class Vehicle implements IVehicule {
  constructor(
  public id?: string,
  public registrationNumber?: string,
  public vehiculeDesc?: string,
  public device?: Device,
  public vehicleType?: VehicleTypes,
  public vehicleModel?: VehicleModel,
  public vehiculeMaxVitesse?: number,
  public driver?: Driver,
  public etatVehicule?: VehicleStatus,
  public frequence?: string,
  public indicatif?: string,
  public lastPosition?: LastPosition,
  public refOrgan?: RefOrgan,
  public refOrganID?: string,
  public  prefOrgan?: string,
  public vehiculeKmDate?: Moment,
  public vehiculeKmEstime?: number,
  public vehicleBrand?: VehicleBrands,
  public fuelType?: FuelTypes,
  public iStracking?:boolean,


  ) { }

}


export function getVehicleIdentifier(vehicle?: Vehicle): string | undefined {
  return vehicle!.id;
}

export interface SearchResult {
  tables?: Vehicle[];
  total?: number;
}

