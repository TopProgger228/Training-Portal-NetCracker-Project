import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { StartComponent } from './first-page/start/start.component';
import { SignInComponent } from './sign-in/sign-in.component';

const routes: Routes = [
  {
    path: 'firstPage',
    component: StartComponent
  },
  {
    path: '',
    redirectTo: 'firstPage',
    pathMatch: 'full'
  }
  // {
  //   path: 'sign-in',
  //   component: SignInComponent
  // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
