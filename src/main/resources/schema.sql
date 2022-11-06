DROP TABLE IF EXISTS player
CREATE TABLE player(id serial primary key, name varchar(255) not null, age number, club varchar(255) not null, nationality varchar(255) not null)