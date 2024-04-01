import { Component, OnInit } from '@angular/core';
import { Post } from '../interfaces/post.interface';
import { Observable } from 'rxjs';
import { PostApiService } from 'src/app/services/post.service';
import { SessionService } from 'src/app/services/session.service';
import { SessionInformation } from 'src/app/models/sessionInformation.interface';

@Component({
  selector: 'app-list-posts',
  templateUrl: './list-posts.component.html',
  styleUrls: ['./list-posts.component.scss']
})
export class ListPostsComponent {

  public post$: Observable<Post[]> = this.postApiService.all();

  constructor(
    private sessionService: SessionService,
    private postApiService: PostApiService) { }

    get user(): SessionInformation | undefined {
      return this.sessionService.sessionInformation;
    }

}
