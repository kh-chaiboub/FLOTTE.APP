import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {getRefOrganIdentifier, RefOrgan} from "../models/ref-organ.model";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {createRequestOption} from '../request/request-util';
import {environment} from '../../../environments/environment';



export type EntityResponseType = HttpResponse<RefOrgan>;
export type EntityArrayResponseType = HttpResponse<RefOrgan[]>;

@Injectable({
  providedIn: 'root'
})
export class RefOrganService {


  protected resourceUrl =  environment.apiUrl + '/reforgan-service/api/ref-organs';


  public getRegOrgans(): Observable<RefOrgan[]> {
    return this.http.get<RefOrgan[]>(`${this.resourceUrl}/ref-organs`);
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);

    return this.http.get<RefOrgan[]>(this.resourceUrl + '/ref-organs', { params: options, observe: 'response' });
  }

  constructor(protected http: HttpClient) {}

  create(refOrgan: RefOrgan): Observable<EntityResponseType> {
    return this.http.post<RefOrgan>(this.resourceUrl, refOrgan, { observe: 'response' });
  }

  update(refOrgan: RefOrgan): Observable<EntityResponseType> {
    return this.http.put<RefOrgan>(`${this.resourceUrl}/${getRefOrganIdentifier(refOrgan) as string}`, refOrgan, { observe: 'response' });
  }

  partialUpdate(refOrgan: RefOrgan): Observable<EntityResponseType> {
    return this.http.patch<RefOrgan>(`${this.resourceUrl}/${getRefOrganIdentifier(refOrgan) as string}`, refOrgan, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<RefOrgan>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
