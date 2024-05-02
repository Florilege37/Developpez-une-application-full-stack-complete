import { Component, OnDestroy, OnInit } from '@angular/core';
import { Topic } from '../../interfaces/topic.interface';
import { Router } from '@angular/router';
import { SessionService } from 'src/app/services/session.service';
import { TopicService } from 'src/app/features/mdd/services/topic.service';
import { Observable, Subscription } from 'rxjs';
import { User } from '../../../../models/user.interface';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-list-topics',
  templateUrl: './list-topics.component.html',
  styleUrls: ['./list-topics.component.scss']
})
export class ListTopicsComponent implements OnInit, OnDestroy{

  public topics$: Observable<Topic[]> = this.topicService.all();
  private topicServiceSubscription!: Subscription;
  private userServiceSubscription!: Subscription;

  public user!: User;

  constructor(
    private topicService: TopicService,
    private userService: UserService) { }

  ngOnInit(): void {
    this.userServiceSubscription = this.userService.getMe().subscribe((user: User) => this.user = user);
  }

  subscribeToTopic(topicId: number, userId: number): void{
    this.topicServiceSubscription = this.topicService.subscribeToTopic(topicId,userId).subscribe();
    this.user.topicSubscribed.push(topicId);
    this.topicServiceSubscription.unsubscribe();
  }

  isSubscribed(topicId: number): boolean {
    return this.user && this.user.topicSubscribed && this.user.topicSubscribed.includes(topicId);
  } 

  ngOnDestroy(): void {
    this.userServiceSubscription.unsubscribe();
  }

}
