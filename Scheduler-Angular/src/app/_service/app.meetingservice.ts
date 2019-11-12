import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Meeting } from '../_model/app.meeting';

@Injectable({
    providedIn: 'root'
})

export class MeetingService{

    constructor(private httpClient:HttpClient){}

    addMeeting(meeting:Meeting, empId:number){
        return this.httpClient.post('http://13.233.124.218:9088/meeting/add?organiserId='+empId,meeting);
    }

    getUpcomingMeetingsCount(empId:number){
        return this.httpClient.get('http://13.233.124.218:9088/meeting/getCount?empId='+empId);
    }

    getMeetings(empId:number){
        return this.httpClient.get('http://13.233.124.218:9088/meeting/upcoming/view?empId='+empId);
    }

    getNextMeeting(empId:number){
        return this.httpClient.get('http://13.233.124.218:9088/meeting/getNext?empId='+empId);
    }

    getPastMeetings(empId:number){
        return this.httpClient.get('http://13.233.124.218:9088/meeting/past/view?empId='+empId);
    }

    approveMeeting(meetingId:number, empId:number){
        return this.httpClient.get('http://13.233.124.218:9088/meeting/respond/approve?empId='+empId+'&meetingId='+meetingId);
    }

    maybeMeeting(meetingId:number, empId:number){
        return this.httpClient.get('http://13.233.124.218:9088/meeting/respond/maybe?empId='+empId+'&meetingId='+meetingId);
    }

    cancelMeeting(meetingId:number, empId:number){
        return this.httpClient.get('http://13.233.124.218:9088/meeting/respond/cancel?empId='+empId+'&meetingId='+meetingId);
    }

}