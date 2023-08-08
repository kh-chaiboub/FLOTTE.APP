import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import {getAlerteIdentifier, alerte } from '../models/alerts.model';
import { Observable } from 'rxjs';
import {createRequestOption} from '../request/request-util';
import {CustomHttpRespone} from '../models/custom-http-response';
import {isPresent} from '../util/operators';
import {environment} from '../../../environments/environment';

export type EntityResponseType = HttpResponse<alerte>;
export type EntityArrayResponseType = HttpResponse<alerte[]>;
@Injectable({
  providedIn: 'root'
})
export class AlerteService {

   public resourceUrl = environment.apiUrl + '';


  constructor(protected http: HttpClient) { }



  create(alerteh: alerte): Observable<EntityResponseType> {
     return this.http.post<alerte>(this.resourceUrl, alerteh, { observe: 'response' });

  }

  update(alerteh: alerte): Observable<EntityResponseType> {
    return this.http.put<alerte>(`${this.resourceUrl}/${getAlerteIdentifier(alerteh) as string}`, alerteh, {
      observe: 'response',
     });
  }

  partialUpdate(alerteh: alerte): Observable<EntityResponseType> {
    return this.http.patch<alerte>(`${this}/${getAlerteIdentifier(alerteh) as string}`, alerteh, {
      observe: 'response',

    });
  }


  find(id: string): Observable<EntityResponseType> {
     return this.http.get<alerte>(`${this.resourceUrl}/${id}`, { observe: 'response' });

  }


  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<alerte[]>(this.resourceUrl, { params: options, observe: 'response' });

  }

  public getalerte(): Observable<alerte[]> {
    return this.http.get<alerte[]>(`${this.resourceUrl}`);

  }

  delete(id: string): Observable<CustomHttpRespone> {
     return this.http.delete<CustomHttpRespone>(`${this.resourceUrl}/${id}`);

  }


  addVehicleModelToCollectionIfMissing(
    vehicleModelCollection: alerte[],
    ...vehicleModelsToCheck: (alerte | null | undefined)[]
  ): alerte[] {
    const vehicleModels: alerte[] = vehicleModelsToCheck.filter(isPresent);
    if (vehicleModels.length > 0) {
      const vehicleModelCollectionIdentifiers = vehicleModelCollection.map(
        // tslint:disable-next-line:no-non-null-assertion
        vehicleModelItem => getAlerteIdentifier(vehicleModelItem)!
      );
      const vehicleModelsToAdd = vehicleModels.filter(vehicleModelItem => {
        const vehicleModelIdentifier = getAlerteIdentifier(vehicleModelItem);
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
