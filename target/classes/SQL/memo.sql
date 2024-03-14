-- Oracle DDL -------------------------
drop table memo;

create table memo(
	no number(8),
	name varchar2(30) not null,
	msg varchar2(100),
	wdate timestamp default systimestamp,
	primary key (no)

);

drop sequence memo_seq;

create sequence memo_seq nocache;
----------------------------------------

-- MySQL DDL ---------------------------
drop table if exists memo;

create table if not exists memo(
	no int primary key auto_increment,
	name varchar(30) not null,
	msg varchar(100),
	wdate datetime default now()
);
	
-------------------------------------------

----------------------------------------