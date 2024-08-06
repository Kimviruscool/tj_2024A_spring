/*프로그래머스 SQL 문제*/
# ============================================================= 2024-08-06 ===================================================================
#1. 인기있는 아이스크림
SELECT FLAVOR from FIRST_HALF order by TOTAL_ORDER DESC , SHIPMENT_ID ASC
#2.모든 레코드 조회하기
SELECT * from ANIMAL_INS order by ANIMAL_ID ASC
#3.역순 정렬하기
SELECT NAME,DATETIME from ANIMAL_INS order by ANIMAL_ID DESC
#4. 동물의 아이디와 이름
SELECT ANIMAL_ID,NAME from ANIMAL_INS order by ANIMAL_ID ASC
#5. 여러 기준으로 정렬하기
SELECT ANIMAL_ID,NAME,DATETIME from ANIMAL_INS order by NAME ASC ,DATETIME DESC
#6. 과일로 만든 아이스크림 고르기
select FIRST_HALF.FLAVOR from FIRST_HALF inner join ICECREAM_INFO on FIRST_HALF.FLAVOR = ICECREAM_INFO.FLAVOR where TOTAL_ORDER>3000 and ICECREAM_INFO.INGREDIENT_TYPE = 'fruit_based'
#7.조건에 부합하는 중고거래 댓글 조회하기
SELECT A.TITLE,A.BOARD_ID,B.REPLY_ID,B.WRITER_ID,B.CONTENTS,DATE_FORMAT(B.CREATED_DATE, '%Y-%m-%d') CREATED_DATE FROM USED_GOODS_BOARD A INNER JOIN USED_GOODS_REPLY B ON A.BOARD_ID = B.BOARD_ID WHERE A.CREATED_DATE BETWEEN '2022-10-01' AND '2022-10-31' ORDER BY CREATED_DATE ASC, TITLE ASC
#8.강원도에 위치한 생산공장 목록 출력하기
SELECT FACTORY_ID,FACTORY_NAME,ADDRESS from FOOD_FACTORY where ADDRESS like "강원도%"
#9. 아픈 동물 찾기
SELECT ANIMAL_ID,NAME from ANIMAL_INS where INTAKE_CONDITION = 'sick'
#10. 어린 동물 찾기
SELECT ANIMAL_ID,NAME from ANIMAL_INS where INTAKE_CONDITION != 'aged'
#11. 상위 n개 레코드
SELECT NAME from ANIMAL_INS order by DATETIME limit 1
#12. Python 개발자 찾기
select ID,EMAIL,FIRST_NAME,LAST_NAME from DEVELOPER_INFOS where 'Python' in (SKILL_1,SKILL_2,SKILL_3) order by ID ASC
