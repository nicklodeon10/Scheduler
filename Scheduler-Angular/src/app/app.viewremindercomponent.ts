import {Component, OnInit} from '@angular/core';
import { ReminderService } from './_service/app.reminderservice';
import { Reminder } from './_model/app.reminder';

@Component({
    selector: 'viewreminder',
    templateUrl: '/_pages/app.viewallreminder.html'
})
export class ViewReminderComponent implements OnInit{

    constructor(private reminderService:ReminderService){}

    empId:number=3;

    reminderList:Reminder[];

    ngOnInit(){
        this.reminderService.getAllReminders(this.empId).subscribe((data:Reminder[])=>this.reminderList=data);
    }

}