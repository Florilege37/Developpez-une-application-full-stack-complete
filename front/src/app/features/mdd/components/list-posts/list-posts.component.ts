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
    private postApiService: PostApiService,
    private router: Router) {
     }

    onPostClick(postId : number): void{
      this.router.navigate(['/mdd/posts/', postId]);
    }

    goToCreerArticle(): void{
      this.router.navigate(['/mdd/createArticle']);
    }

}
