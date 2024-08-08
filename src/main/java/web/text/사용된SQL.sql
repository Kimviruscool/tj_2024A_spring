# 정의어 : DDL =======================================
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
select * from member;

# 1. 게시물 카테고리
drop table if exists bcategory;
create table  bcategory(
   bcno int unsigned auto_increment ,
    bcname varchar( 30 ) not null unique,
   bcdate datetime default now() not null  ,
    constraint bcategory_bcno_pk primary key ( bcno )
);
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
select *from board;

# 1. 카테고리 샘플 
insert into bcategory( bcname ) values ( '자유' ) , ( '노하우' ) , ( '판매' ) , ( '구매') ;

#  게시물의 댓글 
drop table  if exists breply;
create table breply(
   brno   bigint unsigned auto_increment ,  -- 댓글번호 [ pk ]
    brindex bigint unsigned , -- ** 댓글인덱스( 댓글 위치 분류 ) , 0 : 최상위댓글 , 1이상 : PK(brno)참조하는 상위댓글번호  
    brcontent varchar(255) , -- 댓글내용 
    brdate datetime default now() , -- 작성일 
    no bigint , -- 댓글을 작성한 작성자의 회원번호 [fk]
    bno bigint unsigned ,  -- 댓글이 위치한 게시물번호 [ fk ]
    primary key( brno ) , 
    foreign key( no ) references member(no) on update cascade on delete cascade , 
    foreign key( bno ) references board( bno ) on update cascade on delete cascade
);
select * from breply;

# 제품 테이블          : 1 , 제품 1개(PK) 당 이미지 여러개(FK) 
drop table if exists product;
create table product( 
   pno int auto_increment , -- 제품번호        ( 테이블당 권장 )
    ptitle varchar(100) , -- 제품명 
    pcontent varchar(255) , -- 제품설명 
    pprice int , -- 제품가격
    pdate datetime default now() , -- 제품 등록일 ( 테이블당 권장 )
    pview int default 0 , -- 제품조회수
    primary key( pno )   -- pk 설정 
); 

drop table if exists productimg;
# 제품 이미지 테이블    : M   , 
create table productimg( 
   pimgno int auto_increment , -- 제품 이미지번호 
    pimgname text , -- 제품 이미지 이름 
    pimgdate datetime default now() , -- ( 관례적으로 레코드 등록일는 필수 )
    pno int , 
    primary key( pimgno ) ,
    foreign key( pno ) references product( pno ) 
    on update cascade on delete cascade -- 만약에 pk가 삭제되면 참조하는 fk 레코드 도 같이 삭제 
);

select * from product;
select * from productimg;


