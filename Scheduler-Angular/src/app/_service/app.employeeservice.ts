import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Employee } from '../_model/app.employee';

@Injectable({
    providedIn: 'root'
})

export class EmployeeService{

    constructor(private httpClient:HttpClient){}

    searchByName(empName:String){
        return this.httpClient.get('http://localhost:9088/employee/search/name?empName='+empName);
    }

    searchByEmail(empEmail:String){
        return this.httpClient.get('http://localhost:9088/employee/search/email?empEmail='+empEmail);
    }

    addEmployee(employee:Employee){
        return this.httpClient.post('http://localhost:9088/employee/add', employee);
    }

}