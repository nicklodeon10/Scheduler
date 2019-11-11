import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import { HeaderComponent } from './app.headercomponent';
import { FooterComponent } from './app.footercomponent';
import { HomeComponent } from './app.homecomponent';
import { DashboardComponent } from './app.dashboardcomponent';
import {ErrorPageComponent} from './app.errorpagecomponent';
import { LoginComponent } from './app.logincomponent';
import { RegisterComponent } from './app.registercomponent';
import { HttpClientModule } from '@angular/common/http';
import { NgxPaginationModule } from 'ngx-pagination';
import { PastMeetingComponent } from './app.pastmeetingcomponent';
import { AddMeetingComponent } from './app.addmeetingcomponent';


@NgModule({
    imports: [
        BrowserModule,
        HttpClientModule,
        NgxPaginationModule      
    ],
    declarations: [
        AppComponent,
        HeaderComponent,
        FooterComponent,
        HomeComponent,
        DashboardComponent,
        ErrorPageComponent,
        LoginComponent,
        RegisterComponent,
        PastMeetingComponent,
        AddMeetingComponent
		],
    providers: [ ],
    bootstrap: [AppComponent]
})

export class AppModule { }