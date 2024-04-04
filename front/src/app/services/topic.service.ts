import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../pages/interfaces/post.interface';
import { Topic } from '../pages/interfaces/topic.interface';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private pathService = 'api/topics';

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

  /*public delete(id: string): Observable<any> {
    return this.httpClient.delete(`${this.pathService}/${id}`);
  }

  public create(post: Post): Observable<Post> {
    return this.httpClient.post<Post>(this.pathService, post);
  }

  public update(id: string, post: Post): Observable<Post> {
    return this.httpClient.put<Post>(`${this.pathService}/${id}`, post);
  }

  */

}
