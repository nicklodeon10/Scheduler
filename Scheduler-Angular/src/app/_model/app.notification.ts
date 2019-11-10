import { Employee } from "./app.employee";

export class Notification{
    notId:number;
    toEmp:Employee;
    fromEmpId:String;
    notMessage:String;
    meetingId:number;
    notTime:any;
    seen:boolean;
}