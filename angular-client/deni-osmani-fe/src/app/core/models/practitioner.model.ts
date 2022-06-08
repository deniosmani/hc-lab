import { Organization } from "./organization.model"

export interface Practitioner{
  id:number,
  name:string,
  surname:string,
  gender:Gender,
  birthDay:Date,
  address:String,
  phone:string,
  email:string,
  qualification:Qualification,
  organization:Organization
}
export interface Qualification{
  value:string;
}
export interface Gender{
  value:string;
}
