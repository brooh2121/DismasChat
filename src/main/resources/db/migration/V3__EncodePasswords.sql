CREATE EXTENSION if not EXISTS pgcrypto;

UPDATE usr set password = crypt(password, gen_salt('bf',8));