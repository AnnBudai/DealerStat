insert into user (id, username, password, active) values (1, 'admin', '123', true);

insert into user_roles (user_id, roles) values (1, 'Trader'), (1, 'Admin');