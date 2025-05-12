select * from movie;		-- movie 테이블 조회
truncate table movie;		-- 데이터삭제

desc movie;					-- desc : 테이블의 정보를 보여주는 명령어

-- insert
insert into movie values('파묘','20240222',12,'Horror');				-- movie 테이블에 ","기준으로 각 컬럼에 값 삽입
insert into movie values('오리엔트특급살인','20171129',12,'Drama');
insert into movie (movie_name, genre) values('반칙의제황','Comedy');	-- 모든 컬럼에 값을 넣는게 아닌 movie_name,genre 컬럼에만 값을 넣으려면

ALTER TABLE movie CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;		-- 한글을 insert할때 오류가 날때 utf8로 설정 변경 명령어
ALTER TABLE movie MODIFY movie_name VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- select 
select * 				-- 모든 컬럼의 값 출력
from movie 				-- movie 테이블에서
where 1=1;				-- 참이라면 출력


select movie_name		
	, open_date			
	, genre				
from movie; 			-- movie테이블에서 movie_name, open_date, genre 컬럼만 출력		


select *
from movie
limit 1					-- oracle인 경우 where rownum = 1
;						-- 최대 한개의 행만 출력, 결과 중 첫번째 행



-- where 절 연습
select *							-- 모든 컬럼 조회
from movie 							-- movie테이블에서
where open_date >= '20240101'		-- open_date 값이 '20240101'이상인 데이터만 조회
-- and open_date != '99991231'
-- and genre = 'Horror'
or genre = 'Drama'					-- 또는 genre이 'Drama'인 데이터 조회
;

-- update
update 	movie 						-- movie테이블의 데이터를 변경
set 	open_date = '20240914'		-- movie_name컬럼의 '반칙의 제황'데이터에
where 	movie_name  = '반칙의제황';	-- open_date 값을 '20240914'로 변경

select * from movie;				-- 변경한 후 테이블 조회 확인



update 	movie				
set 	rating = 10					-- rating이 12로 설정되어있는 데이터들 전부
where 	rating = 12					-- 10으로 변경
;


-- delete
insert into movie(movie_name, open_date)	-- movie테이블에 movie_name, open_date컬럼에
values ('베테랑2','20240913')					-- 값 삽입, 새로운 데이터 생성


-- genre값이 null인 컬럼 삭제 
delete from movie 
-- where genre = ''							-- null을 선택하지 못함
-- where genre = '[NULL]'					-- null을 선택하지 못함
-- where genre = null						-- 이것도 실패
where genre is null							-- 비어있는 값을 찾을 때는 is null
;

select * from movie 
where rating is not null					-- 비어있지 않은 값을 찾을 때는 is not null
;


-- Advanced SQL
insert into movie 								-- movie테이블에 새로운 데이터들 생성	
values('아바타3', '20241003', 12, 'Fantasy');
insert into movie
values('아바타4', '20261003', 12, 'Fantasy');
insert into movie
values('아바타5', '20281003', 19, 'Fantasy');
insert into movie
values('아바타6', '20301003', 0, 'Fantasy');


select *
from 	movie 
where 	rating between 12 and 19				-- rating이 12와 19 사이인 데이터들 조회
;


select * 
from 	movie
where 	open_date between '20240101' and '20280101'
;												-- 개봉일자가 2024.1.1부터 2028.1.1 사이인 데이터 조회


-- concat : 문자(열)을 붙이는 기술
-- Fantasy 장르의 영화 데이터를 '|' 구분자로 출력하시오
select concat(	movie_name,'|',
				open_date,'|',	
				rating,'|',
				genre) as 'reqdata'				-- as : 별칭
from movie 	
where genre = 'Fantasy'							-- genre가 'Fantasy'인 데이터들만 '|'구분자로 문자를 붙여서 출력							
;

-- concat 예시
select concat('한화의 돌아온', '에이스', '-류현진')	-- concat을 사용하면 문자을 붙여서 출력
from dual
;