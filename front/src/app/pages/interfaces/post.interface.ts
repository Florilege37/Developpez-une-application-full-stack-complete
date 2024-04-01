export interface Post{
    id: number;
    title: string;
    description: string;
    user_id: number;
    topic_id: number;
    created_at: Date;
}