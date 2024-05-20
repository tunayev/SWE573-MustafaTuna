import type {AuthUser} from "~/stores/auth";

export interface User {
    id: number;
    email: string;
    nickname: string | null;
    avatar: string | null;
    role: string;
    communities: Community[];
    enabled: boolean;
    username: string;
    authorities: [];
    accountNonExpired: boolean;
    credentialsNonExpired: boolean;
    accountNonLocked: boolean;
}

export interface Community {
    id: number;
    name: string;
    description: string;
    isPrivate: boolean;
    users: User[];
    admin: User;
    moderators: User[];
    posts: Post[];
}

export interface Post {
    id: number;
    title: string;
    body: PostField[] | string;
    user: User;
    community: Community;
    communityId: string;
    template: Template;
    templateId: string;
    comments: Comment[];
}

export interface Template {
    name: string;
    fields: PostField[] | string
}

export interface PostField {
    type: "text" | "image" | "video" | "audio" | "geolocation" | "textarea";
    name: string;
    value: string | null;
}

export interface Comment {
    id: number;
    body: string;
    user: User;
    post: Post;
}
