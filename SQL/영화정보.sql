create table moviedb(
	title varchar2(50) primary key,
	b_poster varchar2(50) not null,
	s_poster varchar2(50) not null,
	info1 varchar2(1000),
	info2 varchar2(1000)
);




insert into moviedb (title, b_poster, s_poster) values ('뒤틀린집','b_뒤틀린집.jpg','s_뒤틀린집.jpg');
insert into moviedb (title, b_poster, s_poster) values ('마녀2','b_마녀2.jpg','s_마녀2.jpg');
insert into moviedb (title, b_poster, s_poster) values ('명탐정 코난-할로윈의 신부','b_코난.jpg','s_코난.jpg');
insert into moviedb (title, b_poster, s_poster) values ('헤어질 결심','b_헤어질 결심.jpg','s_헤어질 결심.jpg');
insert into moviedb (title, b_poster, s_poster) values ('한산: 용의 출현','b_한산.jpg','s_한산.jpg');
insert into moviedb (title, b_poster, s_poster) values ('미니언즈2','b_미니언즈.jpg','s_미니언즈.jpg');
insert into moviedb (title, b_poster, s_poster) values ('외계인 1부','b_외계인.jpg','s_외계인.jpg');
insert into moviedb (title, b_poster, s_poster) values ('탑건:매버릭','b_탑건.jpg','s_탑건.jpg');
insert into moviedb (title, b_poster, s_poster) values ('브로커','b_브로커.jpg','s_브로커.jpg');
insert into moviedb (title, b_poster, s_poster) values ('비상선언','b_비상선언.jpg','s_비상선언.jpg');

select * from moviedb;
drop table moviedb;
delete from moviedb;