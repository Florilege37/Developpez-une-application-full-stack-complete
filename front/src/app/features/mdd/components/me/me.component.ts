import { Component, OnDestroy, OnInit } from '@angular/core';
import { SessionService } from '../../../../services/session.service';
import { User } from '../../../../models/user.interface';
import { map } from 'rxjs/operators';
import { UserService } from '../../../../services/user.service';
import { FormBuilder, Validators } from '@angular/forms';
import { UserUpdateRequest } from 'src/app/models/userUpdateRequest.interface';
import { Observable, Subscription } from 'rxjs';
import { Topic } from '../../interfaces/topic.interface';
import { TopicService } from 'src/app/features/mdd/services/topic.service';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit, OnDestroy {

  public nickname?: String;
  public email?: String;
  public userId!: number;
  public user!: User;

  public topics$!: Observable<Topic[]>;

  private userServiceSubscription!: Subscription;
  private topicServiceSubscription!: Subscription;


  public form = this.fb.group({
    nickname: [
      '',
      [
        Validators.required,
      ]
    ],
    email: [
      '',
      [
        Validators.required
      ]
    ]
  });

  constructor(
    private userService: UserService,
    private fb: FormBuilder,
    private sessionService: SessionService,
    private topicService: TopicService) {
  }

  ngOnInit(): void {
    this.userServiceSubscription = this.userService.getMe().subscribe((user: User) => {
      this.user = user;
      this.nickname=user.nickname;
      this.email=user.email;
      this.userId=user.id;
      this.form.patchValue({
        nickname: user.nickname,
        email: user.email
      });
      this.topics$ = this.topicService.subscribedTopics(this.userId.toString());
    });
  }

  submit(): void{
    const userUpdateRequest = this.form.value as UserUpdateRequest;
    this.userServiceSubscription = this.userService.update(this.userId,userUpdateRequest).subscribe(() => {
      // Après la création réussie, rechargement de la page
      window.location.reload();
      this.userServiceSubscription.unsubscribe();
    })
  }

  isSubscribed(topicId: number): boolean {
    return this.user && this.user.topicSubscribed && this.user.topicSubscribed.includes(topicId);
  }
  
  unSubscribeToTopic(topicId: number, userId: number): void{
    this.topicServiceSubscription = this.topicService.unSubscribeToTopic(topicId,userId).subscribe();
    const index = this.user.topicSubscribed.indexOf(topicId);
    if (index !== -1) {
      this.user.topicSubscribed.splice(index, 1);
    }
    this.topicServiceSubscription.unsubscribe();
  }

  disconnect(): void{
    this.sessionService.logOut();
  }

  ngOnDestroy(): void {
    this.userServiceSubscription.unsubscribe();
  }

}
