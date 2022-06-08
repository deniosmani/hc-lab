import { Organization } from "./organization.model";
import { Patient } from "./patient.model";
import { Practitioner } from "./practitioner.model";

export interface ServiceType{
  value:string;
}
export interface ExamPriority{
  value:string;
}
export interface ExamStatus{
  value:string;
}
export interface Examination{
  id:number,
  serviceTypeDto:ServiceType,
  startDate:Date,
  endDate:Date,
  diagnosis:string,
  patient:Patient,
  practitioners:Practitioner[],
  organization:Organization,
  priority:ExamPriority,
  status:ExamStatus
}
