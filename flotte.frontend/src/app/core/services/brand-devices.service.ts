import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import {getDeviceBrandIdentifier,Brand } from '../../core/models/device-brand.model';
import { Observable } from 'rxjs';
import {createRequestOption} from '../../core/request/request-util';
import {CustomHttpRespone} from '../../core/models/custom-http-response';
import {isPresent} from '../../core/util/operators';
import {DeviceCategory} from '../../core/models/device-category.model';
import {environment} from '../../../environments/environment';

export type EntityResponseType = HttpResponse<Brand>;
export type EntityArrayResponseType = HttpResponse<Brand[]>;
@Injectable({
  providedIn: 'root'
})
export class BranddevisService {

  public resourceUrl = environment.apiUrl + '/device-service/api/brands/brands';


  constructor(protected http: HttpClient) { }

  public getcategorie(): Observable<DeviceCategory[]> {

    return this.http.get<DeviceCategory[]>(this.resourceUrl + `/device-service/api/categories/categories`);
  }

  public getCategoryByModel(id: string): Observable<Brand[]> {
    return this.http.get<Brand[]>(this.resourceUrl + `/${id}`);
  }

  create(branddevice: Brand): Observable<EntityResponseType> {
    return this.http.post<Brand>(this.resourceUrl, branddevice, { observe: 'response' });
  }

  update(branddevice: Brand): Observable<EntityResponseType> {
    return this.http.put<Brand>(`${this.resourceUrl}/${getDeviceBrandIdentifier(branddevice) as string}`, branddevice, {
      observe: 'response',
    });
  }

  partialUpdate(branddevice: Brand): Observable<EntityResponseType> {
    return this.http.patch<Brand>(`${this.resourceUrl}/${getDeviceBrandIdentifier(branddevice) as string}`, branddevice, {
      observe: 'response',
    });
  }


  find(id: string): Observable<EntityResponseType> {
    return this.http.get<Brand>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  // tslint:disable-next-line:ban-types
  findByCategory(id: String): Observable<EntityArrayResponseType> {

    return this.http.get<Brand[]>(`${this.resourceUrl}/category/${id}`, {observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<Brand[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  public getbranddevice(): Observable<Brand[]> {
    return this.http.get<Brand[]>(`${this.resourceUrl}`);
  }

  delete(id: string): Observable<CustomHttpRespone> {
    return this.http.delete<CustomHttpRespone>(`${this.resourceUrl}/${id}`);
  }

  addVehicleModelToCollectionIfMissing(
    vehicleModelCollection: Brand[],
    ...vehicleModelsToCheck: (Brand | null | undefined)[]
  ): Brand[] {
    const vehicleModels: Brand[] = vehicleModelsToCheck.filter(isPresent);
    if (vehicleModels.length > 0) {
      const vehicleModelCollectionIdentifiers = vehicleModelCollection.map(
        // tslint:disable-next-line:no-non-null-assertion
        vehicleModelItem => getDeviceBrandIdentifier(vehicleModelItem)!
      );
      const vehicleModelsToAdd = vehicleModels.filter(vehicleModelItem => {
        const vehicleModelIdentifier = getDeviceBrandIdentifier(vehicleModelItem);
        if (vehicleModelIdentifier == null || vehicleModelCollectionIdentifiers.includes(vehicleModelIdentifier)) {
          return false;
        }
        vehicleModelCollectionIdentifiers.push(vehicleModelIdentifier);
        return true;
      });
      return [...vehicleModelsToAdd, ...vehicleModelCollection];
    }
    return vehicleModelCollection;
  }
}
