import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})

export class MeetingService{

    constructor(private httpClient:HttpClient){}

    getUpcomingMeetingsCount(empId:number){
        return this.httpClient.get('http://localhost:9088/meeting/getCount?empId='+empId);
    }

    getMeetings(empId:number){
        return this.httpClient.get('http://localhost:9088/meeting/upcoming/view?empId='+empId);
    }

}