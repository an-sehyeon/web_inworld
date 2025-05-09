-- catesian product를 의도적으로 사용하기

with ye as 												-- with절을 이용해 ye라는 임시 테이블을 생성해 '2022','2023','2024'라는 세 가지 년도를 생성
(
	select '2022' as yyyy from dual
	union all
	select '2023' as yyyy from dual
	union all
	select '2024' as yyyy from dual
), mon as												-- '01'에서 '12'개의 월의 데이터를 가지고 있는 mon라는 임시테이블 생성
(
	select '01' as mm from dual union all
	select '02' from dual union all
	select '03' from dual union all
	select '04' from dual union all
	select '05' from dual union all
	select '06' from dual union all
	select '07' from dual union all
	select '08' from dual union all
	select '09' from dual union all
	select '10' from dual union all
	select '11' from dual union all
	select '12' from dual
), yyyymm as 											-- 년도와 월을 조합하여 'yyyymm' 형식으로 만든 yyyymm이라는 임시테이블 생성
(
	select 	concat(ye.yyyy, mon.mm) as yyyymm			-- 년도와 월을 결합하여 yyyymm 형식 생성
	from 	ye, mon										-- 카테시안 곱 : ye와 mon의 모든 조합 생성 
	order by yyyymm										-- 결과를 yyyymm 오름차순으로 정렬
)
select 	m.* 											-- movie 테이블에서 open_date 값이 'yyyymm' 조합과 일치하는 영화 조회
from 	movie m
   , 	yyyymm y
where 	substr(m.open_date, 1, 6) = y.yyyymm			-- open_date의 첫번째부터 6번째까지 값이 yyyymm의 조합 중 일치하는 데이터 필터링
;



-- 정규화 3단계를 통해 테이블이 분할되었다.
-- 강원도에 있는 상급종합의 병원명, 시군구명, 주소를 구하시오.
-- bak 테이블은 이용하지 마시오.

select	h.hptl_nm, g.siggu_cd_nm, h.addr			-- hptl_mast테이블의 hptl_nm, siggu_cd_dtl테이블의 siggu_cd_nm, hptl_mast테이블의 addr 데이터 조회
from	hptl_mast h									-- 
	,	sido_cd_dtl s								-- 
	,	typ_cd_dtl t								-- 
	,	siggu_cd_dtl g								-- 
where 	s.sido_cd_nm = '강원'						-- sido_cd_dtl테이블의 sido_cd_nm이 '강원'인 데이터
and 	h.sido_cd = s.sido_cd						-- hptl_mast테이블의 sido_cd의 값과 sido_cd_dtl테이블의 sido_cd의 값이 같고, 
and 	t.typ_cd_nm = '상급종합'						-- typ_cd_dtl테이블의 typ_cd_nm이 '상급종합'인 데이터
and 	h.typ_cd = t.typ_cd							-- hptl_mast테이블의 typ_cd의 값과 typ_cd_dtl테이블의 typ_cd의 값이 같고,
and 	g.siggu_cd = h.siggu_cd 					-- siggu_cd_dtl테이블의 siggu_cd의 값과 hptl_mast테이블의 siggu_cd의 값이 같은, 위의 모든 조건에 일치한다면 
;




























