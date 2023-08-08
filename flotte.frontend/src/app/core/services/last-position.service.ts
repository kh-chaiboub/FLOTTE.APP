import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { environment } from '../../../environments/environment';;
import {createRequestOption} from '../request/request-util';
import { ILastPosition, LastPosition } from '../models/last-position.model';
import {Vehicle} from '../models/vehicle.model';
import DataSource from 'devextreme/data/data_source';

type EntityResponseType = HttpResponse<ILastPosition>;
type EntityArrayResponseType = HttpResponse<ILastPosition[]>;

@Injectable({ providedIn: 'root' })
export class LastPositionService {
  public resourceUrl = environment.apiUrl + '/position-service/api/last-positions';
  private host = environment.apiUrl + '/vehicule-service/api/vehicules';

  constructor(protected http: HttpClient) {}

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ILastPosition>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ILastPosition[]>(this.resourceUrl + '/last-positions', { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(lastPosition: ILastPosition): ILastPosition {
    const copy: ILastPosition = Object.assign({}, lastPosition, {
      crationDate: lastPosition.crationDate && lastPosition.crationDate.isValid() ? lastPosition.crationDate.toJSON() : undefined,
      fixTime: lastPosition.fixTime && lastPosition.fixTime.isValid() ? lastPosition.fixTime.toJSON() : undefined,
      deviceTime: lastPosition.deviceTime && lastPosition.deviceTime.isValid() ? lastPosition.deviceTime.toJSON() : undefined,
      serverTime: lastPosition.serverTime && lastPosition.serverTime.isValid() ? lastPosition.serverTime.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.crationDate = res.body.crationDate ? moment(res.body.crationDate) : undefined;
      res.body.fixTime = res.body.fixTime ? moment(res.body.fixTime) : undefined;
      res.body.deviceTime = res.body.deviceTime ? moment(res.body.deviceTime) : undefined;
      res.body.serverTime = res.body.serverTime ? moment(res.body.serverTime) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((lastPosition: ILastPosition) => {
        lastPosition.crationDate = lastPosition.crationDate ? moment(lastPosition.crationDate) : undefined;
        lastPosition.fixTime = lastPosition.fixTime ? moment(lastPosition.fixTime) : undefined;
        lastPosition.deviceTime = lastPosition.deviceTime ? moment(lastPosition.deviceTime) : undefined;
        lastPosition.serverTime = lastPosition.serverTime ? moment(lastPosition.serverTime) : undefined;
      });
    }
    return res;
  }
  public getLastPositions(): Observable<LastPosition[]> {
    return this.http.get<LastPosition[]>(`${this.resourceUrl + '/last-positions'}`);
  }
  getDataSource(lastPositions: LastPosition[]) {

    return new DataSource({
      store: {
        type:  'array',
        data: lastPositions,
        key: 'id',

      },


    });
  }
  public getVehicles(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(`${this.host}/vehicules`);
  }
  public getLastPositionsRealTime()  {
    return this.http.get<LastPosition[]>(`${this.resourceUrl + '/last-positions'}`);
      }


}
