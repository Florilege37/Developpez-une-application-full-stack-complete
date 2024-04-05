import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  
  public isLogged = this.sessionService.isLogged;

  constructor(private router: Router,
    private sessionService: SessionService) {}

  goToArticles() {
    this.router.navigate(['/posts']);
  }

  goToThemes() {
    this.router.navigateByUrl("/topics");
  }

  goToMe() {
    this.router.navigate(['/me']);
  }

}
