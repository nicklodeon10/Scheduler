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


@NgModule({
    imports: [
        BrowserModule        
    ],
    declarations: [
        AppComponent,
        HeaderComponent,
        FooterComponent,
        HomeComponent,
        DashboardComponent,
        ErrorPageComponent,
        LoginComponent,
        RegisterComponent
		],
    providers: [ ],
    bootstrap: [AppComponent]
})

export class AppModule { }