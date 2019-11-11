import {Component, OnInit} from '@angular/core';
import { Meeting } from './_model/app.meeting';
import { MeetingService } from './_service/app.meetingservice';
import { ReminderService } from './_service/app.reminderservice';
import { NotificationService } from './_service/app.notificationservice';
import { Reminder } from './_model/app.reminder';
import { Notification } from './_model/app.notification';

@Component({
    selector: 'dashboard',
    templateUrl: '/_pages/app.dashboard.html'
})
export class DashboardComponent implements OnInit{

    empId=3;

    upcMeetingsCount:number;
    upcReminderCount:number;
    upcNotificationCount:number;
    nextMeeting:Meeting;
    meetingsList:Meeting[];
    reminderList:Reminder[];
    notificationsList:Notification[];
    notificationType:boolean[];

    constructor(private meetingService:MeetingService, private reminderService:ReminderService, private notificationService:NotificationService){}
    
    ngOnInit(){
        this.meetingService.getUpcomingMeetingsCount(this.empId).subscribe((data:number)=>this.upcMeetingsCount=data);
        this.notificationService.getNotificationsCount(this.empId).subscribe((data:number)=>this.upcNotificationCount=data);
        this.reminderService.getUpcomingReminderCount(this.empId).subscribe((data:number)=>this.upcReminderCount=data);
        this.meetingService.getMeetings(this.empId).subscribe((data:Meeting[])=>this.meetingsList=data);
        this.meetingService.getNextMeeting(this.empId).subscribe((data:Meeting)=>this.nextMeeting=data);
        this.reminderService.getUpcomingReminders(this.empId).subscribe((data:Reminder[])=>this.reminderList=data);
        this.notificationService.getNotifications(this.empId).subscribe((data:Notification[])=>this.notificationsList=data);
    }

    approveMeeting(meetingId:number, notId:number){
        this.meetingService.approveMeeting(meetingId, this.empId);
        this.notificationService.setSeen(notId);
    }

    maybeMeeting(meetingId:number, notId:number){
        this.meetingService.maybeMeeting(meetingId, this.empId);
        this.notificationService.setSeen(notId);
    }

    cancelMeeting(meetingId:number, notId:number){
        this.meetingService.cancelMeeting(meetingId, this.empId);
        this.notificationService.setSeen(notId);
    }

}