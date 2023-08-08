import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import {createRequestOption} from '../request/request-util';
import {isPresent} from '../util/operators';
import {environment} from '../../../environments/environment';
import {CustomHttpRespone} from '../models/custom-http-response';
import {getDevicesIdentifier, Device}  from '../models/device.model';
import DataSource from 'devextreme/data/data_source';
import {BrandCountInDevice} from '../models/brandCountInDevice';
import {Vehicle} from "../models/vehicle.model";
export type EntityResponseType = HttpResponse<Device>;
export type EntityArrayResponseType = HttpResponse<Device[]>;


@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  public resourceUrl = environment.apiUrl + '/device-service/api/devices';

  constructor(protected http: HttpClient) {}

  create(device: Device): Observable<EntityResponseType> {
    return this.http.post<Device>(this.resourceUrl+"/devices" , device, { observe: 'response' });
  }

  update(device: Device): Observable<EntityResponseType> {
    // console.log(getDevicesIdentifier(device));
    // console.log(device.id);
   return this.http.put<Device>(`${this.resourceUrl}/devices/${device.id}`, device, {
      observe: 'response',
    });

  }

  public getDeviceProjection(page:number,size:number): Observable<Vehicle[]> {
    let pageRequest = '';
    if(page!=null){
      pageRequest= page+'&';
    }
    if(size!=null){
      pageRequest=pageRequest+size
    }
    console.log(`${this.resourceUrl}/projection/devices?${pageRequest}`)
    return this.http.get<Device[]>(`${this.resourceUrl}/projection/devices?${pageRequest}`);
  }


  partialUpdate(device: Device): Observable<EntityResponseType> {
    return this.http.patch<Device>(`${this.resourceUrl}/devices/${getDevicesIdentifier(device) as string}`, device, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
       return this.http.get<Device>(`${this.resourceUrl}/devices/${id}`, { observe: 'response' });
  }
// /devices/imei
  public getDeviceByidObservable(id:string): Observable<Device> {
    return this.http.get<Device>(`${this.resourceUrl}/devices/${id}`);

  }
  public getDeviceByImeiObservable(imei:string): Observable<Device> {
    return this.http.get<Device>(`${this.resourceUrl}/devices/imei/${imei}`);
  }

  public getDeviceByDeviceIDObservable(deviceid:string): Observable<Device> {

    // return this.http.get<Device[]>(`${this.resourceUrl}/devices`);
    return this.http.get<Device>(`${this.resourceUrl}/devices/deviceID/${deviceid}`);

  }


  query(req?: any): Observable<Device[]> {
    const options = createRequestOption(req);
    return this.http.get<Device[]>(this.resourceUrl+"/devices");
  }

public getTotalDeviceByBrand(req?: any):Observable<BrandCountInDevice[]>{
 // const options = createRequestOption(req);
  // return this.http.get<Brand[]>(`${this.resourceUrl}/TotaldeviceByBrand` , { params: options, observe: 'response' });
  return this.http.get<BrandCountInDevice[]>(`${this.resourceUrl}/devices/countByBrand` );



}

  public getDevices(): Observable<Device[]> {
    return this.http.get<Device[]>(`${this.resourceUrl}/devices`)
  }
  getDataSource(devices: Device[]) {

    return new DataSource({
      store: {
        type:  'array',
        data: devices,
        key: 'id',

      },
    });
  }


  delete(id: string): Observable<CustomHttpRespone> {
    return this.http.delete<CustomHttpRespone>(`${this.resourceUrl}/devices/${id}`);
  }


  addDevicesToCollectionIfMissing(
    devicesCollection: Device[],
    ...devicesToCheck: (Device | null | undefined)[]
  ): Device[] {
    const device: Device[] = devicesToCheck.filter(isPresent);
    if (device.length > 0) {
      const devicesCollectionIdentifiers = devicesCollection.map(
        // tslint:disable-next-line:no-non-null-assertion
        devicesItem => getDevicesIdentifier(devicesItem)!
      );
      const devicesToAdd = device.filter(devicesItem => {
        const devicesIdentifier = getDevicesIdentifier(devicesItem);
        if (devicesIdentifier == null || devicesCollectionIdentifiers.includes(devicesIdentifier)) {
          return false;
        }
        devicesCollectionIdentifiers.push(devicesIdentifier);
        return true;
      });
      return [...devicesToAdd, ...devicesCollection];
    }
    return devicesCollection;
  }
}
