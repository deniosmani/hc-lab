import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Organization } from '../models/organization.model';

@Injectable({
  providedIn: 'root'
})
export class HttpOrganizationService {

  constructor(private httpClient: HttpClient) { }

  getByPage(search:string,page: number, size:number,sortBy:string, sortOrder: string = 'asc'){
    return this.httpClient.get<any>(`${environment.serverUrl}/organizations/filter?search=${search}&pageNo=${page-1}&pageSize=${size}&sortBy=${sortBy}&sortOrder=${sortOrder}`)
  }
  deleteOrganization(organization:Organization){
    return this.httpClient.delete<any>(`${environment.serverUrl}/organizations/${organization.id}`,{responseType:'text' as 'json'});
  }
  findById(id:number){
    return this.httpClient.get<any>(`${environment.serverUrl}/organizations/${id}`)
  }
  save(organization:Organization){
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.post<any>(`${environment.serverUrl}/organizations`,organization,{headers:headers});
  }
  update(organization:Organization){
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.put<any>(`${environment.serverUrl}/organizations/${organization.id}`,organization,{headers:headers})
  }
  getAllOrgType(){
    return this.httpClient.get<any>((`${environment.serverUrl}/organizationTypes`));
  }
  getNames(){
    return this.httpClient.get<any>((`${environment.serverUrl}/organizations/names`));
  }
  getPractitoners(id:number){
    return this.httpClient.get<any>((`${environment.serverUrl}/organizations/getpract/${id}`));
  }
}
