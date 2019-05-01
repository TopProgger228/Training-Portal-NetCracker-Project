import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { News } from './news'

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  private listNewsUrl = 'http://localhost:8080/api/news/listNews';
  private listActiveNewsUrl = 'http://localhost:8080/api/news/listActiveNews';
  private updateNewsUrl = 'http://localhost:8080/api/news/updateNews';
  private createNewsUrl = 'http://localhost:8080/api/news/createNews';

  constructor(private http: HttpClient) { }

  getNews(): Observable<any> {
    return this.http.get<News>(this.listNewsUrl, httpOptions);
  }

  getActiveNews(): Observable<any> {
    return this.http.get<News>(this.listActiveNewsUrl, httpOptions);
  }

  updateNews(id: string, title: string, createDate: string, context: string, isActive: string): Observable<any> {
    const updatereq = new HttpRequest('POST', this.updateNewsUrl , null, {
      reportProgress: true,
      responseType: 'text',
      params: new HttpParams().set('id', id)
      .set('title', title)
      .set('createDate', createDate)
      .set('context', context)
      .set('isActive', isActive),
    });
    return this.http.request(updatereq);
  }

  createNews( title: string, context: string, isActive: string): Observable<any> {
    const createreq = new HttpRequest('POST', this.createNewsUrl , null, {
      reportProgress: true,
      responseType: 'text',
      params: new HttpParams()
      .set('title', title)
      .set('context', context)
      .set('isActive', isActive),
    });
    return this.http.request(createreq);
  }

}
