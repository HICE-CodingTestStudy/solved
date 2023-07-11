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

//https://school.programmers.co.kr/learn/courses/30/lessons/164671
//조회수가 가장 많은 중고거래 게시판의 첨부파일 조회하기
SELECT concat("/home/grep/src/",BOARD_ID,"/",FILE_ID,FILE_NAME,FILE_EXT) as FILE_PATH
from USED_GOODS_FILE
where BOARD_ID = (
select BOARD_ID
from USED_GOODS_BOARD
order by VIEWS desc
limit 1
)
order by FILE_ID desc;

//https://school.programmers.co.kr/learn/courses/30/lessons/151138
//자동차 대여 기록에서 장기/단기 대여 구분하기
SELECT HISTORY_ID,CAR_ID, DATE_FORMAT(START_DATE, "%Y-%m-%d") as START_DATE,DATE_FORMAT(END_DATE, "%Y-%m-%d") as END_DATE,
case
when datediff(END_DATE,START_DATE)+1 >=30 then "장기 대여"
else "단기 대여"
end as RENT_TYPE
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where START_DATE like "2022-09%"
order by HISTORY_ID desc;

//https://school.programmers.co.kr/learn/courses/30/lessons/157343
//특정 옵션이 포함된 자동차 리스트 구하기
SELECT CAR_ID,CAR_TYPE,DAILY_FEE,OPTIONS
from CAR_RENTAL_COMPANY_CAR
where OPTIONS like "%네비%"
order by CAR_ID desc;