import { DeviceCategory } from './device-category.model';

export interface IBrand {
  id?: string;
  deviceBrand?: string;
  deviceCategory?: DeviceCategory;
  totalDevice?:number;
}

export class Brand implements IBrand {
  constructor(public id?: string, public deviceBrand?: string, public deviceCategory?: DeviceCategory,public totalDevice?:number) {}
}

export function getDeviceBrandIdentifier(deviceBrand: Brand): string | undefined {
  return deviceBrand.id;
}
