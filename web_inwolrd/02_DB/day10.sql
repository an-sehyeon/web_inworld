-- 정규화 연습
-- hptl_mast
-- htpl_nm이 PK라고 가정(실제로는 아님)
-- PK가 단일컬럼이라면 진부분 집합이 없다
-- --> 2차 정규화는 불필요
-- 3차 정규화 대상 => typ_cd로 결정되는 typ_cd_nm
-- 3차 정규화 대상 => sido_cd로 결정되는 sido_cd_nm
-- 3차 정규화 대상 => siggu_cd로 결정되는 siggu_cd_nm

-- 사전 테스트

-- typ_cd의 종류와 그에 따른 typ_cd_nm을 모두 출력하시오
-- 방법1)
select distinct typ_cd, typ_cd_nm					-- distinct : 중복된 값을 제거하고 유일한 값만 반환
from hptl_mast
;

-- 방법2)
select 	 typ_cd, typ_cd_nm
from 	 hptl_mast
group by typ_cd, typ_cd_nm 
;


-- typ_cd의 종류 개수를 구하시오.
-- 방법1)
select 	count(*) as "개수"
from(	select   typ_cd, typ_cd_nm					-- hptl_mast테이블에 typ_cd,typ_cd_nm을 그룹화 한 결과 집합을 T1으로 설정 후
		from	 hptl_mast 							-- T1의 개수를 출력
		group by typ_cd, typ_cd_nm
) as T1
;

-- 방법2)
select count(distinct typ_cd, typ_cd_nm) as "개수"	-- typ_cd와 typ_cd_nm 컬럼의 중복된 값을 제거 후 유일한 값의 개수 출력
from hptl_mast 
;



-- typ_cd와 typ_cd_nm을 보관하는 테이블을 만드시오
-- 테이블명 : typ_cd_dtl
create table 	typ_cd_dtl 								-- 새로운 테이블 생성
select distinct typ_cd
			  , typ_cd_nm								-- 중복된 행을 제거하고 고유한 값들만 typ_cd_dtl테이블에 저장
from 			hptl_mast
;


create table 	siggu_cd_dtl as 						-- siggu_cd_dtl 테이블 생성
select distinct siggu_cd, siggu_cd_nm					-- hptl_mast테이블의 siggu_cd, siggu_cd_nm 데이터를 새로운 테이블에 저장
from 			hptl_mast 
;

create table sido_cd_dtl as 							-- sido_cd_dtl 테이블 생성
select distinct sido_cd, sido_cd_nm						-- hptl_mast테이블의 sido_cd, sido_cd_nm 데이터를 새로운 테이블에 저장
from hptl_mast 
;


select 	*												-- typ_cd_dtl, siggu_cd_dtl, sido_cd_dtl 테이블의 데이터값 출력
from 	typ_cd_dtl
   ,	siggu_cd_dtl
   ,	sido_cd_dtl
;

-- hptl_mast를 hptl_mast_bak에 복제
-- 전체 백업은 실제로는 export를 이용하는 경우가 많음
create table hptl_mast_bak
select * from hptl_mast
;

-- 3개 nm필드(컬럼) drop
alter table hptl_mast drop column typ_cd_nm;			-- typ_cd_nm 컬럼 삭제
alter table hptl_mast drop column sido_cd_nm;			-- sido_cd_nm 컬럼 삭제
alter table hptl_mast drop column siggu_cd_nm;			-- siggu_cd_nm 컬럼 삭제

select * from hptl_mast;



-- Analytic Function
select  hptl_nm																				-- 병원이름 조회
	 ,	doc_num																				-- 의사 수 조회
	 , 	rank() over(order by doc_num desc) as "전체등수"										-- "전체등수"라는 컬럼 설정 후 의사 수를 기준으로 내림차순으로 순위 조회			
	 ,  rank() over(partition by sido_cd													-- sido_cd코드로 구분해서 그 내에서 등수, "지역내등수"로 설정
	 				order by doc_num desc) as "지역내등수"
	 ,  avg(doc_num)  over(order by doc_num desc											-- 해당 행과 이전/다음 행의 의사 수 평균을 "3평균"(별칭)으로 설정
	 				 	   rows between 1 preceding
	 				 	   		and		1 following
	 				 	   ) as "3평균"
	 , 	sum(doc_num)  over(order by doc_num desc			 								-- 현재 행까지의 의사 수 누적합
	 					  rows between unbounded preceding
	 					  and current row) as "의사수누적 합"
	 , 	lead(doc_num) over(order by doc_num desc) as lead_num								-- 다음 행의 의사 수
	 ,	lag(doc_num)  over(order by doc_num desc) as lag_num								-- 이전 행의 의사 수
	 ,	lag(doc_num)  over(order by doc_num desc) - doc_num as "내 앞순위와의 의사수 차이"		-- 이전 행과의 의사 수 차이
	 ,	lag(doc_num, 2)  over(order by doc_num desc) - doc_num as "내 앞앞순위와의 의사수 차이"	-- 두 개 전 행과의 의사 수 차이
from hptl_mast
;
	 
