import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})

export class ReminderService{

    constructor(private httpClient:HttpClient){}

    getUpcomingReminderCount(empId:number){
        return this.httpClient.get('http://localhost:9088/reminder/getCount?empId='+empId);
    }

    getUpcomingReminders(empId:number){
        return this.httpClient.get('http://localhost:9088/reminder/upcoming/view?empId='+empId);
    }

    getAllReminders(empId:number){
        return this.httpClient.get('http://localhost:9088/reminder/view?empId='+empId);
    }
}