import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Examination } from '../models/examination.model';

@Injectable({
  providedIn: 'root'
})
export class HttpExaminationService {

  constructor(private httpClient:HttpClient) { }
  getByPage(practitioner:string,patient:string,orgId:number,page: number, size:number,sortBy:string, sortOrder: string = 'asc'){
    return this.httpClient.get<any>(`${environment.serverUrl}/examinations/proba?practitioner=${practitioner}&patient=${patient}&orgId=${orgId}&pageNo=${page-1}&pageSize=${size}&sortBy=${sortBy}&sortOrder=${sortOrder}`)
  }
  deleteExamination(examination:Examination){
    return this.httpClient.delete<any>(`${environment.serverUrl}/examinations/${examination.id}`,{responseType:'text' as 'json'});
  }
  findById(id:number){
    return this.httpClient.get<any>(`${environment.serverUrl}/examinations/${id}`)
  }
  save(examination:Examination){
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.post<any>(`${environment.serverUrl}/examinations`,examination,{headers:headers});
  }
  update(examination:Examination){
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.put<any>(`${environment.serverUrl}/examinations/${examination.id}`,examination,{headers:headers})
  }
  getAllOrganizations(){
    return this.httpClient.get<any>(`${environment.serverUrl}/organizations/list`)
  }
  getAllPractitioners(orgId:number){
    return this.httpClient.get<any>(`${environment.serverUrl}/practitioners/listall/${orgId}`)
  }
  getAllExamStatus(){
    return this.httpClient.get<any>(`${environment.serverUrl}/examstatuses`)
  }
  getAllExamPriority(){
    return this.httpClient.get<any>(`${environment.serverUrl}/exampriorities`)
  }
  getAllServiceType(){
    return this.httpClient.get<any>(`${environment.serverUrl}/servicetypes`)
  }
  getAllPatients(){
    return this.httpClient.get<any>((`${environment.serverUrl}/patients/list`));
  }
}
