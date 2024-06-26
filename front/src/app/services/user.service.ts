import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.interface';
import { UserUpdateRequest } from '../models/userUpdateRequest.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private pathService : string = 'api/user';

  constructor(private httpClient: HttpClient) { }

  public getMe(): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/me`);
  }

  public getById(id: string): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/${id}`);
  }

  public delete(id: string): Observable<any> {
    return this.httpClient.delete(`${this.pathService}/${id}`);
  }

  public update(id: number, userUpdateRequest: UserUpdateRequest){
    return this.httpClient.put(`${this.pathService}/update/${id}`, userUpdateRequest);
  }
}
