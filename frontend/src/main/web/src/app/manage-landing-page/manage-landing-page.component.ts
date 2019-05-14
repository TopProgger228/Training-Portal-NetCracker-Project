import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { TokenStorageService } from '../auth/token-storage.service';

import { NewsService } from '../services/News.service';
import { News } from '../services/news';
import { TrainersInfo } from '../services/trainers-info';
import { TrainersserService } from '../first-page/trainers-list/trainersser.service';

@Component({
  selector: 'app-manage-landing-page',
  templateUrl: './manage-landing-page.component.html',
  styleUrls: ['./manage-landing-page.component.css']
})
export class ManageLandingPageComponent implements OnInit {

  news: News[];
  currentNews: News;

  trainers: TrainersInfo[];
  updateMessage: string = null;
  loggedOut = false;
  EditNewsRow: number = -1;
  EditTrainerRow: number = -1;
  addNewNews: boolean = false;
  newNews: News = { 'id': 0, 'title': '', 'createDate': '', 'context': '', 'isActive': true };


  constructor(private router: Router, private newsService: NewsService,
    private token: TokenStorageService,
    private trainerService: TrainersserService) { }

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

          this.trainerService.getInfo().subscribe(
            data => {
              this.trainers = data;
              console.log(this.trainers);
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

  setEditNewsRow(rowid: number) {
    this.EditNewsRow = rowid;
  }

  setEditTrainerRow(rowid: number) {
    this.EditTrainerRow = rowid;
  }

  updateNewsRow(news: News) {
    console.log(news);
    this.currentNews = news;
    this.newsService.updateNews(this.currentNews.id.toString(), this.currentNews.title, this.currentNews.createDate, this.currentNews.context, this.currentNews.isActive.toString()).subscribe(
      data => {
        this.setupdateMessage(data);
      },
      error => {
        this.setErrorMessage();
      }
    );
    this.EditNewsRow = -1;
  }

  setupdateMessage(data: any){
    if (data.body) {
      if (data.statusText === "OK")
        this.updateMessage = "Updated successfully";
      else this.updateMessage = "Something going wrong, try later";
    } 
    else this.updateMessage = "Connection lost, try later";
  }

  setErrorMessage(){
    this.updateMessage = "Error, try later";
  }

  updateTrainerRow(trainer: TrainersInfo) {
    console.log(trainer);
    this.trainerService.updateTrainerInfo(trainer).subscribe(
      data => {
        this.setupdateMessage(data);
      },
      error => {
        this.setErrorMessage();
      }
    );
    this.EditTrainerRow = -1;
  }

  createNewTrainer() {
    this.router.navigate(['admin/add-user']);
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
