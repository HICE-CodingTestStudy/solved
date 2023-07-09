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