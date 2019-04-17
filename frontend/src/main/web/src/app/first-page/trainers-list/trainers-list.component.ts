import { Component, OnInit } from '@angular/core';
import { TrainersserService } from './trainersser.service';


@Component({
  selector: 'app-trainers-list',
  templateUrl: './trainers-list.component.html',
  styleUrls: ['./trainers-list.component.css']
})
export class TrainersListComponent implements OnInit {

  trainers = {};
  
  constructor( private MyFirstService : TrainersserService) { }

  ngOnInit() {

    this.trainers = this.MyFirstService.getDate() 
  }

}
