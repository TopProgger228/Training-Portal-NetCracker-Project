import { Component, OnInit, Input } from '@angular/core';
import { TokenStorageService } from '../../auth/token-storage.service';

import { NewsService } from '../../services/News.service';
import { News } from '../../services/news';
@Component({
  selector: 'app-first-slide',
  templateUrl: './first-slide.component.html',
  styleUrls: ['./first-slide.component.css']
})

export class FirstSlideComponent implements OnInit {
  news: News[];
  constructor(private newsService: NewsService, private token: TokenStorageService) { }

  ngOnInit() {
    this.newsService.getActiveNews().subscribe(
      data => {
        this.news = data;
        console.log(this.news);
      }
    )
  }

}
