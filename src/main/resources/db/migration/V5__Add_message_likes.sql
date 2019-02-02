CREATE TABLE message_likes (
  user_id BIGINT not null REFERENCES usr,
  message_id BIGINT not null REFERENCES message,
  PRIMARY KEY (user_id,message_id)
)