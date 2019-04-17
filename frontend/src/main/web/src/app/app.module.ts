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
import { AdminComponent } from './admin/admin.component';
import { TrainerComponent } from './trainer/trainer.component';
import { MngComponent } from './mng/mng.component';
import { UserComponent } from './user/user.component';
import { RegisterComponent } from './register/register.component';


@NgModule({
  declarations: [
    AppComponent,
    StartComponent,
    SignInComponent,
    AdminComponent,
    TrainerComponent,
    MngComponent,
    UserComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FirstPageModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
