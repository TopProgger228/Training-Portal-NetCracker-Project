import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import { MenuComponent } from './menu/menu.component';
import { CoursesListComponent } from './courses-list/courses-list.component';
import { CoursesContainerComponent } from './courses-list/courses-container/courses-container.component';
import { FirstSlideComponent } from './first-slide/first-slide.component';
import { TrainersListComponent } from './trainers-list/trainers-list.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [

    MenuComponent,
    CoursesListComponent,
    CoursesContainerComponent,
    FirstSlideComponent,
    TrainersListComponent,
    FooterComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    MenuComponent,
    CoursesListComponent,
    CoursesContainerComponent,
    FirstSlideComponent,
    TrainersListComponent,
    FooterComponent
  ]
})

export class FirstPageModule { }

