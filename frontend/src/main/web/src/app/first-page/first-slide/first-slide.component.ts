import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-first-slide',
  templateUrl: './first-slide.component.html',
  styleUrls: ['./first-slide.component.css']
})

export class FirstSlideComponent implements OnInit {
@Input()
role: string;
  constructor() { }

  ngOnInit() {
  }

}
