import {Component, OnInit} from '@angular/core';
import { ReminderService } from './_service/app.reminderservice';
import { Reminder } from './_model/app.reminder';

@Component({
    selector: 'viewreminder',
    templateUrl: '/_pages/app.viewallreminder.html'
})
export class ViewReminderComponent implements OnInit{

    empId:number;
    reminderList:Reminder[];

    constructor(private reminderService:ReminderService){
        this.empId=+sessionStorage.getItem('userId');
    }

    ngOnInit(){
        this.reminderService.getAllReminders(this.empId).subscribe((data:Reminder[])=>this.reminderList=data);
    }

}