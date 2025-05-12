SELECT * FROM joinentity;
SELECT * FROM tbl_board;
SELECT * FROM psychology_test_result;





CREATE TABLE tbl_board (
	bno BIGINT AUTO_INCREMENT PRIMARY KEY,		-- 게시글 번호 (PK)
	title VARCHAR(200) NOT NULL,					-- 제목
	content TEXT NOT NULL,							-- 내용
	writer VARCHAR(100) NOT NULL,					-- 작성자
	regdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,	-- 작성일 (기본값 : 현재시간)
	updatedate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일 (기본값 : 현재시간, 수정 시 업데이트)
);


CREATE TABLE joinentity (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    birth_date VARCHAR(255),
    phone VARCHAR(255),
    address VARCHAR(255),
    affiliation VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    target VARCHAR(255),
    role VARCHAR(255) NOT NULL
);

CREATE TABLE psychology_test_result (
	id INT AUTO_INCREMENT PRIMARY KEY, 		-- 기본키 자동증가
	user_id INT NOT NULL,						-- 사용자 ID (joinentity 테이블과 연결)
	test_name VARCHAR(50) NOT NULL,			-- 질문지 이름
	test_result JSON NOT NULL,					-- 사용자가 선택한 질문지 값(JSON 형태로 저장)
	total_score INT NOT NULL,					-- 총점수 (계산된 결과)
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,			-- 저장된 날짜 및 시간
	FOREIGN KEY (user_id) REFERENCES joinentity(id) ON DELETE CASCADE		-- 회원 정보 테이블과 FK 설정
);

UPDATE joinentity
SET target = '관리자'
WHERE ROLE = 'ROLE_ADMIN'
;