import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import {ILocalites, Localites} from '../../core/models/localites.model';
import {environment} from '../../../environments/environment';
import {ILocalitesCivil,LocalitesCivil} from '../../core/models/localitesCivil.model'

@Injectable({
  providedIn: 'root'
})
export class FlotteMapService {
  public resourceUrl = environment.localitePositionVl
  public resourceUrlCivil = environment.localitePositionCV

  constructor(private http: HttpClient) { }

  findLocalite(lon: any,lat:any): Observable<ILocalites> {

    return this.http.get<Localites>(this.resourceUrl + `/localite/?lon=${lon}&lat=${lat}`);
  }

  findLocaliteCivil(lon: any,lat:any): Observable<ILocalitesCivil> {

    return this.http.get<LocalitesCivil>(this.resourceUrlCivil + `/reverse?format=json&lon=${lon}&lat=${lat}`);
  }

  findLocaliteByName(name:string): Observable<ILocalitesCivil[]> {

    return this.http.get<LocalitesCivil[]>(this.resourceUrlCivil + `/search?q=${name}`);
  }

  getBtShapes() {
    return this.http.get('assets/geojson/geojson.json');
  }
  getCmpShapes() {
    return this.http.get('assets/geojson/Compagnies.json');
  }
  getRegShapes() {
    return this.http.get('assets/geojson/Region_Gr.json');
  }

  getVl() {
    return this.http.get('assets/geojson/location.json');
  }

}
