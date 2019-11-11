import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthenticationService } from '../_service/app.authenticationservice';

//Author: Devang
//Description: Performs Routing for invalid user
//Created On: 21/10/2019

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(private router: Router, private authService: AuthenticationService) { }

  //Routes to error page if user is not logged in
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authService.isUserLoggedIn()){
      	return true;
	}
	this.router.navigate(['login']);
      	return false;

  }

}