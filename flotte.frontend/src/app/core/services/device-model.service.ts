import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import {getDeviceModelIdentifier, DeviceModel } from '../../core/models/device-model.model';
import { Observable } from 'rxjs';
import {createRequestOption} from '../../core/request/request-util';
import {CustomHttpRespone} from '../../core/models/custom-http-response';
import {environment} from '../../../environments/environment';
import {isPresent} from '../../core/util/operators';
import {DeviceCategory} from '../../core/models/device-category.model';



export type EntityResponseType = HttpResponse<DeviceModel>;
export type EntityArrayResponseType = HttpResponse<DeviceModel[]>;
@Injectable({
  providedIn: 'root'
})
export class DeviceModelService {

  public resourceUrl = environment.apiUrl + '/device-service/api/models/models';

  constructor(protected http: HttpClient) { }



  public getcategorie(): Observable<DeviceCategory[]> {

    return this.http.get<DeviceCategory[]>(environment.apiUrl + `/device-service/api/categories/categories`);
  }

  public getCategoryByModel(id: string): Observable<DeviceModel[]> {
    return this.http.get<DeviceModel[]>(environment.apiUrl + `/device-service/api/models/models${id}`);
  }

  create(gpsModel: DeviceModel): Observable<EntityResponseType> {
    return this.http.post<DeviceModel>(this.resourceUrl, gpsModel, { observe: 'response' });
  }

  update(gpsModel: DeviceModel): Observable<EntityResponseType> {
    return this.http.put<DeviceModel>(`${this.resourceUrl}/${getDeviceModelIdentifier(gpsModel) as string}`, gpsModel, {
      observe: 'response',
    });
  }

  partialUpdate(gpsModel: DeviceModel): Observable<EntityResponseType> {
    return this.http.patch<DeviceModel>(`${this.resourceUrl}/${getDeviceModelIdentifier(gpsModel) as string}`, gpsModel, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<DeviceModel>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  // tslint:disable-next-line:ban-types
  findByBrand(id: String): Observable<EntityArrayResponseType> {
    return this.http.get<DeviceModel[]>(`${this.resourceUrl}/brands/${id}`, {observe: 'response' });
  }



  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<DeviceModel[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  public getGpsModel(): Observable<DeviceModel[]> {
    return this.http.get<DeviceModel[]>(`${this.resourceUrl}`);
  }

  delete(id: string): Observable<CustomHttpRespone> {
    return this.http.delete<CustomHttpRespone>(`${this.resourceUrl}/${id}`);
  }

  addcategoriModelToCollectionIfMissingg(
    vehicleModelCollection: DeviceModel[],
    ...vehicleModelsToCheck: (DeviceModel | null | undefined)[]
  ): DeviceModel[] {
    const gpsModels: DeviceModel[] = vehicleModelsToCheck.filter(isPresent);
    if (gpsModels.length > 0) {
      const vehicleModelCollectionIdentifiers = vehicleModelCollection.map(
        // tslint:disable-next-line:no-non-null-assertion
        vehicleModelItem => getDeviceModelIdentifier(vehicleModelItem)!
      );
      const vehicleModelsToAdd = gpsModels.filter(vehicleModelItem => {
        const vehicleModelIdentifier = getDeviceModelIdentifier(vehicleModelItem);
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
