import { Component, OnInit } from '@angular/core';
import { SessionService } from '../../services/session.service';
import { User } from '../interfaces/user.interface';
import { UserService } from '../../services/user.service';
import { FormBuilder, Validators } from '@angular/forms';
import { UserUpdateRequest } from 'src/app/models/userUpdateRequest.interface';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  public nickname?: String;
  public email?: String;
  public userId!: number;


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
    private fb: FormBuilder,) {
}

  ngOnInit(): void {
    this.userService.getMe().subscribe((user: User) => {
      this.nickname=user.nickname;
      this.email=user.email;
      this.userId=user.id;
      this.form.patchValue({
        nickname: user.nickname,
        email: user.email
      });
    });
  }

  submit(){
    const userUpdateRequest = this.form.value as UserUpdateRequest;
    this.userService.update(this.userId,userUpdateRequest).subscribe();
  }

}
