import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { FirstPageModule } from './first-page/first-page.module';
import { StartComponent } from './first-page/start/start.component'

import { SignInComponent } from './sign-in/sign-in.component';

import { httpInterceptorProviders } from './auth/auth-interceptor';
import { RegisterComponent } from './register/register.component';
import { CoursesInfoComponent } from './courses-info/courses-info.component';

import { UsersInfoComponent } from './users-info/users-info.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap' ; 
import { AddNewManagerComponent } from './add-new-manager/add-new-manager.component';
import { GroupsScheduleComponent } from './groups-schedule/groups-schedule.component';
import { AddNewCourseComponent } from "./add-new-course/add-new-course.component";
import {ShowStudentsComponent} from "./show-students/show-students.component";
import { AddStudentMailSenderComponent } from './mail-sender/add-student';
import {MyScheduleComponent} from "./my-schedule/my-schedule.component";
import { ManageNewsComponent } from './manage-news/manage-news.component';
import { StudentsInfoComponent } from './students-info/students-info.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import {PasswordResetMailSenderComponent} from "./mail-sender/resetPasswordMail";

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
    MyScheduleComponent,
    ManageNewsComponent,
    //CoursesListComponent
    AddStudentMailSenderComponent,
    CoursesInfoComponent,
    StudentsInfoComponent,
    ShowStudentsComponent,
    StudentsInfoComponent,
    ResetPasswordComponent,
    PasswordResetMailSenderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FirstPageModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
