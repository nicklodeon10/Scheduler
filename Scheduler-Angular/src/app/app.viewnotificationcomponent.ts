import {Component, OnInit} from '@angular/core';
import { NotificationService } from './_service/app.notificationservice';
import { Notification } from './_model/app.notification';

@Component({
    selector: 'viewnotifications',
    templateUrl: '/_pages/app.viewallnotifications.html'
})
export class ViewNotificationComponent implements OnInit{

    constructor(private notService:NotificationService){}

    empId:number=3;
    notList:Notification[];

    ngOnInit(){
        this.notService.getAllNotifications(this.empId).subscribe((data:Notification[])=>this.notList=data);
    }

}