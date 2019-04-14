import { Component, OnInit } from '@angular/core';
import {TRAINERS} from './trainers';
import {Trainer} from './trainer';


@Component({
  selector: 'app-trainers-list',
  templateUrl: './trainers-list.component.html',
  styleUrls: ['./trainers-list.component.css']
})
export class TrainersListComponent implements OnInit {

  trainers: Array<Trainer> = TRAINERS;
  
  constructor() { }

  ngOnInit() {
  }

}
