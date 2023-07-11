//https://school.programmers.co.kr/learn/courses/30/lessons/144856
//저자 별 카테고리 별 매출액 집계하기
SELECT a.AUTHOR_ID, a.AUTHOR_NAME, b.CATEGORY, sum(s.SALES * b.PRICE) as TOTAL_SALES
from BOOK b inner join BOOK_SALES s on b.BOOK_ID=s.BOOK_ID inner join AUTHOR a on a.AUTHOR_ID = b.AUTHOR_ID
where s.SALES_DATE like "2022-01%"
group by a.AUTHOR_ID, a.AUTHOR_NAME, b.CATEGORY
order by a.AUTHOR_ID, b.CATEGORY desc;

//https://school.programmers.co.kr/learn/courses/30/lessons/133026
//성분으로 구분한 아이스크림 총 주문량
SELECT b.INGREDIENT_TYPE, sum(a.TOTAL_ORDER) as TOTAL_ORDER
from FIRST_HALF a inner join ICECREAM_INFO b on a.FLAVOR = b.FLAVOR
group by b.INGREDIENT_TYPE
order by sum(a.TOTAL_ORDER)

//https://school.programmers.co.kr/learn/courses/30/lessons/131116
//식품 분류 별 가장 비싼 식품의 정보 조회하기

//오답
SELECT CATEGORY, max(PRICE) as MAX_PRICE, PRODUCT_NAME
from FOOD_PRODUCT
where CATEGORY in ("식용유","과자", "국", "김치")
group by CATEGORY
order by MAX_PRICE desc;
//이 경우 최대값은 제대로 나오지만 해당 값에 대한 이름이 나오는게 아니라 그냥 냅다 처음게 나오는듯

//정답
SELECT CATEGORY,PRICE as MAX_PRICE, PRODUCT_NAME
from FOOD_PRODUCT
where PRICE
in (select max(PRICE) as MAX_PRICE
from FOOD_PRODUCT
group by CATEGORY) and
CATEGORY in ("식용유","과자", "국", "김치")
group by CATEGORY
order by MAX_PRICE desc;

//https://school.programmers.co.kr/learn/courses/30/lessons/151137
//자동차 종류 별 특정 옵션이 포함된 자동차 수 구하기
SELECT CAR_TYPE, count(CAR_ID) as CARS
from CAR_RENTAL_COMPANY_CAR
where OPTIONS like "%시트%"
group by CAR_TYPE
order by CAR_TYPE

//https://school.programmers.co.kr/learn/courses/30/lessons/131123
//즐겨찾기가 가장 많은 식당 정보 출력하기
SELECT FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
from REST_INFO
where FAVORITES in (
SELECT max(FAVORITES)
from REST_INFO
group by FOOD_TYPE
)
group by FOOD_TYPE
order by FOOD_TYPE desc;

//https://school.programmers.co.kr/learn/courses/30/lessons/164668
//조건에 맞는 사용자와 총 거래금액 조회하기
SELECT u.USER_ID, u.NICKNAME, sum(b.PRICE) as TOTAL_SALES
from USED_GOODS_BOARD b inner join USED_GOODS_USER u on b.WRITER_ID = u.USER_ID
where b.STATUS = "DONE"
group by USER_ID
having TOTAL_SALES >=700000
order by TOTAL_SALES;

//https://school.programmers.co.kr/learn/courses/30/lessons/144855
//카테고리 별 도서 판매량 집계하기
SELECT b.CATEGORY, sum(s.SALES) as TOTAL_SALES
from BOOK b inner join BOOK_SALES s on b.BOOK_ID = s.BOOK_ID
where s.SALES_DATE like "2022-01%"
group by b.CATEGORY
order by b.CATEGORY

//https://school.programmers.co.kr/learn/courses/30/lessons/132202
//진료과별 총 예약 횟수 출력하기
SELECT MCDP_CD as 진료과코드, count(APNT_YMD) as 5월예약건수
from APPOINTMENT
where APNT_YMD like "2022-05%"
group by MCDP_CD
order by 5월예약건수, 진료과코드;

//https://school.programmers.co.kr/learn/courses/30/lessons/157340
//자동차 대여 기록에서 대여중/ 대여 가능 여부 구분하기
SELECT p.CAR_ID, if(count(case when p.AVAILABILITY="대여중" then 1 end)>0,"대여중","대여 가능") as AVAILABILITY
from (SELECT CAR_ID, if(START_DATE<="2022-10-16"and"2022-10-16"<=END_DATE, "대여중", "대여 가능") as AVAILABILITY
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by CAR_ID,AVAILABILITY) p
group by p.CAR_ID
order by p.CAR_ID desc;

//https://school.programmers.co.kr/learn/courses/30/lessons/151139
//대여 횟수가 많은 자동차들의 월별 대여 횟수 구하기
select * from (
select month(START_DATE) as MONTH, CAR_ID, count(*) as RECORDS from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where CAR_ID in(
select c.CAR_ID as CAR_ID from
( SELECT CAR_ID, count(case when START_DATE>="2022-08-01" and START_DATE<="2022-10-31" then 1 end) as RENT_COUNT , START_DATE
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by CAR_ID ) c
where c.RENT_COUNT >= 5)
group by month(START_DATE), CAR_ID
) d
where d.RECORDS>0
order by d.MONTH, d.CAR_ID desc;

//https://school.programmers.co.kr/learn/courses/30/lessons/59040
//고양이와 개는 몇 마리 있을까
SELECT ANIMAL_TYPE, count(*) as count
from ANIMAL_INS
group by ANIMAL_TYPE
order by ANIMAL_TYPE;
