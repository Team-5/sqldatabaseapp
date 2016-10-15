use sqldatabaseapp;
drop table if exists employee;
create table customer (
  id int unsigned not null auto_increment,
  firstName varchar(24) not null,
  lastName varchar(18) not null,
  homePhone varchar(14) not null,
  state varchar(12) not null,
  city varchar (24) not null,
  age int not null,
  primary key(id)
);