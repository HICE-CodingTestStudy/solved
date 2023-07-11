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
