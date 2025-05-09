-- table creation
create table movie									-- movie라는 이름을 가진 테이블 생성
(
	movie_name	varchar(100),						-- 100 크기의 movie_name이라는 컬럼 생성
	open_date	varchar(8) default '99991231',		-- 8크기의 open_date 컬럼을 생성하고 아무것도 입력하지 않을 시 기본값을 99991231로 설정
	rating		int(2)								-- int형 데이터타입의 크기 2만큼의 rating이라는 컬럼 생성
);

select * from movie;								-- movie 테이블 조회

-- 장르(genre)를 추가
-- alter 명령어
alter table movie add column genre varchar(20);


-- 100세 관람가 => rating이 3으로 변경되어야 함
-- modify : 컬럼 속성 수정할때 사용하는 명령어
alter table movie modify column rating int(3);

select *
from hptl_mast
;