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