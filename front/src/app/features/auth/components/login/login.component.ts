import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/features/auth/interfaces/loginRequest.interface';
import { SessionInformation } from 'src/app/models/sessionInformation.interface';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { SessionService } from 'src/app/services/session.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent{
 
  public onError = false;

  private authServiceSubscription!: Subscription;

  public form = this.fb.group({
    emailOrNickname: [
      '',
      [
        Validators.required,
      ]
    ],
    password: [
      '',
      [
        Validators.required
      ]
    ]
  });

  constructor(private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService) {
  } 
  
  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.authServiceSubscription = this.authService.login(loginRequest).subscribe({
      next: (response: SessionInformation) => {
        this.authService.token = response.token;
        this.sessionService.logIn(response);
        this.router.navigate(['/mdd']);
        this.authServiceSubscription.unsubscribe()
      },
      error: _ => {
        this.onError = true;
        this.authServiceSubscription.unsubscribe();
      }
    });
  }

  retour(): void{
    this.router.navigate(['']);
  }

}
