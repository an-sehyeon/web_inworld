select 	*
from 	stadium			-- stadium 테이블 조회
;

select 	*
from 	schedule		-- schedule 테이블 조회
;

select 	*
from 	team 			-- team 테이블 조회
;


-- 1. schedule 테이블에서 3줄의 데이터만 한 번 살펴보시오.
select 	*
from 	schedule 
limit	3
;


-- 2. schedule 테이블의 총 건수를 구하시오.
select 	count(*)
from 	schedule 
;


-- 3. 2012년 7월의 schedule은 몇 개인지 구하시오.
select	count(*)
from 	schedule
where 	SCHE_DATE like '201207%'
;


-- 4. 각 팀별로 schedule 건수를 구하시오. (홈팀기준)
select 	HOMETEAM_ID, count(*)
from 	schedule  
group by HOMETEAM_ID 
;


-- 5. 원정 팀이 승리한 경기의 개수를 구하시오.
select 	count(*)
from 	schedule 
where 	AWAY_SCORE > HOME_SCORE 
;


-- 6. 원정 팀이 이긴 경기의 경기일자, 승리팀과 패배팀을 구하시오.
--    승리팀과 패배팀은 코드로 표시
select 	sche_date as "경기일자", awayteam_id as "승리팀", hometeam_id as "패배팀"
from 	schedule 
where   AWAY_SCORE > HOME_SCORE 
;


-- 7. 원정 팀이 이긴 경기의 경기일자, 승리팀과 패배팀을 구하시오.
--    승리팀과 패배팀은 팀명으로 표시
select 	sche_date 
	 ,	(select team_name from team							-- team_id와 s의 awayteam_id가 동일한 데이터를 "승리팀"으로 설정,
	 	 where 	team_id = s.AWAYTEAM_ID) as "승리팀"        	-- team 테이블의 team_name 데이터를 가져옴
	 ,	(select team_name from team							-- team id와 sdml hometeam_id가 동일한 데이터를 "패배팀"으로 설정,
	 	 where 	team_id = s.HOMETEAM_ID) as "패배팀"			-- team 테이블의 team_name 데이터를 가져옴
from 	schedule s											-- shedule 테이블의 별칭을 s로 설정	
where   AWAY_SCORE > HOME_SCORE 							-- 원정 팀이 이긴 경기만 조회
;

-- 8. 전체 경기 중 홈팀이 승리한 경기의 비율, 무승부인 비율, 
--    원정팀 승리 비율을 구하시오.
with tot_game as (													-- tot_game 임시테이블을 생성 후
	 select count(*) as tot											-- 비율을 계산하기 위해 전체 경기수를 조회 후 tot으로 설정
	 from	schedule
				 )
select 	
	(select	count(*)										
	 from 	schedule												-- 홈팀이 승리한 경기의 비율 계산	
	 where 	home_score > away_score) / tot * 100 as hw_ratio		-- (홈팀이 승리한 경기 수 / tot) * 100, 결과값을 hw_ratio로 설정
   ,(select	count(*)
	 from 	schedule												-- 무승부 경기의 비율 계산										
	 where 	home_score = away_score) / tot * 100 as dr_ratio		-- (무승부 경기 수 / tot) * 100, 결과값을 dr_ratio로 설정
   ,(select	count(*)
	 from 	schedule												-- 원정팀이 승리한 경기의 비율 계산									
	 where 	home_score < away_score) / tot * 100 as aw_ratio		-- (원정팀이 승리한 경기 수 / tot) * 100, 결과값을 aw_ratio로 설정
from	tot_game
;


-- 9. 가장 경기를 많이 한 팀을 구하고 그 팀의 평균 득점을 구하시오.	
with schedule_new as(												-- schedule_new 임시테이블 생성
	select 	hometeam_id as team_id									-- schedule테이블의 hometeam_id를 team_id로 설정
		 ,	home_score as score from schedule						-- schedule테이블의 home_score를 score로 설정		
	union all														-- 위의 결과 집합과 아래 결과 집합을 연결
	select awayteam_id, away_score from schedule), 					-- schedule테이블의 awayteam_id, away_score 조회	
schedule_t as (														-- schedule_t 임시테이블 생성
	select team_id, avg(score), count(*) as cnt						-- schedule_new테이블에서 team_id, score의 평균값,
	from schedule_new												-- team_id를 그룹화 한 개수	를 cnt로 설정
	group by team_id												
	order by cnt desc												-- cnt를 내림차순
			  )
select 	*															
from 	schedule_t													-- schedule_t 테이블에서 cnt의 최댓값에 해당하는 데이터(행)만 출력
where 	cnt = (select max(cnt) from schedule_t)								
;