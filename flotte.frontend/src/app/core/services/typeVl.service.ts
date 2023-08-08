import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {createRequestOption} from '../../core/request/request-util';
import {CustomHttpRespone} from '../../core/models/custom-http-response';
import {isPresent} from '../../core/util/operators';
import {getVehicleTypesIdentifier, VehicleTypes} from '../../core/models/vehicle-types.model';

export type EntityResponseType = HttpResponse<VehicleTypes>;
export type EntityArrayResponseType = HttpResponse<VehicleTypes[]>;


@Injectable({
  providedIn: 'root'
})
export class TypeService {


  public resourceUrl = environment.apiUrl + '/vehicule-service/api/vehicle-types';

  constructor(protected http: HttpClient) {}

  public getTypes(): Observable<VehicleTypes[]> {
    return this.http.get<VehicleTypes[]>(`${this.resourceUrl}/vehicle-types`);
  }

  delete(id: string): Observable<CustomHttpRespone> {
    return this.http.delete<CustomHttpRespone>(`${this.resourceUrl}/vehicle-types/${id}`);
  }

  create(vehicleTypes: VehicleTypes): Observable<EntityResponseType> {
    return this.http.post<VehicleTypes>(`${this.resourceUrl}/vehicle-types`, vehicleTypes, { observe: 'response' });
  }



  find(id: string): Observable<EntityResponseType> {
    return this.http.get<VehicleTypes>(`${this.resourceUrl}/vehicle-types/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<VehicleTypes[]>(`${this.resourceUrl}/vehicle-types`, { params: options, observe: 'response' });
  }



  addVehicleTypesToCollectionIfMissing(
    vehicleTypesCollection: VehicleTypes[],
    ...vehicleTypesToCheck: (VehicleTypes | null | undefined)[]
  ): VehicleTypes[] {
    const vehicleTypes: VehicleTypes[] = vehicleTypesToCheck.filter(isPresent);
    if (vehicleTypes.length > 0) {
      const vehicleTypesCollectionIdentifiers = vehicleTypesCollection.map(
        vehicleTypesItem => getVehicleTypesIdentifier(vehicleTypesItem)!
      );
      const vehicleTypesToAdd = vehicleTypes.filter(vehicleTypesItem => {
        const vehicleTypesIdentifier = getVehicleTypesIdentifier(vehicleTypesItem);
        if (vehicleTypesIdentifier == null || vehicleTypesCollectionIdentifiers.includes(vehicleTypesIdentifier)) {
          return false;
        }
        vehicleTypesCollectionIdentifiers.push(vehicleTypesIdentifier);
        return true;
      });
      return [...vehicleTypesToAdd, ...vehicleTypesCollection];
    }
    return vehicleTypesCollection;
  }
}
