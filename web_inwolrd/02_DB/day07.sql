-- Aggregate Function
-- count, distinct
select * from movie;
select count(*) from movie;				-- 결과값의 개수

-- 영화 이름이 아바타로 시작하는 영화 개수는?
select count(*)
from movie
where movie_name like '아바타%'			-- movie_name에서 아바타로 끝나는 영화의 개수 출력
;

-- rating, genre의 종류는 무엇들이 있는가?
select distinct rating, genre			-- 개별의 열을 기준으로 중복을 제거하는 것이 아니라 rating과 genre의 열을 함께 묶어서 보기 때문에 같은 열에 중복값O
from   movie
;

-- 장르의 종류 수는?
select count(distinct genre)			-- genre의 열에 중복을 제거한 후 값의 개수
from movie
;

-- 12세 이상 관람가인 영화의 장르의 개수는?
select count(distinct genre)			-- 중복을 제거한 장르의 개수
from movie
where rating >= 12						-- 조건절 : rating이 12이상일 때
;

-- 다양한 집계함수
select min(rating), min(movie_name)			-- min : 해당 열에서 최소값, movie_name에서 최소값은 제목의 사전순으로 가장 앞에 있는 이름
	 , round(avg(rating),1),sum(rating)		-- rating의 평균값을 구한 후 소수점 한자리까지 반환, rating의 총 합
from   movie
;

-- 다양한 SQL 연습
-- 1단계 : 데이터 준비
create table movie_n as					-- 데이터는 포함하지 않고 movie 테이블의 구조만 복사해서 movie_n이라는 새로운 테이블 생성
select *
from movie 
where 1 = 0								-- 조건이 1 = 0은 거짓이므로 아무 데이터도 반환하지 않음
;

select *
from movie_n
;


insert into movie_n values('무제01','19991201',12,'romance');
insert into movie_n values('무제02','19751001',19,'ani');
insert into movie_n values('무제03','20011201',15,'musical');
insert into movie_n values('무제04','19741201',12,'romance');
insert into movie_n values('무제05','20101201',15,'ani');
insert into movie_n values('무제06','20201201',00,'musical');
insert into movie_n values('무제07','20231201',12,'romance');
insert into movie_n values('무제08','20111201',15,'musical');
insert into movie_n values('무제09','20151201',19,'ani');
insert into movie_n values('무제10','20181201',00,'romance');


-- movie_n에 있는 데이터의 개수는?
select count(*)
from movie_n
;

-- genre가 모두 대문자가 아닌 데이터는 대문자로 업데이트 하시오.
update movie_n
set genre = upper(genre)					-- upper(genre) : genre열의 값을 대문자로 변환
-- where genre != upper(genre)				-- 이미 대문자로 되어있는 경우에는 업데이트X, 소문자나 혼합된 대소문자만 대문자로 변환
;


-- 연구대상
select genre, upper(genre)					-- movie_n 테이블에서 genre열의 값을 대문자로 변환
from   movie_n
where  genre = upper(genre) 				-- 대문자인 데이터만 조회
;



-- 개봉일자 순으로 오래된 것부터
select *
from movie
order by open_date							-- 오름차순
;


-- 개봉일자 순으로 최신 것부터
select *
from movie
order by open_date desc						-- 내림차순
;


-- 2000년 이후 영화는 '최신'
-- 이전 영화는 '고전'으로 표시되는 결과를 만드시오.
-- 영화제목, 개봉일, 최신구분
-- 개봉일 역순으로 배열하시오.

-- 방법1
select	movie_name, open_date, gubun					-- movie_name, open_date, gubun 열을 반환
from													
(
		select movie_name, open_date, '최신' as gubun	-- movie_n 테이블에서 open_date 값이 20000101 이상이라면?
		from movie_n									-- gubun컬럼에 '최신'으로 표시
		where open_date >= '20000101'
		union											-- 위의 쿼리와 아래의 쿼리 즉, 두 서브쿼리의 결과를 합침, 중복된 데이터(3개의 컬럼 모두 동일)가 있을 시 제거 
		select movie_name, open_date, '고전' as gubun	-- movie_n 테이블에서 open_date 값이 20000101 이하라면?
		from movie_n									-- gubun컬럼에 '고전'으로 표시
		where open_date < '20000101'
) as T1													-- 위 서브쿼리의 결과 집합을 T1으로 설정
order by open_date desc									-- open_date 기준으로 내림차순(최신 날짜)으로 정렬
;

-- 방법2
select movie_name										-- movie_name, open_date gubun 반환
	 , open_date
	 , case when open_date >= '20000101' then '최신'		-- open_date가 20000101 이상이라면 '최신'으로 표시
	 	 	else '고전' end as gubun						-- 아니라면 고전으로 gubun 컬럼에 표시
from   movie_n											-- movie_n 테이블에서
order by open_date desc 								-- open_date 기준으로 내림차순으로 정렬
;


-- rating 별로 영화 수 구하기
-- group by
select rating, count(*)									-- rating, count 컬럼 반환
from movie_n											-- movie_n 테이블에서 동일한 rating값을 가진 행들을 하나의 그룹으로 묶음
group by rating											-- 그룹화된 각 rating에 대해 count 함수를 사용하여 해당 rating 값을 가진 영화가 몇 개인지 계산
order by count(*) desc
;


-- rating별, 개봉연도별 영화 수 구하기
-- 개수가 많은 것부터, 개수가 같다면 개봉연도가 최신부터

select 	rating										-- rating, oy, 영화 수 컬럼 반환
	 ,	substr(open_date,1,4) as oy					-- open_date컬럼의 데이터를 첫번째부터 4번째까지만 출력, 별칭 oy로 설정
	 , 	count(*) as "영화 수"							-- 해당 열의 개수
from movie_n										-- movie_n 테이블에서 조회
-- group by rating, substr(open_date,1,4)
group by rating, oy									-- rating별 , oy별 그룹으로 나눔
-- group by 1, 2									-- 첫 번째 컬럼, 두 번째 컬럼을 의미하며 추전하지 않음
-- having count(*) > 1
order by count(*) desc, oy desc						-- 영화 수 가 많은 순에서 적은 순으로 내림차순 정렬하고, 개봉연도별 내림차순 정렬
;
