export interface OrganizationType{
  value:string;
}
export interface Organization{
  id:number,
  organizationType:OrganizationType,
  name:string,
  address:string,
  phone:string,
  email:string
}
