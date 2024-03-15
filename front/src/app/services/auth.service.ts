import { Injectable } from "@angular/core";
import { LoginRequest } from "../models/loginRequest.interface";
import { SessionInformation } from "../models/sessionInformation.interface";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: 'root'
  })
export class AuthService {
  
    private pathService = 'api/auth';
  
    constructor(private httpClient: HttpClient) { }
  
    /*public register(registerRequest: RegisterRequest): Observable<void> {
      return this.httpClient.post<void>(`${this.pathService}/register`, registerRequest);
    }*/
  
    public login(loginRequest: LoginRequest): Observable<SessionInformation> {
      return this.httpClient.post<SessionInformation>(`${this.pathService}/login`, loginRequest);
    }
  }