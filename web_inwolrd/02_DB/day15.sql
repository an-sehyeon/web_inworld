-- Sequence
-- nocache 	: 시퀀스 값을 미리 캐싱하지 않고 요청 시마다 디스크에서 하나씩 값을 생성
-- 			 값 유실의 위험은 없지만, 매번 디스크에서 읽어와야 하므로 성능이 약간 낮을 수 있음
-- cache	: 시퀀스 값을 미리 캐싱하여 데이터베이스가 시퀀스 값을 따르게 할당 가능
-- 			 시퀀스 값을 반복해서 요청하는 경우 성능을 향상시킬 수 있지만, 
-- 			 데이터베이스가 비정상 종료되면 캐싱된 값이 유실될 수 있음

-- 성능이 중요한 경우 -> cache를 사용하는 것이 유리, 시퀀스를 대량으로 할당해야 하는 상황에서 캐싱을 통해 성능 향상
-- 값 유실 방지가 중요한 경우 -> nocache가 적합

create sequence seq_test;				-- seq_test 이름의 sequence 생성
create sequence seq_test2 nocache;		-- seq_test2 이름의 sequence 생성


select 	nextval(seq_test)		-- nextval 함수를 사용하여 seq_test 사용
from 	dual;					-- 1씩 증가하는 sequence


drop view hptl_mast_v;			-- hptl_mast_v view 삭제

create view hptl_mast_v as		-- hptl_mast_v 이름을 가진 view 생성
select 	hptl_nm, typ_cd			-- hptl_mast 테이블에서 hptl_nm, typ_cd 컬럼값만 가져옴
from 	hptl_mast
where 	typ_cd = '1'			-- typ_cd 값이 '1'인 데이터만 가져옴
;

select 	*						-- hptl_mast_v테이블의 데이터를 상위 3개만 조회
from 	hptl_mast_v
limit	3
;

-- 사용자의 편의성을 위해 타입코드명을 보여주기로 한다.
create view hptl_mast_v2 as		-- hptl_mast_v2 이름의 테이블 생성
select	hptl_nm, typ_cd_nm		-- hptl_nm, typ_cd_nm 컬럼의 데이터를 가져옴
from	hptl_mast m				-- hptl_mast 테이블을 m으로 설정
	,	typ_cd_dtl t			-- typ_cd_dtl 테이블을 t로 설정
where 	m.typ_cd = t.typ_cd		-- m의 typ_cd와 t의 typ_cd값이 같은 데이터만 조회
;

select	*						-- hptl_mast_v2테이블에서
from 	hptl_mast_v2			-- hptl_nm이 '강북삼성병원'인 데이터 조회
where 	hptl_nm = '강북삼성병원'
;


-- 10. 승률 1위팀의 팀명과 승률을 구하시오.(무승부는 제외)
with full_sche as (														-- full_sche 임시테이블 생성
	select sche_date													-- schedule테이블의 sche_date(경기 일자) 데이터 조회
	     , case when home_score > away_score then hometeam_id			-- home팀이 이겼다면 hometeam_id를	
	            else awayteam_id end as wt								-- 아니라면 away team_id를 wt(이긴팀)에 저장		
	     , case when home_score < away_score then hometeam_id			-- home팀이 졌다면 hometeam_id를
	            else awayteam_id end as lt								-- 아니라면 away team_id를 lt(진팀)에 저장 									
	from   schedule
	where  home_score <> away_score										-- 득점이 같지않은, 즉 승패가 갈린 경기만 조회
), wstat as (															-- 이긴 팀(wt)의 승리 횟수를 구하는 서브쿼리
		select wt, count(*) cnt											-- 이긴 팀, 승리 횟수	
		from   full_sche															
		group by wt														-- wt를 기준으로 그룹화	
), lstat as (															-- 진 팀(lt)의 패배 횟수를 구하는 서브쿼리
		select lt, count(*) cnt											-- 진 팀, 패배 횟수				
		from   full_sche															
		group by lt															
), base as (   															-- base : 전승팀 또는 전패팀이 있을 수 있어 모든 팀의 리스트를 생성
		select wt as teamid from wstat									-- wstat테이블에서 wt를 teamid로 설정						
		union															-- 위 결과 집합(teamid)과 아래 결과집합 연결
		select lt from lstat															
)
-- ↓ team테이블에서 team_id와 b.teamid가 같은 데이터의 team_name의 결과 집합을 teanname으로 설정 후 조회
select 	(select team_name from team where team_id = b.teamid) as teamname		
-- nvl함수 : 첫번째 인자값이 없다면? 두번째 인자값으로 대체
	 , 	nvl(w.cnt,0) as wins										-- wstat의 cnt값을 wins로 설정, 값이 없을 경우 0으로 대체	 			
     , 	nvl(l.cnt,0) as loses										-- lstat의 cnt값을 Loses로 설정, 값이 없을 경우 0으로 대체					
     -- ↓ 승리 횟수 ÷ (승리횟수 + 패배횟수) * 100의 결과를 win_ratio로 설정(소수점 첫째자리 반올림), win_ratio : 승률
     , 	round(nvl(w.cnt,0)/(nvl(w.cnt,0)+nvl(l.cnt,0))*100,0) as win_ratio															
from   	base b left outer join wstat w on b.teamid = w.wt			-- base(b)와 wstat(w)를 teamid와 wt 기준으로 외부 조인 										
              left outer join lstat l on b.teamid = l.lt			-- base(b)와 lstat(l)를 teamid와 lt 기준으로 외부 조인										
order by win_ratio desc            									-- win_ratio를 기준으로 내림차순 정렬						
;

