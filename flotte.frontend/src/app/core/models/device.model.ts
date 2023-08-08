import { Moment } from 'moment';
import { IDeviceModel } from './device-model.model';
import { IRefOrgan } from './ref-organ.model';
import { state } from './enumerations/state.enum';

import {ILastPosition} from './last-position.model';

export interface IDevice {
  id?: string;
  deviceID?: string;
  serialNumber?: string;
  imei?: string;
  active?: boolean;
  creationDate?: Moment;
  expirationDate?: Moment;
  state?: state;
   status?: boolean;
   lastUpdate?: Moment;
  phoneNumber?: string;
  simserialNumber?: string;
   devicemodel?: IDeviceModel;
   refOrgan?: IRefOrgan;
  prefOrgan?:string;
  lastPosition?: ILastPosition;
 }

export class Device implements IDevice {
  constructor(
    public id?: string,
    public deviceID?: string,
    public serialNumber?: string,
    public imei?: string,
    public active?: boolean,
    public creationDate?: Moment,
    public expirationDate?: Moment,
    // tslint:disable-next-line:no-shadowed-variable
    public state?: state,
    public status?: boolean,
    public lastUpdate?: Moment,
    public phoneNumber?: string,
    public simserialNumber?: string,
    public devicemodel?: IDeviceModel,
    public refOrgan?: IRefOrgan,
  public  prefOrgan?:string,
  public lastPosition?: ILastPosition
  ) {
    this.active = this.active || false;
  }
}

export function getDevicesIdentifier(devices: Device): string | undefined {
  return devices.id;
}

// Search Data
export interface SearchResult {
  tables: Device[];
  total: number;
}




