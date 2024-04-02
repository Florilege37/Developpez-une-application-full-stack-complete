import { Component, OnInit } from '@angular/core';
import { Post } from '../interfaces/post.interface';
import { SessionService } from 'src/app/services/session.service';
import { PostApiService } from 'src/app/services/post.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from '../interfaces/user.interface';
import { TopicService } from 'src/app/services/topic.service';
import { Topic } from '../interfaces/topic.interface';
import { Message } from '../interfaces/message.interface';

@Component({
  selector: 'app-detail-posts',
  templateUrl: './detail-posts.component.html',
  styleUrls: ['./detail-posts.component.scss']
})
export class DetailPostsComponent implements OnInit {

  public post: Post | undefined;
  public postId: string;

  public messages?: Message[];

  public userName?: string;
  public topicTheme?: string;

  constructor(
    private route: ActivatedRoute,
    private sessionService: SessionService,
    private postApiService: PostApiService,
    private userService: UserService,
    private topicService: TopicService) {
      this.postId = this.route.snapshot.paramMap.get('id')!;
     }

  ngOnInit(): void {
    this.fetchPost();
  }

  private fetchPost(): void {
    this.postApiService
      .detail(this.postId)
      .subscribe((postResult: Post) => {
        this.post = postResult;
        this.messages = postResult.message;
        this.userService.getById(this.post?.user_id.toString()).subscribe((user: User) => {
          this.userName = user.nickname;
        })
        this.topicService.getById(this.post?.topicId.toString()).subscribe((topic: Topic) => {
          this.topicTheme = topic.theme;
        })

      });
    
  }

}
