import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { FirstPageModule } from './first-page/first-page.module';
import { StartComponent } from './first-page/start/start.component'

import { SignInComponent } from './sign-in/sign-in.component';

import { httpInterceptorProviders } from './auth/auth-interceptor';
import { RegisterComponent } from './register/register.component';

import { UsersInfoComponent } from './users-info/users-info.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap' ; 
import { AddNewManagerComponent } from './add-new-manager/add-new-manager.component';
import { GroupsScheduleComponent } from './groups-schedule/groups-schedule.component';
import { AddNewCourseComponent } from "./add-new-course/add-new-course.component";

@NgModule({
  declarations: [
    AppComponent,
    StartComponent,
    SignInComponent,
    RegisterComponent,
    AddNewCourseComponent,
    UsersInfoComponent,
    AddNewManagerComponent,
    GroupsScheduleComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FirstPageModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
