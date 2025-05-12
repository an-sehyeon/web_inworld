CREATE TABLE test.disney_plus_titles (			-- disney_plus_titles 테이블 생성
	show_id VARCHAR(50) NULL,					-- null 허용
	typ VARCHAR(50) NULL,
	title VARCHAR(200) NULL,
	director VARCHAR(100) NULL,
	casting VARCHAR(1000) NULL,
	country VARCHAR(500) NULL,
	date_added VARCHAR(50) NULL,
	release_year VARCHAR(4) NULL,
	rating VARCHAR(50) NULL,
	duration VARCHAR(50) NULL,
	listed_in VARCHAR(50) NULL,
	description VARCHAR(128) NULL
);



select 	count(*)
from 	disney_plus_titles dpt					-- disney_plus_titles테이블의 데이터 개수 확인
;



select 	*
from 	disney_plus_titles dpt					
order by length(country) desc					-- country 컬럼 값 글자 길이 수 기준 내림차순으로 정렬
;



create table netflix_titles as					-- netflix_titles 테이블 생성
select 	*
from 	disney_plus_titles 						-- disney_plus_titles 테이블의 데이터는 제외하고, 
where 	1 = 0									-- 테이블의 구조만 복사	
;



create table amazon_prime_titles as				-- amazon_prime_titles 테이블 생성
select 	*
from 	disney_plus_titles						-- disney_plus_titles 테이블의 데이터는 제외하고, 
where 	1 = 0									-- 테이블의 구조만 복사
;



alter table amazon_prime_titles modify column description varchar(2000);		-- amazon_prime_titles의 컬럼 타입 변경
alter table amazon_prime_titles modify column casting varchar(2000);
alter table amazon_prime_titles modify column listed_in varchar(100);
alter table amazon_prime_titles modify column director varchar(2000);
alter table netflix_titles modify column description varchar(2000);				-- netflix_titles의 컬럼 타입 변경
alter table netflix_titles modify column listed_in varchar(1000);
alter table netflix_titles modify column casting varchar(2000);
alter table netflix_titles modify column director varchar(2000);



-- 각 방송사의 프로그램 개수를 구하시오.
select 	'netflix' as studio, count(*) cnt				-- netflix_titles 테이블에서 'netflix'를 studio 열로 조회하고 데이터 개수 조회
from 	netflix_titles
union all
select 	'amazon prime' as studio, count(*) cnt			-- amazon_prime_titles 테이블에서 'amazon prime'을 studio 열로 조회하고 데이터 개수 조회
from 	amazon_prime_titles
union all
select 	'disney' as studio, count(*) cnt				-- disney_plus_titles 테이블에서 'disney'를 studio 열로 조회하고 데이터 개수 조회
from 	disney_plus_titles



-- 각 스튜디오 별로 타입별(TV Show, Movie 등등) 개수를 구하시오
with studio_title as (									-- studio_title 임시테이블 생성
		select 	'netflix' as studio, a.*				-- netflix_titles 테이블에서 'netflix'를 studio 열로 조회하고 모든 데이터 조회
		from 	netflix_titles a
		union all
		select 	'amazon' as studio, b.*					-- amazon_prime_titles 테이블에서 'amazon'을 studio 열로 조회하고 모든 데이터 조회
		from 	amazon_prime_titles b
		union all
		select 	'disney' as studio, c.*					-- disney_plus_titles 테이블에서 'disney'를 studio 열로 조회하고 모든 데이터 조회
		from 	disney_plus_titles c 
)
select	studio, typ, count(*)							-- studio_title 테이블에서 studio, typ, 해당 그룹의 데이터 개수 조회
from 	studio_title
group by studio, typ									-- studio, typ 별로 그룹화, 각 그룹의 개수를 구함
;



-- title이 같은 프로그램이 있는지 조사하시오

with studio_titles as ( 						-- studio_titles 임시테이블 생성
	select 'netflix' as studio, a.*				-- netflix_titles의 모든 데이터를 조회하며, 'netflix'를 studio로 설정
	from   netflix_titles a
	union all
	select 'amazon prime' as studio, b.*		-- amazon_prime_titles의 모든 데이터를 조회하며, 'amazon_prime'을 studio로 설정
	from   amazon_prime_titles b
	union all
	select 'disney' as studio, c.*				-- disney_plus_titles의 모든 데이터를 조회하며, 'disney'를 studio로 설정
	from   disney_plus_titles c
)
select title, count(*)							-- studio_titles 테이블에서 title과 
from   studio_titles							-- title을 그룹화하여 개수를 출력하는데				
group by title									-- 그 개수가 2이상인 title과 count만 출력
having count(*) > 1								-- having : 그룹화 후 그룹에 조건을 적용
;



-- 각 스튜디오 별로 가장 많이 제작한 팀과 제작 개수를 구하시오(null 제외)
with studio_name as (							-- studio_name 임시테이블 생성
		select 	'netflix' as name,a.*			-- netflix_titles의 모든 데이터를 조회, netflix를 name 컬럼으로 설정
		from 	netflix_titles a				
		union all								
		select 	'disney' as name,b.*			-- disney_plus_titles의 모든 데이터를 조회하며, 'disney'을 name 컬럼으로 설정
		from 	disney_plus_titles b			
		union all
		select 	'amazon' as name,c.*			-- amazon_prime_titles의 모든 데이터를 조회하며, 'amazon_prime'을 name 컬럼으로 설정
		from 	amazon_prime_titles c			
), stat as (									-- stat 임시 테이블 생성
		select 	name, director, count(*)		-- studio_name 테이블의 name, director, 해당 director 건수 조회
		-- ↓ name별로 그룹화를 하고 제작 건수 기준으로 내림차순으로 순위 매긴 후 rnk로 설정 
			 ,	rank() over(partition by name order by count(*) desc) rnk
		from 	studio_name						
		where 	director <> ' '					-- director이 null이 아닌 데이터만 조회
		group  by name, director				-- name별로 그룹화를 하고, director별로 그룹화
		order by name, count(*) desc			-- name별로 내림차순으로 정렬 후, 개수별로 내림차순으로 정렬
)
select *
from stat
where rnk <= 3									-- rnk 값이 3이하인 데이터만 조회
;







