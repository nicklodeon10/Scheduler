import { Notification } from "./app.notification";
import { Reminder } from "./app.reminder";
import { Meeting } from "./app.meeting";

export class Employee{
    empId:number;
    empName:string;
    userName:string;
    empPassword:string;
    empPhone:string;
    empEmail:string;
    notifications:Notification[];
    reminders:Reminder[];
    meetings:Meeting[];
    active:boolean;
}