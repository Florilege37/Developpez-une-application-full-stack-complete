import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { RegisterRequest } from 'src/app/features/auth/interfaces/registerRequest.interface';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  private authServiceSubscription!: Subscription;

  constructor(private authService: AuthService,
    private fb: FormBuilder,
    private router: Router) {
  } 

  public onError : boolean = false;

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
        Validators.required,
        Validators.email
      ]
    ],
    password: [
      '',
      [
        Validators.required,
      ]
    ]
  });

  retour(): void{
    this.router.navigate(['']);
  }

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.authServiceSubscription = this.authService.register(registerRequest).subscribe({
      next: (_: void) => {
        this.router.navigate(['/login']);
        this.authServiceSubscription.unsubscribe();
      },
      error: _ => {
        this.onError = true;
        this.authServiceSubscription.unsubscribe();
      }
    });
  }
}
