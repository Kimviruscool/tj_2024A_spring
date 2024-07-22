drop database if exists springweb;
create database springweb;
use springweb;

drop tables if exists member;
create table member(
	no bigint auto_increment ,            -- 회원번호
	id varchar(30) not null unique ,            -- 회원 아이디
	pw varchar(30) not null ,            -- 회원 비밀번호
	name varchar(20) not null ,            -- 회원 이름
	email varchar(50) ,               -- 회원 이메일
	phone varchar(13) not null unique,         -- 회원 핸드폰 번호
	constraint member_no_pk primary key(no )       -- 회원 번호 pk
);


# [2] 로그인 
select * from member where id = 'qwe' and pw ='qwe';

insert member(id,pw,name,email,phone) values("kkkkk","1234","김병찬","kk@naver.com","010-1111-1111");

select * from member;
#[4] 아이디 중복 검사
select * from member where id = "kkkkk";
select * from member where id = "KKKKK";
#만일 대소문자를 구분하는 데이터 검색 할때는 binary(필드)
#binary(필드) : 문자 가 아닌 바이트를 기준으로 비교,검색 한다.
select * from member where binary(id) = 'kkkkk'; #소문자 kkkkk
select * from member where binary(id) = 'KKKKK'; #대문자 KKKKK
