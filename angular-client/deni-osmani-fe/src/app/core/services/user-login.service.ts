import { Injectable } from '@angular/core';
import { UserDetails } from '../models/UserDetails';

@Injectable({
  providedIn: 'root'
})
export class UserLoginService {

  userData?:{firstname:string, lastname:string};

  constructor() { }

  get storage(){
    return localStorage;
  }

  setLoginCredentials(userDetails: UserDetails) {
    this.storage.setItem('token', 'Basic ' + btoa(`${userDetails.username}:${userDetails.password}`))
    this.userData = { firstname: userDetails.firstname, lastname: userDetails.lastname};
  }

  logoutUser() {
    this.storage.removeItem('token');
    this.userData = undefined;
  }

  get token() {
    return this.storage.getItem('token');
  }
  get isUserLoggedIn() {
    // if  (this.token) {
    //   return true
    // } else {
    //   return false;
    // }
    return !!this.token;
  }
}
