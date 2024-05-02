import { Component, OnDestroy, OnInit } from '@angular/core';
import { Topic } from '../../interfaces/topic.interface';
import { Observable, Subscription } from 'rxjs';
import { TopicService } from 'src/app/features/mdd/services/topic.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Post } from '../../interfaces/post.interface';
import { UserService } from 'src/app/services/user.service';
import { User } from '../../../../models/user.interface';
import { PostApiService } from 'src/app/features/mdd/services/post.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-create-article',
  templateUrl: './form-create-article.component.html',
  styleUrls: ['./form-create-article.component.scss']
})
export class FormCreateArticleComponent implements OnInit, OnDestroy {

  public topics$: Observable<Topic[]> = this.topicService.all();
  private postApiServiceSubscription!: Subscription;
  private userServiceSubscription!: Subscription;

  public userId!: number;

  constructor(
    private topicService: TopicService,
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router,
    private postApiService: PostApiService) { }
    
    public form = this.fb.group({
      topicId: [
        '',
        [
          Validators.required,
        ]
      ],
      title: [
        '',
        [
          Validators.required
        ]
      ],
      description: [
        '',
        [
          Validators.required,
        ]
      ]
    });

    ngOnInit(): void {
      this.userServiceSubscription = this.userService.getMe().subscribe((user: User) => {
        this.userId=user.id;
      });
    }

  submit(): void{
    const articleRequest = this.form.value as unknown as Post;
    articleRequest.user_id = this.userId;
    this.postApiServiceSubscription = this.postApiService.create(articleRequest).subscribe();
    this.router.navigate(['/mdd']);
    this.postApiServiceSubscription.unsubscribe();
  }

  retour(): void{
    this.router.navigate(['/mdd']);
  }

  ngOnDestroy(): void {
    this.userServiceSubscription.unsubscribe();
  }

}
