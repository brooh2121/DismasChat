delete from message;

INSERT INTO message(id,text,tag,user_id) VALUES
  (1,'first','my_tag',1),
  (2,'second','second_tag',1),
  (3,'third','my_tag',1),
  (4,'fourth','fourth_tag',1);

alter SEQUENCE hibernate_sequence RESTART with 10;