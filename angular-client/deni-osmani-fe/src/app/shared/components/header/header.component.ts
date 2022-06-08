import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserLoginService } from 'src/app/core/services/user-login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private userLoginService:UserLoginService,private router:Router) { }

  ngOnInit(): void {
  }
  logout(){
    this.userLoginService.logoutUser();
    this.router.navigate(["/login"]);
  }
}
