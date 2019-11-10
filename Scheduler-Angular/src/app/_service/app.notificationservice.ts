import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})

export class NotificationService{

    constructor(private httpClient:HttpClient){}

    // //Returns list of all airports
    // getAllAirports():Observable<Airport[]>{
    //     return this.httpClient.get<Airport[]>('http://localhost:9088/airport/getall');
    // }

}