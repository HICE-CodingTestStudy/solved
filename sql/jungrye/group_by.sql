-- 자동차 대여 기록에서 대여중 / 대여 가능 여부 구분하기
-- max를 사용하면 모든 row 중 하나라도 조건을 충족하면 1(True)를 반환하지만, max를 사용하지 않으면 임의의 1개 row에 대해서만 결과를 반환
SELECT CAR_ID,
    MAX(CASE 
        WHEN "2022-10-16" BETWEEN START_DATE AND END_DATE
            THEN "대여중" 
        ELSE "대여 가능" 
        END) AS AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY CAR_ID 
ORDER BY CAR_ID DESC;


-- 대여 횟수가 많은 자동차들의 월별 대여 횟수 구하기
-- 헷갈림
SELECT MONTH(START_DATE) AS MONTH, CAR_ID, COUNT(*) AS RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY 
WHERE CAR_ID IN (
        SELECT CAR_ID 
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE START_DATE >= "2022-08-01" AND START_DATE < "2022-11-01" 
        GROUP BY CAR_ID HAVING COUNT(HISTORY_ID) >= 5
            AND START_DATE >= "2022-08-01" AND START_DATE < "2022-11-01" )
GROUP BY MONTH, CAR_ID
ORDER BY MONTH, CAR_ID DESC


-- 입양 시각 구하기(2)
-- 못풀었는데 대충 WITH RECURSIVE 이용해야된다는건 봄