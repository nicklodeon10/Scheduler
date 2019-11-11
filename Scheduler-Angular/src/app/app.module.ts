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
import { FormsModule } from "@angular/forms";
import { Routes, RouterModule } from '@angular/router';
import { ViewReminderComponent } from './app.viewremindercomponent';
import { ViewNotificationComponent } from './app.viewnotificationcomponent';

const appRoutes:Routes=[
    {path:'',redirectTo: 'home', pathMatch: 'full'},
    {path:'dashboard', component: DashboardComponent},
    {path:'login', component: LoginComponent},
    {path:'register', component: RegisterComponent},
    {path:'home', component: HomeComponent},
    {path:'pastmeeting', component: PastMeetingComponent},
    {path:'addmeeting', component: AddMeetingComponent},
    {path:'viewreminders', component: ViewReminderComponent},
    {path:'viewnotifications', component: ViewNotificationComponent}
];

@NgModule({
    imports: [
        BrowserModule,
        HttpClientModule,
        NgxPaginationModule,
        FormsModule,
        RouterModule.forRoot(appRoutes)
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
        AddMeetingComponent,
        ViewReminderComponent,
        ViewNotificationComponent
		],
    providers: [ ],
    bootstrap: [AppComponent]
})

export class AppModule { }