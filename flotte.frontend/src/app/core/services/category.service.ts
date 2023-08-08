import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {getDeviceCategoryIdentifier, DeviceCategory} from '../../core/models/device-category.model';
import { Observable } from 'rxjs';
import {createRequestOption} from '../../core/request/request-util';
import {CustomHttpRespone} from '../../core/models/custom-http-response';
import {isPresent} from '../../core/util/operators';


export type EntityResponseType = HttpResponse<DeviceCategory>;
export type EntityArrayResponseType = HttpResponse<DeviceCategory[]>;

@Injectable({ providedIn: 'root' })

export class CategoryService {

  public resourceUrl = environment.apiUrl + '/device-service/api/categories/';


  constructor(protected http: HttpClient) {}


  create(deviceCategory: DeviceCategory): Observable<EntityResponseType> {
    return this.http.post<DeviceCategory>(this.resourceUrl + 'categories', deviceCategory, { observe: 'response' });
  }


  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);

    return this.http.get<DeviceCategory[]>(this.resourceUrl + 'categories', { params: options, observe: 'response' });
  }


  find(id: string): Observable<EntityResponseType> {
    return this.http.get<DeviceCategory>(`${this.resourceUrl + 'categories'}/${id}`, { observe: 'response' });
  }
  public getDeviceCategorys(): Observable<DeviceCategory[]> {

    return this.http.get<DeviceCategory[]>(`${this.resourceUrl + 'categories'}`);
  }

  delete(id: string): Observable<CustomHttpRespone> {
    return this.http.delete<CustomHttpRespone>(`${this.resourceUrl + 'categories'}/${id}`);
  }
  public getCategories(): Observable<DeviceCategory[]> {
    return this.http.get<DeviceCategory[]>(`${this.resourceUrl}/categories`);
  }

  addDeviceCategorysToCollectionIfMissing(
    deviceCategoryCollection: DeviceCategory[],
    ...deviceCategoryToCheck: (DeviceCategory | null | undefined)[]
  ): DeviceCategory[] {
    const deviceCategory: DeviceCategory[] = deviceCategoryToCheck.filter(isPresent);
    if (deviceCategory.length > 0) {
      const deviceCategoryCollectionIdentifiers = deviceCategoryCollection.map(
        // tslint:disable-next-line:no-non-null-assertion
        deviceCategoryItem => getDeviceCategoryIdentifier(deviceCategoryItem)!
      );
      const deviceCategoryToAdd = deviceCategory.filter(deviceCategoryItem => {
        const deviceCategoryIdentifier = getDeviceCategoryIdentifier(deviceCategoryItem);
        if (deviceCategoryIdentifier == null || deviceCategoryCollectionIdentifiers.includes(deviceCategoryIdentifier)) {
          return false;
        }
        deviceCategoryCollectionIdentifiers.push(deviceCategoryIdentifier);
        return true;
      });
      return [...deviceCategoryToAdd, ...deviceCategoryCollection];
    }
    return deviceCategoryCollection;
  }


}
