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

# 1. 게시물 카테고리
drop table if exists bcategory;
create table  bcategory(
   bcno int unsigned auto_increment ,
    bcname varchar( 30 ) not null unique,
   bcdate datetime default now() not null  ,
    constraint bcategory_bcno_pk primary key ( bcno )
);
insert into bcategory( bcname ) values ( '자유' ) , ( '노하우' ) , ( '판매' ) , ( '구매') ;
select * from bcategory;

# 2. 게시물
drop table if exists board;
create table board(
   bno bigint unsigned auto_increment ,
    btitle varchar( 255 ) not null ,
    bcontent longtext ,
    bfile longtext ,
    bview int unsigned default 0 not null ,
    bdate datetime  default now() not null  ,
    no  bigint ,
    bcno int unsigned,
    constraint board_bno_pk primary key( bno ) ,
    constraint board_no_fk foreign key( no) references member( no ) on update cascade on delete cascade ,
    constraint board_bcno_fk foreign key( bcno ) references bcategory( bcno ) on update cascade on delete cascade
);
#========== 확인용 ==================================================
select *from board;
select * from member;
select * from bcategory;
#================================================ 내가 작성한코드 ===============================
# [2] 로그인
select * from member where id = 'qwe' and pw ='qwe';

insert member(id,pw,name,email,phone) values("kkkkk","1234","김병찬","kk@naver.com","010-1111-1111");
insert member(id,pw,name,email,phone) values("aaaaa","1234","김병찬","kk@naver.com","010-1111-1111");


select * from member;
#[4] 아이디 중복 검사
select * from member where id = "kkkkk";
select * from member where id = "KKKKK";
#만일 대소문자를 구분하는 데이터 검색 할때는 binary(필드)
#binary(필드) : 문자 가 아닌 바이트를 기준으로 비교,검색 한다.
select * from member where binary(id) = 'kkkkk'; #소문자 kkkkk
select * from member where binary(id) = 'KKKKK'; #대문자 KKKKK
#JDBC : select  * from member where binary(id) = ?;

#5 탈퇴
delete from member where no = 9 and pw = 'qwe123'; #패스워드는 중복이 가능하므로 식별 역할이 불가능하다.
#JDBC : delete from member where no = ? and pw = ?;

#6 수정
update member set pw = 'qwe1234', name = '리리리', phone = '010-0101-1010' where no = 13 and pw = 'qwe123';
#JDBC : update member set pw = ?, name = ?, phone = ? where no = ? and pw = ?;

#7 카테고리 전체 출력
select * from bcategory;

#8 글쓰기 bcno[fk]카테고리, no[fk]회원
insert into board(bcno,btitle,bcontent,no)values(1,"aa","atest",1);
insert into board(bcno,btitle,bcontent,no)values(2,"bb","btest",1);
insert into board(bcno,btitle,bcontent,no)values(3,"cc","ctest",1);
insert into board(bcno,btitle,bcontent,no)values(4,"dd","dtest",1);
insert into board(bcno,btitle,bcontent,no)values(1,"ee","etest",1);
insert into board(bcno,btitle,bcontent,no)values(1,"ff","ftest",1);
#1번 회원이 1번카테고리에 안녕제목의 아무거나글을 작성
# JDBC : insert into board(bcno,btitle,bcontent,no)values(?,?,?,?);

#9 게시판 출력
select * from board join member;
select *from board;

#10 상세 게시물 출력
select * from board join member join bcategory on board.bcno = bcategory.bcno where bno = 6;
select distinct * from board join member join bcategory;
#JDBC : select * from board join member join bcategory on board.bcno = bcategory.bcno where bno = ?;
#inner join

#게시물 전체 출력
select * from board;

#limit 연산자 이용한 레코드 제한
#limit 개수 : 개수만큼의 레코드 조회
#limit 시작 레코드, 개수 : 시작 레코드(0~) 부터 개수만큼의 레코드 조회alter
select * from board limit 0;
select * from board limit 1;
select * from board limit 1, 3;
	#페이징 처리 활용 , 가정 : 하나의 페이지 당 ㄱ ㅔ시물 표시 수는 5개 씩
    #1페이지 처리
select * from board limit 0,5; #1페이지 일때 시작인덱스 : 0 , 1*5
    #2페이지 처리
select * from board limit 5,5; #2페이지 시작 : 5 , 2 * 5 > 10
    #3페이지 처리
select * from board limit 10,5; #3페이지 시작 : 10
#시작 인덱스의 계산식 : (현재페이지번호-1) * 페이지당 게시물 수

-- 활용
select * from board inner join member on board.no = member.no limit 0,5;
#JDBC
select * from board inner join member on board.no = member.no limit ?,?;

-- 정렬 : 작성일 순으로
select * from board inner join member on board.no = member.no order by board.no desc limit 0,5;

-- [1] 레코드 총 개수 세기
	# count( * ) 		: 조회 한 레코드의 총 개수 반환 하는 함수 , 필드가 null 포함 해서 개수 계산
    # count( 필드명 ) 	: 조회 한 레코드의 총 개수 반환 하는 함수 , 특정 필드의 null 제외한 개수 계산
# 1. 레코드 조회
select * from board;
# 2. 레코드 총개수 조회
select count(*) from board;			# 7  , null 포함 레코드 총 개수
# 2. 레코드 총개수 조회
select count( bno ) from board;		# 7	 , 해당 필드의 null 제외한 레코드 총 개수
select count( bfile ) from board; 	# 2  , 해당 필드의 null 제외한 레코드 총 개수

-- 활용1. 전체 게시물 수
select count(*) as 총게시물수 from board;
-- 활용2. 카테고리별 게시물 수
select * from bcategory;
select count(*) as 총게시물수 from board where bcno = 1;	# 1. (자유)1번카테고리 의 전체 게시물 수
select count(*) as 총게시물수 from board where bcno = 2;  	# 2. (노하우)2번카테고리 의 전체 게시물 수
select count(*) as 총게시물수 from board where bcno = 3;  	# 3. (판매)3번카테고리 의 전체 게시물 수
select count(*) as 총게시물수 from board where bcno = 4;  	# 4. (구매)4번카테고리 의 전체 게시물 수
	# JDBC
    # select count(*) as 총게시물수 from board where bcno = ?;

-- 활용3. 전체 게시물의 페이징 처리
select * from board inner join member on board.no = member.no order by board.bno desc limit 0 , 5 ;

-- 활용4. 카테고리별 게시물의 페이징 처리
	-- select 필드명 from 테이블명1 inner join 테이블명2 on 조인조건 where 일반조건 order by 정렬조건 limit 레코드제한;
select * from board inner join member on board.no = member.no  where bcno = 1 order by board.bno desc limit 0 , 5 ;
    # JDBC
    # select * from board inner join member on board.no = member.no where bcno = ? order by board.bno desc limit ? , ? ;
-- 20240731 [검색.조회]
-- 전체 레코드 조회
select * from board;
#특정 필드값의 레코드 조회
select * from board where btitle = "안";
#특정 필드값이 포함된 레코드 조회
select * from board where btiitle like 'a%'; # a시작하는 값 검색
select * from board where btiitle like '%a'; # a끝나는 값 검색
select * from board where btiitle like '%a%'; # a포함된 값 검색
#활용
select * 								#조회
from board inner join member 			#테이블 조인
on board.no = member.no 				#테이블 조인 조건
where bcno =  1 and btitle like '%d' 	#일반조건 : 1. 카테고리 조건	2.검색조건
order by bno desc 						#정렬 조건
limit 0 , 5;							#레코드 제한
-- 활용2
select count(*) as 총게시물수 from board where bcno = 1 and btitle like '%d%';
#board 테이블의 bcno 가 1이며 btitle 에 d가 포함된 게시물 갯수를 count