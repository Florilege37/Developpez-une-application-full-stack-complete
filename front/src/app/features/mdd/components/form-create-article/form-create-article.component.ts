import { Component, OnInit } from '@angular/core';
import { Topic } from '../../interfaces/topic.interface';
import { Observable } from 'rxjs';
import { TopicService } from 'src/app/features/mdd/services/topic.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Post } from '../../interfaces/post.interface';
import { UserService } from 'src/app/services/user.service';
import { User } from '../../../../models/user.interface';
import { PostApiService } from 'src/app/features/mdd/services/post.service';

@Component({
  selector: 'app-form-create-article',
  templateUrl: './form-create-article.component.html',
  styleUrls: ['./form-create-article.component.scss']
})
export class FormCreateArticleComponent implements OnInit {

  public topics$: Observable<Topic[]> = this.topicService.all();

  public userId!: number;

  constructor(
    private topicService: TopicService,
    private fb: FormBuilder,
    private userService: UserService,
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
      this.userService.getMe().subscribe((user: User) => {
        this.userId=user.id;
      });
    }

  submit(){
    const articleRequest = this.form.value as unknown as Post;
    articleRequest.user_id = this.userId;
    console.log(articleRequest);
    this.postApiService.create(articleRequest);
    console.log("test");
  }

}
