import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../interfaces/post.interface';

@Injectable({
  providedIn: 'root'
})
export class PostApiService {

  private pathService : string = 'api/posts';

  constructor(private httpClient: HttpClient) {
  }

  public all(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(`${this.pathService}/me/posts`);
  }

  public detail(id: string): Observable<Post> {
    return this.httpClient.get<Post>(`${this.pathService}/${id}`);
  }

  public create(post: Post): Observable<Post> {
    return this.httpClient.post<Post>(`${this.pathService}/create`, post);
  }

}
