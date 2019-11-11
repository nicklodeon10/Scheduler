import {Component} from '@angular/core';
import { Employee } from './_model/app.employee';
import { Router } from '@angular/router';
import { AuthenticationService } from './_service/app.authenticationservice';

@Component({
    selector: 'login',
    templateUrl: '/_pages/app.login.html'
})
export class LoginComponent{

    username:String;
    password:String;
    user:Employee;
    invalidLogin:boolean=false;

    constructor(private router: Router, private loginservice: AuthenticationService) { }

//Checks user authentication
  checkLogin() {
    if (this.loginservice.authenticate(this.username, this.password)) {
      this.loginservice.getRole(this.username).subscribe((data:Employee)=>{
        sessionStorage.setItem('userId',String(data.empId)); 
      });
      this.loginservice.getRole(this.username).subscribe((data:Employee)=>{
        sessionStorage.setItem('userId',String(data.empId)); 
      });
      alert("Successfully Logged In.");
      this.invalidLogin = false;
      console.log(sessionStorage.getItem('userId'));
      //this.router.navigate(['dashboard']);
    } else{
      alert("Invalid Login Credentials.");
      this.invalidLogin = true;
    }
  }

}