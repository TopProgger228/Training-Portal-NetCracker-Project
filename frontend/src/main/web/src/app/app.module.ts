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
import { CoursesInfoComponent } from './courses-info/courses-info.component';

import { UsersInfoComponent } from './users-info/users-info.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap' ;
import { AddNewManagerComponent } from './add-new-manager/add-new-manager.component';
import { GroupsScheduleComponent } from './groups-schedule/groups-schedule.component';
import { AddNewCourseComponent } from "./add-new-course/add-new-course.component";
import {ShowStudentsComponent} from "./show-students/show-students.component";
import { AddStudentMailSenderComponent } from './mail-sender/add-student';
import {MyScheduleComponent} from "./my-schedule/my-schedule.component";
import {ManageLandingPageComponent} from "./manage-landing-page/manage-landing-page.component";
import { StudentsInfoComponent } from './students-info/students-info.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import {PasswordResetMailSenderComponent} from "./mail-sender/resetPasswordMail";
import { ManagerProfileComponent } from './manager-profile/manager-profile.component';
import {TrainerLessonAttendanceComponent} from "./trainer-lesson-attendance/trainer-lesson-attendance.component";
import {TrainerAttendanceComponent} from "./trainer-attendance/trainer-attendance.component";
import {AdminAttendanceComponent} from "./admin-attendance/admin-attendance.component";
import { ReactiveFormsModule } from '@angular/forms';
import {StudentProfileComponent} from "./student-profile/student-profile.component";
import {CoursePageComponent} from "./first-page/courses-list/course-page/course-page.component";
import { CourseAttendeesComponent } from './course-attendees/course-attendees.component';

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
    ManageLandingPageComponent,
    //CoursesListComponent
    AddStudentMailSenderComponent,
    CoursesInfoComponent,
    StudentsInfoComponent,
    ShowStudentsComponent,
    ManagerProfileComponent,
    StudentsInfoComponent,
    ResetPasswordComponent,
    PasswordResetMailSenderComponent,
    ManagerProfileComponent,
    TrainerLessonAttendanceComponent,
    TrainerAttendanceComponent,
    AdminAttendanceComponent,
    ManagerProfileComponent,
    StudentProfileComponent,
    CoursePageComponent,
    CourseAttendeesComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FirstPageModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    BrowserModule,
    ReactiveFormsModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
