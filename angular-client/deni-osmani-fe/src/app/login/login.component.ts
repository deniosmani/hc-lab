import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { UserDetails } from '../core/models/UserDetails';
import { HttpUserService } from '../core/services/http-user.service';
import { UserLoginService } from '../core/services/user-login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm?:FormGroup;
  subscriptions: Subscription=new Subscription();
  constructor(
    private fb:FormBuilder,
    private router:Router,
    private httpUser: HttpUserService,
    private userLogin: UserLoginService
  ) { }
  badLogin=false;
  ngOnInit(): void {
    this.createForm();
  }
  createForm() {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }
  ngOnDestroy(): void {
    console.log('Unsubscribe subscriptions');
    this.subscriptions.unsubscribe();
  }
  login() {
    console.log('Pre login');
    this.subscriptions.add(
      this.httpUser.login(this.loginForm?.value).subscribe(
        (userDetais: UserDetails) => {
          console.log('userDetails:', userDetais);
          this.userLogin.setLoginCredentials(userDetais);
          this.badLogin=false;
          this.router.navigate(['/home']);
        },
        error=>{
          this.badLogin=true;
        }
      )
    );
    console.log('Kraj login');
  }
}
