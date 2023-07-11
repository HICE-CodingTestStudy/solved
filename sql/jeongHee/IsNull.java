//https://school.programmers.co.kr/learn/courses/30/lessons/131114
//경기도에 위치한 식품창고 목록 출력하기
SELECT WAREHOUSE_ID, WAREHOUSE_NAME, ADDRESS, ifnull(FREEZER_YN,"N") as FREEZER_YN
from FOOD_WAREHOUSE
where ADDRESS like "경기%"
order by WAREHOUSE_ID;

//https://school.programmers.co.kr/learn/courses/30/lessons/59039
//이름이 없는 동물의 아이디
SELECT ANIMAL_ID
from ANIMAL_INS
where NAME is null
order by ANIMAL_ID;

//https://school.programmers.co.kr/learn/courses/30/lessons/59407
//이름이 있는 동물의 아이디
SELECT ANIMAL_ID
from ANIMAL_INS
where NAME is not null
order by ANIMAL_ID;

//https://school.programmers.co.kr/learn/courses/30/lessons/59410
//NULL 처리하기
SELECT ANIMAL_TYPE, ifnull(NAME,"No name"), SEX_UPON_INTAKE
from ANIMAL_INS
order by ANIMAL_ID;