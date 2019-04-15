import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { FirstPageModule } from './first-page/first-page.module';
import { StartComponent } from './first-page/start/start.component'


@NgModule({
  declarations: [
    AppComponent,
    StartComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FirstPageModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
