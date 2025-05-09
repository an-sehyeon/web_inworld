-- Subquery
select 	bh.hptl_nm										-- 서브쿼리에서 설정한 bh에서 병원이름 출력
	 ,	s.siggu_cd_nm									-- siggu_cd_dtl테이블(s)에서 siggu_cd_nm 출력
from 	(
			select 	hptl_nm, siggu_cd					-- hptl_mast테이블에서 doc_num이 1000 초과인 데이터들의
			from 	hptl_mast							-- hptl_nm, siggu_cd 컬럼 조회
			where 	doc_num > 1000
		) as bh											-- big hospital, 위 서브쿼리의 결과집합을 bh로 설정
	 ,  siggu_cd_dtl s									-- siggu_cd_dtl 테이블을 s로 설정
where bh.siggu_cd = s.siggu_cd							-- hptl_mast테이블의 siggu_cd와 siggu_cd_dtl테이블의 siggu_cd 코드가 같다면 조회
;


-- with절 활용
with bh as (											-- hptl_mast테이블에서 hptl_nm, siggu_cd, addr, sido_cd, typ_cd
	select 	hptl_nm, siggu_cd, addr, sido_cd, typ_cd	-- 조회 결과를 담은 bh 임시 테이블 생성
	from 	hptl_mast
			)
select 	bh.hptl_nm										-- hh의 hptl_nm 조회
	 , 	bh.sido_cd										-- bh의 sido_cd 조회	
	 , 	s.siggu_cd_nm									-- s의 siggu_cd_nm 조회	
	 , 	bh.addr											-- bh의 addr 조회
from	bh
	 ,	siggu_cd_dtl s									-- siggu_cd_dtl 테이블을 s로 설정
	 ,	typ_cd_dtl t									-- typ_cd_dtl 테이블을 t로 설정
	 ,	sido_cd_dtl c									-- sido_cd_dtl 테이블을 c로 설정
where 	bh.siggu_cd = s.siggu_cd						-- bh의 siggu_cd와 s의 siggu_cd의 데이터가 동일하고,
and 	t.typ_cd = bh.typ_cd							-- t의 typ_cd와 bh의 typ_cd 데이터가 동일하고,
and 	c.sido_cd = bh.sido_cd							-- c의 sido_cd와 bh의 sido_cd 데이터가 동일하고,
and 	c.sido_cd_nm = '강원'							-- c의 sido_cd_nm 데이터의 값이 '강원'이고,
and 	t.typ_cd_nm = '상급종합'							-- t의 typ_cd_nm 데이터의 값이 '상급종합'인 데이터만 출력
;


-- Scalar Subquery
-- scalar : 값 1개
-- Scalar Subquery는 인풋에 대한 결과가 반드시 1개가 나오는 쿼리로
-- 인풋과 아웃풋 쌍을 일부 저장하고 있다. (caching)
-- select 절에 나타나는 subquery를 일컫는 용어
with bh as(												-- hptl_mast 테이블의 hptl_nm, siggu_cd, addr, sido_cd, typ_cd
	 select hptl_nm, siggu_cd, addr, sido_cd, typ_cd	-- 조회 되는 as 임시테이블 생성
	 from	hptl_mast
		  )
select	bh.hptl_nm										
	 , 	bh.sido_cd
-- 	 ,	s.siggu_cd_nm
	 ,	(select siggu_cd_nm from siggu_cd_dtl			-- siggu_cd_dtl 테이블에서 siggu_cd와 hptl_mast테이블의 siggu_cd가 같은
	 	 where siggu_cd = bh.siggu_cd) as siggu_cd_nm	-- siggu_cd_nm 데이터를 가진 결과 집합을 siggu_cd_nm으로 설정하고 조회
	 ,	bh.addr
from	bh
-- 	 ,	siggu_cd_dtl s
	 ,	typ_cd_dtl t
	 ,	sido_cd_dtl c
where 	t.typ_cd = bh.typ_cd
and 	c.sido_cd = bh.sido_cd
and 	c.sido_cd_nm = '강원'
and 	t.typ_cd_nm = '상급종합'
;


-- exists + in
-- 데이터 준비
-- 의사 수가 0명인 병원을 blacklist라고 생성한다.
create table blacklist as						-- blacklist 테이블 생성
select 	hptl_nm, sido_cd, siggu_cd				-- hptl_mast테이블에서 
from	hptl_mast								-- doc_num 값이 0인 데이터 중
where 	doc_num = 0								-- hptl_nm, sido_cd, siggu_cd 데이터 가져옴
;


select 	*
from 	blacklist
;


-- 서울에 있는 병원 중 blacklist에 존재하는 병원을 찾는 쿼리
-- 방법1)
select 	h.hptl_nm						-- hptl_nm이 hptl_mast에도 있고 blacklist에도 있기 때문에 명확하게 하기 위해서 h.hptl_nm이라고 해줌
	 ,	addr
	 ,	hptl_url
from	hptl_mast h
	 ,	blacklist b
where	h.hptl_nm = b.hptl_nm			-- hptl_nm 값이 hptl_mast테이블에도 있고, blacklist테이블에도 있고,
and 	h.siggu_cd = b.siggu_cd			-- h의 siggu_cd 데이터와 b의 siggu_cd 데이터가 동일하고,
and 	h.sido_cd = '110000' 			-- h의 sido_cd 값이 '110000'인 데이터 조회
;

-- 방법2) exists
select 	hptl_nm
	 ,	addr
	 ,	hptl_url
from	hptl_mast h
where 	sido_cd = '110000'
and 	exists (select  1
				from	blacklist
				where 	hptl_nm = h.hptl_nm
				and 	siggu_cd = h.siggu_cd)
;				


-- 방법3) in
select 	hptl_nm
	 ,	addr
	 ,	hptl_url
from	hptl_mast h
where 	sido_cd = '110000'
and 	(hptl_nm, siggu_cd) in (select hptl_nm, siggu_cd from blacklist)
;










