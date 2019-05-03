import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { StartComponent } from './first-page/start/start.component';
import { RegisterComponent } from './register/register.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { UsersInfoComponent } from './users-info/users-info.component';
import { AddNewManagerComponent } from './add-new-manager/add-new-manager.component';
import {GroupsScheduleComponent} from "./groups-schedule/groups-schedule.component";
import { AddNewCourseComponent } from './add-new-course/add-new-course.component';
import {PasswordResetComponent} from "./password-reset/password-reset.component";
import {AddStudentComponent} from "./add-student/add-student.component";
import {MyScheduleComponent} from "./my-schedule/my-schedule.component";
import {ManageNewsComponent} from "./manage-news/manage-news.component";
import {CoursesInfoComponent} from "./courses-info/courses-info.component";
import {ShowStudentsComponent} from "./show-students/show-students.component";
import {StudentsInfoComponent} from "./students-info/students-info.component";


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
    component: AddStudentComponent
  },
  {
    path: 'admin/manage-news',
    component: ManageNewsComponent
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
    path: 'password-reset',
    component: PasswordResetComponent
  },
  {
    path: 'signup',
    component: RegisterComponent
  },
  {
    path: 'showallstudents',
    component: ShowStudentsComponent
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
