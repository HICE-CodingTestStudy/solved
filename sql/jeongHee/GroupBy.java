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
