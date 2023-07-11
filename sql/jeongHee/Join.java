//https://school.programmers.co.kr/learn/courses/30/lessons/131124
//그룹별 조건에 맞는 식당 목록 출력하기
select m.MEMBER_NAME, r.REVIEW_TEXT, DATE_FORMAT(r.REVIEW_DATE,"%Y-%m-%d") as REVIEW_DATE
from MEMBER_PROFILE m inner join REST_REVIEW r on m.MEMBER_ID = r.MEMBER_ID
where m.MEMBER_ID = (
SELECT MEMBER_ID
from REST_REVIEW
group by MEMBER_ID
order by count(*) desc
limit 1 )
order by REVIEW_DATE, r.REVIEW_TEXT;

//https://school.programmers.co.kr/learn/courses/30/lessons/144854
//조건에 맞는 도서와 저자 리스트 출력하기
SELECT b.BOOK_ID, a.AUTHOR_NAME, DATE_FORMAT(b.PUBLISHED_DATE,"%Y-%m-%d") as PUBLISHED_DATE
from BOOK b inner join AUTHOR a on b.AUTHOR_ID = a.AUTHOR_ID
where b.CATEGORY = "경제"
order by PUBLISHED_DATE;

//https://school.programmers.co.kr/learn/courses/30/lessons/133027
//주문량이 많은 아이스크림들 조회하기
select c.FLAVOR
from(
SELECT *
from FIRST_HALF
union all
select *
from JULY) c
group by c.FLAVOR
order by sum(c.TOTAL_ORDER) desc
limit 3

//https://school.programmers.co.kr/learn/courses/30/lessons/131117
//5월 식품들의 총매출 조회하기
SELECT p.PRODUCT_ID, p.PRODUCT_NAME, p.PRICE*o.AMOUNT as TOTAL_SALES
from FOOD_PRODUCT p inner join (
select PRODUCT_ID, sum(AMOUNT) as AMOUNT, PRODUCE_DATE
from FOOD_ORDER
where PRODUCE_DATE like "2022-05%"
group by PRODUCT_ID
) o
on p.PRODUCT_ID = o.PRODUCT_ID
order by TOTAL_SALES desc, p.PRODUCT_ID

//https://school.programmers.co.kr/learn/courses/30/lessons/59042
//없어진 기록 찾기
SELECT ANIMAL_ID, NAME
from ANIMAL_OUTS
where ANIMAL_ID not in (
select ANIMAL_ID
from ANIMAL_INS
)
order by ANIMAL_ID;

//https://school.programmers.co.kr/learn/courses/30/lessons/59043
//있었는데요 없었습니다
SELECT i.ANIMAL_ID, i.NAME
from ANIMAL_INS i inner join ANIMAL_OUTS o on i.ANIMAL_ID = o.ANIMAL_ID
where i.DATETIME>o.DATETIME
order by i.DATETIME

//https://school.programmers.co.kr/learn/courses/30/lessons/59044
//오랜 기간 보호한 동물(1)
SELECT NAME, DATETIME
from ANIMAL_INS
where ANIMAL_ID not in (
select ANIMAL_ID
from ANIMAL_OUTS
)
order by DATETIME
limit 3;

//https://school.programmers.co.kr/learn/courses/30/lessons/59045#fn1
//보호소에서 중성화한 동물
SELECT i.ANIMAL_ID, i.ANIMAL_TYPE, i.NAME
from ANIMAL_INS i inner join ANIMAL_OUTS o on i.ANIMAL_ID = o.ANIMAL_ID
where i.SEX_UPON_INTAKE like "Intact%" and (o.SEX_UPON_OUTCOME like "Spayed%" or o.SEX_UPON_OUTCOME like "Neutered%")
order by i.ANIMAL_ID

//https://school.programmers.co.kr/learn/courses/30/lessons/131533
//상품 별 오프라인 매출 구하기
SELECT a.PRODUCT_CODE , a.PRICE * b.AMOUNT as SALES
from PRODUCT a inner join (
select PRODUCT_ID, sum(SALES_AMOUNT) as AMOUNT
from OFFLINE_SALE
group by PRODUCT_ID
) b on a.PRODUCT_ID = b.PRODUCT_ID
order by SALES desc, a.PRODUCT_CODE ;