-- 2000년 이전 작품은 고전, 2000이후 작품은 최신으로 분류하고
-- 고전 최신이 각각 몇 개인지 구하기
select 	*
from 	movie_n
;

select case when open_date < '20000101' then '고전'		-- open_date가 2000년 이전 이라면 '고전'이라고 하고
			else '최신' end as gubun						-- 아니라면 '최신'이라고 하고 gubun컬럼을 생성해서 넣는다
	 , 	count(*)										-- 고전과 구분에 몇개의 데이터가 있는지 카운터하고
from 	movie_n											-- movie_n 테이블에서 조회
group by gubun											-- gubun 컬럼을 기준으로 데이터를 그룹화 한다
;



-- Challenge(Difficulty : GOD)
-- 영화통계를 구하되
-- 2000년 이전, 2000년대, 2010년대, 2020년대의 건수를 구하시오.
-- 가로로 출력되도록 작성하시오.
-- 2000년 이전	2000년대		2010년대		2020년대
-- 			1		 10			  7			  2

-- 기존 방식
select case when open_date < 		'20000101' 				  then '2000년 이전'		-- open_date가 200000101 미만이라면 '2000년 이전'이라고 하고
			when open_date between 	'20000101' and '20091231' then '2000년대'		-- open_date가 200000101과 20091231 사이라면 '2000년대'라고 하고
			when open_date between 	'20100101' and '20191231' then '2010년대'		-- open_date가 20100101과 20191231 사이라면 '2010년대'라고 하고
			else '2020년대' end as gubun												-- 위의 조건이 맞지않는것들은 '2020년대'라고 하고 결과 집합을 gubun이라고 설정	
		  ,	count(*)
from movie_n
group by gubun			
;

-- 가로로 출력
select 	sum(case when open_date < '20000101' then 1 else 0 end) as '2000년 이전'						
	 ,	sum(case when open_date between '20000101' and '20091231' then 1 else 0 end) as '2000년대'
	 ,	sum(case when open_date between '20100101' and '20191231' then 1 else 0 end) as '2010년대'
	 ,	sum(case when open_date >= '20200101' then 1 else 0 end) as '2020년대'
from movie_n 
;


-- 병원 데이터 실습
-- 1. data load 확인
select *
from hptl_mast
limit 10
;


-- 2. 건수 세어보기
select count(*)
from hptl_mast
;						-- 77,139건


-- 3. select test
-- 병원 이름이 서울 로 시작하는 병원의 비율
select sum (case when hptl_nm like '서울%'
				 then 1 else 0 end) / count(*) * 100 		-- 서울로 시작하는 병원의 개수를 구한 뒤 전체 병원 수를 나눈 후 곱하기 100
	   as '서울로 시작하는 병원 비율'
from 	hptl_mast
;


select sum (case when hptl_nm like '%대학%'
				 then 1 else 0 end) / count(*) * 100 
	   as '대학이 들어가는 병원 비율'
from 	hptl_mast
;


-- 4. secu_key_cd 컬럼 삭제
alter table hptl_mast drop column secu_key_cd;

-- 5. 실습과제 1
-- 경기에 있는 병원 10곳의 병원이름과 전화번호 맨 뒷자리 4자리 가져오기
select 	hptl_nm 
	 ,	tel_no 
	 ,	right(tel_no, 4) as '전화번호 뒷자리'				-- 병원이름과, 전화번호 오른쪽에서 4자리 출력
from 	hptl_mast 
where 	addr like '%경기도%'								-- 위 컬럼 출력의 기준은 addr 컬럼에서 경기도가 들어가는 데이터만 추출
;














