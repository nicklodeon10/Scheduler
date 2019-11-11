import {Component} from '@angular/core';
import { Employee } from './_model/app.employee';
import { EmployeeService } from './_service/app.employeeservice';
import { Meeting } from './_model/app.meeting';
import { MeetingService } from './_service/app.meetingservice';

@Component({
    selector: 'addmeeting',
    templateUrl: '/_pages/app.addmeeting.html'
})
export class AddMeetingComponent{

    constructor(private empService:EmployeeService, private meetingService:MeetingService){
        this.empId=+sessionStorage.getItem('userId');
    }

    empId:number;

    participantId:String="";

    empNameFound=false;
    empEmailFound=false;
    empFound:Employee={empId: 0,userName:"", empEmail:"", active:true, empName:"", empPassword:"", empPhone:"",meetings:null, notifications: null, reminders:null};
    empListFound:Employee[];

    nameSearchText:String;
    emailSearchText:String;

    meeting:Meeting={active:true,endTime:null,location:"",meetingId:null,meetingTitle:"",organiser:null,participantStatus:null,participants:null,startTime:null};

    searchByName(){
        this.empService.searchByName(this.nameSearchText).subscribe((data:Employee[])=>this.empListFound=data);
        this.empNameFound=true;
        this.empEmailFound=false;
    }

    searchByEmail(){
        this.empService.searchByEmail(this.emailSearchText).subscribe((data:Employee)=>this.empFound=data);
        console.log(this.empFound);
        this.empEmailFound=true;
        this.empNameFound=false;
    }
    
    addParticipant(empId:number){
        this.participantId+=String(empId)+' ';
        alert("Participant Added");
        this.empEmailFound=false;
        this.empNameFound=false;
    }

    addedMeeting:Meeting;
    addMeeting(){
        this.meeting.participants=this.participantId;
        this.meetingService.addMeeting(this.meeting, this.empId).subscribe((data:Meeting)=>this.addedMeeting=data);
        alert("Meeting Added. All Participants have been notified.");
    }

}