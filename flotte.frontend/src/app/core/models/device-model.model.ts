import { Brand } from './device-brand.model';

export interface IDeviceModel {
  id?: string;
  deviceModel?: string;
  brand?: Brand;
}

export class DeviceModel implements IDeviceModel {
  constructor(public id?: string, public deviceModel?: string, public brand?: Brand) {}
}


export function getDeviceModelIdentifier(deviceModel: DeviceModel): string | undefined {
  return deviceModel.id;
}
