import {Component, OnInit} from '@angular/core';
import { MeetingService } from './_service/app.meetingservice';
import { Meeting } from './_model/app.meeting';

@Component({
    selector: 'pastmeeting',
    templateUrl: '/_pages/app.pastmeeting.html'
})
export class PastMeetingComponent implements OnInit{

    empId:number=3;

    pastMeetings:Meeting[];

    constructor(private meetingService:MeetingService){}

    ngOnInit(){
        this.meetingService.getPastMeetings(this.empId).subscribe((data:Meeting[])=>this.pastMeetings=data);
    }

}