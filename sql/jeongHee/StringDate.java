//https://school.programmers.co.kr/learn/courses/30/lessons/157341
//대여 기록이 존재하는 자동차 리스트 구하기
SELECT distinct c.CAR_ID
from CAR_RENTAL_COMPANY_CAR c inner join CAR_RENTAL_COMPANY_RENTAL_HISTORY h on c.CAR_ID = h.CAR_ID
where c.CAR_TYPE = "세단" and h.START_DATE like "2022-10%"
order by CAR_ID desc;

//https://school.programmers.co.kr/learn/courses/30/lessons/157342
//자동차 평균 대여 기간 구하기
SELECT a.CAR_ID, round(a.RENT/a.CNT+1,1) as AVERAGE_DURATION
from(
SELECT CAR_ID, sum(DATEDIFF(END_DATE, START_DATE)) as RENT, count(*) as CNT
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by CAR_ID
) a
where round(a.RENT/a.CNT+1,1) >=7
order by AVERAGE_DURATION desc, a.CAR_ID desc;

//https://school.programmers.co.kr/learn/courses/30/lessons/164672
//조건에 부합하는 중고거래 상태 조회하기
SELECT BOARD_ID,WRITER_ID,TITLE,PRICE,
case STATUS
when "DONE" then "거래완료"
when "SALE" then "판매중"
else "예약중"
end as STATUS
from USED_GOODS_BOARD
where CREATED_DATE = "2022-10-05"
order by BOARD_ID desc;