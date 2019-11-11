import {Component} from '@angular/core';
import { EmployeeService } from './_service/app.employeeservice';
import { Employee } from './_model/app.employee';
import { Router } from '@angular/router';

@Component({
    selector: 'register',
    templateUrl: '/_pages/app.register.html'
})
export class RegisterComponent{

    user:Employee={active:true,empEmail:"",userName:"",reminders:null,notifications:null,meetings:null,empPhone:"",empPassword:"",empName:"",empId:null};

    constructor(private router:Router ,private empService:EmployeeService){}

    registerUser(){
        this.empService.addEmployee(this.user).subscribe();
        alert("User Registered.");
        this.router.navigate(['login']);
    }

}