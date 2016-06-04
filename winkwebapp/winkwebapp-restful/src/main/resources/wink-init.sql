drop database if exists wink;
/*create database wink;*/
CREATE DATABASE wink DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

use wink;

create table tbl_user (
   id bigint auto_increment,
  username varchar(100),
  password varchar(256),
  createtime DATETIME NOT NULL,
constraint pk_tbl_user primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_tbl_user_username on tbl_user(username);
