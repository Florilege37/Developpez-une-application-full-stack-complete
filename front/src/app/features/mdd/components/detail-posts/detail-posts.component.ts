import { Component, OnInit } from '@angular/core';
import { Post } from '../../interfaces/post.interface';
import { SessionService } from 'src/app/services/session.service';
import { PostApiService } from 'src/app/features/mdd/services/post.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from '../../../../models/user.interface';
import { TopicService } from 'src/app/features/mdd/services/topic.service';
import { Topic } from '../../interfaces/topic.interface';
import { Message } from '../../interfaces/message.interface';
import { FormBuilder, Validators } from '@angular/forms';
import { MessageService } from '../../services/message.service';

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

  public form = this.fb.group({
    message: [
      '',
      [
        Validators.required,
      ]
    ]
  });

  constructor(
    private route: ActivatedRoute,
    private sessionService: SessionService,
    private postApiService: PostApiService,
    private userService: UserService,
    private fb: FormBuilder,
    private topicService: TopicService,
    private router: Router,
    private messageService: MessageService) {
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

  createMessage(): void{
    const message = this.form.value as Message;
    if (this.sessionService.sessionInformation?.id !== undefined) {
      message.user_id = this.sessionService.sessionInformation.id;
    } else {
      message.user_id = 0;
    }
    message.postId = parseInt(this.postId);
    this.messageService.createMessage(message).subscribe(() => {
      // Après la création réussie, rechargement de la page
      window.location.reload();
    })
  }

  retour(): void{
    this.router.navigate(['/mdd']);
  }

}
