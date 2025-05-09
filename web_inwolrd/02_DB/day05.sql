select * from movie;									-- movie테이블 조회

-- not null
create table movie2										-- movie2 테이블 생성
(
	movie_name	varchar(100) not null,					-- movie_name컬럼에 null 허용X
	open_date 	varchar(8)	default '99991231'			-- open_date 컬럼생성, 값 설정하지않으면 기본값을 '99991231'로 설정
);

insert into movie2(open_date) values('20240919');		-- movie_name(not null)이 없어서 오류 

insert into movie2(open_date, movie_name)				-- 데이터 삽입
values('20240319', '아바타4');

-- default
insert into movie2(movie_name) values('베테랑2');			-- 데이터 삽입, open_date 값을 넣지 않았기 때문에 기본값으로 설정

select * from movie2;									-- movie2테이블 조회





-- Primary Key(중복X)
alter table movie2
add constraint movie2_pk primary key(movie_name);
-- PK를 포함한 인덱스는 조회 성능을 좋게 한다.
-- 대신에 데이터 삽입, 수정, 삭제는 성능이 저하된다.
-- 오늘 밤에 데이터를 10억건을 넣어야한다?
-- 매 row마다 인덱스 추가 + PK(Constraint) 계약조건을 위반하는지 안하는지 검사
-- 인덱스 제거 -> 데이터 적재 -> 인덱스 재구성(rebuild)

insert into movie2(movie_name) values('아바타4');
-- movie_name을 primary key로 지정했기 때문에 이미 존재하는 중복 데이터 삽입 불가능

-- insert into select(특수기술), 타입이 다르면 안됨
insert into movie2 
select movie_name, open_date from movie
where movie_name <> '아바타4';

select * from movie2;


-- insert into movie2 
-- select movie_name, open_date from movie
-- where movie_name not in ('아바타4', 'abata4');		-- movie_name컬럼에서 제외할 데이터값이 2개이상일때, not in : and, in : or



-- CTAS
-- create table as select

-- 1) 아바타4 데이터만 가지고 테이블을 생성하고 싶음
-- 		movie3 생성
create table movie3 as								
select * from movie2 
where movie_name = '아바타4';

select * from movie3;



-- 2) movie2의 컬럼만 가지고 테이블을 만들고 싶다.
-- 데이터는 필요없다.
create table movie4 as
select 	* 
from 	movie2 
where 1 = 0
;

select * from movie4;


-- ----------------------------------------

-- as 예시
select 	avg(m.r) as "평균관람가연령"					-- 평균 값을 가지고 있는 컬럼명을 "평균관람가연령"으로 설정
	,	count(*) as "rating이 있는 총영화건수"			-- 연령제한이 있는 영화건수를 나타낼 컬럼명을 "rating이 있는 총영화건수"로 설정
from	(
			select 	movie_name 	as mn				-- movie테이블에서 movie_name을 가져와서 mn으로 설정 
				,	rating 		as r				-- rating을 가져와서 r으로 설정
			from	movie 
			where 	rating is not null				-- rating이 null이 아닌 경우만 가져옴
		)	as m									-- 위의 서브쿼리를 m으로 설정
;