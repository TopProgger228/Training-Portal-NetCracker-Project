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
import {UserProfileComponent} from "./user-profile/user-profile.component";
import {CoursePageComponent} from "./first-page/courses-list/course-page/course-page.component";
import { CourseAttendeesComponent } from './course-attendees/course-attendees.component';
import { ManagerAttendanceComponent } from './manager-attendance/manager-attendance.component';
import { OneStudetMngAttComponent } from './one-studet-mng-att/one-studet-mng-att.component';
import { UserAttCourseListComponent } from './user-att-course-list/user-att-course-list.component';
import { LessonAttComponent } from './lesson-att/lesson-att.component';
import { OneCheckAttItemComponent } from './one-check-att-item/one-check-att-item.component';
import { ScheduleInfoComponent } from './schedule-info/schedule-info.component';
import {ToasterService} from "./services/toaster.service";
import { SpinnerComponent } from './spinner/spinner.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatButtonModule, MatInputModule, MatListModule, MatSelectModule} from "@angular/material";
import {MatExpansionModule} from '@angular/material/expansion'
import {UserCoursesComponent} from "./user-profile/user-courses/user-courses.component";
import {NgxMaterialTimepickerModule} from "ngx-material-timepicker";
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import { OneLessonAdminAttComponent } from './admin-attendance/one-lesson-admin-att/one-lesson-admin-att.component';
import { AllCoursesAdminAttComponent } from './admin-attendance/all-courses-admin-att/all-courses-admin-att.component';
import { ByTrainerCoursesAdminAttComponent } from './admin-attendance/by-trainer-courses-admin-att/by-trainer-courses-admin-att.component';
import { ByStudentCoursesAdminAttComponent } from './admin-attendance/by-student-courses-admin-att/by-student-courses-admin-att.component';
import { ByLevelCoursesAdminAttComponent } from './admin-attendance/by-level-courses-admin-att/by-level-courses-admin-att.component';
import { CoursesAdminAttComponent } from './admin-attendance/courses-admin-att/courses-admin-att.component';
import { OneStudentAdminAttComponent } from './admin-attendance/one-student-admin-att/one-student-admin-att.component';

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
    UserProfileComponent,
    StudentProfileComponent,
    ManagerAttendanceComponent,
    OneStudetMngAttComponent,
    UserAttCourseListComponent,
    LessonAttComponent,
    StudentProfileComponent,
    CoursePageComponent,
    CourseAttendeesComponent,
    OneCheckAttItemComponent,
    ScheduleInfoComponent,
    SpinnerComponent,
    UserCoursesComponent,
    MyScheduleComponent,
    UserCoursesComponent,
    OneLessonAdminAttComponent,
    AllCoursesAdminAttComponent,
    ByTrainerCoursesAdminAttComponent,
    ByStudentCoursesAdminAttComponent,
    ByLevelCoursesAdminAttComponent,
    CoursesAdminAttComponent,
    OneStudentAdminAttComponent

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FirstPageModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    BrowserModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatListModule,
    MatButtonModule,
    MatExpansionModule,
    MatInputModule,
    NgxMaterialTimepickerModule,
    MatTooltipModule,
    MatTableModule,
    MatPaginatorModule
  ],
  providers: [httpInterceptorProviders, ToasterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
