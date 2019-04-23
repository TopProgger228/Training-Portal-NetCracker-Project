import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { StartComponent } from './first-page/start/start.component';
import { RegisterComponent } from './register/register.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { UsersInfoComponent } from './users-info/users-info.component';
import { AddNewManagerComponent } from './add-new-manager/add-new-manager.component';
import {GroupsScheduleComponent} from "./groups-schedule/groups-schedule.component";
import {CoursesListComponent} from "./first-page/courses-list/courses-list.component";


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
    path: 'admin/groups-schedule',
    component: GroupsScheduleComponent
  },
  {
    path: 'admin/create_new_course',
    component: CoursesListComponent
  },
  {
    path: 'admin/add-user',
    component: AddNewManagerComponent
  },
  {
    path: 'auth/login',
    component: SignInComponent
  },

  {
    path: 'signup',
    component: RegisterComponent
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
