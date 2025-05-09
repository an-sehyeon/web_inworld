-- lpad, rpad
-- movie_n 테이블의 개봉일을 10자리로 하고 앞에 *을 붙이시오.
-- rating이 3자리 미만이면 3자리로 맞추시오.

select 	open_date
	 ,	lpad(open_date, 10, '*') as od			-- open_date 컬럼 데이터값을 전부 10자리로 맞추고 빈공간은 왼쪽부터 *을 붙임
	 ,  lpad(rating, 3, 0) as rt				-- rating 컬럼 데이터값을 전부 3자리로 맞추고 빈공간은 왼쪽부터 0을 붙임
from 	movie_n
;

with cust as(												-- with절을 활용하여 임시 테이블 cust 생성
	 select '390912-1337812' as jn							-- '390912-1337812'값을 ju컬럼에 설정	
	 from dual
)
select	jn													-- (concat : 문자열을 붙이는 기술)을 활용해 substr 활용한 문자열과 ******을 붙임		
	 ,	concat(substr(jn, 1,7),'*******') as sec_jn1		-- ju의 첫번째부터 7번째까지 출력하고 ********을 붙인 후 sec_jn1 컬럼에 설정
	 ,	rpad(substr(jn, 1,7),14, '*') as sec_jn2			-- ju의 첫번째부터 7번째까지 출력하고 오른쪽으로 총 14자리까지 '*'로 채운 후 sec_jn2 컬럼에 설정
from 	cust
;


-- 일반의, 인턴, 레지던트 수를 제출하되 각 데이터를 4자리를 구분자는 ;으로 할 것
select 	hptl_nm 
-- 	 ,	doc_gnrl_num
-- 	 ,	doc_int_num
-- 	 ,	doc_resi_num
	 ,	concat(lpad(doc_gnrl_num, 4, 0), ';'				-- doc_gnrl_num에 오른쪽끝에서 4개를 가져오는데 값이 4자리보다 짧은 경우 왼쪽에 0을 추가하여 4자리로 맞춤, 뒤에 ';'을 붙임
	 ,	lpad(doc_int_num, 4, 0), ';'
	 ,	lpad(doc_resi_num, 4, 0)) as data					-- 위 결과 집합을 data 컬럼이라고 설정
from	hptl_mast
;


-- replace : 특정 문자열을 다른 문자열로 교체
select 	"도더리가 -그건 네 자유지-라고 말했다." as original		-- original 컬럼 값을 "도더리가 -그건 네 자유지-라고 말했다."이라고 설정
	 , 	replace("도더리가 -그건 네 자유지-라고 말했다.",			-- replace를 활용하여 "그건 네 자유지" 부분을 "That is your freezone"로 교체
	 			"그건 네 자유지", "That is your freezone")
		as eng_ver											-- 다른 문자열로 교체한 결과를 eng_ver으로 설정
from dual
;



-- 실습과제2
-- 병원 이름에 병원 > Hospital, 대학교 > Univ.로 대체하시오.
select replace(replace (hptl_nm, '병원', 'Hospital'),		-- hptl_nm의 값에 '병원'이라는 문자를 'Hospital'로 교체하고,
			   '대학교', 'Univ.') as result					-- '대학교'라는 문자를 'Univ.'으로 교체 후 결과 집합을 result로 설정
from hptl_mast
;



-- 실습과제3
-- 첫 줄에는 서울에 있는 총 병원수
-- 두 번째 줄에는 서울에 있는 병원의 평균 의사 수
-- 세 번째 줄부터는 서울에 있는 병원 이름, 의사 수
select "서울병원수" as "항목", count(*) as "데이터"					
from 	hptl_mast
where 	sido_cd_nm = '서울'									-- sido_cd_nm이 서울인 데이터의 수를 출력
union all 													-- 위의 집합과 아래 집합을 연결하는 구문
select "서울병원평균의사수", avg(doc_num) as "데이터"
from 	hptl_mast
where 	sido_cd_nm = '서울'									-- sido_cd_nm이 서울인 데이터의 평균 의사 수 출력
union all 
select "-----------------------", "---------------------"
from dual
union all
select 	hptl_nm, doc_num									-- sido_cd_nm이 서울인 데이터의 hptl_nm과 doc_num 컬럼 값 출력
from 	hptl_mast 
where 	sido_cd_nm = '서울'
;


-- 실습과제4
-- sido_cd_nm별 병원수를 구하고, 병원 수가 많은 시도부터 나열하시오.
select 	 sido_cd_nm
	 , 	 count(*) as '지역별 병원수'
from 	 hptl_mast 											-- hptl_mast테이블에서 sido_cd_nm컬럼의 값들 중 같은 데이터들끼리 그룹화를 한 후 
group by sido_cd_nm 										-- 해당 그룹의 데이터 수를 내림차순으로 정렬		
order by count(*) desc
