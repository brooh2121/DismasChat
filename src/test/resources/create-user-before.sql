delete from user_role where roles not in ('Admin');
DELETE from usr where username != 'Dismas';

insert into usr (id, active, password, username) VALUES (2,true,'$2a$08$nBt1pz4guyb0iD1GOEwNr.1GAX5VwWp3GkUDFbSpL6eF2qHiGrJ0K','MAX');

insert into user_role(user_id, roles) VALUES (2,'User');