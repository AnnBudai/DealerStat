CREATE TABLE comment (
  id int(11) NOT NULL auto_increment,
  approved bit NOT NULL,
  create_at date,
  message varchar(255),
  user_id int(11),
  game_object_id int(11),
  PRIMARY KEY (id)
)engine=MyISAM;

CREATE TABLE game (
  id int(11) NOT NULL auto_increment,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
)engine=MyISAM;

CREATE TABLE game_object (
  id int(11) NOT NULL auto_increment,
  created_at date NOT NULL,
  filename varchar(255),
  status int(11),
  text varchar(255),
  title varchar(255),
  updated_at date,
  game_id int(11) NOT NULL,
  user_id int(11) NOT NULL,
  PRIMARY KEY (id)
)engine=MyISAM;

CREATE TABLE user (
  id int(11) NOT NULL auto_increment,
  active bit NOT NULL,
  create_at date,
  email varchar(255),
  first_name varchar(255),
  last_name varchar(255),
  password varchar(255) NOT NULL,
  username varchar(255) NOT NULL,
  activation_code varchar(255),
  PRIMARY KEY (id)
)engine=MyISAM;

CREATE TABLE user_roles (
  user_id int(11) NOT NULL,
  roles varchar(255)
)engine=MyISAM;

alter table user_roles
    add constraint user_role_user_fk
    foreign key (user_id) references user(id);

alter table game_object
    add constraint game_object_user_fk
    foreign key (user_id) references user(id);

alter table comment
    add constraint comment_user_fk
    foreign key (user_id) references user(id);

alter table game_object
    add constraint game_fk
    foreign key (game_id) references game(id);

alter table comment
    add constraint comment_game_object_fk
    foreign key (game_object_id) references game_object(id);