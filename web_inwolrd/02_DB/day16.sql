-- 11. 	경기일자, 홈팀이름, 원정팀 이름을 구하고 마지막 컬럼에
-- 		홈팀이 3점차 이상으로 이긴 경우 "대승리"
--		홈팀이 2점차 이상으로 이긴 경우 "승리"
-- 		비긴 경우 "무승부"
-- 		진 경우 "망신"
select 	sche_date																
		-- team 테이블의 team_id와 schedule의 hometeam_id가 같은 team_name을 hometeam으로 설정
	 ,	(select team_name from team where team_id = s.HOMETEAM_ID) as hometeam	
	 	-- team 테이블의 team_id와 schedule의 awayteam_id가 같은 team_name을 awayteam으로 설정
	 ,	(select team_name from team where team_id = s.AWAYTEAM_ID) as awayteam 	
	 ,	case when home_score > away_score + 2 then "대승리"						-- home_score가 away_score보다 3이상 크다면 ? 대승리
	 		 when home_score > away_score  then "승리"							-- home_score가 away_score보다 크지만 3미만이라면 ? 승리
	 		 when home_score = away_score  then "무승부"							-- home_score가 away_score과 같다면 ? 무승부
	 		 else "망신" end as res												-- home_score가 away_score보다 작다면 ? 망신, 결과 집합을 res로 설정
from 	schedule s
;



-- 12. 	11번의 결과를 바탕으로
-- 		대승리, 승리, 무승부, 망신이 각각 몇 경기인지 구하시오.
-- 		순서는 대승리 > 승리 > 무승부 > 망신 순으로 정렬하시오.
with new_stat as (
	select	sche_date																
	 	 ,	(select team_name from team where team_id = s.HOMETEAM_ID) as hometeam	
	 	 ,	(select team_name from team where team_id = s.AWAYTEAM_ID) as awayteam 	
	 	 ,	case when home_score > away_score + 2 then "1.대승리"						
	 		 	 when home_score > away_score  then "2.승리"							
	 		 	 when home_score = away_score  then "3.무승부"						
	 		 	 else "4.망신" end as res											
from 	schedule s
)
select	res,count(*)						-- new_stat테이블에서 res를 기준으로 그룹화를 하고 오름차순으로 정렬하여 
from 	new_stat							-- 해당 res가 몇 개인지 조회
group by res
order by res
;



-- 13. 	승강제로 승리는 2점, 무승부는 1점, 패배는 0점으로 했을 때
--		팀을 승점 순으로 나열하시오.
with new_stat as(											-- new_stat 임시테이블 생성, 각 팀의 승점을 계산하기 위한 테이블
	select  hometeam_id as team_id							-- schedule 테이블의 hometeam_id를 team_id라는 별칭으로 조회
		 ,	case when home_score > away_score then 2		-- home_score가 away_score보다 크다면 ? 2
		 		 when home_score = away_score then 1		-- home_score가 away_score과 같다면 ? 1
		    	 else 0 end as pt							-- home_score가 away_score보다 작다면 ? 0인 결과 집합을 pt로 설정
	from 	schedule										-- 
	union all												-- 위 코드와 아래 코드를 연결
	select 	awayteam_id as team_id							-- awayteam_id를 team_id라는 별칭으로 조회
		 ,	case when away_score > home_score then 2		-- away_score가 home_score보다 크다면 ? 2
		 		 when away_score = home_score then 1		-- away_score가 home_score과 같다면 ? 1
		 		 else 0 end as pt							-- away_score가 home_score보다 작다면 ? 0인 결과 집합을 pt로 설정
	from	schedule										-- 
)
select 	(select team_name									-- team 테이블에서 team_id가 new_stat테이블의 team_id와 같은 데이터의
		 from 	team										-- team_name을 team_name 이름으로 조회
		 where 	team_id = s.team_id) as team_name			-- 
	 ,	 sum(pt)											-- pt의 총 합(승점)
from 	new_stat s											-- 
group by team_name											-- team_name 기준으로 그룹화
order by sum(pt) desc										-- sum(pt) 기준으로 내림차순 정렬
;



-- 14. 	원정경기에서 2점차 이상으로 이긴 팀 중
-- 		홈경기에서 2점차 이상으로 진 적 없는 팀(not exists)
select 	distinct awayteam_id									-- 중복제거 후 awayteam_id 조회
from 	schedule s												-- schedule테이블에서, s로 설정
where 	away_score >= home_score + 2							-- 조건절 : away_score이 home_score보다 2이상일 때
-- not exists : 내부 쿼리 조건을 만족하는 행이 하나라도 있으면 false, 없으면 true
-- not exists가 내부 쿼리 조건을 만족하지 않는 경우(즉, 홈에서 2점 차 이상으로 진 적이 없는 경우)에만 해당 팀을 결과로 출력하는 방식
and 	not exists (select 	1									-- not exists : 특정 조건의 데이터가 존재하지 않는지 여부 파악
					from 	schedule 							-- schedule 테이블에서
					-- ↓ hometeam_id가 바깥 쿼리의 awayteam_id와 동일, not exists의 내부 쿼리에서의 hometeam_id가 외부쿼리의 awayteam_id임 
					where 	hometeam_id = s.awayteam_id			  
					and 	away_score >= home_score + 2		-- away_score가 home_score보다 2이상인 경우가 없는 경우 즉, 홈에서 2점차 이상으로 진 적이 있냐?
					)											-- not exists 쿼리 조건을 만족하는 데이터가 없어서, true이기 때문에 결과 출력됨
;


-- 15. 	13번에서 팀별로 승점을 구한것을 가지고,
-- 		컬럼 하나를 추가하여 윗 팀과의 승점 차이를 구하시오.
with new_stat as(											
	select  hometeam_id as team_id							
		 ,	case when home_score > away_score then 2		
		 		 when home_score = away_score then 1		
		    	 else 0 end as pt							
	from 	schedule										 
	union all												
	select 	awayteam_id as team_id							
		 ,	case when away_score > home_score then 2		
		 		 when away_score = home_score then 1		
		 		 else 0 end as pt							
	from	schedule										 
), res as (
	select (select 	team_name									
		 	from 	team										
		 	where 	team_id = s.team_id) as team_name			 
	 	 ,	sum(pt) pt											
from 	new_stat s											
group by team_id											
order by sum(pt) desc										
)
select 	team_name														-- res 테이블에서 team_name 출력
	 ,	pt as "승점"														-- pt를 "승점"으로 출력
-- 	 over :  집계 함수나 순위 함수를 사용할 때, 해당 함수가 데이터를 어떻게 분석할지 범위나 정렬 순서를 정의
-- 	 partition by는 데이터를 그룹화하는 역할
-- 	 partition null : 그룹화를 하지 않고, 즉 모든 행을 하나의 그룹으로 간주
-- 					  모든 데이터가 동일한 그룹에 속하게 되어, 그 그룹 내에서 계산을 수행
	 ,	lead(pt) over(partition by null									-- lead : 다음 행의 값을 참조
	 				  order by pt desc) as "아래 순위의 승점"				-- 아래 행의 pt값을 "아래 순위의 승점"으로 출력
	 , 	lag(pt) over(partition by null									-- lag : 이전 행의 값을 참조
	 				  order by pt desc) as "윗 순위의 승점"				-- 윗 행의 pt값을 "윗 순위의 승점"으로 출력
	 , 	lag(pt) over(partition by null									  
	 				  order by pt desc)- pt as "윗 순위와의 승점차"			-- 윗 행의 pt값 - 현재 행의 pt 값을 "윗 순위와의 승점차"로 출력
from res	 				  											
;



-- 만약 팀별로 경기마다 경기가 오래된 순서대로 1부터 최종까지 매긴다면?
-- row_number : 동률일때도 숫자가 달리 매겨진다.
-- rank : 동률이면 같은 숫자가 매겨지며, 그 뒤 순위는 정상적으로
--        1등이 공동이면 그 뒤는 3등
-- dense_rank : 동률이면 같은 숫자가 매겨지며, 그 뒤 순위는 바로 이어서
--        1등이 공동이면 그 뒤는 2등
select 	s.*												-- schedule테이블의 모든 데이터 조회
	 ,	rank() over(partition by hometeam_id			-- hometeam_id끼리 그룹화를 한 다음 rank()함수를 사용하여, 
	 				order by sche_date desc) as rn		-- sche_date를 기준으로 순위 책정, sche_date 기준으로 내림차순 정렬을 했기 때문에
from 	schedule s 										-- hometeam_id별로 최신경기부터 1로 시작 
;



-- 16. 팀별로 최근 홈 3경기만 가져오시오.
-- row_number : 동일한 값이 있더라도 각 행에 고유의 순위가 매겨짐
--				즉, 순위가 겹치지 않음
with new_schedule as (															-- new_schedule 임시테이블 생성
		select 	s.*																-- schedule테이블의 모든 데이터 조회
			 ,	row_number() over(partition by hometeam_id 						-- hometeam_id끼리 그룹화를 하여, row_number()함수를 활용하여,
			 					  order by sche_date desc) as rn				-- 각 hometeam_id의 순위를 책정
		from	schedule s														-- sche_date를 기준으로 내림 정렬을 함으로써 최신경기부터 1로 시작
)
select	(select team_name from team where team_id = hometeam_id) as "팀명"		-- team 테이블의 team_id와 hometeam_id가 같은 team_name을 "팀명"으로 조회
	 ,	sche_date																-- new_schedule 테이블의 sche_date, rn 컬럼 조회		
	 ,	rn																			
from	new_schedule																				
where 	rn <= 3																	-- rn이 3이하인것만 조회		
;



-- 17. 2012년 7월 일정을 홈팀 기준으로 가로로 표기하시오.
-- ex)  K02   20120701   20120713   20120731
--      K03   20120702   20120720   (null)
select 	hometeam_id, count(*)
from 	schedule 
where 	sche_date like '201207%'
group by hometeam_id
;

-- 홈팀 ID와 경기 날짜를 조회하며, 경기별로 최신 순서대로 순위를 매기기 위해 new_schedule 임시 테이블을 생성
with new_schedule as (													
		select 	hometeam_id																						
			 ,	sche_date												
			 ,	row_number() over(partition by hometeam_id				-- hometeam_id를 그룹화 한 다음 row_number()를 통해 각 순위를 책정
			 					  order by sche_date desc) as rn		-- sche_date 기준으로 내림차순 정렬(최신순부터),  결과집합 = rn으로 설정
	 	from 	schedule												-- 
	 	where 	sche_date like '201207%'								-- sche_date 값이 '201207'이 들어가는 데이터만 조회
-- new_schedule의 순번에 따라 경기 일자를 g1, g2, g3 등의 칼럼에 각각 저장하기 위해 new_schedule2 임시 테이블 생성
), 	new_schedule2 as (													
		select 	hometeam_id												
			 ,	case when rn = 1 then sche_date else null end as g1		-- rn이 1이면 sche_date 1이 아니면 null, g1으로 설정
			 ,	case when rn = 2 then sche_date else null end as g2		-- rn이 2이면 sche_date 2이 아니면 null, g2으로 설정
			 ,	case when rn = 3 then sche_date else null end as g3		-- rn이 3이면 sche_date 3이 아니면 null, g3으로 설정
			 ,	case when rn = 4 then sche_date else null end as g4		-- rn이 4이면 sche_date 4이 아니면 null, g4으로 설정
			 ,	case when rn = 5 then sche_date else null end as g5		-- rn이 5이면 sche_date 5이 아니면 null, g5으로 설정
		from 	new_schedule
)
-- 최종 결과를 한 행에 보여주기 위해 hometeam_id를 기준으로 그룹화하여 각 g1, g2, g3, g4, g5의 최대값을 선택
select	hometeam_id														
-- max() : 각 hometeam_id별로 가장 최근 경기 날짜를 한 행에 표시하기 위함
	 ,	max(g1) as g1													-- 	g1에 저장된 경기 일자의 최대값(최신 경기 일자)을 g1으로 조회
	 ,	max(g2) as g2													-- 	g2에 저장된 경기 일자의 최대값을 g2으로 조회
	 ,	max(g3) as g3													-- 	g3에 저장된 경기 일자의 최대값을 g3으로 조회
	 ,	max(g4) as g4													-- 	g4에 저장된 경기 일자의 최대값을 g4으로 조회
	 ,	max(g5) as g5													-- 	g5에 저장된 경기 일자의 최대값을 g5으로 조회
from	new_schedule2														
group by hometeam_id													-- hometeam_id별로 그룹화	
;