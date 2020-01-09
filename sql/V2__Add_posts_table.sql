CREATE TABLE posts
(
    id      UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users (id),
    content TEXT NOT NULL
);
