import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Position} from '../../core/models/position.model';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {KmByVehicule} from '../../core/models/kmByVehicule';





export type EntityResponseType = HttpResponse<Position>;
export type EntityArrayResponseType = HttpResponse<Position[]>;

@Injectable({
  providedIn: 'root'
})
export class PositionService {

  public resourceUrl = environment.localitePositionVl
  public apiUrlServiceKM = environment.UrlKmCalcul;


  constructor(protected http: HttpClient) {}


  findByDeviceIDanBetweenDate(deviceid: string,dateD:string,dateF:string): Observable<Position[]> {
    return this.http.get<Position[]>(`${this.resourceUrl}/traget?id=${deviceid}&start=${dateD}&end=${dateF}`);
  }

  findByDeviceIDanBetweenDateDanddateF(deviceid: string,dateD:string,dateF:string): Observable<Position[]> {
    return this.http.get<Position[]>(`${this.resourceUrl}/positions?id=${deviceid}&start=${dateD}&end=${dateF}`);
  }
  findCalculbyKmBetweenDateAnddateF(vl: string,dateD:string,dateF:string): Observable<KmByVehicule> {
    return this.http.get<KmByVehicule>(`${this.apiUrlServiceKM}klmop?id=${vl}&start=${dateD}&end=${dateF}`);

  }


}
