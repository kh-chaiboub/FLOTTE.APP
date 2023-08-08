import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import {getVehicleModelIdentifier, VehicleModel} from '../../core/models/vehicle-model.model';
import {createRequestOption} from '../../core/request/request-util';
import {environment} from '../../../environments/environment';
import {CustomHttpRespone} from '../../core/models/custom-http-response';
import {isPresent} from '../../core/util/operators';




export type EntityResponseType = HttpResponse<VehicleModel>;
export type EntityArrayResponseType = HttpResponse<VehicleModel[]>;

@Injectable({ providedIn: 'root' })
export class VehicleModelService {

  public resourceUrl = environment.apiUrl + '/vehicule-service/api/vehicle-models';
  public resourceUrlFile = environment.apiUrl + '/uploadfiles-service';
  constructor(protected http: HttpClient) {}

  upload(fileToUploaddefautImage: File,fileToUploadmouve_D_Image: File,fileToUploadmouve_G_Image:File,fileToUploadstop_D_Image:File,fileToUploadstop_G_Image:File,fileToUploadalert_Mission_Image:File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', fileToUploaddefautImage);
    formData.append('file', fileToUploadmouve_D_Image);
    formData.append('file', fileToUploadmouve_G_Image);
    formData.append('file', fileToUploadstop_D_Image);
    formData.append('file', fileToUploadstop_G_Image);
    formData.append('file', fileToUploadalert_Mission_Image);

    console.log(formData.getAll('file'))
    const req = new HttpRequest('POST', `${this.resourceUrlFile}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }
  public getDataImage(imagevl: string): Observable<EntityArrayResponseType> {
    console.log(environment.apiUrl + `/uploadfiles-service/getImage/${imagevl}`);
    return this.http.get<any>(environment.apiUrl + `/uploadfiles-service/getImage/${imagevl}`, {observe: 'response' });
  }

  public getBrandByModel(id: string): Observable<EntityArrayResponseType>  {
    return this.http.get<VehicleModel[]>(this.resourceUrl + `/vehicle-models/modelsBybrands/${id}`, {observe: 'response' });
  }

  create(vehicleModel: VehicleModel): Observable<EntityResponseType> {
    return this.http.post<VehicleModel>(this.resourceUrl +`/vehicle-models`, vehicleModel, { observe: 'response' });
  }

  update(vehicleModel: VehicleModel): Observable<EntityResponseType> {
    return this.http.put<VehicleModel>(`${this.resourceUrl}/${getVehicleModelIdentifier(vehicleModel) as string}`, vehicleModel, {
      observe: 'response',
    });
  }





  partialUpdate(vehicleModel: VehicleModel): Observable<EntityResponseType> {
    return this.http.patch<VehicleModel>(`${this.resourceUrl}/${getVehicleModelIdentifier(vehicleModel) as string}`, vehicleModel, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<VehicleModel>(`${this.resourceUrl}/vehicle-models/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<VehicleModel[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  public getVehicleModel(): Observable<VehicleModel[]> {
    return this.http.get<VehicleModel[]>(`${this.resourceUrl}/vehicle-models`);
  }

  delete(id: string): Observable<CustomHttpRespone> {
    return this.http.delete<CustomHttpRespone>(`${this.resourceUrl}/vehicle-models/${id}`);
  }

  addVehicleModelToCollectionIfMissing(
    vehicleModelCollection: VehicleModel[],
    ...vehicleModelsToCheck: (VehicleModel | null | undefined)[]
  ): VehicleModel[] {
    const vehicleModels: VehicleModel[] = vehicleModelsToCheck.filter(isPresent);
    if (vehicleModels.length > 0) {
      const vehicleModelCollectionIdentifiers = vehicleModelCollection.map(
        // tslint:disable-next-line:no-non-null-assertion
        vehicleModelItem => getVehicleModelIdentifier(vehicleModelItem)!
      );
      const vehicleModelsToAdd = vehicleModels.filter(vehicleModelItem => {
        const vehicleModelIdentifier = getVehicleModelIdentifier(vehicleModelItem);
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
