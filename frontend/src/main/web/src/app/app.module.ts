import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './first-page/menu/menu.component';
import { CoursesListComponent } from './first-page/courses-list/courses-list.component';
import { CoursesContainerComponent } from './first-page/courses-list/courses-container/courses-container.component';
import { FirstSlideComponent } from './first-page/first-slide/first-slide.component';
import { TrainersListComponent } from './first-page/trainers-list/trainers-list.component';
import { FooterComponent } from './first-page/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    CoursesListComponent,
    CoursesContainerComponent,
    FirstSlideComponent,
    TrainersListComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
