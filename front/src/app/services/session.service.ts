import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { SessionInformation } from '../models/sessionInformation.interface';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private readonly SESSION_KEY = 'session_information';
  public isLogged : boolean = false;
  public sessionInformation: SessionInformation | undefined;

  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

  constructor(
    private router: Router
  ) {
    // Récupérez les informations de session depuis le Local Storage lors de l'initialisation du service
    const sessionData = localStorage.getItem(this.SESSION_KEY);
    if (sessionData) {
      this.sessionInformation = JSON.parse(sessionData);
      this.isLogged = true;
      this.isLoggedSubject.next(this.isLogged);
    }
  }

  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  public logIn(user: SessionInformation): void {
    this.sessionInformation = user;
    this.isLogged = true;
    this.isLoggedSubject.next(this.isLogged);
    // Stockez les informations de session dans le Local Storage
    localStorage.setItem(this.SESSION_KEY, JSON.stringify(this.sessionInformation));
  }

  public logOut(): void {
    this.sessionInformation = undefined;
    this.isLogged = false;
    localStorage.clear();
    this.router.navigate(['']);
    this.next();
  }

  private next(): void {
    this.isLoggedSubject.next(this.isLogged);
  }
}
