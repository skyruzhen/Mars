create table person(id  int  primary key , name varchar(50), age varchar(3), address varchar(200) );
insert into person(id, name,age,address) values(hibernate_sequence.nextval, "李振", 27, "上海");
insert into person(id, name,age,address) values(hibernate_sequence.nextval, "李振", 28, "合肥");