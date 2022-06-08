import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Practitioner } from '../models/practitioner.model';

@Injectable({
  providedIn: 'root'
})
export class HttpPractitionerService {

  constructor(private httpClient: HttpClient) { }

  getByPage(name:string,unassigned:string,page: number, size:number,sortBy:string, sortOrder: string = 'asc'){
    return this.httpClient.get<any>(`${environment.serverUrl}/practitioners/proba?name=${name}&unassigned=${unassigned}&pageNo=${page-1}&pageSize=${size}&sortBy=${sortBy}&sortOrder=${sortOrder}`)
  }
  deletePractitioner(practitioner:Practitioner){
    return this.httpClient.delete<any>(`${environment.serverUrl}/practitioners/${practitioner.id}`,{responseType:'text' as 'json'});
  }
  findById(id:number){
    return this.httpClient.get<any>(`${environment.serverUrl}/practitioners/${id}`)
  }
  save(practitioner:Practitioner){
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.post<any>(`${environment.serverUrl}/practitioners`,practitioner,{headers:headers});
  }
  update(practitioner:Practitioner){
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.put<any>(`${environment.serverUrl}/practitioners/${practitioner.id}`,practitioner,{headers:headers})
  }
  getAllOrganizations(){
    return this.httpClient.get<any>(`${environment.serverUrl}/organizations/list`)
  }
  getGenders(){
    return this.httpClient.get<any>(`${environment.serverUrl}/genders`)
  }
  getQualifications(){
    return this.httpClient.get<any>(`${environment.serverUrl}/qualifications`)
  }

}
