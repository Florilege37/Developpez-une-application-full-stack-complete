import { Injectable } from "@angular/core";
import { LoginRequest } from "../interfaces/loginRequest.interface";
import { SessionInformation } from "../../../models/sessionInformation.interface";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { RegisterRequest } from "../interfaces/registerRequest.interface";

@Injectable({
    providedIn: 'root'
  })
export class AuthService {
  
    private pathService = 'api/auth';

    public token !: string;
  
    constructor(private httpClient: HttpClient) { }
  
    public register(registerRequest: RegisterRequest): Observable<void> {
      return this.httpClient.post<void>(`${this.pathService}/register`, registerRequest);
    }
  
    public login(loginRequest: LoginRequest): Observable<SessionInformation> {
      return this.httpClient.post<SessionInformation>(`${this.pathService}/login`, loginRequest);
    }
  }