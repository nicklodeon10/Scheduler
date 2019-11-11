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

    public barLabel: string = "Password strength:";
    public myColors = ['#DD2C00', '#FF6D00', '#FFD600', '#AEEA00', '#00C853'];

    buttonDisable:boolean=true;

    nameValid:boolean=true;
    validateName(){
        this.nameValid=/^[a-zA-Z ]+$/.test(this.user.empName);
        console.log(this.nameValid);
    }

    phoneValid:boolean=true;
    validatePhone(){
        if(String(this.user.empPhone).length==10){
            this.phoneValid=true;
        }else{
            this.phoneValid=false;
        }
    }

    emailValid:boolean=true;
    validateEmail(){
        this.emailValid=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(this.user.empEmail);
    }

    enableButton(){
        this.buttonDisable=!(this.nameValid&&this.phoneValid&&this.emailValid);
    }

}