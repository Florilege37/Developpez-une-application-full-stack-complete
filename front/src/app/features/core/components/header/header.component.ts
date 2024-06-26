import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  
  public isLogged : boolean = this.sessionService.isLogged;

  constructor(private router: Router,
    private sessionService: SessionService) {}

  goToArticles(): void {
    this.router.navigate(['/mdd']);
  }

  goToThemes(): void {
    this.router.navigateByUrl("/mdd/topics");
  }

  goToMe(): void {
    this.router.navigate(['/mdd/me']);
  }

}
