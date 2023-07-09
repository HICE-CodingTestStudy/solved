//https://school.programmers.co.kr/learn/courses/30/lessons/144856
//저자 별 카테고리 별 매출액 집계하기
SELECT a.AUTHOR_ID, a.AUTHOR_NAME, b.CATEGORY, sum(s.SALES * b.PRICE) as TOTAL_SALES
from BOOK b inner join BOOK_SALES s on b.BOOK_ID=s.BOOK_ID inner join AUTHOR a on a.AUTHOR_ID = b.AUTHOR_ID
where s.SALES_DATE like "2022-01%"
group by a.AUTHOR_ID, a.AUTHOR_NAME, b.CATEGORY
order by a.AUTHOR_ID, b.CATEGORY desc;