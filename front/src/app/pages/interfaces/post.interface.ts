import { Message } from "./message.interface";

export interface Post{
    id: number;
    title: string;
    description: string;
    user_id: number;
    topicId: number;
    created_at: Date;
    userName: String;
    message: Message[];

}