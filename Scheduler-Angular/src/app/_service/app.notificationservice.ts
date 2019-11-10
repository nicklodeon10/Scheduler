import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})

export class NotificationService{

    constructor(private httpClient:HttpClient){}

    getNotificationsCount(empId:number){
        return this.httpClient.get('http://localhost:9088/notification/getCount?empId='+empId);
    }

}