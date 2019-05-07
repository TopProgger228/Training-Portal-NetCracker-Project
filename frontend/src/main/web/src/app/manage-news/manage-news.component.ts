import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { TokenStorageService } from '../auth/token-storage.service';

import { NewsService } from '../services/News.service';
import { News } from '../services/news';

@Component({
  selector: 'app-manage-news',
  templateUrl: './manage-news.component.html',
  styleUrls: ['./manage-news.component.css']
})
export class ManageNewsComponent implements OnInit {

  news: News[];
  currentNews: News;

  loggedOut = false;
  EditRow: number = -1;
  addNewNews: boolean = false;
  newNews: News = { 'id': 0, 'title': '', 'createDate': '', 'context': '', 'isActive': true };


  constructor(private router: Router, private newsService: NewsService, private token: TokenStorageService) { }

  ngOnInit() {
    if (this.token.getToken()) {
      this.token.getAuthorities().every(role => {
        if (role === 'Admin') {
          this.newsService.getNews().subscribe(
            data => {
              this.news = data;
              console.log(this.news);
            }
          )
        } else {
          this.router.navigate(['firstPage']);
        }
        return false;
      });
    } else {
      this.loggedOut = true;
      this.router.navigate(['auth/login']);
    };
  }

  setEditRow(rowid: number) {
    this.EditRow = rowid;
  }

  updateRow(news: News) {
    console.log(news);
    this.currentNews = news;
    this.newsService.updateNews(this.currentNews.id.toString(), this.currentNews.title, this.currentNews.createDate, this.currentNews.context, this.currentNews.isActive.toString()).subscribe(
      data => {
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );

    this.EditRow = -1;
  }

  createNewNews() {
    console.log(this.newNews);
    this.newsService.createNews(this.newNews.title, this.newNews.context, this.newNews.isActive.toString()).subscribe(
      data => {
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
    this.addNewNews = false;

  }
  setNewNews() {
    this.addNewNews = true;

    console.log(this.addNewNews);
  }

  logout() {
    this.loggedOut = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }


}
