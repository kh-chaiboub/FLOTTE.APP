
import {Device} from './device.model';

export class DeviceCategory {
  id?: null;
  deviceCategory?: string | null;
  devices?: Device[] | null;


  constructor() {
    this.id = null;
    this.deviceCategory = '';
    this.devices = null;
  }
}



export function getDeviceCategoryIdentifier(deviceCategory: DeviceCategory): null | undefined {
  return deviceCategory.id;
}

export interface SearchResult {
  tables: DeviceCategory[];
  total: number;
}

