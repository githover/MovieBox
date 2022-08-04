create table no_memdb(	--비회원 테이블
	no_phone varchar2(30) primary key
	,no_birth varchar(8) not null
	,no_pwd varchar2(30) not null
);



select * from no_memdb;
delete from no_memdb;
drop table no_memdb;

create table no_imsi_table(	--비회원 임시 테이블 
	no_phone varchar2(30) not null, 
	title varchar2(50),
	theater varchar2(30),
	ticket_date varchar2(30),
	ticket_time varchar2(30),
	seat varchar2(50),
	ad_num int default 0,
	ch_num int default 0,
	ticket_num varchar2(20),
	foreign key(no_phone) references no_memdb(no_phone),
	foreign key(title) references moviedb(title)
);



select * from no_imsi_table;
delete from no_imsi_table;
drop table no_imsi_table;

create table no_ticketdb(		--비회원 예매정보
	no_phone varchar2(30)  not null, 	--비회원 전화번호
	title varchar2(50) not null,
	theater varchar2(30) not null,
	ticket_date varchar2(30) not null,
	ticket_time varchar2(30) not null,
	seat varchar2(50) not null,
	ticket_num varchar2(13) not null,
	ad_num int default 0, 
	ch_num int default 0,
  foreign key(no_phone) references no_memdb(no_phone),
  foreign key(title) references moviedb(title)

);
select * from no_ticketdb;
delete from no_ticketdb;
drop table no_ticketdb;







