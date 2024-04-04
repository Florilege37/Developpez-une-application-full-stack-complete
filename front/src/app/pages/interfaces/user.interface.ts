export interface User {
    id: number;
    email: string;
    nickname: string;
    password: string;
    createdAt: Date;
    topicSubscribed : number[];
  }
  