INSERT  into usr (
id,
username,
password,
active
)
VALUES (
nextval('hibernate_sequence'),
'Dismas',
'123qweASD',
TRUE
);

INSERT into user_role (
user_id,
roles
)
VALUES /*(
1,
'USER'
),*/
(
1,
'Admin'
);

--commit;