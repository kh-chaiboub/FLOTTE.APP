import { Injectable } from '@angular/core';
import {getVehicleBrandsIdentifier, VehicleBrands} from '../../core/models/vehicle-brands.model';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import {createRequestOption} from '../../core/request/request-util';
import {isPresent} from '../../core/util/operators';
import {environment} from '../../../environments/environment';
import {CustomHttpRespone} from '../../core/models/custom-http-response';


export type EntityResponseType = HttpResponse<VehicleBrands>;
export type EntityArrayResponseType = HttpResponse<VehicleBrands[]>;


@Injectable({
  providedIn: 'root'
})
export class BrandService {

  public resourceUrl = environment.apiUrl + '/vehicule-service/api/vehicle-brands/';

  constructor(protected http: HttpClient) {}

  create(vehicleBrands: VehicleBrands): Observable<EntityResponseType> {
    return this.http.post<VehicleBrands>(this.resourceUrl+'vehicle-brands', vehicleBrands, { observe: 'response' });
  }



  find(id: string): Observable<EntityArrayResponseType> {

    return this.http.get<VehicleBrands[]>(`${this.resourceUrl+'vehicle-brands'}/${id}`, {observe: 'response' });
  }

  findByType(id: string): Observable<EntityArrayResponseType> {

    return this.http.get<VehicleBrands[]>(`${this.resourceUrl}vehicle-brands/byType/${id}`, {observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<VehicleBrands[]>(this.resourceUrl + '/vehicle-brands', { params: options, observe: 'response' });
  }



  public getBrands(): Observable<VehicleBrands[]> {
    return this.http.get<VehicleBrands[]>(`${this.resourceUrl + '/vehicle-brands'}`);
  }

  delete(id: string): Observable<CustomHttpRespone> {
    return this.http.delete<CustomHttpRespone>(`${this.resourceUrl + '/vehicle-brands'}/${id}`);
  }

  addVehicleBrandsToCollectionIfMissing(
    vehicleBrandsCollection: VehicleBrands[],
    ...vehicleBrandsToCheck: (VehicleBrands | null | undefined)[]
  ): VehicleBrands[] {
    const vehicleBrands: VehicleBrands[] = vehicleBrandsToCheck.filter(isPresent);
    if (vehicleBrands.length > 0) {
      const vehicleBrandsCollectionIdentifiers = vehicleBrandsCollection.map(
        vehicleBrandsItem => getVehicleBrandsIdentifier(vehicleBrandsItem)!
      );
      const vehicleBrandsToAdd = vehicleBrands.filter(vehicleBrandsItem => {
        const vehicleBrandsIdentifier = getVehicleBrandsIdentifier(vehicleBrandsItem);
        if (vehicleBrandsIdentifier == null || vehicleBrandsCollectionIdentifiers.includes(vehicleBrandsIdentifier)) {
          return false;
        }
        vehicleBrandsCollectionIdentifiers.push(vehicleBrandsIdentifier);
        return true;
      });
      return [...vehicleBrandsToAdd, ...vehicleBrandsCollection];
    }
    return vehicleBrandsCollection;
  }
}
