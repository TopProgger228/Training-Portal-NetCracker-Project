import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';

import { MenuComponent } from './menu/menu.component';
import { CoursesListComponent } from './courses-list/courses-list.component';
import { CoursesContainerComponent } from './courses-list/courses-container/courses-container.component';
import { FirstSlideComponent } from './first-slide/first-slide.component';
import { TrainersListComponent } from './trainers-list/trainers-list.component';
import { FooterComponent } from './footer/footer.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


import { TrainersserService } from './trainers-list/trainersser.service';
import { CoursePageComponent } from './courses-list/course-page/course-page.component';


const routes: Routes =[

  ];

@NgModule({
  declarations: [

    MenuComponent,
    CoursesListComponent,
    CoursesContainerComponent,
    FirstSlideComponent,
    TrainersListComponent,
    FooterComponent,
    //CoursePageComponent
  ],
  imports: [
    CommonModule,
    NgbModule.forRoot(),
    [RouterModule.forRoot(routes)],
  ],
  exports: [
    MenuComponent,
    CoursesListComponent,
    CoursesContainerComponent,
    FirstSlideComponent,
    TrainersListComponent,
    FooterComponent,
    [RouterModule]
  ],
  providers: [TrainersserService]
})

export class FirstPageModule { }
