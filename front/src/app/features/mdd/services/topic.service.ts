import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../interfaces/post.interface';
import { Topic } from '../interfaces/topic.interface';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private pathService : string = 'api/topics';

  constructor(private httpClient: HttpClient) {
  }

  public getById(id: string): Observable<Topic> {
    return this.httpClient.get<Topic>(`${this.pathService}/${id}`);
  }

  public all(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(`${this.pathService}/get`);
  }

  public subscribeToTopic(id: number, userId: number): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/${userId}/subs/${id}`, null);
  }

  public unSubscribeToTopic(id: number, userId: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.pathService}/${userId}/subs/${id}`);
  }

  public getByTheme(theme: string): Observable<Topic> {
    return this.httpClient.get<Topic>(`${this.pathService}/getTheme/${theme}`);
  }

  public subscribedTopics(userId: string): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(`${this.pathService}/all/${userId}`);
  }

}
