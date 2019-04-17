import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { StartComponent } from './first-page/start/start.component';
import { RegisterComponent } from './register/register.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { UserComponent } from './user/user.component';
import { AdminComponent } from './admin/admin.component';
import { MngComponent } from './mng/mng.component';
import { TrainerComponent } from './trainer/trainer.component';

const routes: Routes = [
  {
    path: 'firstPage',
    component: StartComponent
  },
  {
    path: 'user',
    component: UserComponent
  },
  {
    path: 'mng',
    component: MngComponent
  },
  {
    path: 'admin',
    component: AdminComponent
  },
  {
    path: 'trainer',
    component: TrainerComponent
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
    redirectTo: 'firstPage',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
