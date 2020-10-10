-- 2020-10-07 SQL Paging Upgrade

-- 조회 관련 태스트
select f1.id, f1.name, f1.price, f2.name as storename from foodmenu_tbl f1, foodstore_tbl f2 where f1.store_id = f2.id
select count(*) from foodmenu_tbl f1, foodstore_tbl f2 where f1.store_id = f2.id

-- Oracle 11 - 자동번호 생성 테이블 정의
-- Table 생성 (FOODMENU_TBL)
-- NEW.ID (Table의 id를 가리킴)
CREATE TABLE foodmenu_tbl
(
    id NUMBER PRIMARY KEY,
    name VARCHAR2(30),
    price NUMBER,
    store_id NUMBER,
    cnt NUMBER,
    regidate DATE
);

-- Sequence 정의
CREATE SEQUENCE foodmenu_sequence
START WITH 1
INCREMENT BY 1;

-- Trigger 생성
-- BEFORE INSERT on '테이블명'
CREATE OR REPLACE TRIGGER foodmenu_trigger
BEFORE INSERT
    ON foodmenu_tbl
REFERENCING NEW AS NEW
FOR EACH ROW
BEGIN
SELECT foodmenu_sequence.nextval INTO :NEW.ID FROM dual;
END;


-- Table 생성 (FOODSTORE_TBL)
CREATE TABLE foodstore_tbl
(
    id NUMBER PRIMARY KEY,
    name VARCHAR2(30),
    address VARCHAR2(30),
    regidate DATE
);


-- Sequence 정의
CREATE SEQUENCE foodstore_sequence
START WITH 1
INCREMENT BY 1;

-- Trigger 생성
-- BEFORE INSERT on '테이블명'
CREATE OR REPLACE TRIGGER foodstore_trigger
BEFORE INSERT
    ON foodstore_tbl
REFERENCING NEW AS NEW
FOR EACH ROW
BEGIN
SELECT foodstore_sequence.nextval INTO :NEW.ID FROM dual;
END;


-- Sequence 정의
CREATE SEQUENCE foodstore_sequence
START WITH 1
INCREMENT BY 1;

-- Sequence 삭제	//
drop sequence foodname_sequence;

-- Trigger 생성
-- BEFORE INSERT on '테이블명'
-- NEW.ID (Table의 id를 가리킴)
CREATE OR REPLACE TRIGGER foodstore_trigger
BEFORE INSERT
    ON foodstore_tbl
REFERENCING NEW AS NEW
FOR EACH ROW
BEGIN
SELECT foodstore_sequence.nextval INTO :NEW.ID FROM dual;
END;

-- 뷰 생성하기
create or replace view foodmenu_view as 
select f1.id, f1.name, f1.price, f2.name as storename from foodmenu_tbl f1, foodstore_tbl f2 where f1.store_id = f2.id order by f1.id desc;

-- 페이징 SQL(뷰 방식)
SELECT * FROM ( 
SELECT /*+ INDEX_DESC(Z OP_SAMPLE_PK) */ ROWNUM AS RNUM, Z.* FROM ( 
SELECT * FROM FOODMENU_VIEW
) Z WHERE ROWNUM <= 20
) WHERE RNUM >= 11;

-- 페이징 SQL(뷰 원본으로 작성)
SELECT * FROM ( 
SELECT /*+ INDEX_DESC(Z OP_SAMPLE_PK) */ ROWNUM AS RNUM, Z.* FROM ( 
SELECT f1.id, f1.name, f1.price, f2.name as storename from foodmenu_tbl f1, foodstore_tbl f2 where f1.store_id = f2.id order by f1.id desc 
) Z WHERE ROWNUM <= 10
) WHERE RNUM >= 1;

-- 특정 쿼리 페이징 SQL(뷰 원본으로 작성)
SELECT * FROM ( 
SELECT /*+ INDEX_DESC(Z OP_SAMPLE_PK) */ ROWNUM AS RNUM, Z.* FROM ( 
SELECT f1.id, f1.name, f1.price, f2.name as storename from foodmenu_tbl f1, foodstore_tbl f2 where f1.store_id = f2.id and f1.name like '%무봉리%' order by f1.id desc 
) Z WHERE ROWNUM <= 10
) WHERE RNUM >= 1

-- 싱글 쿼리 (페이징)
SELECT * FROM ( 
SELECT /*+ INDEX_DESC(Z OP_SAMPLE_PK) */ ROWNUM AS RNUM, Z.* FROM ( 
SELECT * from foodmenu_tbl order by id desc 
) Z WHERE ROWNUM <= 10
) WHERE RNUM >= 1

-- 특정 싱글 쿼리 SQL
SELECT * FROM ( 
SELECT /*+ INDEX_DESC(Z OP_SAMPLE_PK) */ ROWNUM AS RNUM, Z.* FROM ( 
SELECT * from foodmenu_tbl where name like '%야해해%' order by id desc 
) Z WHERE ROWNUM <= 10
) WHERE RNUM >= 1


-- Sample Insert 문
-- 태스트 코드(foodmenu_tbl)
insert into foodmenu_tbl(name, price, store_id, cnt, regidate) values('사슴1', 1500, 1, 0, '2020-01-01');
insert into foodmenu_tbl(name, price, store_id, cnt, regidate) values('사슴2', 1400, 1, 0, '2020-01-01');
insert into foodmenu_tbl(name, price, store_id, cnt, regidate) values('사슴3', 1300, 1, 0, '2020-01-01');
insert into foodmenu_tbl(name, price, store_id, cnt, regidate) values('사슴4', 1100, 1, 0, '2020-01-01');
insert into foodmenu_tbl(name, price, store_id, cnt, regidate) values('사슴5', 1200, 1, 0, '2020-01-01');
insert into foodmenu_tbl(name, price, store_id, cnt, regidate) values('사슴6', 1300, 1, 0, '2020-01-01');
insert into foodmenu_tbl(name, price, store_id, cnt, regidate) values('사슴7', 1500, 1, 0, '2020-01-01');

-- 상점(foodstore_tbl)
-- 태스트 코드
insert into foodstore_tbl(name, address, regidate) values('치즈집1', '천사1', '2020-01-03');
insert into foodstore_tbl(name, address, regidate) values('치즈집2', '천사2', '2020-01-03');
insert into foodstore_tbl(name, address, regidate) values('치즈집3', '천사3', '2020-01-03');
insert into foodstore_tbl(name, address, regidate) values('치즈집4', '천사4', '2020-01-03');
insert into foodstore_tbl(name, address, regidate) values('치즈집5', '천사5', '2020-01-03');
insert into foodstore_tbl(name, address, regidate) values('치즈집6', '천사6', '2020-01-03');

-- 삭제 관련 Delete Query
delete from foodmenu_tbl; 
select * from foodmenu_tbl where name = '야해해';


-- Transaction 실습 DB (은행 - Account)
-- Oracle 11 - 자동번호 생성 테이블 정의
-- Table 생성 (FOODMENU_TBL)
-- NEW.ID (Table의 id를 가리킴)
CREATE TABLE account_tbl
(
    idx NUMBER PRIMARY KEY,
    name VARCHAR2(30),
    balance NUMBER,
    regidate TIMESTAMP
);

-- Sequence 정의
CREATE SEQUENCE account_sequence
START WITH 1
INCREMENT BY 1;

-- Trigger 생성
-- BEFORE INSERT on '테이블명'
CREATE OR REPLACE TRIGGER account_trigger
BEFORE INSERT
    ON account_tbl
REFERENCING NEW AS NEW
FOR EACH ROW
BEGIN
SELECT account_sequence.nextval INTO :NEW.IDX FROM dual;
END;