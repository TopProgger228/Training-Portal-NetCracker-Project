import {Component, OnInit} from '@angular/core';
import {TrainersserService} from './trainersser.service';
import {TrainersInfo} from "../../services/trainers-info";


@Component({
  selector: 'app-trainers-list',
  templateUrl: './trainers-list.component.html',
  styleUrls: ['./trainers-list.component.css']
})
export class TrainersListComponent implements OnInit {

  trainers: TrainersInfo[];

  constructor(private service: TrainersserService) {
  }

  ngOnInit() {

    this.service.getInfo().subscribe(
      data => {
        this.trainers = data;
        console.log(data)
      }
    );
  }

}
