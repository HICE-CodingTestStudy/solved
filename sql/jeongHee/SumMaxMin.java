//https://school.programmers.co.kr/learn/courses/30/lessons/131115
//가격이 제일 비싼 식품의 정보 출력하기
SELECT *
from FOOD_PRODUCT
order by PRICE desc
limit 1;

//https://school.programmers.co.kr/learn/courses/30/lessons/59415#
//최댓값 구하기
SELECT DATETIME as 시간
from ANIMAL_INS
order by DATETIME desc
limit 1;

//https://school.programmers.co.kr/learn/courses/30/lessons/131697
//가장 비싼 상품 구하기
SELECT max(PRICE) as MAX_PRICE
from PRODUCT;