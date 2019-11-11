import {HttpEvent, 
    HttpInterceptor, 
    HttpHandler, 
    HttpRequest, 
    HttpResponse, 
    HttpErrorResponse
} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {retry, catchError} from 'rxjs/operators';

//Author: Devang
//Description: Error interceptor and to add tokens to requests
//Created On: 21/10/2019

export class HttpErrorInterceptor implements HttpInterceptor{

intercept(request:HttpRequest<any>, next:HttpHandler):Observable<HttpEvent<any>>{
    if (sessionStorage.getItem('username') && sessionStorage.getItem('token')) {
        request = request.clone({
          setHeaders: {
            Authorization: sessionStorage.getItem('token')
          }
        })
      }
    return next.handle(request)
    .pipe(
        retry(1),
        catchError((error: HttpErrorResponse)=>{
            let errorFlag=true;
            let errorMessage='';
            if(error.error instanceof ErrorEvent){
                errorMessage=`Error: ${error.error.message}`;
            }else{
                errorMessage=`${error.error}`;
                console.log(errorMessage);
                if(error.status==0 || errorMessage==='[object Object]'){
                    errorMessage='Could not connect to Web Service';
                    errorFlag=false;
                }
                if(error.status==401){
                    alert("Invalid Login Credentials.")
                }
            }
            if(errorFlag){
                window.alert(errorMessage);
            }
            return throwError(errorMessage);
        })
    )
}

}