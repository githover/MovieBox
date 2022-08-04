create table memdb( 				--회원 테이블
	m_id varchar2(30) primary key,
	m_name varchar2(30) not null,
	m_pwd varchar2(30) not null,
	m_email varchar2(50),
	m_phone varchar2(30) not null
);
select * from memdb;
delete from memdb where m_id='yang1234';
drop table memdb;

create table imsi_table(		--회원 임시 테이블
	m_id varchar2(30) not null,
	title varchar2(50) ,
	theater varchar2(30) ,
	ticket_date varchar2(30) ,
	ticket_time varchar2(30) ,
	seat varchar2(50) ,
	ad_num int default 0, 
	ch_num int default 0,
	ticket_num varchar2(20),
	foreign key(m_id) references memdb(m_id),
	foreign key(title) references moviedb(title)
);

select * from imsi_table;
delete from imsi_table;
drop table imsi_table;

create table m_ticketdb(	--회원 예매정보
	m_id varchar2(30) not null, --회원 아이디
	title varchar2(50) not null,
	theater varchar2(30) not null,
	ticket_date varchar2(30) not null,
	ticket_time varchar2(30) not null,
	seat varchar2(50) not null,
	ticket_num varchar2(13) not null,
	ad_num int default 0, 
	ch_num int default 0,
  foreign key(m_id) references memdb(m_id),
  foreign key(title) references moviedb(title)

);



delete from m_ticketdb;
select * from m_ticketdb;
drop table m_ticketdb;



