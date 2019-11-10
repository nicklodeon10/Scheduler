import { Notification } from "./app.notification";
import { Reminder } from "./app.reminder";
import { Meeting } from "./app.meeting";

export class Employee{
    empId:number;
    empName:String;
    userName:String;
    empPassword:String;
    empPhone:String;
    empEmail:String;
    notifications:Notification[];
    reminders:Reminder[];
    meetings:Meeting[];
    active:boolean;
}