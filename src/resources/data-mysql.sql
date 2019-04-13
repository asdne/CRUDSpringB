insert into roles (id,name) values (1,'ROLE_ADMIN'),(2,'ROLE_USER');
UPDATE roleseq set next_val=3;
insert into user (id, login, password) VALUES (1,'admin','password');
update user_seq set next_val=2;
insert into user_roles  (user_id, role_id) VALUES (1,1);