//https://school.programmers.co.kr/learn/courses/30/lessons/131114
//경기도에 위치한 식품창고 목록 출력하기
SELECT WAREHOUSE_ID, WAREHOUSE_NAME, ADDRESS, ifnull(FREEZER_YN,"N") as FREEZER_YN
from FOOD_WAREHOUSE
where ADDRESS like "경기%"
order by WAREHOUSE_ID;