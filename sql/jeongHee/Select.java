// https://school.programmers.co.kr/learn/courses/30/lessons/131112
// 강원도에 위치한 생산공장 목록 출력하기

SELECT FACTORY_ID, FACTORY_NAME, ADDRESS
        from FOOD_FACTORY
        where ADDRESS like "강원%"
        order by FACTORY_ID asc;

//https://school.programmers.co.kr/learn/courses/30/lessons/59034
//모든 레코드 조회하기
SELECT *
from ANIMAL_INS
order by ANIMAL_ID asc

//https://school.programmers.co.kr/learn/courses/30/lessons/59035
//역순 정렬하기
SELECT NAME, DATETIME
from ANIMAL_INS
order by ANIMAL_ID desc;

//https://school.programmers.co.kr/learn/courses/30/lessons/59036
//아픈 동물 찾기
SELECT ANIMAL_ID, NAME
from ANIMAL_INS
where INTAKE_CONDITION = "SICK";

//https://school.programmers.co.kr/learn/courses/30/lessons/59037
//어린 동물 찾기
SELECT ANIMAL_ID, NAME
from ANIMAL_INS
where INTAKE_CONDITION != "Aged";

//https://school.programmers.co.kr/learn/courses/30/lessons/59403
//동물의 아이디와 이름
SELECT ANIMAL_ID, NAME
from ANIMAL_INS
order by ANIMAL_ID asc;

//https://school.programmers.co.kr/learn/courses/30/lessons/59404
//여러 기준으로 정렬하기
SELECT ANIMAL_ID, NAME, DATETIME
from ANIMAL_INS
order by NAME asc, DATETIME desc;

//https://school.programmers.co.kr/learn/courses/30/lessons/59405
//상위 n개 레코드
SELECT NAME
from ANIMAL_INS
order by DATETIME asc
limit 1;

//https://school.programmers.co.kr/learn/courses/30/lessons/1315
//조건에 맞는 회원 수 구하기
SELECT count(USER_ID) as USERS
from USER_INFO
where AGE>=20 and AGE<=29 and JOINED LIKE "2021%";

//https://school.programmers.co.kr/learn/courses/30/lessons/132203
//흉부외과 또는 일반외과 의사 목록 출력하기
SELECT DR_NAME, DR_ID, MCDP_CD, DATE_FORMAT(HIRE_YMD,"%Y-%m-%d")
from DOCTOR
where MCDP_CD = "CS" or MCDP_CD = "GS"
order by HIRE_YMD desc, DR_NAME asc;

//https://school.programmers.co.kr/learn/courses/30/lessons/131120
//3월에 태어난 여성 회원 목록 출력하기
SELECT MEMBER_ID, MEMBER_NAME, GENDER, DATE_FORMAT(DATE_OF_BIRTH,"%Y-%m-%d") as DATE_OF_BIRTH
from MEMBER_PROFILE
where TLNO != "NULL" and GENDER = 'W' and DATE_OF_BIRTH like "%-03-%"
order by MEMBER_ID;

//https://school.programmers.co.kr/learn/courses/30/lessons/164673
//조건에 부합하는 중고거래 댓글 조회하기
SELECT TITLE, b.BOARD_ID, REPLY_ID, r.WRITER_ID, r.CONTENTS, DATE_FORMAT(r.CREATED_DATE,"%Y-%m-%d") as CREATED_DATE
from USED_GOODS_BOARD b inner join USED_GOODS_REPLY r on b.BOARD_ID = r.BOARD_ID
where b.CREATED_DATE like "2022-10-%"
order by r.CREATED_DATE , b.TITLE;

//https://school.programmers.co.kr/learn/courses/30/lessons/131118
//서울에 위치한 식당 목록 출력하기
SELECT a.REST_ID, a.REST_NAME, a.FOOD_TYPE, a.FAVORITES, a.ADDRESS, ROUND(avg(b.REVIEW_SCORE),2) as SCORE
from REST_INFO a inner join REST_REVIEW b on a.REST_ID = b.REST_ID
where a.ADDRESS like "서울%"
group by REST_ID
order by ROUND(avg(b.REVIEW_SCORE),3) desc, a.FAVORITES desc;

//https://school.programmers.co.kr/learn/courses/30/lessons/144853
//조건에 맞는 도서 리스트 출력하기
SELECT BOOK_ID, DATE_FORMAT(PUBLISHED_DATE,"%Y-%m-%d") as PUBLISHED_DATE
from BOOK
where CATEGORY = "인문" and PUBLISHED_DATE like "2021-%"
order by PUBLISHED_DATE;

//https://school.programmers.co.kr/learn/courses/30/lessons/133025
//과일로 만든 아이스크림 고르기
SELECT a.FLAVOR
from FIRST_HALF a inner join ICECREAM_INFO b on a.FLAVOR = b.FLAVOR
where a.TOTAL_ORDER >=3000 and b.INGREDIENT_TYPE like "fruit%"
order by a.TOTAL_ORDER desc;

//https://school.programmers.co.kr/learn/courses/30/lessons/151136
//평균 일일 대여 요금 구하기
SELECT ROUND(AVG(DAILY_FEE),0) as AVERAGE_FEE
from CAR_RENTAL_COMPANY_CAR
where CAR_TYPE = "SUV";

//https://school.programmers.co.kr/learn/courses/30/lessons/132201
//12세 이하인 여자 환자 목록 출력하기
SELECT PT_NAME, PT_NO ,GEND_CD, AGE, IFNULL(TLNO, "NONE") as TLNO
from PATIENT
where AGE <=12 and GEND_CD = "W"
order by AGE desc, PT_NAME;

//https://school.programmers.co.kr/learn/courses/30/lessons/133024
//인기있는 아이스크림
SELECT FLAVOR
from FIRST_HALF
order by TOTAL_ORDER desc, SHIPMENT_ID;

//https://school.programmers.co.kr/learn/courses/30/lessons/131536
//재구매가 일어난 상품과 회원 리스트 구하기
SELECT USER_ID, PRODUCT_ID
from ONLINE_SALE
group by USER_ID,PRODUCT_ID having count(ONLINE_SALE_ID)>=2
order by USER_ID, PRODUCT_ID desc;

//https://school.programmers.co.kr/learn/courses/30/lessons/131537
//오프라인 온라인 판매 데이터 통합하기
SELECT DATE_FORMAT(SALES_DATE,"%Y-%m-%d") as SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT
from ONLINE_SALE
where SALES_DATE like "2022-03%"
union all
SELECT DATE_FORMAT(SALES_DATE,"%Y-%m-%d") as SALES_DATE, PRODUCT_ID, NULL USER_ID, SALES_AMOUNT
from OFFLINE_SALE
where SALES_DATE like "2022-03%"
order by SALES_DATE, PRODUCT_ID, USER_ID;