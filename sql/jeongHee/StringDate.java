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

//https://school.programmers.co.kr/learn/courses/30/lessons/132204
//취소되지 않은 진료 예약 조회하기
SELECT a.APNT_NO,p.PT_NAME,p.PT_NO,d.MCDP_CD,d.DR_NAME,a.APNT_YMD
from APPOINTMENT a inner join PATIENT p on a.PT_NO = p.PT_NO
inner join DOCTOR d on a.MDDR_ID = d.DR_ID
where a.MCDP_CD = "CS" and a.APNT_YMD like "2022-04-13%" and a.APNT_CNCL_YMD is null
order by a.APNT_YMD

//https://school.programmers.co.kr/learn/courses/30/lessons/131113
//조건별로 분류하여 주문상태 출력하기
SELECT ORDER_ID,PRODUCT_ID, DATE_FORMAT(OUT_DATE, "%Y-%m-%d") as OUT_DATE,
case
when OUT_DATE <= "2022-05-01" then "출고완료"
when OUT_DATE > "2022-05-01" then "출고대기"
else "출고미정"
end as 출고여부
from FOOD_ORDER
order by ORDER_ID;