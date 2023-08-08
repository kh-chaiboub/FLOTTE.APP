import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpResponse} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {getVehicleIdentifier, Vehicle} from '../models/vehicle.model';
import {createRequestOption} from '../request/request-util';
import {CustomHttpRespone} from '../models/custom-http-response';
import DataSource from 'devextreme/data/data_source';


export type EntityResponseType = HttpResponse<Vehicle>;
export type EntityArrayResponseType = HttpResponse<Vehicle[]>;

@Injectable({ providedIn: 'root' })
export class VehicleService {

  private host = environment.apiUrl + '/vehicule-service/api/vehicules';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<Vehicle[]>(this.host + '/vehicules', {params: options, observe: 'response'});
  }



  create(vehicle: Vehicle): Observable<EntityResponseType> {
    return this.http.post<Vehicle>(this.host + '/vehicules/', vehicle, { observe: 'response' });
  }

  public addVehicle(formData: FormData): Observable<Vehicle> {
    return this.http.post<Vehicle>(`${this.host}/new`, formData);
  }

  public updateVehicle(formData: FormData): Observable<Vehicle> {
    return this.http.post<Vehicle>(`${this.host}/update`, formData);
  }

  update(vehicule: Vehicle): Observable<EntityResponseType> {
    return this.http.put<Vehicle>(
      `${this.host}/vehicules/${getVehicleIdentifier(vehicule) as string}`,
      vehicule,
      { observe: 'response' }
    );
  }

  findById(id: string): Observable<Vehicle> {
    return this.http.get<Vehicle>(this.host + `/vehicules/${id}`);
  }

  countByDeviceStatusTrue():Observable<any>{

    return this.http.get(this.host + `/count/status/true`);

  }
  countByDeviceStatusFalse():Observable<any>{

    return this.http.get(this.host + `/count/status/false`);

  }

  countByVLnotDoteDevice():Observable<any>{

    return this.http.get(this.host + `/count/vehicules/notDote/`);

  }

  public getVehicles(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(`${this.host}/vehicules`);
  }
  public getVehiclesProjection(page:number,size:number): Observable<Vehicle[]> {
    let pageRequest = '';
    if(page!=null){
      pageRequest= page+'&';
    }
    if(size!=null){
      pageRequest=pageRequest+size
    }
    console.log(`${this.host}/projection/vehicules?${pageRequest}`)
    return this.http.get<Vehicle[]>(`${this.host}/projection/vehicules?${pageRequest}`);
  }
  public getVehiclesForMaps(page:number,size:number): Observable<Vehicle[]> {
    let pageRequest = '';
    if(page!=null){
      pageRequest= page+'&';
    }
    if(size!=null){
      pageRequest=pageRequest+size
    }

    return this.http.get<Vehicle[]>(`${this.host}/projection/vehiculeInmaps?${pageRequest}`);
  }

  public getVehiclesContainsImm(imm:string): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(`${this.host}/search/containsByImm?imm=${imm}`);
  }
  delete(id: string): Observable<CustomHttpRespone> {
    return this.http.delete<CustomHttpRespone>(`${this.host}/vehicules/${id}`);
  }

  getDataSource(vls: Vehicle[]) {

    return new DataSource({
      store: {
        type:  'array',
        data: vls,
        key: 'id',

      },
    });
  }
}
