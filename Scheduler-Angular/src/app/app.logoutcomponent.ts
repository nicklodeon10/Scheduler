import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './_service/app.authenticationservice';
import { Router } from '@angular/router';

//Author: Devang
//Description: Component for logout
//Created On: 21/10/2019

@Component({
  selector: '/_pages/app-logout',
  template: ''
})
export class LogoutComponent implements OnInit {

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router) {
  }

  ngOnInit() {
    this.authenticationService.logOut();
    sessionStorage.setItem('userId',null);
    this.router.navigate(['login']);
  }

}