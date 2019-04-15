import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { StartComponent } from './first-page/start/start.component';

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
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
