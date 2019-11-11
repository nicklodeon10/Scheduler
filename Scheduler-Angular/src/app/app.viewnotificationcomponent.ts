import {Component, OnInit} from '@angular/core';
import { NotificationService } from './_service/app.notificationservice';
import { Notification } from './_model/app.notification';

@Component({
    selector: 'viewnotifications',
    templateUrl: '/_pages/app.viewallnotifications.html'
})
export class ViewNotificationComponent implements OnInit{

    empId:number;
    notList:Notification[];

    constructor(private notService:NotificationService){
        this.empId=+sessionStorage.getItem('userId');
    }

    ngOnInit(){
        this.notService.getAllNotifications(this.empId).subscribe((data:Notification[])=>this.notList=data);
    }

}