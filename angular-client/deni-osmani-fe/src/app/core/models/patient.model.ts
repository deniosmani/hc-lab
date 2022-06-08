import { Organization } from "./organization.model";
import { Gender, Practitioner } from "./practitioner.model";

export interface Patient{
  id:number,
  name:string,
  surname:string,
  birthDate:Date,
  address:string,
  phone:string,
  email:string,
  deceased:string,
  organization:Organization,
  primaryCareProvider:Practitioner,
  gender:Gender,
  martialStatus:MartialStatus
}

export interface MartialStatus{
  value:string
}
