import {Component, OnInit} from '@angular/core';
import {TrainersserService} from './trainersser.service';
import {TrainersInfo} from "../../interface/trainers-info";
import {DomSanitizer} from "@angular/platform-browser";


@Component({
  selector: 'app-trainers-list',
  templateUrl: './trainers-list.component.html',
  styleUrls: ['./trainers-list.component.css']
})
export class TrainersListComponent implements OnInit {

  trainers: TrainersInfo[];

  constructor(private service: TrainersserService, private sanitizer : DomSanitizer) {
  }

  ngOnInit() {

    this.service.getInfo().subscribe(
      data => {
        this.trainers = data;
        console.log(data);

        for (let i = 0; i < this.trainers.length; i++) {
            if (this.trainers[i].photo != null){
                this.trainers[i].photo = this.sanitizer
                  .bypassSecurityTrustUrl('data:image/jpeg;base64,' + this.trainers[i].photo);
            }
        }
      }
    );
  }

}
