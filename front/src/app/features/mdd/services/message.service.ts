import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from '../interfaces/message.interface';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private pathService = 'api/message/create';

  constructor(private httpClient: HttpClient) {
  }

  public createMessage(message: Message): Observable<Message> {
    return this.httpClient.post<Message>(this.pathService, message);
  }

}
