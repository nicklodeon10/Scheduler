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

  temp:number=0;

//Checks user authentication
  checkLogin() {
    if (this.loginservice.authenticate(this.username, this.password)) {
      this.loginservice.getRole(this.username).subscribe((data:Employee)=>{
        sessionStorage.setItem('userId',String(data.empId)); 
      });
      this.redirect();
    } else{
      alert("Invalid Login Credentials.");
      this.invalidLogin = true;
    }
  }

  redirect(){
    this.temp++;
    if(this.temp==2){
      alert("Successfully Logged In.");
      this.invalidLogin = false;
      this.temp=0;
      this.router.navigate(['dashboard']).then(()=>{
        window.location.reload();
      });;
    }
  }

}