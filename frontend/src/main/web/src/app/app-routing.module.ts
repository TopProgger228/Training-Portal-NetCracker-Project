import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { StartComponent } from './first-page/start/start.component';
import { RegisterComponent } from './register/register.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { UsersInfoComponent } from './users-info/users-info.component';
import { AddNewManagerComponent } from './add-new-manager/add-new-manager.component';
import {GroupsScheduleComponent} from "./groups-schedule/groups-schedule.component";
import { AddNewCourseComponent } from './add-new-course/add-new-course.component';
import {AddStudentMailSenderComponent} from "./mail-sender/add-student";
import {MyScheduleComponent} from "./my-schedule/my-schedule.component";
import {ManageLandingPageComponent} from "./manage-landing-page/manage-landing-page.component";
import {CoursesInfoComponent} from "./courses-info/courses-info.component";
import {ShowStudentsComponent} from "./show-students/show-students.component";
import {StudentsInfoComponent} from "./students-info/students-info.component";
import {ResetPasswordComponent} from "./reset-password/reset-password.component";
import {PasswordResetMailSenderComponent} from "./mail-sender/resetPasswordMail";
import {ManagerProfileComponent} from "./manager-profile/manager-profile.component";
import {TrainerLessonAttendanceComponent} from "./trainer-lesson-attendance/trainer-lesson-attendance.component";
import {TrainerAttendanceComponent} from "./trainer-attendance/trainer-attendance.component";
import {AdminAttendanceComponent} from "./admin-attendance/admin-attendance.component";
import {StudentProfileComponent} from "./student-profile/student-profile.component";
import {UserProfileComponent} from "./user-profile/user-profile.component";
import {ManagerAttendanceComponent} from "./manager-attendance/manager-attendance.component";
import {CoursePageComponent} from "./first-page/courses-list/course-page/course-page.component";
import {CourseAttendeesComponent} from "./course-attendees/course-attendees.component";
import {ScheduleInfoComponent} from "./schedule-info/schedule-info.component";



const routes: Routes = [
  {
    path: 'firstPage',
    component: StartComponent
  },
  {
    path: 'admin/users',
    component: UsersInfoComponent
  },
  {
    path: 'trainer/courses-info',
    component: CoursesInfoComponent
  },
  {
    path: 'manager/students-info',
    component: StudentsInfoComponent
  },
  {
    path: 'manager/profile/:username',
    component: ManagerProfileComponent
  },
  {
    path: 'student/my-schedule/:username',
    component: MyScheduleComponent
  },
  {
    path: 'trainer/check-course-students',
    component: CourseAttendeesComponent
  },
  {
    path: 'admin/groups-schedule',
    component: GroupsScheduleComponent
  },
  {
    path: 'admin/create_new_course',
    component: AddNewCourseComponent
  },
  {
    path: 'admin/add-user',
    component: AddNewManagerComponent
  },
  {
    path: 'admin/add-student',
    component: AddStudentMailSenderComponent
  },
  {
    path: 'admin/manage-news',
    component: ManageLandingPageComponent
  },
  {
    path: 'student/my-schedule',
    component: MyScheduleComponent
  },
  {
    path: 'auth/login',
    component: SignInComponent
  },
  {
    path: 'auth/reset-password',
    component: PasswordResetMailSenderComponent
  },
  {
    path: 'signup',
    component: RegisterComponent
  },
  {
    path: 'password_reset',
    component: ResetPasswordComponent
  },
  {
    path: 'trainer/students-info',
    component: ShowStudentsComponent
  },
  {
    path: 'trainer/check-attendance',
    component: TrainerAttendanceComponent
  },
  {
    path: 'trainer/check-lesson-attendance',
    component: TrainerLessonAttendanceComponent
  },
  {
    path: 'admin/all-attendance',
    component: AdminAttendanceComponent
  },
  {
    path: 'manager/student-profile/:username',
    component: StudentProfileComponent
  },
  {
    path: 'myprofile',
    component: UserProfileComponent
  },
  {
    path: 'manager/student-att',
    component: ManagerAttendanceComponent
  },
  {
    path: 'student/course-page/:name',
    component: CoursePageComponent
  },
  {
    path: 'admin/schedule-info',
    component: ScheduleInfoComponent
  },
  {
    path: '**',
    component: StartComponent
  },
  {
    path: '',
    redirectTo: 'auth/login',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
