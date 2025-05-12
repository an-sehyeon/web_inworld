-- case when
select 	*
from 	movie
;


-- open_date가 2020년 이전이면 '고전'
--			   2024년이면 '최신'
--			   2024년 이후라면 '미래'
-- 로 구분하여 영화제목과 구분을 출력하시오.
select 	movie_name
	   	, case 	when	open_date <= '20201231' 	then '고전'
			  	when	open_date like '2024%'		then '최신'
				when	open_date >= '20250101' 	then '미래'
				else 	'기타' end as 'gubun' 
from movie
;


-- 구분의 종류와 해당 항목의 개수를 출력
select gubun, count(*)	-- gubun의 종류와 같은 gubun에 해당하는 수 출력  
from(
select 	movie_name
	   	, case 	when	open_date <= '20201231' 	then '고전'		-- open_date가 '20201231' 이전이라면 고전으로 설정
			  	when	open_date like '2024%'		then '최신'		-- open_date가 '2024'로 시작한다면 최신으로 설정 
				when	open_date >= '20250101' 	then '미래'		-- open_date가 '20250101' 이후라면 미래로 설정
				else 	'기타' end as 'gubun' 						-- 위의 조건에 해당하지 않는다면 기타로 설정,
from movie															-- 최종적으로 위 결과를 gubun(별칭)으로 설정
) as T					-- 서브쿼리의 별칭을 T로 설정
group by gubun			-- 같은 gubun을 그룹화
;


select 	movie_name													
		, case	when	rating <=	'12'	then '전체관람가'			-- rating이 12 이하라면 '전체관람가'
				when 	rating like	'15%'	then '청소년'				-- rating에 15 가 들어간다면 '청소년'		
				when 	rating >=	'18'	then '성인'				-- rating이 18 이상이라면 '성인'
				else 	'' end as 	'연령고지'						-- 모든 조건에 부합하지 않는다면 " "으로 설정하고 결과를 '연령고지'라고 설정			
from movie 															-- movie테이블에서 movie_name과 연령고지를 출력
;


-- with clause의 활용
select * from movie;

with old_movie as 	(					-- movie테이블에서 open_date가 '20200101'미만인 데이터들의 결과집합을 old_movie로 설정
	select *
	from 	movie 
	where open_date < '20200101'
), comedy_movie as	(					-- movie테이블에서 genre가 'comedy'인 데이터들의 결과집합을 comedy_movie로 설정
	select 	*
	from 	movie
	where 	genre = 'comedy'
), old_old_movie as	(					-- old_movie에서 open_date가 '19550101'미만인 데이터들의 결과집합을 old_old_movie로 설정
	select 	*
	from 	old_movie
	where 	open_date < '19550101'
)
select	 * 
from 	old_movie						-- old_movie 데이터 출력
union									-- 위의 집합과 아래 집합을 연결
select	 *
from 	comedy_movie					-- comedy_movie 데이터 출력
union									-- 위의 집합과 아래 집합을 연결
select 	 *
from 	old_old_movie					-- old_old_movie 데이터 출력
;


-- built-in function
-- 1. single-row function
-- 1) numeric
select abs(-88.99) from dual;			-- dual 대신 dummy를 쓰는 DB도 존재, 절대값
select ceil(-4.9), ceil(4.9) from dual;	-- 소수점 올림
select round(33.897, 2)					-- 소수점 반올림
	 , round(33.897, -2)
	 , round(33.897, -1)
from dual
;


-- 2) text
select lower('Maria DB'), upper('Heidi SQL') from dual;		-- 소문자, 대문자로 변경
select length('asldfjaakdsjfidsaclasdfjasd') from dual;		-- 문자 길이 
select length('한글은') from dual;							-- 한글의 길이

select substr('20231123013122', 1, 8) from dual;			-- 첫번째부터 8개 
select substr('20231123013122', 9, 6) from dual;			-- 9번째부터 6개
select substr('Lincoln Mistery',4,4)  from dual;			-- 4번째부터 4개
-- 두 번째 인자를 생략하면 끝까지
select substr('Lincoln Mistery',4) 	  from dual;			-- 4번째부터 끝까지
-- 음수일 경우 우측부터
select substr('Lincoln Mistery',-4) 	  from dual;		-- 뒤에서 4개



-- 3) to_date(oracle), str_to_date(mysql), to_char(공통으로 사용)
select str_to_date('2024/03-23', '%Y/%m-%d')
from   dual;


select	*
from	movie;


-- 반칙의제황의 개봉일이 100일 밀렸다
-- 반칙의제황의 개봉일을 +100일 수정하시오

-- 반칙의제황 개봉일을 날짜열로 변환
select	str_to_date(open_date, '%Y%m%d') as date1
-- 변환된 날짜의 100일 뒤
	,	date_add(str_to_date(open_date, '%Y%m%d'), interval 100 day) as date2
-- 100일 뒤의 날짜를 문자열로 변환
	, 	to_char(date_add(str_to_date(open_date, '%Y%m%d'), interval 100 day), 'yyyymmdd') as date3
from	movie 
where 	movie_name = '반칙의제황'
;

-- 위 결과값을 반칙의제황 open_date 업데이트
update	movie
set		open_date = to_char(date_add(str_to_date(open_date, '%Y%m%d'), interval 100 day), 'yyyymmdd')
where 	movie_name = '반칙의제황'
;

-- 반칙의제황의 개봉일이 100년 밀렸다
update	movie
set		open_date = to_char(date_add(str_to_date(open_date, '%Y%m%d'), interval 100 year), 'yyyymmdd')
where 	movie_name = '반칙의제황'
;

-- 모든 영화의 개봉일이 1일 밀렸다
update	movie
set		open_date = to_char(date_add(str_to_date(open_date, '%Y%m%d'), interval 1 day), 'yyyymmdd')
;