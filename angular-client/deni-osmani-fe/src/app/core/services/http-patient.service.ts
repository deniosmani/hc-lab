import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Patient } from '../models/patient.model';

@Injectable({
  providedIn: 'root'
})
export class HttpPatientService {

  constructor(private httpClient:HttpClient) { }
  getByPage(name:string,genderInput:string,page: number, size:number,sortBy:string, sortOrder: string = 'asc'){
    return this.httpClient.get<any>(`${environment.serverUrl}/patients/proba?name=${name}&genderInput=${genderInput}&pageNo=${page-1}&pageSize=${size}&sortBy=${sortBy}&sortOrder=${sortOrder}`)
  }
  deletePatient(patient:Patient){
    return this.httpClient.delete<any>(`${environment.serverUrl}/patients/${patient.id}`,{responseType:'text' as 'json'});
  }
  findById(id:number){
    return this.httpClient.get<any>(`${environment.serverUrl}/patients/${id}`)
  }
  save(patient:Patient){
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.post<any>(`${environment.serverUrl}/patients`,patient,{headers:headers});
  }
  update(patient:Patient){
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.put<any>(`${environment.serverUrl}/patients/${patient.id}`,patient,{headers:headers})
  }
  getAllOrganizations(){
    return this.httpClient.get<any>(`${environment.serverUrl}/organizations/list`)
  }
  getGenders(){
    return this.httpClient.get<any>(`${environment.serverUrl}/genders`)
  }
  getMartialStatuses(){
    return this.httpClient.get<any>(`${environment.serverUrl}/martialstatuses`)
  }
  getAllPractitioners(orgId:number){
    return this.httpClient.get<any>(`${environment.serverUrl}/practitioners/list/${orgId}`)
  }
}
