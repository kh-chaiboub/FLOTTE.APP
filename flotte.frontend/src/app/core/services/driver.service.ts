import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../../environments/environment';

import {getDriverIdentifier, Driver} from '../../core/models/driver.model';
import {createRequestOption} from '../../core/request/request-util';
import {isPresent} from '../../core/util/operators';
import {CustomHttpRespone} from '../../core/models/custom-http-response';
import {Vehicle} from "../models/vehicle.model";




export type EntityResponseType = HttpResponse<Driver>;
export type EntityArrayResponseType = HttpResponse<Driver[]>;

@Injectable({ providedIn: 'root' })
export class  DriverService {

  public resourceUrl = environment.apiUrl + '/vehicule-service/api/drivers';

  constructor(protected http: HttpClient) {}

  create(driver: Driver): Observable<EntityResponseType> {
    return this.http.post<Driver>(this.resourceUrl, driver, { observe: 'response' });
  }

  public getDriverProjection(page:number,size:number): Observable<Driver[]> {
    let pageRequest = '';
    if(page!=null){
      pageRequest= page+'&';
    }
    if(size!=null){
      pageRequest=pageRequest+size
    }
    console.log(`${this.resourceUrl}/projection/drivers?${pageRequest}`)
    return this.http.get<Driver[]>(`${this.resourceUrl}/projection/drivers?${pageRequest}`);
  }


  public getDrivers(): Observable<Driver[]> {
    return this.http.get<Driver[]>(`${this.resourceUrl}`);

  }



  find(id: number): Observable<EntityResponseType> {
    return this.http.get<Driver>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<Driver[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  public getdriver(): Observable<Driver[]> {
    return this.http.get<Driver[]>(`${this.resourceUrl}`);
  }

  delete(id: string): Observable<CustomHttpRespone> {
    return this.http.delete<CustomHttpRespone>(`${this.resourceUrl}/${id}`);
  }

  adddriverToCollectionIfMissing(
    driverCollection: Driver[],
    ...driverToCheck: (Driver | null | undefined)[]
  ): Driver[] {
    const serviceCategories: Driver[] = driverToCheck.filter(isPresent);
    if (serviceCategories.length > 0) {
      const driverCollectionIdentifiers = driverCollection.map(
        driverItem => getDriverIdentifier(driverItem)!
      );
      const driverToAdd = serviceCategories.filter(driverItem => {
        const driverIdentifier = getDriverIdentifier(driverItem);
        if (driverIdentifier == null || driverCollectionIdentifiers.includes(driverIdentifier)) {
          return false;
        }
        driverCollectionIdentifiers.push(driverIdentifier);
        return true;
      });
      return [...driverToAdd, ...driverCollection];
    }
    return driverCollection;
  }
}
