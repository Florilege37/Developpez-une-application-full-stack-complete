import { Component, OnInit } from '@angular/core';
import { Post } from '../../interfaces/post.interface';
import { Observable } from 'rxjs';
import { PostApiService } from 'src/app/features/mdd/services/post.service';
import { SessionService } from 'src/app/services/session.service';
import { SessionInformation } from 'src/app/models/sessionInformation.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-posts',
  templateUrl: './list-posts.component.html',
  styleUrls: ['./list-posts.component.scss']
})
export class ListPostsComponent {

  public post$: Observable<Post[]> = this.postApiService.all();

  public postUserNickname!: String;

  constructor(
    private sessionService: SessionService,
    private postApiService: PostApiService,
    private router: Router) { }

    get user(): SessionInformation | undefined {
      return this.sessionService.sessionInformation;
    }

    onPostClick(postId : number){
      this.router.navigate(['/posts/', postId]);
    }

    goToCreerArticle(){
      this.router.navigate(['/createArticle']);
    }

}