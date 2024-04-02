import { Component, OnInit } from '@angular/core';
import { SessionService } from '../../services/session.service';
import { User } from '../interfaces/user.interface';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  public user: User | undefined;

  constructor(
    private sessionService: SessionService,
    private userService: UserService) {
}

  ngOnInit(): void {
    this.userService.getMe().subscribe((user: User) => this.user = user);
  }

}
