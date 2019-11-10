import { Employee } from "./app.employee";

export class Meeting{
    meetingId:number;
    meetingTitle:String;
    startTime:any;
    endTime:any;
    organiser:Employee;
    participants:String;
    participantStatus:String;
    location:String;
    active:boolean;
}